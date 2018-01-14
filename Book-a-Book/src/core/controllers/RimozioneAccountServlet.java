/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.managers.ManagerAccount;
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
@WebServlet(name = "RimozioneAccountServlet", urlPatterns = {"/profilo/rimozione-account"})
public class RimozioneAccountServlet extends HttpServlet {

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

        ManagerAccount manager = new ManagerAccount();
        ManagerRegistrazione man = new ManagerRegistrazione();
        String url = "";
        
        if (request.getParameter("tipo").equals("persona")) {
            

            manager.rimozioneAccountUtente(request.getParameter("email"));
            if (request.getSession().getAttribute("persona") != null) {
                request.getSession().removeAttribute("persona");
            }
            url = "/skeleton-pages/index.jsp";
            request.getSession().setAttribute("rimozioneU", "true");
            

        } else if (request.getParameter("tipo").equals("richiesta")) {
            

            man.modificaStatoBiblioteca(request.getParameter("isil"), "Rimuovere", Integer.parseInt(request.getParameter("idAdmin")));
            request.getSession().setAttribute("richiestaR", "true");
            url = "/profilo/profiloPersonale-Bibliotecario.jsp";
            

        } else if (request.getParameter("tipo").equals("biblioteca")) {
            

            manager.richiestaRimozioneAccount(request.getParameter("isil"));

            if (request.getSession().getAttribute("biliotecario") != null) {
                request.getSession().removeAttribute("bibliotecario");
            }

            request.getSession().setAttribute("rimozioneB", "true");
            url = "/skeleton-pages/index.jsp";
            
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

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
        processRequest(request, response);
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
