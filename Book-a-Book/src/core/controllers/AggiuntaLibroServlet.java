/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.DAO.BibliotecaDAOStub;
import core.DAO.LibroDAO;
import core.DAO.PosizioneDAO;
import core.entities.Autore;
import core.entities.Libro;
import core.managers.ManagerLibri;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manuel
 */
@WebServlet(name = "AggiuntaLibroServlet", urlPatterns = {"/libri/aggiunta-libro"})
public class AggiuntaLibroServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            
            boolean ignore = Boolean.parseBoolean(request.getParameter("ignore"));
            String isbn = request.getParameter("isbn");
            String titolo = request.getParameter("titolo");
            String editore = request.getParameter("editore");
            String pathFoto = request.getParameter("path_foto");
            String descrizione = request.getParameter("descrizione");
            String dataString = request.getParameter("data");
            
            String[] dataArray = dataString.split("-");
            GregorianCalendar dataPubblicazione = new GregorianCalendar();
            dataPubblicazione.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(dataArray[0]));
            dataPubblicazione.set(GregorianCalendar.MONTH, Integer.parseInt(dataArray[1]));
            dataPubblicazione.set(GregorianCalendar.YEAR, Integer.parseInt(dataArray[2]));
            
            List<Autore> autoriList = new ArrayList<>();
            ManagerLibri manager = new ManagerLibri(new BibliotecaDAOStub(),new PosizioneDAO(), new LibroDAO());
            Libro book;
            
            if(!ignore){
                String[] autori = request.getParameterValues("autore");
                for(String a : autori){
                    autoriList.add(new Autore(a));
                    
                }
                
                
               book = manager.aggiuntaLibro(isbn, titolo, editore, dataPubblicazione, descrizione, pathFoto, autoriList);
            } else {
               book = manager.visualizzaLibro(isbn);
            }
            
            request.getSession().setAttribute("libro", book);
            out.print(book);
        }
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
