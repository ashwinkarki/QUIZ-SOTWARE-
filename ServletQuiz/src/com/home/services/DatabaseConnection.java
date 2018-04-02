package com.home.services;

import com.home.model.Question;
import com.home.model.Score;
import com.home.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    public Connection getDatabaseConnection() throws SQLException, ClassNotFoundException {
        final String URL="jdbc:mysql://localhost:3306/squiz";
        final String USERNAME="root";
        final String PASSWORD="";
        Connection conn=null;
        Class.forName("com.mysql.jdbc.Driver");
      conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
      return  conn;
    }

    public int insert(Question q) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO question(question,option1,option2,option3,option4,correct_answer,category) VALUES (?,?,?,?,?,?,?)";
     Connection conn=getDatabaseConnection();
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        stmt.setString(1,q.getQuestion());
        stmt.setString(2,q.getOption1());
        stmt.setString(3,q.getOption2());
        stmt.setString(4,q.getOption3());
        stmt.setString(5,q.getOption4());
        stmt.setString(6,q.getCorrectAnswer());
        stmt.setString(7,q.getCategory());

        int result=stmt.executeUpdate();
        return result;

    }

    public void insertUser(User u) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO user(name,email,password) VALUES (?,?,?)";
        Connection conn=getDatabaseConnection();
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        stmt.setString(1,u.getName());
        stmt.setString(2,u.getEmail());
        stmt.setString(3,u.getPassword());

        int result=stmt.executeUpdate();
    }

    public Question getSingleQuestion(String catName) throws SQLException, ClassNotFoundException {
        Question q=null;
        Connection conn=getDatabaseConnection();
        String sql="SELECT * from question WHERE category=? LIMIT 1 ";
        PreparedStatement stmt=null;

        stmt=conn.prepareStatement(sql);
        stmt.setString(1,catName);
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

    public User checkUser(String email, String password) throws SQLException, ClassNotFoundException {
        User u=null;
        Connection conn=getDatabaseConnection();
        String sql="SELECT * FROM user where email=? AND password=?";
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        stmt.setString(1,email);
        stmt.setString(2,password);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
          u=new User();
          u.setId(rs.getInt("id"));
          u.setEmail(rs.getString("email"));

        }
        return u;
    }

    public String findRoleByUserId(int id) throws SQLException, ClassNotFoundException  {
        Connection conn=getDatabaseConnection();
        String sql="SELECT r.authority from role AS r INNER JOIN user_role AS ur on r.id=ur.role_id WHERE ur.user_id=?";
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        String role=null;
        stmt.setInt(1,id);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
        role=rs.getString("authority");
        }

        return  role;
    }

    public void saveScore(int userId, String qId, int score) throws SQLException, ClassNotFoundException {
        Connection conn=getDatabaseConnection();
        String sql="INSERT INTO score(user_id,question_id,score) VALUES (?,?,?)";
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        stmt.setInt(1,userId);
        stmt.setString(2,qId);
        stmt.setInt(3,score);

        int result=stmt.executeUpdate();
    }

    public List<Score> getQuestionAlreadyPlayedbyUser(int id) throws SQLException, ClassNotFoundException {
        List<Score> scoreList=new ArrayList<>();
        Score s=null;
        Connection conn=getDatabaseConnection();
        String sql="SELECT * FROM score where user_id=?";
        PreparedStatement stmt=null;
        stmt= conn.prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            s=new Score();
            s.setId(rs.getInt("id"));
            s.setUserId(rs.getInt("user_id"));
            s.setQuestionId(rs.getInt("question_id"));
            s.setScore(rs.getInt("score"));
            scoreList.add(s);
        }
       return scoreList;
    }

    public Question getQuestionNotYetPlayed(List<Score> scoreList,String catName) throws SQLException, ClassNotFoundException  {
    Question q=null;
    Connection conn=getDatabaseConnection();
    String param="";
    for(int i=0;i<scoreList.size();i++){
        param+="?";
        if(scoreList.size()-1==i){
            break;
        }
        else{
            param+=",";
        }
    }
    String sql="SELECT * FROM question WHERE category=? AND id not in ("+param+") limit 1";

    PreparedStatement stmt=null;
    stmt=conn.prepareStatement(sql);
        stmt.setString(1,catName);
    int index=2;
    for(Score s:scoreList){
        stmt.setInt(index,s.getQuestionId());
        index++;
    }

        System.out.println(sql);
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

    public List<Score> getAllScoreOfCurrentuser(int userId) throws SQLException, ClassNotFoundException  {
        List<Score> scoreList=new ArrayList<>();
        Connection conn=getDatabaseConnection();
        Score s=null;
        String sql="SELECT * FROM score where user_id=?";
        PreparedStatement stmt=null;

        stmt=conn.prepareStatement(sql);
        stmt.setInt(1,userId);

        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            s=new Score();
            s.setId(rs.getInt("id"));
            s.setQuestionId(rs.getInt("question_id"));
            s.setUserId(rs.getInt("user_id"));
            s.setScore(rs.getInt("score"));
            scoreList.add(s);
        }
        return scoreList;
    }

    public void delete() throws SQLException, ClassNotFoundException {
        Connection conn=getDatabaseConnection();
        String sql="DELETE FROM score";
        PreparedStatement stmt=null;

        stmt=conn.prepareStatement(sql);
        int result=stmt.executeUpdate();

    }

    public List<String> getCategory() throws SQLException, ClassNotFoundException {
       List<String> catList=new ArrayList<>();
        Question q=null;
        Connection conn=getDatabaseConnection();
        String sql="SELECT DISTINCT category FROM question";
        PreparedStatement stmt=null;
        stmt=conn.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){

           catList.add(rs.getString("category"));
        }
        return catList;
    }
}
