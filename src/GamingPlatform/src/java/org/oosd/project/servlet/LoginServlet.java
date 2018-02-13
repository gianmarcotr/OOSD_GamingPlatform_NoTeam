package org.oosd.project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;

 
import org.oosd.project.beans.User;
import org.oosd.project.utils.MyUtils;



@WebServlet(urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    public LoginServlet(){
        super();
    }

    //GET MOSTRA LOGIN PAGE
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //INOLTRO A /WEB-INF/views/loginView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
     }

    //POST ESEGUE LOGIN Quando l'utente inserisce userName e password e clicca Submit
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = null;
        boolean hasError = false;
        String errorString = null;
       
        if(userName == null || password == null  || userName.length() == 0 || password.length() == 0){
            hasError = true;
            errorString = "Request username and password!";
        }else{
            Connection conn = MyUtils.getStoredConnection(request);
            try{
                user = User.findUser(conn, userName, password);
                if (user == null){
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            }catch(SQLException e){
                    e.printStackTrace();
                    hasError = true;
                   errorString = e.getMessage();
                }
        }
        //if error, inoltro a WEN-NF/views/loginView.jsp
        if(hasError){
            //Salvo le informazioni nella request attribute prima di inoltrare
            request.setAttribute("errorString", errorString);
            //Inoltro a /WEB-INF/vies/loginView.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }
       
        //if no error
        //Memorizzo le informazioni in Sessione
        //e reindirizzo alla profile page dell'utente
        else{
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            //redirect to userInfo page
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
