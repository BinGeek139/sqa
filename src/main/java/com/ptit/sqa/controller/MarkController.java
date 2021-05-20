package com.ptit.sqa.controller;

import com.ptit.sqa.entity.*;
import com.ptit.sqa.model.MarkForm;
import com.ptit.sqa.model.MarkResponse;
import com.ptit.sqa.repo.ClazzRepository;
import com.ptit.sqa.repo.MarkRepository;
import com.ptit.sqa.repo.SemesterRepository;
import com.ptit.sqa.service.impl.ClassServiceImpl;
import com.ptit.sqa.service.impl.MarkFormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MarkController {

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    ClassServiceImpl classService;

    @Autowired
    MarkFormServiceImpl markFormService;

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    MarkRepository markRepository;

    @GetMapping(path = {"mark"})
    public String login(Model model, Authentication authentication){
        //get currentSemester
        Semester currentSemester = semesterRepository.findActivatingSemester();
        //get current user id
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = user.getId();

        List<Map<String, String>> classes= classService.findAllActivatingClass(currentSemester.getId(),currentUserId);
        model.addAttribute("classes", classes);
        return "mark";
    }

    @GetMapping(path="/mark/{classname}")
    public String classDetail(Model model, @PathVariable("classname") String classname){

        Clazz clazz = clazzRepository.findClassByName(classname);

        MarkForm markForm=new MarkForm();
        markForm.setClazz(clazz);
        markFormService.findClassDetail(markForm);

        model.addAttribute("markForm",markForm);
        return "classdetail";
    }

    @RequestMapping(value="/mark/{class}", params = "submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute MarkForm markForm, Model model)  {

        for(MarkResponse markResponse : markForm.getMarkResponses()){
            for (int i=0; i<markResponse.getMarkList().size(); i++){
                Mark mark = markResponse.getMarkList().get(i);
                if (mark.getId() == null && mark.getMark() != null) {
                    markRepository.insert(mark.getMark(),
                            markResponse.getClassStudentId(),
                            markForm.getSpoints().get(i).getId());
                } else {
                    markRepository.update(mark.getMark(), mark.getId());
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
