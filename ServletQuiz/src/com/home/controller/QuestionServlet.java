package com.home.controller;

import com.home.model.Question;
import com.home.model.Score;
import com.home.model.User;
import com.home.services.DatabaseConnection;
import com.home.services.QuestionService;

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

@WebServlet(name = "QuestionServlet",urlPatterns = {"/create","/save","/list-question","/edit","/delete","/playquiz","/play-quiz-next"})
public class QuestionServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String url=request.getParameter("url");
        if(url.equalsIgnoreCase("create-question")) {
            System.out.println("Inside do post");

            String question = request.getParameter("question");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");
            String correctAnswer = request.getParameter("correctAnswer");
            String category = request.getParameter("category");

            Question ques = new Question();
            ques.setQuestion(question);

            ques.setOption1(option1);
            ques.setOption2(option2);
            ques.setOption3(option3);
            ques.setOption4(option4);

            ques.setCorrectAnswer(correctAnswer);
            ques.setCategory(category);

            try {
                new DatabaseConnection().insert(ques);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(url.equalsIgnoreCase("play-n")){
            String categorySelected=request.getParameter("categoryName");
            System.out.println(categorySelected);
        String optionSelected=request.getParameter("option");
            System.out.println(optionSelected);
        String correctAnswer=request.getParameter("correctAnswer");
        String qId=request.getParameter("id");
            HttpSession session=request.getSession();
            User user= (User) session.getAttribute("usession");
        if(optionSelected.equalsIgnoreCase(correctAnswer)){
            try {
                new DatabaseConnection().saveScore(user.getId(),qId,5);
               // request.getRequestDispatcher("question/playquiz.jsp").forward(request,response);
                List<Score> scoreList=new DatabaseConnection().getQuestionAlreadyPlayedbyUser(user.getId());

                Question question=new DatabaseConnection().getQuestionNotYetPlayed(scoreList,categorySelected);

                if(question!=null){
                    request.setAttribute("q",question);
                    request.getRequestDispatcher("WEB-INF/question/playquiz.jsp").forward(request,response);
                }
                else{
                    List<Score> userScores=new DatabaseConnection().getAllScoreOfCurrentuser(user.getId());
                    request.setAttribute("s",userScores);
                    request.getRequestDispatcher("WEB-INF/question/score.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                new DatabaseConnection().saveScore(user.getId(),qId,0);
               // request.getRequestDispatcher("question/playquiz.jsp").forward(request,response);
                List<Score> scoreList=new DatabaseConnection().getQuestionAlreadyPlayedbyUser(user.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        }

       }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url=request.getParameter("url");
        String categoryName=request.getParameter("cat");
        if(url.equalsIgnoreCase("create")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/question/create.jsp");
            dispatcher.forward(request,response);
        }
        else if(url.equalsIgnoreCase("list-question")){
            try {
                List<Question> questionList=new QuestionService().getAll();
                if(questionList.size()>0){
                    request.setAttribute("qlist",questionList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/question/list.jsp");
            dispatcher.forward(request,response);
    }
    else if(url.equalsIgnoreCase("edit-question")){
        String id=request.getParameter("id");
        try {
            Question question = new QuestionService().getQuestionById(id);
            if(question!=null){
                request.setAttribute("q",question);
                RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/question/edit.jsp");
                dispatcher.forward(request,response);
            }
        }
        catch(Exception e){

        }
        }
        else if(url.equalsIgnoreCase("play-quiz") || url.equalsIgnoreCase("categoryName")){
            try {
                Question question=new DatabaseConnection().getSingleQuestion(categoryName);
                request.setAttribute("catName",categoryName);
                request.setAttribute("q",question);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("WEB-INF/question/playquiz.jsp").forward(request,response);
        }
       }}
