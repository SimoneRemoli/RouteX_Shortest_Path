package Model;

import com.example.servlets.ConnectionFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouteInfo {

    private String partenza;
    private String arrivo;
    private String citta;
    private String tipoViaggiatore;
    private int nCambi;
    private String listaCambi;
    private String stazInterscambio;
    private int nStazAttraversate;
    private Double tempoDiArrivo;
    private int nStazioniCitta;
    private Double percTerrenoUtilizzato;
    private String utente;


    @SuppressWarnings("unchecked")
    public RouteInfo(HttpServletRequest request)
    {
        try {
            // Conversione sicura di listacambi
            Object objListaCambi = request.getAttribute("listacambi");
            List<String> listaCambiList = new ArrayList<>();

            if (objListaCambi instanceof List) {
                listaCambiList = (List<String>) objListaCambi;
            } else if (objListaCambi instanceof String) {
                listaCambiList.add((String) objListaCambi);
            }
            this.listaCambi = String.join(", ", listaCambiList);

            // Conversione sicura di nodicruciali
            Object objStazInterscambio = request.getAttribute("nodicruciali");
            List<String> stazInterList = new ArrayList<>();
            if (objStazInterscambio instanceof List) {
                stazInterList = (List<String>) objStazInterscambio;
            } else if (objStazInterscambio instanceof String) {
                stazInterList.add((String) objStazInterscambio);
            }
            this.stazInterscambio = String.join(", ", stazInterList);

            this.partenza = (String) request.getAttribute("inizio");
            this.arrivo = (String) request.getAttribute("fine");
            this.citta = (String) request.getAttribute("city");
            this.tipoViaggiatore = (String) request.getAttribute("status");
            this.nCambi = (int) request.getAttribute("numero_cambi");
            this.nStazAttraversate = (int) request.getAttribute("numero");
            this.tempoDiArrivo = (Double) request.getAttribute("minutaggio");
            this.nStazioniCitta = (int) request.getAttribute("stazionitotali");
            this.percTerrenoUtilizzato = (Double) request.getAttribute("suolometropolitano");
            this.utente = (String) request.getAttribute("codiceFiscale");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public RouteInfo() {

    }

    @SuppressWarnings("unchecked")
    public static String convertListObjectToString(Object obj) {
        if (obj instanceof List) {
            List<?> tempList = (List<?>) obj;
            if (!tempList.isEmpty() && tempList.get(0) instanceof String) {
                List<String> lista = (List<String>) tempList;
                return String.join(", ", lista);
            }
        } else if (obj instanceof String) {
            return (String) obj;
        }
        return "";
    }

    public boolean checkStazione(String city, String startStation, String endStation) throws Exception {
        try (Connection conn = ConnectionFactory.gettConnection()){
            boolean start = false, end = false;
            CallableStatement cs = conn.prepareCall("{ CALL RouteX_Update.VerificaStazioni(?, ?, ?) }");

            cs.setString(1, city);
            cs.setString(2, startStation);
            cs.setString(3, endStation);

            ResultSet rs =  cs.executeQuery();

            while(rs.next())
            {
                start = rs.getBoolean("p_startExists");
                end = rs.getBoolean("p_endExists");
            }

            return start && end;

        } catch (Exception e) {
            throw new Exception("Errore durante il login: " + e.getMessage());
        }
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getTipoViaggiatore() {
        return tipoViaggiatore;
    }

    public void setTipoViaggiatore(String tipoViaggiatore) {
        this.tipoViaggiatore = tipoViaggiatore;
    }

    public int getnCambi() {
        return nCambi;
    }

    public void setnCambi(int nCambi) {
        this.nCambi = nCambi;
    }

    public String getListaCambi() {
        return listaCambi;
    }

    public void setListaCambi(String listaCambi) {
        this.listaCambi = listaCambi;
    }

    public String getStazInterscambio() {
        return stazInterscambio;
    }

    public void setStazInterscambio(String stazInterscambio) {
        this.stazInterscambio = stazInterscambio;
    }

    public int getnStazAttraversate() {
        return nStazAttraversate;
    }

    public void setnStazAttraversate(int nStazAttraversate) {
        this.nStazAttraversate = nStazAttraversate;
    }

    public Double getTempoDiArrivo() {
        return tempoDiArrivo;
    }

    public void setTempoDiArrivo(Double tempoDiArrivo) {
        this.tempoDiArrivo = tempoDiArrivo;
    }

    public int getnStazioniCitta() {
        return nStazioniCitta;
    }

    public void setnStazioniCitta(int nStazioniCitta) {
        this.nStazioniCitta = nStazioniCitta;
    }

    public Double getPercTerrenoUtilizzato() {
        return percTerrenoUtilizzato;
    }

    public void setPercTerrenoUtilizzato(Double percTerrenoUtilizzato) {
        this.percTerrenoUtilizzato = percTerrenoUtilizzato;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }
}
