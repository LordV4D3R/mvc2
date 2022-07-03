/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.controller;

import an.utils.DBHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import an.registration.RegistrationDAO;
import an.registration.RegistrationDTO;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";//nhung thanh phan lien
    //quan toi chuyen URL tuyet doi ko dc viet trong code ma phai khai bang bien
    //hang. Ten bien hang bat buoc phai dung chu in het 
    private final String SEARCH_PAGE = "searchpage.html";

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = INVALID_PAGE;

        try {

            //1. call Model/DAO
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);
            //- new DAO obj, then call method on DAO obj
               
            //2. process result
            if (result != null) {
                url = SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                //add cookie to client using reqObj
                //tạo cookie
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 3);
//                response.addCookie(cookie);
            }

        } catch (NamingException ex) {
            log("ProcessRequestController _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("ProcessRequestController _ SQL _ " + ex.getMessage());
        } finally {
//            response.sendRedirect(url); dòng này sẽ làm hiển thị đường truyền
//sendRedirect là đưa thành phần vào trong resobj, resobj mới render dữ liệu
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request,response);
            response.sendRedirect(url);
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
