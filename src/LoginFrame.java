import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class LoginFrame implements ActionListener {
    JTextField id, password;
    UserTable user1;
    ArticleTable article1;
    int userNumID;
    JFrame jf;

    public LoginFrame(UserTable user1, ArticleTable article1) {
        this.user1 = user1;
        this.article1 = article1;
    }

    public void createLoginFrame() {
        jf = new JFrame();
        jf.setSize(300, 300);
        jf.setLocation(500, 300);
        jf.setTitle("Insta Login");
        jf.setLayout(null);

        String[] label_name = {"ID", "password"};

        JLabel jl = new JLabel(label_name[0]);
        jl.setSize(80, 30);
        jl.setLocation(30, 30);
        jl.setHorizontalAlignment(JLabel.LEFT);

        jf.add(jl);

        jl = new JLabel(label_name[1]);
        jl.setSize(80, 30);
        jl.setLocation(30, 60);
        jl.setHorizontalAlignment(JLabel.LEFT);

        jf.add(jl);

        id = new JTextField();
        id.setSize(130, 30);
        id.setLocation(110, 30);

        jf.add(id);

        password = new JTextField();
        password.setSize(130, 30);
        password.setLocation(110, 60);

        jf.add(password);

        String btn_name = "Login";
        JButton jb = new JButton(btn_name);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(100, 200);

        jf.add(jb);

        String btn_sign = "Sign Up";
        jb = new JButton(btn_sign);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(100, 225);

        jf.add(jb);

        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Login") {
            String input_id = id.getText();
            String input_password = password.getText();

            try {
                userNumID = user1.loginReturnNum(input_id, input_password);
                if(userNumID == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input!!");
                }
                else { //로그인 성공!
                    JOptionPane.showMessageDialog(null, "Welcome!!");
                    MainPage mainPage = new MainPage(user1, article1, userNumID);
                    mainPage.createMainPage();
                    jf.dispose();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            /*JOptionPane.showMessageDialog(null, "Welcome!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid ID!!");
            }
        }*/
        }
        if (e.getActionCommand() == "Sign Up") {
            SignupFrame usr = new SignupFrame(user1, article1);
            usr.createSignupFrame();
        }
    }
}