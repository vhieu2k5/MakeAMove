
package make_a_move;

public class HistoryTxt {
    public int ID;
    public String userName;
    public String result;
    public String date;

    public HistoryTxt(int ID, String userName, String result, String date) {
        this.ID = ID;
        this.userName = userName;
        this.result = result;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
