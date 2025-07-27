package com.example.servlets;

import Model.Permessi;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public  abstract class City
{
    //protected String nome;
    /*public City(String a)
    {
        this.nome = a;
    }*/
    ArrayList<Integer> percorsi_codifica = new ArrayList<Integer>();
    ArrayList<String> Percorsi_Con_Nomi = new ArrayList<String>();
    ArrayList<String> Sequenze_di_cambiamento = new ArrayList<String>();
    ArrayList<String> Sequenze_nodi_cruciali = new ArrayList<String>();
    private int numero_cambi;
    ArrayList<String> linee = new ArrayList<String>();
    protected int[][] matriceAdiacenza; //le classi figlie possono specializzare la loro matrice di adiacenza
    protected void caricaMatriceDaFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                //System.out.println("Linea letta: " + line + "values.lenght = " + values.length);
                //System.out.println("Valore values = " + Arrays.toString(values));

                for (int col = 0; col < (values.length); col++) {
                    matriceAdiacenza[row][col] = Integer.parseInt(values[col].trim());
                    //System.out.println("matrice["+row+"]"+"["+col+"]"+"="+matriceAdiacenza[row][col]);

                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*for (int i = 0; i < matriceAdiacenza.length; i++) {
            for (int j = 0; j < matriceAdiacenza[i].length; j++) {
                System.out.print(matriceAdiacenza[i][j] + " ");
            }
            System.out.println();
        }*/
    }



    public void caricaMatriceDaClasspath(String resourcePath) {
        try (
                InputStream inputStream = getClass().getResourceAsStream(resourcePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < 76) {
                String[] values = line.split(",");
                for (int col = 0; col < values.length && col < 76; col++) {
                    matriceAdiacenza[row][col] = Integer.parseInt(values[col].trim());
                }
                row++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore nel caricamento della matrice da classpath", e);
        }
    }
    public void views_arrays(int[]a,int[]b, int[][]matrice)
    {
        //System.out.print("Matrice.lenght =  "+ matrice.length);
        System.out.print("Know = ");
        for(int i=0; i< matrice.length; i++)
        {
            System.out.print( "[ " + a[i] + " ] ");
        }
        System.out.println();
        System.out.print("Cost = ");
        for(int i=0; i< matrice.length; i++)
        {
            System.out.print( "[ " + b[i] + " ] ");
        }

    }
    public boolean checkfill(int[]a, int[]b, int dim)
    {
        for(int i=0;i<dim;i++)
        {
            if(a[i] == -1)
                return true;
        }
        return false;
    }
    public int[] trovaAdj(int nodo, int[][]adiacenza)
    {
        int index = 0;
        int [] adiacenti = new int[(adiacenza.length)-1];
        Arrays.fill(adiacenti,-1);
        for(int i=0;i< adiacenza.length;i++)
        {
            if((adiacenza[nodo][i]!=-1)&&(adiacenza[nodo][i]!=0))
            {
                adiacenti[index] = i;
                index = index + 1;
            }
        }
        return adiacenti;
    }
    public int nodo_costo_minore(int[]cost, int[]know)
    {
        int min=0, minimo_nodo=0, temp = 10000;
        for(int i=0;i<cost.length;i++)
        {
            if(know[i]==-1)
            {
                min = cost[i];

                if(temp > min) {
                    temp = min;
                    minimo_nodo = i;
                }
            }
        }
        return minimo_nodo;
    }
    public void stampaPercorso(int nodo, int[] precedente) {
        Stack<Integer> percorso = new Stack<>();
        int app;
        while (nodo != -1) {
            percorso.push(nodo);
            nodo = precedente[nodo];
        }
        while (!percorso.isEmpty()) {
            app = percorso.pop();
            System.out.print(app + " ");
            percorsi_codifica.add(app);

        }
    }
    public void Dijkstra(int partenza, int arrivo, String city) throws Exception {
        int nodo_partenza = partenza, nodo_arrivo = arrivo;
        int [] know = new int[matriceAdiacenza.length];
        int [] cost = new int[matriceAdiacenza.length];
        Arrays.fill(cost, 1000);
        Arrays.fill(know, -1);
        this.views_arrays(know,cost, matriceAdiacenza);
        System.out.println();
        int [] adiacenti_vector = new int[(matriceAdiacenza.length)-1];
        int adj_temp = 0;
        know[nodo_partenza] = 1;
        cost[nodo_partenza] = 0;
        System.out.println();
        int[] precedente = new int[matriceAdiacenza.length];  // Array per tracciare il percorso
        Arrays.fill(precedente, -1);

        while(this.checkfill(know,cost,matriceAdiacenza.length))
        {
            adiacenti_vector = this.trovaAdj(nodo_partenza, matriceAdiacenza);
            for(int i=0;i<adiacenti_vector.length;i++)
            {
                if(adiacenti_vector[i]!=-1)
                {
                    adj_temp = adiacenti_vector[i];
                    if(know[adj_temp]==-1) { // se il nodo adiacente non è conosciuto

                        // System.out.println("nodo_partenza = "+nodo_partenza+" e adj_temp = "+ adj_temp);

                        if(cost[adj_temp] > cost[nodo_partenza] + matriceAdiacenza[nodo_partenza][adj_temp])
                        {
                            cost[adj_temp] = cost[nodo_partenza] + matriceAdiacenza[nodo_partenza][adj_temp];
                            precedente[adj_temp] = nodo_partenza;
                        }

                    }
                }
            }
            nodo_partenza = this.nodo_costo_minore(cost,know);
            know[nodo_partenza] = 1;
        }

        this.views_arrays(know,cost, matriceAdiacenza);
        System.out.println();
        System.out.println("Path.");
        for (int i = 0; i < matriceAdiacenza.length; i++)
        {
            if(i==nodo_arrivo) { //se levassi questa condizione mi stamperebbe tutti i percorsi dalla stazione di partenza to *
                System.out.print("Nodo " + i + ": Num fermate da attraversare = " + cost[i] + ", Percorso = ");
                this.stampaPercorso(i, precedente);
                System.out.println();
            }
        }

       /* for (int i = 0; i < percorsi.size(); i++) {
            System.out.println("Fermata da attraversare " + i + ": " + percorsi.get(i));
        }*/

        PercorsiDAO percorso = new PercorsiDAO(percorsi_codifica,city); //dalle codifiche ai nomi (MAPPING)
        Percorsi_Con_Nomi = percorso.getPercorsi_Con_Fermate(); //Sputa i veri percorsi
        for(int i=0;i<Percorsi_Con_Nomi.size();i++) System.out.print(" " + Percorsi_Con_Nomi.get(i)+" ---> ");
        numero_cambi = percorso.getCambi_linee_metropolitane();
        linee = percorso.getLinee();
        Sequenze_di_cambiamento = percorso.getSequenze_di_cambiamento();
        Sequenze_nodi_cruciali = percorso.getSequenze_nodi_cruciali();



    }
    public ArrayList<String> getSequenze_nodi_cruciali()
    {
        return Sequenze_nodi_cruciali;
    }
    public ArrayList<String> getSequenze_di_cambiamento()
    {
        return Sequenze_di_cambiamento;
    }
    public int getNumero_cambi()
    {
        return numero_cambi;
    }
    public  ArrayList<String> getPercorsi_Nomi()
    {
        return Percorsi_Con_Nomi;
    }
    public ArrayList<Integer> getPercorsi_codifica()
    {
        return percorsi_codifica;
    }
    public  ArrayList<String> getLinee()
    {
        return linee;
    }


}

//Se ti da fastidio visualizzarla qui su whatsapp, sul branch main: https://github.com/SimoneRemoli/RouteX_Shortest_Path/blob/main/src/main/java/com/example/servlets/City.java