package com.ptit.sqa.controller;


import com.ptit.sqa.model.StatisticRequest;
import com.ptit.sqa.model.StatisticResponse;
import com.ptit.sqa.service.impl.StatisticService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatisticControllerTest {
    @TestConfiguration
    public static class StatisticControllerConfiguration{
        @Bean
        public StatisticService statisticService(){
            return new StatisticService();
        }
    }
    @Autowired
    StatisticService statisticService;
    @Test
    public void test1() throws IOException, InvalidFormatException {
        Workbook workbook=new XSSFWorkbook(new File("testcase.xlsx"));
        Sheet sheet=workbook.getSheetAt(0);
        int rowStart=11;
        int numberOfTest=(int)sheet.getRow(8).getCell(2).getNumericCellValue();
        for (int i = rowStart; i < rowStart+numberOfTest ; i++) {
            Row row = sheet.getRow(i);
            Double input=row.getCell(1).getNumericCellValue();
            Double output=row.getCell(2).getNumericCellValue();
            StatisticRequest statisticRequest=new StatisticRequest();
            statisticRequest.setIdSemester(input.longValue());
            List<StatisticResponse> statisticResponses = statisticService.statisticMark(statisticRequest);
            if(statisticResponses.size() == output.intValue()){
                row.getCell(3).setCellValue("Pass");
            }else {
                row.getCell(3).setCellValue("Fail");
            }

        }
        workbook.close();


//        List<StatisticResponse> statisticResponses = statisticService.statisticMark(null);
//        statisticResponses.size();
//        System.out.println(statisticResponses.toString());
    }
}
