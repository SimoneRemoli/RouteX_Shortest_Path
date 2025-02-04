package com.example.servlets;

public class London extends City {

    public London()
    {
        this.matriceAdiacenza = new int[513][513];
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/London.csv");
    }
}
