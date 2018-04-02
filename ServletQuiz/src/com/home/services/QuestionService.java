package com.home.services;

import com.home.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    private DatabaseConnection db=new DatabaseConnection();

    public List<Question> getAll() throws Exception {
        List<Question> questionList=new ArrayList<>();
        Question q=null;
        Connection conn=new DatabaseConnection().getDatabaseConnection();
        String sql="SELECT * from question";
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            q=new Question();
            int id=rs.getInt("id");
            String question= rs.getString("question");
          String option1 = rs.getString("option1");
          String option2=  rs.getString("option2");
            String option3=   rs.getString("option3");
            String option4=  rs.getString("option4");
          String correctAnswer= rs.getString("correct_answer");
          String category= rs.getString("category");
            q.setId(id);
            q.setQuestion(question);
            q.setOption1(option1);
            q.setOption2(option2);
            q.setOption3(option3);
            q.setOption4(option4);
            q.setCorrectAnswer(correctAnswer);
            q.setCategory(category);

            questionList.add(q);
        }
        return questionList;

    }

    public Question getQuestionById(String id) throws Exception {
        Question q=null;
        Connection conn=new DatabaseConnection().getDatabaseConnection();
                String sql="SELECT * FROM question where id=?";
        PreparedStatement stmt=null;

        stmt=conn.prepareStatement(sql);
        stmt.setString(1,id);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            q=new Question();
            q.setId(rs.getInt("id"));
           q.setQuestion(rs.getString("question"));

            q.setOption1(rs.getString("option1"));
            q.setOption2(rs.getString("option2"));
            q.setOption3(rs.getString("option3"));
            q.setOption4(rs.getString("option4"));

            q.setCorrectAnswer(rs.getString("correct_answer"));
            q.setCategory(rs.getString("category"));


        }
        return q;
    }
}
