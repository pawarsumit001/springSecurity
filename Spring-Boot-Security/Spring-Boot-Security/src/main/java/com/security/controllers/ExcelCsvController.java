package com.security.controllers;

import com.security.models.User;
import com.security.repository.SecureRepo;
import com.security.services.CsvService;
import com.security.services.UserExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ExcelCsvController {
    @Autowired
    private SecureRepo secureRepo;
    @Autowired
    private CsvService csvService;

    @GetMapping("/in")
    public String index() {
        return "index.html";
    }

    @GetMapping("/download/user.csv")
    public void downloadCsvFile(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        response.setHeader("content-Disposition", "attachment;file-user.csv");
        csvService.downloadCsvfile(response.getWriter());

    }


    @GetMapping("/download/user.xlsx")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> list = secureRepo.findAll();

        UserExcelService excelExporter = new UserExcelService(list);

        excelExporter.export(response);
    }

}

