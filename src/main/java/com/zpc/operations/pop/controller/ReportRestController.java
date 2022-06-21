package com.zpc.operations.pop.controller;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import com.zpc.operations.pop.paging.Page;
import com.zpc.operations.pop.paging.PagingRequest;
import com.zpc.operations.pop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportRestController {
    private final ReportService service;

    @Autowired
    public ReportRestController(ReportService service) {
        this.service = service;
    }

    @PostMapping
    public Page<OutwardItemsDetailsReport> list(@RequestBody PagingRequest pagingRequest) {
        return service.getReports(pagingRequest);
    }
}
