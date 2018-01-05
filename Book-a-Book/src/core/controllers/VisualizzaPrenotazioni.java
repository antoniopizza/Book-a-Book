/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Prenotazione;
import core.entities.Utente;
import core.managers.ManagerPrenotazione;
import core.utils.Criterio;
import core.utils.prenotazioniPerId;
import core.utils.prenotazioniPerIsil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mery
 */
@WebServlet(name = "VisualizzaPrenotazioni", urlPatterns = {"/VisualizzaPrenotazioni"})
public class VisualizzaPrenotazioni extends HttpServlet {

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

        String message;
        Collection<Prenotazione> lista = new ArrayList<>();
        Criterio cr;
        int id_Persona = Integer.parseInt(request.getParameter("id_persona"));
        String isil = request.getParameter("isil");
        String user = request.getParameter("user");
        ManagerPrenotazione manPren = new ManagerPrenotazione();
        //In questa servlet verra' passato L'id_persona, o il l'isil, e il tipo di utente(Persona,Biblioteca) con un setParameter.
        if (user.equalsIgnoreCase("Biblioteca")) {
            cr = new prenotazioniPerIsil(isil);
            lista = manPren.visualizzaPrenotazioni(cr);
            if (lista.isEmpty()) {
                message = "Nessun dato corrispondente.";
            } else {
                message = "correct";
            }
        } else if (user.equalsIgnoreCase("Persona")) {
            cr = new prenotazioniPerId(id_Persona);
            lista = manPren.visualizzaPrenotazioni(cr);
            if (lista.isEmpty()) {
                message = "Nessun dato corrispondente.";
            } else {
                message = "correct";
            }
        } else {
            message = "Il tipo di utente non e' definito.";
        }
        request.setAttribute("lista", lista);
        request.setAttribute("message", message);
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
