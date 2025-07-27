package Controller;

import com.example.servlets.ConnectionFactory;
import com.example.servlets.UtenteDAO;
import Model.Credentials;
import Model.Permessi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;


@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            // Leggi i dati dal form
            String nome = request.getParameter("firstName");
            String cognome = request.getParameter("lastName");
            String codiceFiscale = request.getParameter("codicefiscale");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Date dataDiNascita = Date.valueOf(request.getParameter("birthdate"));
            boolean disabile = request.getParameter("disabled") != null;
            Permessi permessi = null;
            if (disabile)
                permessi = Permessi.UTENTEDISABILE;
            else
                permessi = Permessi.UTENTENORMALE;


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = ConnectionFactory.gettConnection();

            Credentials cred = new Credentials(nome, cognome, codiceFiscale, password, email, dataDiNascita, disabile, permessi);
            UtenteDAO utente = new UtenteDAO();
            utente.save(cred);

            // Qui potresti salvare nel DB, fare controlli, ecc.
            System.out.println("Registrazione effettuata: " + nome + " " + cognome);

            // Puoi salvare i dati anche nella sessione se ti serve
           // HttpSession session = request.getSession();
            session.setAttribute("nome", nome);
            session.setAttribute("cognome", cognome);
            session.setAttribute("cf", codiceFiscale);
            session.setAttribute("disabile", disabile ? "yes" : null);

            // Reindirizza a una pagina di conferma (o login.jsp)
            response.sendRedirect("loginDone.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}