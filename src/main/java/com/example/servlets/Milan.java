package com.example.servlets;

import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
//p
public class Milan extends City
{
    public Milan() throws Exception {

        this.matriceAdiacenza = new int[124][124]; //milano ha 124 stazioni
        caricaMatriceDaClasspath("/Adjacency_Matrix/Milan.csv");
    }
}
