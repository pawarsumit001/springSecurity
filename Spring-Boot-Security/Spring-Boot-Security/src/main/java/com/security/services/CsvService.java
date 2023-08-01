package com.security.services;

import com.security.models.User;
import com.security.repository.SecureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.io.PrintWriter;
import java.util.List;

@Service
public class CsvService {
    @Autowired
    private SecureRepo secureRepo;

    public void downloadCsvfile(PrintWriter printWriter) {
        printWriter.write("id"+" "+"username"+" "+"email"+" "+"password \n");


        List<User> users = secureRepo.findAll();

        for (User user : users) {
            printWriter.write(user.getId()
                    +  "," + user.getUsername()
                    +  "," + user.getEmail()
                    + "," + user.getPassword()+"\n");

        }

    }


}
