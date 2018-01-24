/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Persona;
import core.managers.ManagerRegistrazione;
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
@WebServlet(name = "RegistraUtenteServlet", urlPatterns = {"/registrazione/registra-utente"})
public class RegistraUtenteServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistraUtenteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistraUtenteServlet at " + request.getContextPath() + "</h1>");
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

        ManagerRegistrazione mr = new ManagerRegistrazione();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String numeroDocumento = request.getParameter("documento");
        String provincia = request.getParameter("provincia");
        String citta = request.getParameter("citta");
        String via = request.getParameter("via");
        String numeroCivico = request.getParameter("civico");
        String CAP = request.getParameter("cap");
        String pathFoto = request.getParameter("foto");
        String numero = request.getParameter("numero");
        
         String url="";
        
        if((mr.checkEmail(email))==0){
            
            request.getSession().setAttribute("errore", "true");
            url = "/registrazione/registrazione-utente.jsp";
            
        }
        else{

        Persona persona =  mr.registra(nome, cognome, email, numeroDocumento, via, citta, numeroCivico, password, pathFoto, provincia, CAP,numero);
        request.getSession().setAttribute("persona", persona);
        
        url = "/skeleton-pages/index.jsp";
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
