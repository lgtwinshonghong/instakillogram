import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.X_AXIS;

public class ArticlePanel extends JPanel {
    public ArticlePanel(UserTable user1, int userNumID) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(800, 750));

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();

        jp1.setBackground(Color.white);
        jp2.setBackground(Color.white);
        jp3.setBackground(Color.white);

        //게시물 작성자의 정보
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, X_AXIS));

        info.setBackground(Color.WHITE);
        info.setPreferredSize(new Dimension(800, 30));
        info.setSize(600, 30);
        String instaid = user1.getUserNickname(userNumID); //인스타 아이디 데베에서 받아와서 저장하면 됨
        JLabel name = new JLabel(instaid);

        String pro = "./image/swing_testss.jpg"; //여기에 인스타 이미지 절대경로 받아옴

        ImageIcon proimg = new ImageIcon(pro);
        Image profimg = proimg.getImage();
        Image updatepro = profimg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon resizeprofile = new ImageIcon(updatepro);

        JButton goprofile = new JButton(resizeprofile);
        goprofile.setPreferredSize(new Dimension(30, 30));

        goprofile.addActionListener((e) -> {
            setVisible(false);
            ProfileFrame pf = new ProfileFrame(user1, userNumID);
            pf.createProfileFrame();
        });

        info.add(goprofile);
        info.add(name);

        //사진등의 게시물 올라가는 곳
        jp1.setLayout(new BoxLayout(jp1, X_AXIS));
        String insta_img = "./image/swing_test.jpg"; //여기에 인스타 이미지 절대경로 받아옴

        ImageIcon img = new ImageIcon(insta_img);
        Image reimg = img.getImage();
        Image updateimg = reimg.getScaledInstance(800, 550, Image.SCALE_SMOOTH);
        ImageIcon resizeimg = new ImageIcon(updateimg);
        JLabel imgs = new JLabel(resizeimg);
        imgs.setSize(800, 550);

        jp1.add(imgs);
        jp1.setPreferredSize(new Dimension(800, 550));
        jp1.setSize(new Dimension(800, 550));


        //좋아요 댓글 작성하는 곳
        FlowLayout fl = new FlowLayout(); //모든 버튼은 좌측부터 오게 설정
        fl.setAlignment(FlowLayout.CENTER);

        ImagePanel white_heart = new ImagePanel(new ImageIcon("./image/white_heart.jpg").getImage());
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        jp2.setLayout(fl);
        jp2.setPreferredSize(new Dimension(600, 30));

        ImageIcon newrheart = new ImageIcon("./image/DM.jpg");
        Image rheartimg = newrheart.getImage();
        Image updaterheart = rheartimg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizerheart = new ImageIcon(updaterheart);

        ImageIcon newwheart = new ImageIcon("./image/w_heart.jpg");
        Image wheartimg = newwheart.getImage();
        Image updatewheart = wheartimg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizewheart = new ImageIcon(updatewheart);

        JButton like = new JButton(resizewheart); // 좋아요 버튼
        like.setPreferredSize(new Dimension(40, 40));

        ImageIcon newre = new ImageIcon("./image/replys.jpg");
        Image reimgs = newre.getImage();
        Image updatere = reimgs.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizere = new ImageIcon(updatere);
        JButton reply = new JButton(resizere); // 댓글 버튼
        reply.setPreferredSize(new Dimension(40, 40));

        like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonn = (JButton) e.getSource();
                String str = buttonn.getText();
                int count = 0; //0이면 좋아요가 안눌린 상태 1이면 좋아요가 눌린 상태
                //좋아요를 받은 경우 나오는 효과 빨간색 변화
                if (str.equals("LIKE")) {
                    buttonn.setBackground(Color.red);
                    buttonn.setText("HEART");
                }
                //좋아요 취소
                else if (str.equals("HEART")) {
                    buttonn.setBackground(Color.white);
                    buttonn.setText("LIKE");
                    count = 0;
                }

            }
        });


        reply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ReplyFrame re = new ReplyFrame(user1, userNumID);
                re.createReplyFrame();
            }
        });

        jp2.add(like, BorderLayout.LINE_START);
        jp2.add(reply, BorderLayout.LINE_START);

        //스크롤바
        JScrollPane scroll = new JScrollPane(jp1);
        scroll.setLocation(790,50);
        scroll.setSize(5,750);

        //게시 글 작성하는 곳
        jp3.setLayout(new BoxLayout(jp3, X_AXIS));
        jp3.setPreferredSize(new Dimension(800, 50));
        jp3.add(new JLabel("글: "));
        //StringBuilder content = article1.readArticle();
        JLabel text = new JLabel("adfasdf"); // 글 내용 위의 주석 부분 (content)가 내용 문자열임
        jp3.add(text);

        add(jp1);
        add(jp2);
        add(jp3);
        add(info);
        add(scroll);

        setBackground(Color.WHITE);

    }
}