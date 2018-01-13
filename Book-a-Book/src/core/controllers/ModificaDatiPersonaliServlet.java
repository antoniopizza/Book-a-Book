/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Persona;
import core.managers.ManagerAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salva
 */
@WebServlet(name = "ModificaDatiPersonaliServlet", urlPatterns = {"/profilo/modifica-datiPersonali"})
public class ModificaDatiPersonaliServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModificaDatiPersonaliServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModificaDatiPersonaliServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ManagerAccount manager = new ManagerAccount();
        String url = "";

        if (request.getParameter("tipoUtente").equals("persona")) {

            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String vecchiaEmail = request.getParameter("vecchiaEmail");
            String email = request.getParameter("nuovaEmail");
            String numeroDocumento = request.getParameter("documento");
            String provincia = request.getParameter("provincia");
            String citta = request.getParameter("citta");
            String via = request.getParameter("via");
            String numeroCivico = request.getParameter("civico");
            String CAP = request.getParameter("cap");
            String pathFoto = request.getParameter("foto");
            String numeroTelefono = request.getParameter("numero");

            Persona p = manager.modificaDatiPersonali(vecchiaEmail, email, nome, cognome, numeroDocumento, provincia, CAP, via, numeroCivico, citta, numeroTelefono, pathFoto);

            if (request.getSession().getAttribute("persona") != null) {
                request.getSession().removeAttribute("persona");
            }

            request.getSession().setAttribute("persona", p);
            request.getSession().setAttribute("modificato", "true");
            url = "/profilo/profiloPersonale-Utente.jsp";

        } else if (request.getParameter("tipoUtente").equals("bibliotecario")) {

            if (request.getParameter("tipoBibliotecario").equals("Responsabile")) {

                String isil = request.getParameter("isil");
                String nome = request.getParameter("nomeBiblioteca");
                String provincia = request.getParameter("provincia");
                String citta = request.getParameter("citta");
                String via = request.getParameter("via");
                String numeroCivico = request.getParameter("civico");
                String CAP = request.getParameter("cap");
                String numero = request.getParameter("numero");

                manager.modificaDatiBiblioteca(isil, nome, via, citta, numeroCivico, numero, provincia, CAP);

            }
            String vecchiaEmail = request.getParameter("vecchiaEmail");
            String email = request.getParameter("email");
            String pathFoto = request.getParameter("foto");

            Bibliotecario b = manager.modificaDatiPersonali(vecchiaEmail, email,pathFoto);

            if (request.getSession().getAttribute("bibliotecario") != null) {
                request.getSession().removeAttribute("bibliotecario");
            }

            request.getSession().setAttribute("bibliotecario", b);
            request.getSession().setAttribute("modificato", "true");

            url = "/profilo/profiloPersonale-Bibliotecario.jsp";

        } else if (request.getParameter("tipoUtente").equals("admin")) {
            
            String vecchiaEmail = request.getParameter("vecchiaEmail");
            String email = request.getParameter("email");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");

            Admin a = manager.modificaDatiPersonali(vecchiaEmail, email, nome, cognome);

            if (request.getSession().getAttribute("admin") != null) {
                request.getSession().removeAttribute("admin");
            }

            request.getSession().setAttribute("admin", a);
            request.getSession().setAttribute("modificato", "true");

            url = "/profilo/profiloPersonale-Admin.jsp";
  

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
