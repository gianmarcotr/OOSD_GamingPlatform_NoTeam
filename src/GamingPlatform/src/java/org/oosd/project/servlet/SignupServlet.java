package org.oosd.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.oosd.project.beans.User;
import org.oosd.project.utils.MyUtils;

/**
 *
 * @author gimmi
 */
@WebServlet(urlPatterns={"/signup"})
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            String errorString;
            String userName = request.getParameter("userName");
            //Controllo se il nome utente è già presente nel sistema
            User userCheck = User.findUser(conn, userName);
            if(userCheck!= null){
                errorString = "UserName già in uso";
                request.setAttribute("errorString", errorString);
                //Redirect alla pagina di registrazione
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
                dispatcher.forward(request, response);
            }
            
            String password = request.getParameter("password");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            User user = new User(userName, nome, cognome, 0, 0, 0, 0, password, 1000);
            User.insertUser(conn, user);
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            //redirect to userInfo page
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (SQLException ex) {
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
