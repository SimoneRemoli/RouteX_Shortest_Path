package com.example.servlets;

public class Athens extends City{
    public Athens()
    {
        this.matriceAdiacenza = new int[66][66];
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/Adjacency_Matrix/Athens.csv");
    }
}
