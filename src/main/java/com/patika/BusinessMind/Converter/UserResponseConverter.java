package com.patika.BusinessMind.Converter;

import java.util.function.Function;
import org.springframework.stereotype.Component;
import com.patika.BusinessMind.Model.HighLevelManager;
import com.patika.BusinessMind.Response.HighLevelManagerResponse;

@Component
public class UserResponseConverter implements Function<HighLevelManager, HighLevelManagerResponse> {

	@Override
	public HighLevelManagerResponse apply(HighLevelManager t) {
		return HighLevelManagerResponse
				.builder()
					.name(t.getName())
					.surname(t.getSurname())
					.email(t.getEmail())
				.build();
	}

}
 