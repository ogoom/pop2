package com.zpc.operations.pop.repository;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataTablesRepository extends JpaRepository<OutwardItemsDetailsReport, Long> {

}
