
package make_a_move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DAOHistory {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
    
    public boolean saveGameRes(int userID, String userName, String result){
        String sql = "INSERT INTO history_game(userID, UserName, Result, Date) VALUES (?, ?, ?, ?)";
        try(
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tour_pm", "root", "@trinhkien1211"
            );
            PreparedStatement ps = conn.prepareStatement(sql);
            
        ){
                ps.setInt(1, userID);
                ps.setString(2, userName);
                ps.setString(3, result);
                String now = LocalDateTime.now().format(FORMATTER);
                ps.setString(4, now);
                return ps.executeUpdate()>0;
        }catch(SQLException e){
            System.err.println("Đã lưu kết quả: " + e.getMessage());
            return false;
        }
    }
        
    public List<String> getHistoryTable(){
        List<String> history = new ArrayList<>();
        String sql = "SELECT * FROM history_game";
        try(
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tour_pm", "root", "@trinhkien1211"
            );
            PreparedStatement ps = conn.prepareStatement(sql);
            
        ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String userName = rs.getString("UserName");
                String result = rs.getString("Result");
                String date = rs.getString("Date");
                history.add(id + "-" + userName + "-" + date + " - " + result);
            }
        }catch(SQLException e){
           System.err.println("Lỗi đọc lịch sử game: " + e.getMessage()); 
        }
        return history;
    }
}
