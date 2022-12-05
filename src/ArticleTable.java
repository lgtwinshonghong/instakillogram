import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleTable extends Table{
    public ArticleTable(Connection con) { super(con); }
    public ArticleTable(Connection con, String tName) { super(con, tName); }

    public void insertArticle(String content) {
        String articleAddressS = "./Articles/" + Integer.toString(cnt) + ".txt";
        String commentAddressS = "./Comment/" + Integer.toString(cnt) + ".txt";
        File articleFile = new File(articleAddressS);
        File commentFile = new File(commentAddressS);
        executeUpdate("insert into " + tableName + " values ( " + Integer.toString(cnt) + ", 0, '" + commentAddressS + "', '" + articleAddressS + "'); ");

        try {
            commentFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(articleFile, false));
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cnt++;
    }

    public void deleteArticle(int id) {
        executeUpdate("delete from " + tableName + " where id = " + id + ";");
    }

    // read all lines in txt file
    public StringBuilder readArticle(int id) {
        ResultSet rs = executeQuery("select address from " + tableName + " where id = " + id + ";");
        StringBuilder content = new StringBuilder();
        try {
            if(rs.next()) {
                String addressS = rs.getString(1);
                File addressFile = new File(addressS);
                BufferedReader reader = new BufferedReader(new FileReader(addressFile));

                String line = "";
                while((line = reader.readLine()) != null) {
                    content.append(line + "\n");
                }
                reader.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public void like(int id) {
        int num = 0;
        ResultSet rs = executeQuery("select likes from " + tableName + " where id = " + Integer.toString(id) + ";");
        try {
            if(rs.next()) {
                num = rs.getInt(1);
            }
            executeUpdate("update " + tableName + " set likes = " + Integer.toString(num + 1) + " where id = " + Integer.toString(id) + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void insertAUser(String ID, String pw, String name, String nickname) { //이어 쓰기https://hianna.tistory.com/588
        String followerS = "./Follower/" + Integer.toString(cnt) + ".txt";
        String followingS = "./Following/" + Integer.toString(cnt) + ".txt";
        File followerF = new File(followerS);
        File followingF = new File(followingS);

        try {
            new FileOutputStream(followerF, false);
            new FileOutputStream(followingF, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int insertRow = executeUpdate("insert into " + tableName + " values (" + cnt + ", '" + ID + "', '" + pw + "', '" + name + "', '" + nickname + "', '" + followerS + "', '" + followingS + "' );");
        cnt++;
    }*/
}
