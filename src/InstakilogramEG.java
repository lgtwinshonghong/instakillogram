import java.sql.*;
import java.util.Scanner;

public class InstakilogramEG {

    static Connection con = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    private static void makeConnection(String url, String userName, String password) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            System.out.println(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //NOTE : 1. check connector
    // 2. check jdk
    public static void main(String args[]) throws SQLException, ClassNotFoundException {


        /*System.out.println("Enter the SQL server password:");
        Scanner kb = new Scanner(System.in);
        String pw = kb.nextLine(); // or create password String variable*/
        String pw = "092719";
        makeConnection("jdbc:mysql://localhost", "root", pw);
        System.out.println("connection!");

        InstakilogramDB DB1 = new InstakilogramDB(con);
        DB1.makeDB("instakilogramDB");
        System.out.println("create database!");

        UserTable user1 = new UserTable(con, "userTable");
        user1.makeTable("userTable", "(num int not null, ID varchar(16) not null, password varchar(16) not null, name varchar(16), nickname varchar(16), primary key(num, ID) )");
        System.out.println("create table!");


        ArticleTable article1 = new ArticleTable(con, "articleTable");
        article1.dropTable("articleTable");
        article1.makeTable("articleTable", "( id int not null, likes int, commentAddress varchar(64), address varchar(64), primary key(id) )");

        // test
        /*article1.insertArticle("melong\nbabo\najdafs dsaf .sd\ns");
        StringBuilder content = article1.readArticle(1);
        System.out.println(content);*/

        //user1.follow(true, 1, 2);
        //System.out.println(user1.getUserNickname(2) + "'s follower cnt : " + user1.getCountFollower(2));
        //System.out.println(user1.getUserNickname(1) + "'s following cnt : " + user1.getCountFollowing(1));


        LoginFrame f = new LoginFrame(user1, article1);
        f.createLoginFrame();
    }

}
