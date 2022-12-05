import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static javax.swing.BoxLayout.X_AXIS;

//메인 패널
public class MainPage extends JFrame {
    ArticleTable article1;
    UserTable user1;
    int userNumID;

    public MainPage(UserTable user,  int id) {
        user1 = user;
        userNumID = id;
    }
    public MainPage(UserTable user, ArticleTable article, int id) {
        article1 = article;
        user1 = user;
        userNumID = id;
    }

    public void createMainPage() throws SQLException {
        //JFrame jf = new JFrame("gachongram"); //가장 중요한 프레임 설정
        JScrollPane scroll; //스크롤 바 형성

        JPanel jp4 = new JPanel();
        //JPanel basic = new JPanel();

        //기본 창관련
        //창의 전체 사이즈 설정
        //setLocation(500,500); //나오는 위치
        setSize(800, 900); //가로 세로

        //컴포넌트 위치 설정
        Container contentPane = getContentPane();

        //여러가지 기능이 있음
        JPanel jp = new JPanel();//게시물 업로드 화면으로 넘어가도록 만드는 버튼을 만드는 코드

        //글쓰기
        //uproad.setLocation(540,10);//게시물 업로드 버튼 위치 설정
        ImageIcon newuproad = new ImageIcon("./image/plus.jpg");
        Image uproadimg = newuproad.getImage();
        Image updateuproad = uproadimg.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon resizeuproad = new ImageIcon(updateuproad);
        JButton uproad = new JButton(resizeuproad);//uproad.setLocation(540,10);//게시물 업로드 버튼 위치 설정
        uproad.setBounds(30,30,565,0);
        uproad.setPreferredSize(new Dimension(40, 40));

        //팔로우 창
        ImageIcon newf = new ImageIcon("./image/follow.jpg");
        Image fimg = newf.getImage();
        Image updatef = fimg.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon resizef = new ImageIcon(updatef);
        JButton profile = new JButton(resizef);//uproad.setLocation(540,10);//게시물 업로드 버튼 위치 설정
        profile.setBounds(30,30,530,0);
        profile.setPreferredSize(new Dimension(40, 40));

        //dm 창
        ImageIcon newdm = new ImageIcon("./image/DM.jpg");
        Image dmimg = newdm.getImage();
        Image updatedm = dmimg.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon resizedm = new ImageIcon(updatedm);
        JButton DM = new JButton(resizedm);
        DM.setPreferredSize(new Dimension(40, 40));
        DM.setBounds(30,30,500,0);

        // 글쓰기 버튼 액션 listener
        uproad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UploadFrame(user1, article1, userNumID);
                //              setContentPane(a);
                //              revalidate();
                //              repaint();
            }
        });

        //프로필 버튼 액션 listener
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ProfileFrame pf = new ProfileFrame(user1, userNumID);
                pf.createProfileFrame();
//                setContentPane(pf);
//                revalidate();
//                repaint();
            }
        });

        //dm 버튼 액션 listener
        DM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                DMFrame dm = new DMFrame(user1, userNumID);
                dm.createDMFrame();
//                setContentPane(a);
//                revalidate();
//                repaint();
            }
        });

        //jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        jp.setPreferredSize(new Dimension(600, 45));

        //로고 넣기
        JLabel logolabel = new JLabel();
        logolabel.setSize(200, 30);
        ImageIcon newlogo = new ImageIcon("./image/real_logo.png");
        Image logoimg = newlogo.getImage();
        Image updateImg = logoimg.getScaledInstance(200,30,Image.SCALE_SMOOTH);
        ImageIcon resizelogo = new ImageIcon(updateImg);
        logolabel.setIcon(resizelogo);
        JPanel a = new JPanel();
        a.setSize(400,30);
        jp.add(logolabel);
        jp.add(a);
        jp.add(uproad);
        jp.add(profile);
        jp.add(DM);
        //logoimg.setLayout(null);

        //게시물 작성자의 정보
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, X_AXIS));

        String pro = "./image/swing_testss.jpg"; //여기에 인스타 이미지 절대경로 받아옴

        ImageIcon proimg = new ImageIcon(pro);
        Image profimg = proimg.getImage();
        Image updatepro = profimg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon resizeprofile = new ImageIcon(updatepro);
        JButton goprofile = new JButton(resizeprofile);
        goprofile.setPreferredSize(new Dimension(30, 30));

        goprofile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ProfileFrame pf = new ProfileFrame(user1, userNumID);
                pf.createProfileFrame();
            }
        });

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();



        // 게시글 여러 개 불러오기 형은 같은 글 반복해도 되니까 그냥 여러 개 나오게 해주삼
        for(int i=1; i< 2; i++) {
            info.setPreferredSize(new Dimension(800, 30));
            info.setSize(600, 30);
            String instaid = user1.getUserNickname(userNumID); //인스타 아이디 데베에서 받아와서 저장하면 됨
            JLabel name = new JLabel(instaid);

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


            //게시 글 작성하는 곳

            jp3.setLayout(new BoxLayout(jp3, X_AXIS));
            jp3.setPreferredSize(new Dimension(800, 50));
            jp3.add(new JLabel("글: "));
            //StringBuilder content = article1.readArticle();
            JLabel text = new JLabel("adfasdf"); // 글 내용 위의 주석 부분 (content)가 내용 문자열임
            jp3.add(text);


        }

        jp4.setLayout(new BoxLayout(jp4, BoxLayout.Y_AXIS));
        jp4.setPreferredSize(new Dimension(800, 750));

        //스크롤바
        scroll = new JScrollPane(jp1);
        scroll.setSize(5,750);
        scroll.setLocation(790,50);

        JPanel total = new JPanel();
        total.setBackground(Color.WHITE);
        info.setBackground(Color.WHITE);
        jp2.setBackground(Color.WHITE);
        jp3.setBackground(Color.WHITE);
        jp.setBackground(Color.WHITE);
        jp1.setBackground(Color.WHITE);
        jp4.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        //jp4.add(jp);
        jp4.add(info);
        jp4.add(jp1);
        jp4.add(jp2);
        jp4.add(jp3);
        jp4.add(scroll);
        total.add(jp);
        total.add(jp4);

        add(total);

        //x눌러서 창 닫게 만드는 구문
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //창 띄워주는 구문
        setVisible(true);

    }
}
