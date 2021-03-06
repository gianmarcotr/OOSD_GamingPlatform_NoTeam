/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oosd.project.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.oosd.project.beans.Owners;

import org.oosd.project.beans.User;

public class MyUtils {
    
    public static final String ATT_NAME_CONNECTION="ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    
    //Store connection objects in request attriubute
    // (informazion stored only exist during requests)
    
    public static void storeConnection(ServletRequest request, Connection conn){
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
    
    //Get the Connection object has neem stored in attribute of the request
     public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    //Store user info in Session
     public static void storeLoginedUser(HttpSession session, User loginedUser){
         session.setAttribute("loginedUser", loginedUser);
     }
     public static void storeLoginedOwner(HttpSession session, Owners loginedOwner){
         session.setAttribute("loginedOwner", loginedOwner);
     }
     
     
     public static void deleteLoginedUser(HttpSession session){
         session.removeAttribute("loginedUser");
     }
     public static void deleteLoginedOwner(HttpSession session){
         session.removeAttribute("loginedOwner");
     }
     
     
       // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }
    public static Owners getLoginedOwner(HttpSession session) {
        Owners loginedOwner = (Owners) session.getAttribute("loginedOwner");
        return loginedOwner;
    }
    
  
    
   
    
    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }
    
     public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
     
    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
