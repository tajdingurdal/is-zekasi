package com.patika.BusinessMind.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.patika.BusinessMind.Model.HighLevelManager;
import com.patika.BusinessMind.Repository.HighLevelManagerRepository;

@RunWith(MockitoJUnitRunner.class)
class HighLevelManagerServiceImplTest {
	
	@Mock
	HighLevelManagerRepository highLevelManagerRepository;

	HighLevelManagerServiceImpl highLevelManagerServiceImpl;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		highLevelManagerServiceImpl = new HighLevelManagerServiceImpl(highLevelManagerRepository);
	}

	@Test
	void testFindAll() {
		HighLevelManager highLevelManager = new HighLevelManager();
		List<HighLevelManager> list = List.of(highLevelManager);
		
		when(highLevelManagerRepository.findAll()).thenReturn(list);
		
		  Iterable<HighLevelManager> findAll = highLevelManagerServiceImpl.findAll();
		
		Assertions.assertThat(findAll).as("Test findAll() method")
					.isNotEmpty()
					.isEqualTo(list)
					.hasSize(1)
					.contains(highLevelManager);
		verify(highLevelManagerRepository, atLeast(1)).findAll();
	}

	@Test
	void testExistsByEmail() {
		
		String email = "reyhan@gmail.com"; // veritabanında bu email var
		
		when(highLevelManagerRepository.existsByEmail(email)).thenReturn(true);
		
		boolean existsByEmail = highLevelManagerServiceImpl.existsByEmail(email);
		
		Assertions.assertThat(existsByEmail)
			.isNotEqualTo(false)
			.isEqualTo(true)
			.isNotNull()
			.isInstanceOf(Boolean.class);
		verify(highLevelManagerRepository, atLeast(1)).existsByEmail(email);
	}

	@Test
	void testSave() {
		String email = "serkan@gmail.com";
		HighLevelManager highLevelManager = new HighLevelManager(1L, "Serkan", "Gündoğdu", email, "1234");
		
		highLevelManagerRepository.save(highLevelManager);
		
		verify(highLevelManagerRepository, atLeast(1)).save(highLevelManager);
	}

}
