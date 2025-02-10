package com.example.servlets;

public class Naples extends City
{
    public Naples()
    {
        this.matriceAdiacenza = new int[39][39];
        caricaMatriceDaFile("/Users/simoneremoli/IdeaProjects/RouteX_Ispw/Adjacency_Matrix/Naples.csv");
    }
}
