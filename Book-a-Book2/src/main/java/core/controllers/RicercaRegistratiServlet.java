/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Utente;
import core.managers.ManagerUtenti;
import core.utils.Criterio;
import core.utils.RegistratiPerCognome;
import core.utils.RegistratiPerEmail;
import core.utils.RegistratiPerTipo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mirko
 */
@WebServlet(name = "RicercaRegistratiServlet", urlPatterns = {"/utenti/ricerca-utenti"})
public class RicercaRegistratiServlet extends HttpServlet {

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
        
        String message;
        Criterio ricerca = null;
        
        Collection<Utente> listaUtenti = new ArrayList<Utente>();
        String criterio = request.getParameter("criterio");
        
        switch (criterio) {
            case "email":
                ricerca = new RegistratiPerEmail(request.getParameter("valore"));
                break;
            case "tipo":
                ricerca = new RegistratiPerTipo(request.getParameter("valore"));
                break;
            case "cognome":
                ricerca = new RegistratiPerCognome(request.getParameter("valore"));
                break;
            default:
                break;
        }
        
        ManagerUtenti manUtenti = new ManagerUtenti();
        listaUtenti = manUtenti.visualizzaRegistrati(ricerca);
        
        if(listaUtenti.isEmpty()){
            message = "Empty";
        } else {
            message = "correct";
        }
        
        request.setAttribute("listaUtenti", listaUtenti);
        request.setAttribute("message", message);
        RequestDispatcher view = request.getRequestDispatcher("ricerca-registrati.jsp");
        view.forward(request, response);
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
