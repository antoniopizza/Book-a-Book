/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Bibliotecario;
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
@WebServlet(name = "CercaDipendenteServlet", urlPatterns = {"/utenti/cerca-dipendente"})
public class CercaDipendenteServlet extends HttpServlet {

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
         
        
        Collection<Bibliotecario> listaDipendenti = new ArrayList<>();
        String criterio = request.getParameter("criterio");
        
        if(criterio.equals("email")){
             ricerca = new RegistratiPerEmail(request.getParameter("valore"));                     
            
        }else if(criterio.equals("cognome")){
            ricerca = new RegistratiPerCognome(request.getParameter("valore"));
            
        }
        
        ManagerUtenti manUtenti = new ManagerUtenti();
        String isil = ((Bibliotecario)request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil();
        listaDipendenti = manUtenti.visualizzaDipendenti(ricerca,isil);
        
        if(listaDipendenti.isEmpty()){
            message = "Nessun dato corrispondente al criterio selezionato.";
        } else {
            message = "correct";
            request.setAttribute("listaDipendenti", listaDipendenti);
        }
        
        request.setAttribute("message", message);
        RequestDispatcher view = request.getRequestDispatcher("cerca-dipendente-biblioteca.jsp");
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
