/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Copia;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.managers.ManagerPrenotazione;
import java.io.IOException;
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
@WebServlet(name = "PrenotazioneLibroServlet", urlPatterns = {"/prenotazioni/prenotazione-libro"})
public class PrenotazioneLibroServlet extends HttpServlet {

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

        String message = null;
        ManagerPrenotazione manPren = new ManagerPrenotazione();
        if (request.getSession().getAttribute("persona") != null) {
            Persona p = (Persona) request.getSession().getAttribute("persona");
            String isil = (String) request.getSession().getAttribute("isil");
            String isbn = (String) request.getSession().getAttribute("isbn");
            manPren.prenotareLibro(p, isbn, isil);
            if (manPren.prenotareLibro(p, isbn, isil) == false) {
                message = "C'è stato un errore durante qualche operazione.";
            } else {
                message = "correct";
            }

        } else if (request.getSession().getAttribute("bibliotecario") != null) {
            String via = (String) request.getSession().getAttribute("via");
            String citta = (String) request.getSession().getAttribute("citta");
            String civico = (String) request.getSession().getAttribute("civico");
            String provincia = (String) request.getSession().getAttribute("provincia");
            String cap = (String) request.getSession().getAttribute("cap");
            Indirizzo ind = new Indirizzo(via, citta, civico, provincia, cap);

            String numDoc = (String) request.getSession().getAttribute("numDoc");
            String nome = (String) request.getSession().getAttribute("nome");
            String cognome = (String) request.getSession().getAttribute("cognome");
            Persona p = new Persona(numDoc, ind, nome, cognome, null);

            String isil = (String) request.getSession().getAttribute("isil");
            String isbn = (String) request.getSession().getAttribute("isbn");
            if (manPren.prenotareLibro(p, isbn, isil) == false) {
                message = "C'è stato un errore durante qualche operazione.";
            } else {
                message = "correct";
            }
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
