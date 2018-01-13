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
import core.entities.Persona;
import core.entities.Posizione;
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

        Bibliotecario bibliotecario = (Bibliotecario) request.getSession().getAttribute("bibliotecario");
        String isbn = (String) request.getParameter("isbn");
        String isil = (String) request.getParameter("isil");
        String message;
        
        //String test per bibliotecario collegato
        isil = "IT-321";

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAOStub();
        PosizioneDAO posizioneDAO = new PosizioneDAO();
        LibroDAO libroDAO = new LibroDAO();
        ManagerLibri managerLibri = new ManagerLibri(bibliotecaDAO, posizioneDAO, libroDAO);

        //ManagerLibri managerLibri = new ManagerLibri();
        Libro libro;
        libro = managerLibri.visualizzaLibro(isbn);

        if (libro == null) {
            message = "Nessun libro corrisponde all'ISBN ricevuto.";
        } else {
            message = "correct";
        }

        List<Posizione> posizioniTotali = new ArrayList<>();
        List<Copia> copieTotali = new ArrayList<>();
        
        if (isil == null) { //l'utente Bibliotecario non è collegato
            
            List<Biblioteca> biblioteche = bibliotecaDAO.doRetriveAll();
            
            for (Biblioteca b : biblioteche) {
                List<Posizione> pos = (List<Posizione>) managerLibri.visualizzaPosizioniLibro(isbn, b.getIsil());
                posizioniTotali.addAll(pos);
                //System.out.println("Aggiunte da: " + b.getIsil());
            }
            //System.out.println("Num posizioni:" + posizioniTotali.size());

            
            for (Posizione p : posizioniTotali) {
                List<Copia> copie = p.getCopie();
                for (Copia c : copie) {
                    if (c.getLibro().getIsbn().equals(isbn)) {
                        if (c.getStatus().equals("Non Prenotato")) {
                            copieTotali.add(c);
                        }
                    }
                }
            }
        }
        else { //l'utente Bibliotecario è collegato
            posizioniTotali = (List<Posizione>) managerLibri.visualizzaPosizioniLibro(isbn, isil);
            
            
            for (Posizione p : posizioniTotali) {
                List<Copia> copie = p.getCopie();
                for (Copia c : copie) {
                    if (c.getLibro().getIsbn().equals(isbn)) {
                        if (c.getStatus().equals("Non Prenotato")) {
                            copieTotali.add(c);
                        }
                    }
                }
            }
        }

        //System.out.println("Numero copie non prenotate per " + isbn + ": " + copieTotali.size());
        request.setAttribute("message", message);
        request.setAttribute("libro", libro);
        request.setAttribute("numCopieDisponibili", copieTotali.size());

        //valore per testing bibliotecario e persona loggati 
        /*
        Bibliotecario bibliotecario = new Bibliotecario();
        request.getSession().setAttribute("bibliotecario", bibliotecario);
         */
        Persona persona = new Persona();
        request.getSession().setAttribute("persona", persona);

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
