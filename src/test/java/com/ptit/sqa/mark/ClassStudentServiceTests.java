package com.ptit.sqa.mark;

import com.ptit.sqa.service.impl.ClassStudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassStudentServiceTests {
    @Autowired
    private JdbcTemplate JdbcTemplate;

    @Autowired
    private ClassStudentServiceImpl classStudentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldFind99Students(){
        List<Map<String, Object>> list = classStudentService.findAllStudentByClass(1001L);
        assertEquals(99, list.size());
    }
}
