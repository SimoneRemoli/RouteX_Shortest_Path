package com.example.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Rome extends City
{

    public Rome()
    {
        this.matriceAdiacenza = new int[76][76]; //roma ha 76 stazioni
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/Rome.csv");
    }
}
