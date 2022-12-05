import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class DMFrame implements ActionListener {
    UserTable user1;
    int userNumID;

    public DMFrame(UserTable user, int id) {
        user1 = user;
        userNumID = id;
    }

    JFrame jf;
    JTextField jt;
    public void createDMFrame() {
        jf = new JFrame();
        jf.setSize(300, 400);
        jf.setLocation(500, 300);
        jf.setTitle("Direct Message");
        jf.setLayout(null);


        JButton jb = new JButton("Send");
        jb.setSize(85, 30);
        jb.addActionListener(this);
        jb.setLocation(10, 300);

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
        back_main.setLocation(219, 300);

        jf.add(back_main);

        JLabel jl = new JLabel("Message");
        jl.setSize(100, 30);
        jl.setLocation(10, 75);
        jl.setHorizontalAlignment(JLabel.LEFT);

        jf.add(jl);

        jt = new JTextField();
        jt.setSize(250, 200);
        jt.setLocation(10, 100);
        jt.setHorizontalAlignment(JLabel.CENTER);

        jf.add(jt);

        String[] name = {"wooyoung", "myeonson", "jonghyeon", "juhyeok"};
        JComboBox nameCombo = new JComboBox(name);
        nameCombo.setSize(140, 20);
        nameCombo.setLocation(65, 80);
        jf.add(nameCombo);

        jf.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Send")
        {
            JOptionPane.showMessageDialog(null, "Send message success!!");
        }
        if(e.getActionCommand() == "<-")
        {
            jf.setVisible(false);
            //<-----   ----->
            MainPage mp = new MainPage(user1, userNumID);
            try {
                mp.createMainPage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            //jf.dispose()
            //new main_gasta();
        }
    }
}