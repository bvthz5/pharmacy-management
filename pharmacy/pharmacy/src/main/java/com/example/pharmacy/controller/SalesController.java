package com.example.pharmacy.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.service.SalesService;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;
import com.opencsv.CSVWriter;
// import com.example.pharmacy.service.SalesService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SalesService salesService;


    @GetMapping
    public Collection<SalesListView> list(Principal p){
        return salesService.list();
        
    }
    
    @GetMapping("/{pageNo}/{pageSize}/{sortBy}")
	public Collection<SalesListView> getPaginated(@PathVariable Integer pageNo,@PathVariable Integer pageSize,@PathVariable String sortBy)
    {
		return salesService.findPaginated(pageNo, pageSize,sortBy);
	}

   
    @GetMapping("/filter/{days}")
    public Collection<SalesListView> findAllByDateBetween(@PathVariable Integer days){
        System.out.println(days);
        return salesService.findAllByDateBetween(days);
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
        String[] csvHeader = {"Sales ID", "Medicine ID", "Sales Quantity", "Total Amount", "Sales Date"};
        String[] nameMapping = {"salesId", "medicineId", "salesQuantity", "totalAmount", "salesDate"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (SalesListView sales : listSales) {
            csvWriter.write(sales, nameMapping);
        }
         
        csvWriter.close();
         
    }

    // @GetMapping("/export")
    // public void exportCSV(HttpServletResponse response) throws Exception {

    //     //set file name and content type
    //     String filename = "sales.csv";

    //     response.setContentType("text/csv");
    //     response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
    //             "attachment; filename=\"" + filename + "\"");

    //     //create a csv writer
    //     StatefulBeanToCsv<Sales> writer = new StatefulBeanToCsvBuilder<Sales>(response.getWriter())
    //             .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
    //             .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
    //             .withOrderedResults(false)
    //             .build();

    //     //write all users to csv file
    //     writer.write(salesService.list());
                
    // }

    
    @PostMapping("/add")
    public SalesDetailView add(@Valid @RequestBody SalesForm form)
    {
        return salesService.add(form);
    }

    @GetMapping("/{salesId}")
    public SalesDetailView get(@PathVariable("salesId")Integer salesId) throws NotFoundException
    {
        return salesService.get(salesId);
    }    
    

    // @GetMapping("/")
    // public String viewHomePage(Model model) {
    //     return findPaginated(1, "firstName", "asc", model);
    // }

    // @GetMapping("/page/{pageNo}")
    // public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
    //     @RequestParam("sortField") String sortField,
    //     @RequestParam("sortDir") String sortDir,
    //     Model model) {
    //     int pageSize = 5;

    //     Page < Sales > page = salesService.findPaginated(pageNo, pageSize, sortField, sortDir);
    //     List < Sales > listEmployees = page.getContent();

    //     model.addAttribute("currentPage", pageNo);
    //     model.addAttribute("totalPages", page.getTotalPages());
    //     model.addAttribute("totalItems", page.getTotalElements());

    //     model.addAttribute("sortField", sortField);
    //     model.addAttribute("sortDir", sortDir);
    //     model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

    //     model.addAttribute("listEmployees", listEmployees);
    //     return "index";
    // }
    
}
