/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.DAO.BibliotecaDAO;
import core.DAO.BibliotecaDAOStub;
import core.DAO.LibroDAO;
import core.DAO.PosizioneDAO;
import core.entities.Copia;
import core.entities.Libro;
import core.managers.ManagerLibri;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kliffom
 */
@WebServlet(name = "VisualizzaLibroServlet", urlPatterns = {"/libri/visualizza-libro"})
public class VisualizzaLibroServlet extends HttpServlet {

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
        
        String isbn = (String) request.getParameter("isbn");
        String message;
        
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAOStub();
        PosizioneDAO posizioneDAO = new PosizioneDAO();
        LibroDAO libroDAO = new LibroDAO();
        ManagerLibri managerLibri = new ManagerLibri(bibliotecaDAO, posizioneDAO, libroDAO);
        
        //ManagerLibri managerLibri = new ManagerLibri();
        
        Libro libro;
        libro = managerLibri.visualizzaLibro(isbn);
        
        if(libro == null){
            message = "Nessun libro corrisponde all'ISBN ricevuto.";
        }
        else {
            message = "correct";
        }
        
        List<Copia> copie;
        
        request.setAttribute("message", message);
        request.setAttribute("libro", libro);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-libro.jsp");
        dispatcher.forward(request, response);
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
