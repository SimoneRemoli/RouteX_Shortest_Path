package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leggi i dati dal form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String codiceFiscale = request.getParameter("codicefiscale");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        boolean isDisabled = request.getParameter("disabled") != null;

        // Qui potresti salvare nel DB, fare controlli, ecc.
        System.out.println("Registrazione ricevuta: " + firstName + " " + lastName);

        // Puoi salvare i dati anche nella sessione se ti serve
        HttpSession session = request.getSession();
        session.setAttribute("nome", firstName);

        // Reindirizza a una pagina di conferma (o login.jsp)
        response.sendRedirect("loginDone.jsp");
    }
}