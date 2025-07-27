package com.example.servlets;

import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Budapest extends City{
    public Budapest() throws Exception {

        this.matriceAdiacenza = new int[48][48];
        caricaMatriceDaClasspath("/Adjacency_Matrix/Budapest.csv");
    }
}
