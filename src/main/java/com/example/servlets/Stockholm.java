package com.example.servlets;

import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stockholm extends City{
    public Stockholm() throws Exception {

        this.matriceAdiacenza = new int[101][101];
        caricaMatriceDaClasspath("/Adjacency_Matrix/Stockholm.csv");
    }
}
