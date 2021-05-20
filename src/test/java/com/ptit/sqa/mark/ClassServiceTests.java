package com.ptit.sqa.mark;

import com.ptit.sqa.repo.ClazzRepository;
import com.ptit.sqa.service.impl.ClassServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassServiceTests {
    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private ClassServiceImpl classService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldFindFiveClasses(){
        List<Map<String, String>> list = classService.findAllActivatingClass(103L, 1L);

        assertEquals(5, list.size());
        assertEquals("dbcl161", list.get(0).get("cname"));
        assertEquals("Đảm bảo chất lượng phần mềm", list.get(0).get("sname"));
    }

    @Test
    public void ShouldFindNoClasses(){
        List<Map<String, String>> expectList = new ArrayList<>();
        List<Map<String, String>> list = classService.findAllActivatingClass(103L, 10001L);

        assertEquals(expectList, list);

    }
}
