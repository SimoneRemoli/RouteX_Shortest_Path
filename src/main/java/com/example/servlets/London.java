package com.example.servlets;

import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class London extends City {

    public London() throws Exception {

        this.matriceAdiacenza = new int[513][513];
        caricaMatriceDaClasspath("/Adjacency_Matrix/London.csv");
    }
}
