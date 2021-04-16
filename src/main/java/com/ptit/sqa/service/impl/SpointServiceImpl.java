package com.ptit.sqa.service.impl;


import com.ptit.sqa.entity.Spoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpointServiceImpl {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ClassServiceImpl classService;

    public List<Spoint> findAllSpointByClass(Long classId){
        String sql = " select id, name from spoint where spoint.subject_id = ? order by percent";
        Long subjectId = classService.findSubjectByClass(classId).getId();


        List<Spoint> spointList = new ArrayList<>();
        List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql,
                new Object[] {subjectId});
        for (Map row : rows){
            Spoint sp = new Spoint();
            sp.setId((Long) row.get("id"));
            sp.setName((String) row.get("name"));
            sp.setPercent((Integer) row.get("percent"));
            spointList.add(sp);
        }

        return spointList;
    }
}
