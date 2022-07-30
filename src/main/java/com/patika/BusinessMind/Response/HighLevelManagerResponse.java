package com.patika.BusinessMind.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HighLevelManagerResponse {
	private String name;
	private String surname;
	private String email;

	@Override
	public String toString() {
		return "Register Information: \nName: " + name + "\nSurname: " + surname + "\nEmail: " + email;

	}
}
