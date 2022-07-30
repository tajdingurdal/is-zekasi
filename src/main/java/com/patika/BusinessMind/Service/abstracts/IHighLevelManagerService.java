package com.patika.BusinessMind.Service.abstracts;

import com.patika.BusinessMind.Model.HighLevelManager;

public interface IHighLevelManagerService {
    Iterable<HighLevelManager> findAll();

    boolean existsByEmail(String email);

    void save(HighLevelManager user);
}
