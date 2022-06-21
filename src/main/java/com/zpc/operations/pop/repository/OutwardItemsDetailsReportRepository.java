package com.zpc.operations.pop.repository;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutwardItemsDetailsReportRepository extends DataTablesRepository<OutwardItemsDetailsReport, Long> {
}
