package com.example.pharmacy.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Pager;
import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.repository.SalesRepository;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.service.SalesService;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    


    @Override
    public Collection<SalesListView> list() {

        return salesRepository.findByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Sales.Status.ACTIVE.value)
                .stream().map(x -> new SalesListView(x)).collect(Collectors.toList());

        
    }

    @Override
    public Collection<SalesListView> add(Collection<SalesForm> form) {
        // System.out.println("jkgsdjgjksdb->"+form.getSalesQuantity());
        Iterator<SalesForm> itr = form.iterator();
        System.out.println(itr.toString());
        try {
            while (itr.hasNext()) {
                SalesForm salesForm = itr.next();
                System.out.println("Quantity=====>" + salesForm.getSalesQuantity());
                System.out.println("Companay ID==>" + salesForm.getCompanyId());

                // System.out.println("Inside try===>"+itr.next().getSalesQuantity().toString()
                // );

                Integer x = medicineRepository.editStock(salesForm.getSalesQuantity(), salesForm.getMedicineId());

                System.out.println("============" + x);
                if (x == 1) {
                    new SalesDetailView(
                            salesRepository.save(new Sales(salesForm, SecurityUtil.getCurrentUserId())));
                } else {
                    throw new ArithmeticException("----------errrrrrr--------");
                }
            }

        } catch (Exception e) {
            System.out.println("=====================");
            throw new ArithmeticException();

        }
        return salesRepository.findByStatus(Sales.Status.ACTIVE.value)
                .stream().map(x -> new SalesListView(x)).collect(Collectors.toList());

    }

    @Override
    public SalesDetailView get(Integer salesId) throws NotFoundException {
        return salesRepository
                .findBySalesIdAndUserUserIdAndStatus(salesId, SecurityUtil.getCurrentUserId(),
                        Sales.Status.ACTIVE.value)
                .map((sales) -> {
                    return new SalesDetailView(sales);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public Collection<SalesListView> findPaginated(Integer pageNo, Integer pageSize,String sortDir, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize,Sort.Direction.fromString(sortDir), sortBy);
        // Sort.by(sortBy).ascending()
        // Page<SalesListView> pagedResult = salesRepository.findAll(paging);
        // return pagedResult.toList();
        List<SalesListView> pagedResult = salesRepository.findAll(paging).stream().map((data)-> new SalesListView(data)).collect(Collectors.toList());
        return pagedResult;
    }

    @Override
    public Pager<SalesListView> findPager(Integer pageSize, Integer numItems, Integer page, Boolean type, String sort) {
        
        Pager<SalesListView> newsPager = new Pager<>(0, 0, 0);
        Long queryCount;
        List<SalesListView> salesList;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        queryCount = salesRepository.countList(currentDate);

        salesList = StreamSupport
                .stream(salesRepository.getsalesList(currentDate, PageRequest.of(page - 1, numItems, (type == true) ? Sort.Direction.DESC : Sort.Direction.ASC,
                sort))
                .spliterator(), false)
                .map(data -> new SalesListView(data)).collect(Collectors.toList());
            newsPager = new Pager(numItems, queryCount.intValue(), page);
            newsPager.setResult(salesList);
        return newsPager;

}

    @Override
    public Collection<SalesListView> findAllByDateBetween(Date date) 
    {
        // Calendar calendar = Calendar.getInstance();
        // Date today = calendar.getTime();

        // calendar.add(Calendar.DAY_OF_MONTH, -days);
        // Date beforeDays = calendar.getTime();
        return salesRepository.findAllBysalesDate(date);

    }

    @Override
    public List<SalesListView> list(String search,Integer pageNo,Integer pageSize) {
        System.out.println("search is"+ search);
        Pageable paging = PageRequest.of(pageNo,pageSize);
        return StreamSupport.stream(salesRepository
                .searchKey(search,paging).spliterator(), false)
                .map(item -> new SalesListView(item))
                .collect(Collectors.toList());
    }

}



    



