package com.example.servlets;

import java.sql.*;

public class StationDAO {

    int Stazione_di_Partenza, Stazione_di_Arrivo;

    public StationDAO(String startstation, String endstation, String citta) throws Exception {
        connection(startstation,endstation,citta);

    }
    public int getStazione_di_Partenza()
    {
        return Stazione_di_Partenza;
    }
    public int getStazione_di_arrivo()
    {
        return Stazione_di_Arrivo;
    }
    private void connection(Object... params){

        String startstation = (String) params[0];
        String endstation = (String) params[1];
        String citta = (String) params[2];
        //dao



        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = ConnectionFactory.gettConnection();
            CallableStatement cs = conn.prepareCall("{call RestituisciStazioni(?,?,?)}");
            cs.setString(1,startstation);
            cs.setString(2,endstation);
            cs.setString(3,citta);
            boolean HasMoreResultSet = cs.execute();
            int numReslt = 0;

            while(HasMoreResultSet)
            {
                ResultSet rs = cs.getResultSet();
                numReslt++;

                if(rs!=null)
                {
                    if(numReslt==1)
                    {
                        while(rs.next())
                        {
                            this.Stazione_di_Partenza = rs.getInt("id");
                        }
                    } //lalal
                    if(numReslt==2)
                    {
                        while(rs.next())
                        {
                            this.Stazione_di_Arrivo = rs.getInt("id");
                        }

                    }
                }
                HasMoreResultSet = cs.getMoreResults();

            }


        }catch(Exception e)
        {
            throw new RuntimeException("Errore nella connessione");
        }
    }
}


/* su RouteX DB - Remoto
+------+----------+----------+-------+
| id   | nome     | disabile | linea |
+------+----------+----------+-------+
|    0 | Rebibbia | no       | B     |
+------+----------+----------+-------+
 */