/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Prenotazione;
import core.managers.ManagerPrenotazione;
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
        String isil = request.getParameter("isil");
        String isbn = request.getParameter("isbn");
        Prenotazione prenot = null;
        ManagerPrenotazione manPren = new ManagerPrenotazione();
        if (request.getSession().getAttribute("persona") != null) {
            Persona p = (Persona) request.getSession().getAttribute("persona");

            if ((prenot=manPren.prenotareLibro(p, isbn, isil)) == null) {
                message = "C'è stato un errore durante qualche operazione.";
            } else {
                message = "correct";
            }

        } else if (request.getSession().getAttribute("bibliotecario") != null) {
            String via = request.getParameter("via");
            String citta = request.getParameter("citta");
            String civico = request.getParameter("civico");
            String provincia = request.getParameter("provincia");
            String cap =  request.getParameter("cap");
            Indirizzo ind = new Indirizzo(via, citta, civico, provincia, cap);

            String numDoc =  request.getParameter("numDoc");
            String nome = request.getParameter("nome");
            String cognome =  request.getParameter("cognome");
            Persona p = new Persona(numDoc, ind, nome, cognome, null);

            
            if ((prenot=manPren.prenotareLibro(p, isbn, isil)) == null) {
                message = "error";
            } else {
                message = "correct";
            }
        }

        request.setAttribute("message", message);
        request.setAttribute("prenotazione", prenot);
        
        RequestDispatcher view = request.getRequestDispatcher("visualizza-prenotazione.jsp");
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
