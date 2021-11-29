package com.zpc.operations.pop.service;


import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import com.zpc.operations.pop.paging.Paged;
import com.zpc.operations.pop.paging.Paging;
import com.zpc.operations.pop.repository.OutwardItemsDetailsReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final OutwardItemsDetailsReportRepository repository;

    @Autowired
    public ReportService(OutwardItemsDetailsReportRepository repository) {
        this.repository = repository;
    }

    public Paged<OutwardItemsDetailsReport> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<OutwardItemsDetailsReport> reportPage = repository.findAll(request);
        System.out.println(reportPage.getTotalPages());
        return new Paged<OutwardItemsDetailsReport>(reportPage, Paging.of(reportPage.getTotalPages(), pageNumber, size));
    }
}
