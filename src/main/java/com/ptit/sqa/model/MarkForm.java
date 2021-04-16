package com.ptit.sqa.model;

import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Spoint;

import java.util.ArrayList;
import java.util.List;

public class MarkForm {

    private List<MarkResponse> markResponses;

    private List<Spoint> spoints;

    private Clazz clazz;

    public MarkForm(){
        this.markResponses = new ArrayList<>();
        this.spoints = new ArrayList<>();
    }

    public Clazz getClazz(){
        return this.clazz;
    }

    public void setClazz(Clazz clazz){
        this.clazz = clazz;
    }

    public List<MarkResponse> getMarkResponses(){
        return  markResponses;
    }

    public  void setMarkResponses(List<MarkResponse> marks){
        this.markResponses = marks;
    }

    public List<Spoint> getSpoints(){
        return  spoints;
    }

    public  void setSpoints(List<Spoint> spoints){
        this.spoints = spoints;
    }

    public void add(MarkResponse markResponse){
        this.markResponses.add(markResponse);
    }

}
