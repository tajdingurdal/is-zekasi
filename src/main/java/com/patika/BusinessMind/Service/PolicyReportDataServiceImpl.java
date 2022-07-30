package com.patika.BusinessMind.Service;

import com.patika.BusinessMind.Model.PolicyReportData;
import com.patika.BusinessMind.Repository.PolicyReportDataRepository;
import com.patika.BusinessMind.Service.abstracts.IPolicyReportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyReportDataServiceImpl implements IPolicyReportDataService {

	private PolicyReportDataRepository policyReportDataRepository;

	@Autowired
	public PolicyReportDataServiceImpl(PolicyReportDataRepository policyReportDataRepository) {
	
		this.policyReportDataRepository = policyReportDataRepository;
	}

	@Override
	public List<PolicyReportData> findAll() {
		Iterable<PolicyReportData> allFinancialData = policyReportDataRepository.findAll();

		List<PolicyReportData> list = new ArrayList<>();

		for (PolicyReportData financialData : allFinancialData) {
			list.add(financialData);
		}
		return list;
	}

	@Override
	public Optional<PolicyReportData> findById(Long id) {
		Optional<PolicyReportData> policyReportData = policyReportDataRepository.findById(id);
		return policyReportData;
	}
}
