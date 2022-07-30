package com.patika.BusinessMind.Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policy_report_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyReportData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "endorsement")
	private int endorsement; // ciro
	
	@Column(name = "total_profit")
	private int totalProfit; // kar

	@Column(name = "start_year")
	private int startYear;
	
	@Column(name = "end_year")
	private int endYear;
	
	@ManyToOne
	@JoinColumn(name = "insurance_agency_id")
	private InsuranceAgency insuranceAgency;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
 