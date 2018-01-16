/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.DAO.AccountDAO;
import core.DAO.AdminDAO;
import core.DAO.BibliotecaDAO;
import core.DAO.BibliotecarioDAO;
import core.DAO.PersonaDAO;
import core.entities.Account;
import core.entities.Persona;
import core.managers.ManagerAccount;
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
 * @author salva
 */
@WebServlet(name = "ModificaPasswordServlet", urlPatterns = {"/profilo/modifica-password"})
public class ModificaPasswordServlet extends HttpServlet {

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

        ManagerAccount manager = new ManagerAccount();
        AccountDAO accountDAO = new AccountDAO();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account account = accountDAO.doRetriveById(email);
        account.setPassword(password);

        manager.modificaPassword(email, password);

        request.getSession().setAttribute("modificaPass", "true");

        if (request.getParameter("tipoUtente").equalsIgnoreCase("persona")) {

            if ((request.getSession().getAttribute("persona")) != null) {
                request.getSession().removeAttribute("persona");
            }

            request.getSession().setAttribute("persona", (new PersonaDAO().doRetriveByEmail(email)));

        } else if (request.getParameter("tipoUtente").equalsIgnoreCase("admin")) {

            if ((request.getSession().getAttribute("admin")) != null) {
                request.getSession().removeAttribute("admin");
            }

            request.getSession().setAttribute("admin", (new AdminDAO().doRetriveByEmail(email)));

        } else if (request.getParameter("tipoUtente").equalsIgnoreCase("bibliotecario")) {

            if ((request.getSession().getAttribute("bibliotecario")) != null) {
                request.getSession().removeAttribute("bibliotecario");
            }

            request.getSession().setAttribute("bibliotecario", (new BibliotecarioDAO().doRetriveByEmail(email)));
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/skeleton-pages/index.jsp");
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
