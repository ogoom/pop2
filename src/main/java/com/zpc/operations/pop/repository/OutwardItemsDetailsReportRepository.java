package com.zpc.operations.pop.repository;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutwardItemsDetailsReportRepository extends JpaRepository<OutwardItemsDetailsReport, Long> {
    List<OutwardItemsDetailsReport> findByReceivingAccountNo(String str);

    List<OutwardItemsDetailsReport> findByReceivingName(String str);

}
