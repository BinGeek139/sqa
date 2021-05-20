package com.ptit.sqa.service.impl;

import com.ptit.sqa.repo.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    ClazzRepository clazzRepository;

    public List<Map<String, String>> findAllActivatingClass(Long semId, Long userId){

        List<Object[]> list = clazzRepository.findActivatingClass(semId, userId);
        List<Map<String, String>> classList = new ArrayList<>();
        for (Object[] ob : list) {
            Map<String, String> map = new HashMap<>();
            map.put("cname", (String) ob[0]);
            map.put("sname", (String) ob[1]);
            classList.add(map);
        }

        return classList;
    }

}
