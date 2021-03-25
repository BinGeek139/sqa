package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClazzRepository extends JpaRepository<Clazz,Integer> {

    List<Clazz> findByNameContains(String name);
}
