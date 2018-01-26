/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Libro;
import core.entities.Persona;
import core.entities.Prenotazione;
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
@WebServlet(name = "DettagliPrenotazioneServlet", urlPatterns = {"/prenotazioni/dettaglio-prenotazioni"})
public class DettagliPrenotazioneServlet extends HttpServlet {

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
        //In questa servlet verra' passato L'id di una prenotazione e saranno visualizzati i dettagli di quest'ultima.

        String message;
        int id = Integer.parseInt(request.getParameter("id"));
        Prenotazione pren;
        Persona persona;
        Libro libro;
        ManagerPrenotazione manPren = new ManagerPrenotazione();
        if((pren = manPren.visualizzaPrenotazione(id))==null){
            message="error";
        }else{
            message="ok";
        }
        libro = pren.getCopia().getLibro();
        persona = pren.getPersona();

        request.setAttribute("prenotazione", pren);
        request.setAttribute("libro", libro);
        request.setAttribute("persona", persona);
        request.setAttribute("message", message);
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