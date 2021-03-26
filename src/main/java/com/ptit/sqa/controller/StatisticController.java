package com.ptit.sqa.controller;

import com.ptit.sqa.entity.Semester;
import com.ptit.sqa.model.SemesterResponse;
import com.ptit.sqa.model.StatisticRequest;
import com.ptit.sqa.model.StatisticResponse;
import com.ptit.sqa.repo.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class StatisticController {
    @Autowired
    SemesterRepository semesterRepository;


    @GetMapping("statistic")
    public String view(Model model, StatisticRequest statisticRequest){
        if(Objects.isNull(statisticRequest)){
            statisticRequest=new StatisticRequest();
        }


        List<StatisticResponse> statisticResponseList=fakeDataStatistic();
        List<Semester> semesters=semesterRepository.findAll();

        List<SemesterResponse> semesterResponses=semesters.stream().map(semester -> new SemesterResponse(semester.getId(),
                        semester.getName()+"-"+semester.getYear().toString())).collect(Collectors.toList());

        model.addAttribute("statisticResponseList",statisticResponseList);
        model.addAttribute("semesters",semesterResponses);
        model.addAttribute("statisticRequest",statisticRequest);

        return "statistic";

    }

    List<StatisticResponse> fakeDataStatistic(){
        Random random=new Random();
        List<StatisticResponse> statisticResponseList=new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            int sum=random.nextInt(100)+10;
            int pass=random.nextInt(sum);
            int inpass=sum-pass;
            StatisticResponse statisticResponse = new StatisticResponse(i + 1,
                    "Môn học " + i,
                    getAlphaNumericString(5),
                    getAlphaNumericString(20),
                    pass, inpass, sum);
            statisticResponseList.add(statisticResponse);
        }
        return statisticResponseList;
    }


    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
