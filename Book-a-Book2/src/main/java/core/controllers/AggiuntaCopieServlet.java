/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.DAO.BibliotecaDAOStub;
import core.DAO.LibroDAO;
import core.DAO.PosizioneDAO;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import core.managers.ManagerLibri;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
@WebServlet(name = "AggiuntaCopieServlet", urlPatterns = {"/libri/aggiunta-copie"})
public class AggiuntaCopieServlet extends HttpServlet {

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
        
        ManagerLibri manager = new ManagerLibri(new BibliotecaDAOStub(),new PosizioneDAO(),new LibroDAO());
        try (PrintWriter out = response.getWriter()) {

            //dati in sessione
            Biblioteca biblio = ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca();
            Libro book = (Libro) request.getSession().getAttribute("libro");
            
            int nScaffali = Integer.parseInt(request.getParameter("n-scaffali"));
            
            List<Posizione> posizioni = new ArrayList<>();
            
            for(int i = 0;i<nScaffali;i++){
                //definizione scaffale
                String etichettaScaffale = request.getParameter("etichetta-"+i);
                int nCopie = Integer.parseInt(request.getParameter("n-copie-"+i));               
                Posizione p = new Posizione(etichettaScaffale);
                p.setBiblioteca(biblio);
                                               
                //definizione delle copie in uno scaffale
                for(int j= 0; j<nCopie;j++){
                    String codCopia = request.getParameter("copia-"+i+"-"+j);                   
                    Copia c = new Copia(codCopia,Copia.STATUS_NON_PRENOTATO, Copia.DISPONIBILE_SI);
                    p.addCopia(c);
                }
                
                posizioni.add(p);
                
            }
                        
          
            
            if(manager.aggiungiLibroBiblioteca(biblio.getIsil(), book, posizioni)){
                request.setAttribute("libro",book);
                request.setAttribute("message", "correct");
                request.getRequestDispatcher("visualizza-libro.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "error");
                request.getRequestDispatcher("aggiunta-copie.jsp").forward(request, response);
            }

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
