package com.ptit.sqa.service.impl;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService{
    @Override
    public Integer getNumber() {
        return 1;
    }
}
