/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.controller;

import an.registration.RegistrationDAO;
import an.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tranq
 */
@WebServlet(name = "ProcessRequestController", urlPatterns = {"/ProcessRequestController"})
public class ProcessRequestController extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE ="search.jsp";

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

        String url = LOGIN_PAGE;
        try {
            //1. get cookie from request
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {

                //2. traverse toàn bộ cookies để check authientication
                for (Cookie cookie : cookies) {

                    //3. get username and password from name value 
                    //getName, getValue lấy thằng cuối cùng
                    //for trên để do từng từng thằng
                    String username = cookie.getName();
                    String password = cookie.getValue();

                    //4. call DAO để check authentication
                    RegistrationDAO dao = new RegistrationDAO();
                    RegistrationDTO result = dao.checkLogin(username, password);
                    
                    if(result != null){
                        url = SEARCH_PAGE;
                        String fullname = result.getLastname();
                        HttpSession session = request.getSession();
                        session.setAttribute("USERNAME", username);
                        session.setAttribute("FULLNAME", fullname);
                        
                    }//end authentication is successfully checked
                }// end for traverse cookies 
            }//end cookies is existed
        } catch (NamingException ex) {
            log(ex.getMessage());
            request.setAttribute("Error", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            log(ex.getMessage());
            request.setAttribute("Error", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } finally {
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
