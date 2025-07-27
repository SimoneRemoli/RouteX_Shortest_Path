package com.example.servlets;

import Model.Credentials;
import Exception.DAOException;
import Model.Permessi;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.CallableStatement;


public class LoginProcedureDAO{

    public Credentials login(Credentials cred) throws DAOException, SQLException {

        try (Connection conn = ConnectionFactory.gettConnection()){
            CallableStatement cs = conn.prepareCall("{ CALL routex.login_user(?, ?, ?, ?, ?, ?, ?, ?) }");

            cs.setString(1, cred.getEmail());
            cs.setString(2, cred.getPassword());
            cs.registerOutParameter(3, java.sql.Types.INTEGER); // ruolo
            cs.registerOutParameter(4, java.sql.Types.VARCHAR); // codice fiscale
            cs.registerOutParameter(5, java.sql.Types.VARCHAR); // nome
            cs.registerOutParameter(6, java.sql.Types.VARCHAR); // cognome
            cs.registerOutParameter(7, java.sql.Types.DATE);    // data di nascita
            cs.registerOutParameter(8, java.sql.Types.BOOLEAN); // disabile

            cs.execute();

            cred.setPermessi(Permessi.fromint(cs.getInt(3)));
            cred.setCodiceFiscale(cs.getString(4));
            cred.setNome(cs.getString(5));
            cred.setCognome(cs.getString(6));
            cred.setDataDiNascita(cs.getDate(7));
            cred.setDisabile(cs.getBoolean(8));

            return cred;

        } catch (Exception e) {
            throw new DAOException("Errore durante il login: " + e.getMessage());
        }
    }
}


