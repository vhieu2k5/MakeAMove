package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOLogin {
    public int addInfo(SignUp t){
        int result = 0;
        String sqlCheck = "SELECT * FROM login_list WHERE userName = ?";
        String sqlInsert = """
                INSERT INTO login_list(userName, passWord)
                VALUES(?, ?)
            """;
        
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tour_pm", "root", "@trinhkien1211"
            );
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck); 
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
        ){
            psCheck.setString(1, t.getUserName());
            ResultSet rs = psCheck.executeQuery();
            
            if(!rs.next()){
                psInsert.setString(1, t.getUserName());
                psInsert.setString(2, t.getPassWord());
               
                int rows = psInsert.executeUpdate();
                if(rows > 0) result = 10;
                
            }
            else result = 3;     
         } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối hoặc thao tác với CSDL:");
            e.printStackTrace();
        }
        return result;
    }
    
    public void Login(){
        
    }
}

