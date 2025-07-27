package Controller;

import Model.Credentials;
import Exception.DAOException;
import Model.Permessi;
import com.example.servlets.ConnectionFactory;
import com.example.servlets.LoginProcedureDAO;
import com.example.servlets.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            // Leggi i dati dal form
            String email = request.getParameter("Email");
            String password = request.getParameter("Password");


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = ConnectionFactory.gettConnection();

            Credentials cred = new Credentials("", "", "", password, email, null, false, null);
            LoginProcedureDAO utente = new LoginProcedureDAO();
            cred= utente.login(cred);

            // Qui potresti salvare nel DB, fare controlli, ecc.
            System.out.println("Login effettuato: " + cred.getNome() + " " + cred.getCognome());

            // Puoi salvare i dati anche nella sessione se ti serve

            session.setAttribute("nome", cred.getNome());
            session.setAttribute("cognome", cred.getCognome());
            session.setAttribute("cf", cred.getCodiceFiscale());
            session.setAttribute("disabile", cred.getDisabile() ? "yes" : null);

            // Reindirizza a una pagina di conferma (o login.jsp)
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}