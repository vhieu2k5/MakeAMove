
package Make_A_Move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DAOLogin {
    
    public int addInfo(SignUpTxt t){
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
    
    public int loginUser(String userName, String Password){
        String sql = "SELECT ID FROM login_list WHERE userName = ? AND passWord = ?";
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tour_pm", "root", "@trinhkien1211"
            );
            PreparedStatement pts = conn.prepareStatement(sql)
            ){
                pts.setString(1, userName);
                pts.setString(2, Password);

                ResultSet rs = pts.executeQuery();

                if(rs.next()){
                    return rs.getInt("ID");
                }
            }
        catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
