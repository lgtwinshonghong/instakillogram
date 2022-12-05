import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class ProfileFrame implements ActionListener {

    UserTable user1;
    int userNumID;
    int u;
    public ProfileFrame(UserTable user, int id) {
        user1 = user;
        userNumID = id;
    }

    JFrame jf;
    public void createProfileFrame() {
        jf = new JFrame();
        jf.setSize(300, 600);
        jf.setLocation(500, 300);
        jf.setTitle("User profile");
        jf.setLayout(null);


        JButton jb = new JButton("Follower");
        jb.setSize(100, 30);
        jb.addActionListener(this);
        jb.setLocation(40, 30);

        jf.add(jb);

        jb = new JButton("Following");
        jb.setSize(100, 30);
        jb.addActionListener(this);
        jb.setLocation(150, 30);

        jf.add(jb);

        ImageIcon newbacl = new ImageIcon("./image/back.jpg");
        Image backimg = newbacl.getImage();
        Image updateback = backimg.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon resizeback = new ImageIcon(updateback);
        JButton back_main = new JButton(resizeback);
        back_main.setPreferredSize(new Dimension(40, 40));;

        back_main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                //<-----   ----->
                MainPage mp = new MainPage(user1, userNumID);
                try {
                    mp.createMainPage();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //new main_gasta();
            }
        });


        back_main.setSize(40, 40);
        back_main.addActionListener(this);
        back_main.setLocation(122, 480);

        jf.add(back_main);

        int n = user1.getCountFollower(userNumID);
        JLabel jl = new JLabel(Integer.toString(n)); //팔로워 수
        jl.setSize(40, 30);
        jl.setLocation(65, 50);
        jl.setHorizontalAlignment(JLabel.CENTER);

        jf.add(jl);

        n = user1.getCountFollowing(userNumID);
        jl = new JLabel(Integer.toString(n)); //팔로잉 수
        jl.setSize(40, 30);
        jl.setLocation(175, 50);
        jl.setHorizontalAlignment(JLabel.CENTER);

        jf.add(jl);

        JTextField jt = new JTextField("sadasdas");
        jt.setSize(280, 400);
        jt.setLocation(10, 80);
        jt.setHorizontalAlignment(JLabel.CENTER);

        jf.add(jt);

        jf.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Follower") {
            JOptionPane.showMessageDialog(null, "List of followers");
        }
        if (e.getActionCommand() == "Following") {
            JOptionPane.showMessageDialog(null, "List of Followings!!");
        }
        if(e.getActionCommand() == "<-")// 뒤로가기 버튼 누르면 메인 화면
        {
            jf.setVisible(false);
            //<-----   ----->
            MainPage mp = new MainPage(user1, userNumID);
            try {
                mp.createMainPage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            //jf.dispose();
            //new main_gasta();
        }
    }
}