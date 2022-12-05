import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class UploadFrame extends JFrame implements ActionListener{
    UserTable user1;
    ArticleTable article1;
    int userNumID;
    private JFileChooser jfc = new JFileChooser();
    private JButton jbt_open = new JButton("열기");
    private JButton jbt_save = new JButton("저장");
    private JButton back_main = new JButton("<-");
    JTextArea posting = new JTextArea(10, 50);
    private JLabel jlb = new JLabel(" ");
    public UploadFrame(UserTable user, ArticleTable article, int id){
        super("call");
        user1 = user;
        article1 = article;
        userNumID = id;
        this.init();
        this.start();
        this.setSize(650,300);
        this.setVisible(true);
    }

    private void gotoMainPage() {
        MainPage mp = new MainPage(user1, userNumID);
        try {
            mp.createMainPage();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void init(){
        getContentPane().setLayout(new FlowLayout());
        add(jbt_open);
        add(jbt_save);
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
                gotoMainPage();
            }
        });

        add(back_main);
        add(jlb);
        add(posting);
    }
    public void start(){
        jbt_open.addActionListener(this);
        jbt_save.addActionListener(this);

        //           jfc.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
        // 파일 필터
        jfc.setMultiSelectionEnabled(false);//다중 선택 불가
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        if(arg0.getSource() == jbt_open){


            if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                String juso = jfc.getSelectedFile().toString();//파일절대경로주소 저장-->넘기는 변수
                jlb.setText("open : " + jfc.getSelectedFile().toString());

            }
        }
        if(arg0.getSource() == jbt_save){
            String post = posting.getText();//textarea에 있는 글 저장-->넘기는 변수
            article1.insertArticle(post);
            JOptionPane.showMessageDialog(null, "Upload Success");
            setVisible(false);
            gotoMainPage();
            //jlb.setText("posting : " + post);


        }
    }

}

//패널에 그림을 올려주는 클래스