package com.example.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
//p
public class Milan extends City
{
    public Milan()
    {
        this.matriceAdiacenza = new int[124][124]; //milano ha 124 stazioni
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/Milan.csv");
    }
}
