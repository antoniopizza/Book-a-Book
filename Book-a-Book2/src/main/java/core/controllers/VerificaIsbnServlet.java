/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controllers;

import core.entities.Libro;
import core.managers.ManagerLibri;
import core.utils.CriterioPerIsbn;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author manuel
 */
@WebServlet(name = "VerificaIsbnServlet", urlPatterns = {"/libri/verifica-isbn"})
public class VerificaIsbnServlet extends HttpServlet {

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
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        String isbn = request.getParameter("isbn");
        ManagerLibri manager = new ManagerLibri();
        Collection<Libro> b = manager.cercaLibro(new CriterioPerIsbn(isbn));
        String message;

       
        if (b.isEmpty()) {
            message = "empty";
        } else {
            message = "found";
           
        }
        out.print(creaJson(b, message));

    }

    private JSONObject creaJson(Collection<Libro> libri,String message) {
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        JSONArray array = new JSONArray();
        for (Libro b : libri) {
            JSONObject o = new JSONObject();
            o.put("isbn", b.getIsbn());
            o.put("titolo",b.getTitolo());
            o.put("editore",b.getEditore());
            o.put("data",b.getDataPubblicazione().get(GregorianCalendar.DAY_OF_MONTH)+"-"+
                        (b.getDataPubblicazione().get(GregorianCalendar.MONTH)+1)+"-"+
                        b.getDataPubblicazione().get(GregorianCalendar.YEAR));
            o.put("descrizione",b.getDescrizione());
            o.put("path_foto",b.getPathFoto());
            array.put(o);
        }
        obj.put("books",array);
        return obj;
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
