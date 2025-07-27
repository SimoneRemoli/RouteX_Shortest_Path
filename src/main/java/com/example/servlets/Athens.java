package com.example.servlets;

import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Athens extends City{
    public Athens() throws Exception {

        this.matriceAdiacenza = new int[66][66];
        caricaMatriceDaClasspath("/Adjacency_Matrix/Athens.csv");
    }
}
