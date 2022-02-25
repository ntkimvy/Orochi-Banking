/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.MessageDB;
import model.entity.Customer;
import model.entity.Message;

/**
 *
 * @author ThanhNhan
 */
public class LoadUMess extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoadUMess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadUMess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
//        String userName = request.getParameter("userName");
        Customer c = (Customer) request.getSession().getAttribute("cust");
        for (Message m : MessageDB.getMessage(c.getUserName())) {
            if (m.isTypeID()) {
                out.println("<div class=\"right-msg-full\">\n"
                        + "                <div class=\"right-msg\">\n"
                        + "                    <div class=\"right-msg-bubble\">\n"
                        + "                        <div class=\"msg-text\">" + m.getMessage()
                        + "                        </div>\n"
                        + "                        <div class=\"msg-info-time\">" + m.getTime() + "</div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>");
            } else {
                out.println("<div class=\"left-msg-full\">\n"
                        + "                <div class=\"msg-info-name\"><p style='padding: 13px 0'>" + m.getStaffName().trim() + "</p></div>\n"
                        + "                <div class=\"left-msg\">\n"
                        + "                    <div class=\"msg-img\">\n"
                        + "                        <img class=\"msg-img\" src=\"./assets/imgs/Logo.png\">\n"
                        + "                    </div>\n"
                        + "                    <div class=\"left-msg-bubble\">\n"
                        + "                        <div class=\"msg-text\">\n"
                        + m.getMessage()
                        + "                        </div>\n"
                        + "                        <div class=\"msg-info-time\">" + m.getTime() + "</div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>");
            }
        }
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
