/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Prenotazione;
import core.managers.ManagerPrenotazione;
import core.utils.Criterio;
import core.utils.prenotazioniPerCodice;
import core.utils.prenotazioniPerId;
import core.utils.prenotazioniSuDataDiConsegna;
import core.utils.prenotazioniSuDataDiCreazione;
import core.utils.prenotazioniSuDataDiScadenza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mery
 */
@WebServlet(name = "RicercaPrenotazioniPerCriterioServlet", urlPatterns = {"/RicercaPrenotazioniPerCriterioServlet"})
public class RicercaPrenotazioniPerCriterioServlet extends HttpServlet {

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
        Criterio ricerca = null;
        Collection<Prenotazione> lista = new ArrayList<>();
        String criterio = request.getParameter("criterio");
        if (criterio.equals("Per id utente")) {
            ricerca = new prenotazioniPerId(Integer.parseInt(request.getParameter("valore")));
        } else if (criterio.equals("Per codice")) {
            ricerca = new prenotazioniPerCodice(Integer.parseInt(request.getParameter("valore")));
        } else if (criterio.equals("Per data di consegna")) {
            ricerca = new prenotazioniSuDataDiConsegna((GregorianCalendar) request.getAttribute("valore"));
        } else if (criterio.equals("Per data di creazione")) {
            ricerca = new prenotazioniSuDataDiCreazione((GregorianCalendar) request.getAttribute("valore"));
        } else if (criterio.equals("Per data di scadenza")) {
            ricerca = new prenotazioniSuDataDiScadenza((GregorianCalendar) request.getAttribute("valore"));
        }

        ManagerPrenotazione manPren = new ManagerPrenotazione();
        lista = manPren.visualizzaPrenotazioni(ricerca);
        if (lista.isEmpty()) {
            message = "Nessun dato corrispondente al criterio selezionato.";
        } else {
            message = "correct";
        }

        request.setAttribute("message", message);
        RequestDispatcher view = request.getRequestDispatcher("ricercaPrenotazioni.jsp");
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
