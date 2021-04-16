package com.ptit.sqa.controller;

import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Mark;
import com.ptit.sqa.entity.Semester;
import com.ptit.sqa.entity.Spoint;
import com.ptit.sqa.model.MarkForm;
import com.ptit.sqa.model.MarkResponse;
import com.ptit.sqa.repo.ClazzRepository;
import com.ptit.sqa.repo.SemesterRepo;
import com.ptit.sqa.service.impl.ClassServiceImpl;
import com.ptit.sqa.service.impl.MarkFormServiceImpl;
import com.ptit.sqa.service.impl.MarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MarkController {

    @Autowired
    SemesterRepo semesterRepo;

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    ClassServiceImpl classService;

    @Autowired
    MarkFormServiceImpl markFormService;

    @Autowired
    MarkServiceImpl markService;

    @GetMapping(path = {"mark"})
    public String login(Model model){
        //get currentSemester
        Semester currentSemester = semesterRepo.findById(103L).orElse(null);
        //get current user id
        Long currentUserId = 10033L;

        List<Map<String, Object>> classes= classService.findAllActivatingClass(currentSemester.getId(),currentUserId);
        model.addAttribute("classes", classes);
        return "mark";
    }

    @GetMapping(path="/mark/{classname}")
    public String classDetail(Model model, @PathVariable("classname") String classname){

        Clazz clazz = classService.findClassByName(classname);

        MarkForm markForm=new MarkForm();
        markForm.setClazz(clazz);
        markFormService.findClassDetail(markForm);

        model.addAttribute("markForm",markForm);
        return "classdetail";
    }

    @RequestMapping(value="/mark/{class}", params = "submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute MarkForm markForm, Model model)  {

        for (Spoint spoint : markForm.getSpoints()){
            System.out.println(spoint.getId());
        }

        for(MarkResponse markResponse : markForm.getMarkResponses()){
            for (int i=0; i<markResponse.getMarkList().size(); i++){

                Mark mark = markResponse.getMarkList().get(i);

                if (mark.getId() == null && mark.getMark() != null) {
                    markService.insert(mark.getMark(),
                            markResponse.getClassStudentId(),
                            markForm.getSpoints().get(i).getId());
                } else {
                    markService.update(mark.getMark(), mark.getId());
                }
            }
        }
        return "redirect:/mark/{class}";
    }

    @RequestMapping(value="/mark/{class}", params = "reset", method = RequestMethod.POST)
    public String reset(@ModelAttribute MarkForm markForm, Model model)  {

        return "redirect:/mark/{class}";
    }

    @RequestMapping(value="/mark/{class}", params = "cancel", method = RequestMethod.POST)
    public String cancel(@ModelAttribute MarkForm markForm, Model model)  {

        return "redirect:/mark";
    }

}
