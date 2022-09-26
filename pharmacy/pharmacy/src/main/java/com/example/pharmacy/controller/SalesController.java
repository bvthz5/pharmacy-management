package com.example.pharmacy.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
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

    @GetMapping("/{pageNo}/{pageSize}/{sortBy}")
    public Collection<SalesListView> getPaginated(@PathVariable Integer pageNo, @PathVariable Integer pageSize,
            @PathVariable String sortBy) {
        return salesService.findPaginated(pageNo, pageSize, sortBy);
    }

    @GetMapping("/filter/{days}")
    public Collection<SalesListView> findAllByDateBetween(@PathVariable Integer days) {
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
        String[] csvHeader = { "Sales ID", "Medicine ID", "Sales Quantity", "Total Amount", "Sales Date" };
        String[] nameMapping = { "salesId", "medicineId", "salesQuantity", "totalAmount", "salesDate" };

        csvWriter.writeHeader(csvHeader);

        for (SalesListView sales : listSales) {
            csvWriter.write(sales, nameMapping);
        }

        csvWriter.close();

    }

}
