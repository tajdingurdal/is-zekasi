package com.patika.BusinessMind.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.patika.BusinessMind.Model.Customer;
import com.patika.BusinessMind.Model.InsuranceAgency;
import com.patika.BusinessMind.Model.PolicyReportData;
import com.patika.BusinessMind.Repository.PolicyReportDataRepository;

@RunWith(MockitoJUnitRunner.class)
class PolicyReportDataServiceImplTest {

	@Mock
	PolicyReportDataRepository policyReportDataRepository;

	PolicyReportDataServiceImpl policyReportDataServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		policyReportDataServiceImpl = new PolicyReportDataServiceImpl(policyReportDataRepository);
	}

	@Test
	void testFindAll() {

		InsuranceAgency agency = new InsuranceAgency();
		Customer customer = new Customer();
		PolicyReportData policyReportData = new PolicyReportData(1L, 2000, 1000, 2020, 2022, agency, customer);

		List<PolicyReportData> list = List.of(policyReportData);

		when(policyReportDataRepository.findAll()).thenReturn(list);

		List<PolicyReportData> findAll = policyReportDataServiceImpl.findAll();

		Assertions.assertThat(findAll).as("Test findAll() method")
					.isNotEmpty()
					.hasSize(1)
					.contains(policyReportData)
				    .extracting(PolicyReportData::getTotalProfit)
				    .contains(1000);
		
		verify(policyReportDataRepository, atLeast(1)).findAll();

	}

	@Test
	void testFindById() {
		InsuranceAgency agency = new InsuranceAgency(1L, "Agency", "agency@gmail.com", "firstName", "lastName");
		Customer customer = new Customer();
		PolicyReportData policyReportData = new PolicyReportData(1L, 2000, 1000, 2020, 2022, agency, customer);

		when(policyReportDataRepository.findById(any())).thenReturn(Optional.of(policyReportData));
		
		Optional<PolicyReportData> findById = policyReportDataServiceImpl.findById(7L);
		
		
		Assertions.assertThat(Optional.of(findById)).as("Test findById() method")
				  .isNotEmpty()
				  .get()
				  .extracting(t ->t.get().getInsuranceAgency())
				  .isEqualTo(agency)
				  .isEqualToComparingOnlyGivenFields(agency, "agencyName");
				  
				  
		
	}

}
