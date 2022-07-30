package com.patika.BusinessMind.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance_agency")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceAgency { // Sigorta ettiren acente
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "agency_name")
	private String agencyName;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "manager_first_name")
	private String managerFirstName;

	@Column(name = "manager_last_name")
	private String managerLastName;
	
}
 