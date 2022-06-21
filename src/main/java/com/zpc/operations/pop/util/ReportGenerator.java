package com.zpc.operations.pop.util;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import com.zpc.operations.pop.repository.OutwardItemsDetailsReportRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportGenerator {
    OutwardItemsDetailsReport report = new OutwardItemsDetailsReport();
    Map<String, Object> parameters = new HashMap<>();
    private String queryType;
    private String queryString;
    private Long id;
    private String path = "C:\\code\\java\\spring";
    private List<OutwardItemsDetailsReport> reportList = new ArrayList<OutwardItemsDetailsReport>();
    @Autowired
    private OutwardItemsDetailsReportRepository repository;
    private JRBeanCollectionDataSource jrBeanDatasource;

    public String generateReport(Long id) throws FileNotFoundException, JRException {
        report = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        ;
        reportList.add(report);
        jrBeanDatasource = new JRBeanCollectionDataSource(reportList);
        parameters.put("payer_bank", report.getPresentingBank());
        parameters.put("payer", report.getPresentingAccountName());
        parameters.put("beneficiary", report.getReceivingName());
        parameters.put("date_of_transmission", report.gettDate());
        parameters.put("beneficiary_acc_no", report.getReceivingAccountNo());
        parameters.put("amount", report.getAmount());
        parameters.put("imagesDir", path + "\\image2vector.png");
        parameters.put("beneficiary_bank", report.getDraweeBank());
        parameters.put("description", report.getTransDetails());
        parameters.put("ref_no", report.getSeqNo());
        parameters.put("payer_accno", report.getPresentingAccountNo());

        String fileName = report.getReceivingName() + ".pdf";

        File file = ResourceUtils.getFile("classpath:report2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\" + fileName);
        System.out.println("Success..........");
        return fileName;
    }

    public void generateReport(List<OutwardItemsDetailsReport> reports) throws FileNotFoundException, JRException {

        if (reports != null) {
            for (OutwardItemsDetailsReport report : reports) {

                reportList.add(report);
                jrBeanDatasource = new JRBeanCollectionDataSource(reportList);
                parameters.put("payer_bank", report.getPresentingBank());
                parameters.put("payer", report.getPresentingAccountName());
                parameters.put("beneficiary", report.getReceivingName());
                parameters.put("date_of_transmission", report.gettDate());
                parameters.put("beneficiary_acc_no", report.getReceivingAccountNo());
                parameters.put("amount", report.getAmount());
                parameters.put("imagesDir", path + "\\image2vector.png");
                parameters.put("beneficiary_bank", report.getDraweeBank());
                parameters.put("description", report.getTransDetails());
                parameters.put("ref_no", report.getSeqNo());
                parameters.put("payer_accno", report.getPresentingAccountNo());

                String fileName = report.getReceivingName() + ".pdf";

                File file = ResourceUtils.getFile("classpath:report2.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());


                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\" + fileName);
                System.out.println("Success..........");
                reportList.clear();
            }
        }

        return;
    }


}
