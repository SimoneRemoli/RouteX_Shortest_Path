package com.example.servlets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import Model.RouteInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Rome extends City {

    public Rome() throws Exception {

        this.matriceAdiacenza = new int[76][76]; // Roma ha 76 stazioni
        caricaMatriceDaClasspath("/Adjacency_Matrix/Rome.csv");

    }

}
