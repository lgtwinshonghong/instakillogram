import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ReplyFrame extends JFrame {
    UserTable user1;
    int userNumID;

    public ReplyFrame(UserTable user, int id) {
        user1 = user;
        userNumID = id;
    }

    public void createReplyFrame() {


        setSize(600,700);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        ImageIcon newbacl = new ImageIcon("./image/back.jpg");
        Image backimg = newbacl.getImage();
        Image updateback = backimg.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon resizeback = new ImageIcon(updateback);
        JButton back_main = new JButton(resizeback);
        back_main.setPreferredSize(new Dimension(40, 40));;

        back_main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
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


        JPanel back = new JPanel();
        back.add(back_main);
        back.setPreferredSize(new Dimension(600,30));

        //전체 댓글이 보이는 패널
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
        jp.setPreferredSize(new Dimension(600,510));

        JLabel jl = new JLabel();
        jl.setSize(100,450);
        jl.setLocation(0,50);
        jp.add(jl);
        JLabel jl1 = new JLabel();
        jl1.setSize(500,450);
        jl1.setLocation(100,50);
        jp.add(jl1);

        //댓글을 작성하는 패널
        JPanel jp1 = new JPanel();
        JLabel id = new JLabel("reply");



        jp1.setLayout(new BoxLayout(jp1,BoxLayout.X_AXIS));
        jp1.setPreferredSize(new Dimension(600,150));
        jp1.add(id);


        JButton upload = new JButton("등록");

        JTextArea ta = new JTextArea();
        jp1.add(ta);

        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = ta.getText();
                JLabel re = new JLabel(text);
                jp.add(id);
                jp.add(re);
            }
        });

        jp1.add(upload);

        c.add(back);
        c.add(jp);
        c.add(jp1);
        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
        setVisible(true);
    }
}