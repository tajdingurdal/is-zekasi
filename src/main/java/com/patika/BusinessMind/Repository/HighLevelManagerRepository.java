package com.patika.BusinessMind.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patika.BusinessMind.Model.HighLevelManager;

@Repository
public interface HighLevelManagerRepository extends CrudRepository<HighLevelManager, Long> {

	HighLevelManager findByEmail(String email);
    Boolean existsByEmail(String email);

}
