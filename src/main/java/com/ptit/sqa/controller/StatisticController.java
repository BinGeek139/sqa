package com.ptit.sqa.controller;

import com.ptit.sqa.model.StaticResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StatisticController {
    @GetMapping("statistic")
    public String view(Model model){
        Random random=new Random();
        List<StaticResponse> statisticResponseList=new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            int sum=random.nextInt(100)+10;
            int pass=random.nextInt(sum);
            int inpass=sum-pass;
            StaticResponse statisticResponse = new StaticResponse(i + 1,
                    "Môn học " + i,
                    getAlphaNumericString(5),
                    getAlphaNumericString(20),
                    pass, inpass, sum);
            statisticResponseList.add(statisticResponse);
        }
        model.addAttribute("statisticResponseList",statisticResponseList);
        return "statistic";

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
