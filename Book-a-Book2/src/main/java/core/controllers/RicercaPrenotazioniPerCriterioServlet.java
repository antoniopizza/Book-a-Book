/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Prenotazione;
import core.managers.ManagerPrenotazione;
import core.utils.Criterio;
import core.utils.PrenotazioniPerCodice;
import core.utils.PrenotazioniPerId;
import core.utils.PrenotazioniSuDataDiConsegna;
import core.utils.PrenotazioniSuDataDiCreazione;
import core.utils.PrenotazioniSuDataDiScadenza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
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
@WebServlet(name = "RicercaPrenotazioniPerCriterioServlet", urlPatterns = {"/prenotazioni/prenotazioni-per-criterio"})
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
        Collection<Prenotazione> lista;
        String criterio = request.getParameter("criterio");
        switch (criterio) {
            case "utente":
                ricerca = new PrenotazioniPerId(Integer.parseInt(request.getParameter("valore-codice")));
                break;
            case "codice":
                ricerca = new PrenotazioniPerCodice(Integer.parseInt(request.getParameter("valore-codice")));
                break;
            case "ritiro": {
                String stringaData = request.getParameter("valore-data");
                String[] dataArray = stringaData.split("-");
                GregorianCalendar data = new GregorianCalendar(Integer.parseInt(dataArray[2]), Integer.parseInt(dataArray[1]) - 1, Integer.parseInt(dataArray[0]));
                ricerca = new PrenotazioniSuDataDiConsegna(data);
                break;
            }
            case "creazione": {
                String stringaData = request.getParameter("valore-data");
                String[] dataArray = stringaData.split("-");
                GregorianCalendar data = new GregorianCalendar(Integer.parseInt(dataArray[2]), Integer.parseInt(dataArray[1]) - 1, Integer.parseInt(dataArray[0]));
                ricerca = new PrenotazioniSuDataDiCreazione(data);
                break;
            }
            case "scadenza": {
                String stringaData = request.getParameter("valore-data");
                String[] dataArray = stringaData.split("-");
                GregorianCalendar data = new GregorianCalendar(Integer.parseInt(dataArray[2]), Integer.parseInt(dataArray[1]) - 1, Integer.parseInt(dataArray[0]));
                ricerca = new PrenotazioniSuDataDiScadenza(data);
                break;
            }
            default:
                break;
        }

        ManagerPrenotazione manPren = new ManagerPrenotazione();
        lista = manPren.visualizzaPrenotazioni(ricerca);
        if (lista.isEmpty()) {
            message = "Empty";
        } else {
            message = "correct";
        }

        request.setAttribute("message", message);
        request.setAttribute("lista", lista);
        RequestDispatcher view = request.getRequestDispatcher("ricerca-prenotazioni.jsp");
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
