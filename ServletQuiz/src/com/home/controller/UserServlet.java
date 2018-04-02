package com.home.controller;

import com.home.model.User;
import com.home.services.DatabaseConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = {"/createuser","/addUser","/dashboard","/logout"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url=request.getParameter("url");
        System.out.println("url = " + url);

        if(url!=null) {
            if (url.equalsIgnoreCase("create-users")) {
                String userName = request.getParameter("userName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                User u = new User();
                u.setName(userName);
                u.setEmail(email);
                u.setPassword(password);
                try {
                    new DatabaseConnection().insertUser(u);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            else if (url.equalsIgnoreCase("login")|| url.isEmpty()) {

                String email = request.getParameter("email");
                String password = request.getParameter("password");
                try {
                    User user = new DatabaseConnection().checkUser(email, password);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("usession", user);
                        String role = new DatabaseConnection().findRoleByUserId(user.getId());
                        request.setAttribute("r", role);
                        List<String> catList = new DatabaseConnection().getCategory();
                        request.setAttribute("c", catList);
                        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }else{
            HttpSession csession=request.getSession();
            User u=(User) csession.getAttribute("usession");
            if(u==null){
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            else
            {
                request.getRequestDispatcher("dashboard.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside do get user");
        String url=request.getParameter("url");

        System.out.println("url = " + url);
        if(url!=null){


            if(url.equalsIgnoreCase("create-user")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/user/create.jsp");
                dispatcher.forward(request, response);
            }
            else if(url.equalsIgnoreCase("logout")){
                HttpSession session=request.getSession();
                session.invalidate();
                try {
                    new DatabaseConnection().delete();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }
        else{
            HttpSession csession=request.getSession();
            User u=(User) csession.getAttribute("usession");
            if(u==null){
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            else
            {
                request.getRequestDispatcher("dashboard.jsp").forward(request,response);
            }
        }
    }
}
