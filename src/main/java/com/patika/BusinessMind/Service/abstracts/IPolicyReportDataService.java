package com.patika.BusinessMind.Service.abstracts;

import com.patika.BusinessMind.Model.PolicyReportData;

import java.util.List;
import java.util.Optional;

public interface IPolicyReportDataService {
    
    public List<PolicyReportData> findAll();

    Optional<PolicyReportData> findById(Long id);
}
