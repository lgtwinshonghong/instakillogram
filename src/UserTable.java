import java.io.*;
import java.sql.*;

public class UserTable extends Table{
    public UserTable(Connection con) {
        super(con);
    }
    public UserTable(Connection con, String tName) {
        super(con, tName);
    }

    public void deleteTxtFile(String fileName) {
        File f = new File(fileName);
        if(f.exists()) {
            if(f.delete()) System.out.println(fileName + " Delete Success!");
            else System.out.println(fileName + " Delete Fail");
        }
    }

    public int returnUserNumID(String id) {
        ResultSet rs = executeQuery("select num from " + tableName + " where id = '" + id + "';");
        int numID = 0;
        try {
            if(rs.next()) {
                numID = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numID;
    }
    public boolean insertAUser(String ID, String pw, String name, String nickname) {
        if(returnUserNumID(ID) == 0) {
            try {
                makeTable("user" + Integer.toString(cnt) + "_following", "(id int not null, primary key(id) )");
                makeTable("user" + Integer.toString(cnt) + "_follower", "(id int not null, primary key(id) )");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int insertRow = executeUpdate("insert into " + tableName + " values (" + cnt + ", '" + ID + "', '" + pw + "', '" + name + "', '" + nickname + "' );");
            cnt++;
            return true;
        }
        return false;
    }

    public boolean isThereNumID(String tableName, int n) {
        ResultSet rs = executeQuery("select count(*) from " + tableName + " where id = " + Integer.toString(n) + ";");
        try {
            if(rs.next()) {
                int count = rs.getInt(1);
                if(count == 0) return false;
                else return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // isFollow - false : unfollow, true : follow
    public void follow(boolean isFollow, int meNumID, int someoneNumID) {
        String followingTableName = "user" + Integer.toString(meNumID) + "_following";
        String followerTableName = "user" + Integer.toString(someoneNumID) + "_follower";
        if(isFollow) {
            if(!isThereNumID(followingTableName, someoneNumID)) { // check duplicate data
                int insertRow = executeUpdate("insert into " + followingTableName + " values (" + someoneNumID + ");");
                insertRow = executeUpdate("insert into " + followerTableName + " values (" + meNumID + ");");
            }
        }
        else { //delete tuple(data) https://m.blog.naver.com/kee5004/60017447664
            if(isThereNumID(followingTableName, someoneNumID)) {
                int deleteRow = executeUpdate("delete from " + followingTableName + " where id = " + Integer.toString(someoneNumID) + ";");
                deleteRow = executeUpdate("delete from " + followerTableName + " where id = " + Integer.toString(meNumID) + ";");
            }
        }
    }

    public int loginReturnNum(String id, String pw) throws SQLException {
        ResultSet rs = executeQuery("select num from " + tableName + " where id = '" + id + "' and password = '" + pw + "';");
        int numID = 0;
        if(rs.next()) {
            String s = rs.getString("num");
            numID = Integer.parseInt(s);
            return numID;
        }
        else return 0;
        //https://allg.tistory.com/21
    }

    public int getCountFollower(int numID) {
        return returnTableLength("user" + Integer.toString(numID) + "_follower");
    }
    public int getCountFollowing(int numID) {
        return returnTableLength("user" + Integer.toString(numID) + "_following");
    }
    public String getUserNickname(int numID) {
        ResultSet rs = executeQuery("select nickname from " + tableName + " where num = " + Integer.toString(numID) + ";");
        String res = "";
        try {
            if(rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String selectUserNicknameString(int id) throws SQLException {
        ResultSet rs = executeQuery("select nickname from usertable where num = " + id + ";");
        String s = "";
        if(rs.next()) s = rs.getString("nickname");
        return s;
    }

}
