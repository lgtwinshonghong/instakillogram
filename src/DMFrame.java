import com.sun.tools.javac.Main;

import javax.swing.*;
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
        jb.setSize(100, 30);
        jb.addActionListener(this);
        jb.setLocation(205, 180);

        jf.add(jb);

        jb = new JButton("<-");
        jb.setSize(50, 30);
        jb.addActionListener(this);
        jb.setLocation(122, 0);

        jf.add(jb);

        JLabel jl = new JLabel("Message");
        jl.setSize(100, 30);
        jl.setLocation(10, 75);
        jl.setHorizontalAlignment(JLabel.LEFT);

        jf.add(jl);

        jt = new JTextField();
        jt.setSize(200, 200);
        jt.setLocation(10, 100);
        jt.setHorizontalAlignment(JLabel.CENTER);

        jf.add(jt);

        String[] name = {"wooyoung", "myeonson", "jonghyeon", "juhyeok"};
        JComboBox nameCombo = new JComboBox(name);
        nameCombo.setSize(140, 100);
        nameCombo.setLocation(65, 40);
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