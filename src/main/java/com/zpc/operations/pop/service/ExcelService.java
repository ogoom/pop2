package com.zpc.operations.pop.service;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import com.zpc.operations.pop.helper.ExcelHelper;
import com.zpc.operations.pop.repository.OutwardItemsDetailsReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelService {
    @Autowired
    OutwardItemsDetailsReportRepository repository;

    public List<OutwardItemsDetailsReport> save(MultipartFile file) {
        try {
            System.out.println("file service......");
            List<OutwardItemsDetailsReport> reports = ExcelHelper.excelToReports(file.getInputStream());
            System.out.println("Input stream OK......");
            List<OutwardItemsDetailsReport> savedReports = new ArrayList<>();
            return savedReports = (List<OutwardItemsDetailsReport>) repository.saveAll(reports);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<OutwardItemsDetailsReport> getAllReports() {
        return (List<OutwardItemsDetailsReport>) repository.findAll();
    }
}