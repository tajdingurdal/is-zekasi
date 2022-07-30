package com.patika.BusinessMind.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patika.BusinessMind.Model.PolicyReportData;

@Repository
public interface PolicyReportDataRepository extends CrudRepository<PolicyReportData, Long> {

}
