package com.example.servlets;

public class Budapest extends City{
    public Budapest()
    {
        this.matriceAdiacenza = new int[48][48];
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/Adjacency_Matrix/Budapest.csv");
    }
}
