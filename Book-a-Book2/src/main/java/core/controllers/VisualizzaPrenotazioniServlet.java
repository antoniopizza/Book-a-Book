/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Bibliotecario;
import core.entities.Persona;
import core.entities.Prenotazione;
import core.managers.ManagerPrenotazione;
import core.utils.Criterio;
import core.utils.PrenotazioniPerId;
import core.utils.PrenotazioniPerIsil;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "VisualizzaPrenotazioni", urlPatterns = {"/prenotazioni/ricerca-prenotazioni"})
public class VisualizzaPrenotazioniServlet extends HttpServlet {

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
        ArrayList<Prenotazione> lista = new ArrayList<>();
        Criterio cr;
        ManagerPrenotazione manPren = new ManagerPrenotazione();

        if (request.getSession().getAttribute("persona") != null) {
            Persona p = (Persona) request.getSession().getAttribute("persona");
            cr = new PrenotazioniPerId(p.getId());
            lista = (ArrayList<Prenotazione>) manPren.visualizzaPrenotazioni(cr);
        } else if (request.getSession().getAttribute("bibliotecario") != null) {
            Bibliotecario b = (Bibliotecario) request.getSession().getAttribute("bibliotecario");
            cr = new PrenotazioniPerIsil(b.getBiblioteca().getIsil());
            lista = (ArrayList<Prenotazione>) manPren.visualizzaPrenotazioni(cr);
        } else {
            message = "Il tipo di utente non e' definito.";
        }

        if (!lista.isEmpty()) {
            message = "correct";
        } else {
             message = "Empty";
        }

        System.out.println(lista.size());
        request.setAttribute("lista", lista);
        request.setAttribute("message", message);
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
