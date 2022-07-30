package com.patika.BusinessMind.Service;

import com.patika.BusinessMind.Model.HighLevelManager;
import com.patika.BusinessMind.Repository.HighLevelManagerRepository;
import com.patika.BusinessMind.Service.abstracts.IHighLevelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HighLevelManagerServiceImpl implements IHighLevelManagerService {
	
    
    private HighLevelManagerRepository highLevelManagerRepository;

    @Autowired
    public HighLevelManagerServiceImpl(HighLevelManagerRepository highLevelManagerRepository) {
        this.highLevelManagerRepository = highLevelManagerRepository;
    }


    @Override
    public Iterable<HighLevelManager> findAll() {
        return highLevelManagerRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return highLevelManagerRepository.existsByEmail(email);
    }

    @Override
    public void save(HighLevelManager user) {
        this.highLevelManagerRepository.save(user);
    }
}
 