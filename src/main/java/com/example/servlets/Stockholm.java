package com.example.servlets;

public class Stockholm extends City{
    public Stockholm()
    {
        this.matriceAdiacenza = new int[101][101];
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/Adjacency_Matrix/Stockholm.csv");
    }
}
