/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.controller;

import an.registration.RegistrationDAO;
import an.registration.RegistrationDTO;
import an.registration.RegistrationInsertError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AN
 */
@WebServlet(name = "CreateNewAccountController", urlPatterns = {"/CreateNewAccountController"})
public class CreateNewAccountController extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "createAccount.jsp";
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
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        

        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundErr = false;
        String url = ERROR_PAGE;
        try {
            //1. check all user errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundErr = true;
                errors.setFullNameLengthErr("Username is required from 6 to 20 chars ");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setFullNameLengthErr("Password is required from 6 to 20 chars ");
            }
            if (confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setFullNameLengthErr("Those passwords didn’t match. Try again ");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr("Full name is required from 2 to 50 chars ");
            }
            
            if (foundErr) {
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                //insert to db - call dao 
                RegistrationDTO dto
                        = new RegistrationDTO(username, password, fullname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(dto);
                
                if(result){
                    //. transfer to Login page
                    url = LOGIN_PAGE;
                }
            }
 
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountController _ SQL _ " + msg);
            if(msg.contains("duplicate")){
                errors.setUsernameDuplicate(username + " existed!!!");
                request.setAttribute("INSERT_ERRORS", ex);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountController _ Naming _ " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
