package com.ptit.sqa.mark;

import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.model.MarkForm;
import com.ptit.sqa.repo.MarkRepository;
import com.ptit.sqa.repo.SpointRepository;
import com.ptit.sqa.repo.SubjectRepository;
import com.ptit.sqa.service.impl.ClassStudentServiceImpl;
import com.ptit.sqa.service.impl.MarkFormServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MarkFormServiceImplTests {

    @Autowired
    ClassStudentServiceImpl classStudentService;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SpointRepository spointRepository;

    @Autowired
    MarkFormServiceImpl markFormService;

    @Test
    public void shouldFindCorrectSpoints(){
        Clazz clazz = new Clazz();
        clazz.setId(1161L);
        MarkForm markForm = new MarkForm();
        markForm.setClazz(clazz);

        markFormService.findClassDetail(markForm);

        assertEquals(4, markForm.getSpoints().size());
        assertEquals(Long.valueOf(1065),
                markForm.getSpoints().get(0).getId());
        assertEquals("Chuyên cần", markForm.getSpoints().get(0).getName());
    }

    @Test
    public void shouldFindCorrectStudents(){
        Clazz clazz = new Clazz();
        clazz.setId(1161L);
        MarkForm markForm = new MarkForm();
        markForm.setClazz(clazz);

        markFormService.findClassDetail(markForm);
        assertEquals(99, markForm.getMarkResponses().size());
        assertNotNull(markForm.getMarkResponses().stream()
                    .filter(res ->  Long.valueOf(16841).equals(res.getClassStudentId()))
                    .findAny()
                    .orElse(null));

    }
}
