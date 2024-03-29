/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Bibliotecario;
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
 * @author mirko
 */
@WebServlet(name = "ConfermaRitiroLibroServlet", urlPatterns = {"/prenotazioni/conferma-ritiro"})
public class ConfermaRitiroLibroServlet extends HttpServlet {

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
        int idPrenotazione = Integer.parseInt(request.getParameter("id_prenotazione"));
        String status = "Ritirato";
        ManagerPrenotazione manPren = new ManagerPrenotazione();
        
       if (request.getSession().getAttribute("bibliotecario") != null) {
            Bibliotecario b = (Bibliotecario) request.getSession().getAttribute("bibliotecario");
            String email = b.getAccount().getEmail();
            if (manPren.controlloPrenotazione(idPrenotazione, email, status) == false) {
                message = "C'è stato un errore durante qualche operazione.";
            } else {
                message = "correct";
            }
        }
        
        request.setAttribute("message", message);
        RequestDispatcher view = request.getRequestDispatcher("ricerca-prenotazioni");
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
