/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.controller;

import an.registration.RegistrationDAO;
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
 * @author tranq
 */
@WebServlet(name = "DeleteAccountController", urlPatterns = {"/DeleteAccountController"})
public class DeleteAccountController extends HttpServlet {
    private final String ERROR_PAGE = "errors.html";
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
        
        String url = ERROR_PAGE;
        String username = request.getParameter("username"); //pk (primary key)
        String searchValue = request.getParameter("lastSearchValue");
        
        try{
            //1. call DAO
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.deleteAccount(username);
            
            if(result){
                //gọi lại chức năng trc đó (search) bằng cách url rewriting
                url = "MainServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }//end if delete 
        }catch(NamingException ex){
            log("ProcessRequestController _ Naming _ " + ex.getMessage());
        }catch(SQLException ex){
            log("ProcessRequestController _ SQL _ " + ex.getMessage());
        }finally{
            //xài reqdis thì sẽ bị dư paramater (vì reqdis khi forward sẽ duy trì
            //obj nên parameter sẽ bị trùng (tạo thành mảng ko có thứ tự)-> nó 
            //sẽ bốc thằng delete hay search ?
            
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
