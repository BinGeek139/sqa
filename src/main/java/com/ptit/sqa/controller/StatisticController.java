package com.ptit.sqa.controller;

import com.ptit.sqa.model.StatisticRequest;
import com.ptit.sqa.service.impl.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping("statistic")
    public String view(Model model, StatisticRequest statisticRequest) {
        model.addAttribute("statisticResponseList", statisticService.statisticMark(statisticRequest));
        model.addAttribute("statisticRequest", statisticRequest);
        return "statistic";

    }


}
