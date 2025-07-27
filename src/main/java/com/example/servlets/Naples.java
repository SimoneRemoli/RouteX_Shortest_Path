package com.example.servlets;

import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Naples extends City
{
    public Naples() throws Exception {

        this.matriceAdiacenza = new int[39][39];
        caricaMatriceDaClasspath("/Adjacency_Matrix/Naples.csv");
    }
}
