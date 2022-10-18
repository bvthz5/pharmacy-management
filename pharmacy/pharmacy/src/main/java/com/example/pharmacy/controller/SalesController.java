package com.example.pharmacy.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.service.SalesService;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;

// import com.example.pharmacy.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SalesService salesService;

    @GetMapping
    public Collection<SalesListView> list(Principal p) {
        return salesService.list();

    }

    @PostMapping
    public Collection<SalesListView> add(@Valid @RequestBody Collection<SalesForm> form) {
        return salesService.add(form);
    }

    @GetMapping("/{salesId}")
    public SalesDetailView get(@PathVariable("salesId") Integer salesId) throws NotFoundException {
        return salesService.get(salesId);
    }


    // @GetMapping("/search/{keyword}")
    // public String home(Sales sales, Model model,@PathVariable String keyword) {
    //  if(keyword!=null) 
    //  {
    //   Collection<Sales> list = salesService.getByKeyword(keyword);
    //   model.addAttribute("list", list);
    //  }
    //  else {
    //  Collection<Sales> list = salesService.getAllShops();
    //  model.addAttribute("list", list);
    // }
    //  return "index";
    // }

    
    // @Param("keyword")
    // @GetMapping("/search/{keyword}")
    // public Collection<Sales> viewHomePage(Model model, @PathVariable String keyword) {
    //     Collection<Sales> listProducts = salesService.listAll(keyword);
    //     model.addAttribute("listProducts", listProducts);
    //     model.addAttribute("keyword", keyword);
         
    //     return salesService.listAll(keyword);
    // }

    @GetMapping("/search")
    public List<SalesListView> list(
        @RequestParam(value = "search", required = false) String search,
        @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
        @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {
            return salesService.list(search,pageNo,pageSize);
    }

    // @GetMapping("/{pageNo}/{pageSize}/{sortDir}/{sortBy}")
    // public Collection<SalesListView> getPaginated(@PathVariable Integer pageNo, @PathVariable Integer pageSize,@PathVariable String sortDir,
    //         @PathVariable String sortBy) {
    //     return salesService.findPaginated(pageNo, pageSize, sortDir,sortBy);
    // }
    @GetMapping("/page")
    public Collection<SalesListView> getPaginated(
    @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
    @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
    @RequestParam(value = "sortDir", required = false, defaultValue = "ASC") String sortDir,
    @RequestParam(value = "sortBy", required = false, defaultValue = "salesDate") String sortBy) {
        
        return salesService.findPaginated(pageNo, pageSize, sortDir,sortBy);
    }

    
    @GetMapping("/filter/{date}")
    public Collection<SalesListView> findAllByDateBetween(@PathVariable Date date) {
        System.out.println(date);
        return salesService.findAllByDateBetween(date);
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sales_history" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        Collection<SalesListView> listSales = salesService.list();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "Sales ID", "Medicine ID", "Sales Quantity", "Total Amount", "Sales Date" };
        String[] nameMapping = { "salesId", "medicineId", "salesQuantity", "totalAmount", "salesDate" };

        csvWriter.writeHeader(csvHeader);

        for (SalesListView sales : listSales) {
            csvWriter.write(sales, nameMapping);
        }

        csvWriter.close();

    }

    
}
