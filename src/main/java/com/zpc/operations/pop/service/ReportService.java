package com.zpc.operations.pop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import com.zpc.operations.pop.domain.ReportComparators;
import com.zpc.operations.pop.paging.*;
import com.zpc.operations.pop.repository.DataTablesRepository;
import com.zpc.operations.pop.repository.OutwardItemsDetailsReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportService {
    private final OutwardItemsDetailsReportRepository repository;
    private static final Comparator<OutwardItemsDetailsReport> EMPTY_COMPARATOR = (e1, e2) -> 0;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private final DataTablesRepository datarepo;

    @Autowired
    public ReportService(OutwardItemsDetailsReportRepository repository, DataTablesRepository datarepo) {
        this.repository = repository;
        this.datarepo = datarepo;
    }

    private List<String> toStringList(OutwardItemsDetailsReport report) {
        return Arrays.asList(sdf.format(report.gettDate()), report.getDraweeBank(), report.getSeqNo(), report.getSerialNo(),
                String.valueOf(report.getAmount()), report.getReceivingAccountNo(), report.getReceivingName(), report.getPresentingAccountNo(), report.getPresentingAccountName(),
                report.getTransDetails(), report.getPresentingBank(), report.getWindow());
    }

    public Paged<OutwardItemsDetailsReport> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        org.springframework.data.domain.Page<OutwardItemsDetailsReport> reportPage = repository.findAll(request);
        System.out.println(reportPage.getTotalPages());
        return new Paged<OutwardItemsDetailsReport>(reportPage, Paging.of(reportPage.getTotalPages(), pageNumber, size));
    }

    public com.zpc.operations.pop.paging.Page<OutwardItemsDetailsReport> getReports(PagingRequest pagingRequest) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<OutwardItemsDetailsReport> reports = datarepo.findAll();

        return getPageDatatables(reports, pagingRequest);

        //return new Page<>();
    }

    private Page<OutwardItemsDetailsReport> getPageDatatables(List<OutwardItemsDetailsReport> reports, PagingRequest pagingRequest) {
        List<OutwardItemsDetailsReport> filtered = reports.stream()
                .sorted(sortReports(pagingRequest))
                .filter(filterReports(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = reports.stream()
                .filter(filterReports(pagingRequest))
                .count();

        Page<OutwardItemsDetailsReport> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Comparator<OutwardItemsDetailsReport> sortReports(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<OutwardItemsDetailsReport> comparator = ReportComparators.getComparator(((Column) column).getData(), ((Order) order).getDir());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }

    private Predicate<OutwardItemsDetailsReport> filterReports(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return report -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return report -> report.getReceivingName()
                .toLowerCase()
                .contains(value)
                || report.getReceivingAccountNo()
                .toLowerCase()
                .contains(value)
                || report.getDraweeBank()
                .toLowerCase()
                .contains(value)
                || report.gettDate().toString()
                .toLowerCase()
                .contains(value)
                || report.getSeqNo()
                .toLowerCase()
                .contains(value);
    }
}
