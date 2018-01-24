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
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import core.managers.ManagerLibri;
import core.utils.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
@WebServlet(name = "CercaLibroServlet", urlPatterns = {"/libri/cerca-libro"})
public class CercaLibroServlet extends HttpServlet {

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
        String searchKey = request.getParameter("searchKey");
        String criterioName = request.getParameter("criterio");
        Collection<Libro> libri = new ArrayList<>();
        Bibliotecario bibliotecario = (Bibliotecario) request.getSession().getAttribute("bibliotecario");

        Criterio criterio = null;
        if (criterioName.equalsIgnoreCase("titolo")) {
            
            if (bibliotecario == null) {
                criterio = new CriterioPerTitolo(searchKey);
            } else {
                criterio = new CriterioPerTitoloBiblioteca(searchKey, bibliotecario.getBiblioteca());
                System.out.println("Sono una biblioteca");
            }
            
        } else if (criterioName.equalsIgnoreCase("autore")) {
            
            if (bibliotecario == null) {
                criterio = new CriterioPerAutore(searchKey);
            } else {
                criterio = new CriterioPerAutoreBiblioteca(searchKey, bibliotecario.getBiblioteca());
                System.out.println("Sono una biblioteca");
            }
            
        } else if (criterioName.equals("editore")) {
            
            if (bibliotecario == null) {
                criterio = new CriterioPerEditore(searchKey);
            } else {
                criterio = new CriterioPerEditoreBiblioteca(searchKey, bibliotecario.getBiblioteca());
                System.out.println("Sono una biblioteca");
            }
            
        } else if (criterioName.equals("isbn")) {
            
            if (bibliotecario == null) {
                criterio = new CriterioPerIsbn(searchKey);
            } else {
                criterio = new CriterioPerIsbnBiblioteca(searchKey, bibliotecario.getBiblioteca());
                System.out.println("Sono una biblioteca");
            }
            
        } else {
            //YOU SHOULD NOT BE HERE!
        }

        

        ManagerLibri managerLibri = new ManagerLibri();
        libri = managerLibri.cercaLibro(criterio);

        if (libri.isEmpty()) {
            message = "Nessun libro corrispondente al criterio inserito.";
        } else {
            message = "correct";
        }
//
//        List<Biblioteca> biblioteche = bibliotecaDAO.doRetriveAll();
//        List<Integer> disponibili = new ArrayList<Integer>();
//        for (Libro l : libri) {
//            List<Posizione> posizioniTotali = new ArrayList<>();
//
//            for (Biblioteca b : biblioteche) {
//                List<Posizione> pos = (List<Posizione>) managerLibri.visualizzaPosizioniLibro(l.getIsbn(), b.getIsil());
//                posizioniTotali.addAll(pos);
//                //System.out.println("Aggiunte da: " + b.getIsil());
//            }
//            //System.out.println("Num posizioni:" + posizioniTotali.size());
//
//            List<Copia> copieTotali = new ArrayList<>();
//            for (Posizione p : posizioniTotali) {
//                List<Copia> copie = p.getCopie();
//                for (Copia c : copie) {
//                    if (c.getLibro().getIsbn().equals(l.getIsbn())) {
//                        if (c.getStatus().equals("Non Prenotato")) {
//                            copieTotali.add(c);
//                        }
//                    }
//                }
//            }
//            if (copieTotali.size() == 0) {
//                disponibili.add(0);
//            } else {
//                disponibili.add(1);
//            }
//        }

        request.setAttribute("message", message);
        request.setAttribute("libri", libri);
//        request.setAttribute("disponibili", disponibili);

        RequestDispatcher dispatcher = request.getRequestDispatcher("cerca-libro.jsp");
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
