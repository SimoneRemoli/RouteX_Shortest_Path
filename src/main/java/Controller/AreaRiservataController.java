package Controller;

import Model.RouteInfo;
import com.example.servlets.RouteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/areaRiservata")
public class AreaRiservataController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);
            if (session != null) {
                String cf = (String) session.getAttribute("cf");
                if (cf != null) {
                    RouteDAO routeDAO = new RouteDAO();
                    // Ottieni la lista di percorsi dal DAO
                    List<RouteInfo> listaPercorsi = routeDAO.getData(cf);

                    // Salva in request per la JSP
                    request.setAttribute("listaPercorsi", listaPercorsi);

                    request.getRequestDispatcher("areaRiservata.jsp").forward(request, response);
                    return;
                }
            }

            // Se non sei loggato o cf è null, reindirizza a login o pagina di errore
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}