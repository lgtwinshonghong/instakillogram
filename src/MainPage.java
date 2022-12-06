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
        jp.setPreferredSize(new Dimension(800, 45));

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

        // 게시글 여러 개 불러오기 형은 같은 글 반복해도 되니까 그냥 여러 개 나오게 해주삼
        JPanel total = new JPanel();
        jp.setBackground(Color.WHITE);

        total.add(jp);
        total.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        JPanel feedPanel = new JPanel();

        /*
            message:[승환->우영]
            게시글 여러개 추가하는 부분 아래 있다.
            지금은 ArticlePanel이 UserTable 하나랑 유저id 정수 하나밖에 안받음
            게시글 하나당 사진, 글내용, 작성자, 댓글 등 필요한게 저거 두개보단 많은걸로 아는데
            아직 그 기능들이개발 안된것같고 나도 시간없어서 그냥 놔둠

            우영아 고생하겠지만 기능들 개발되면 ArticlePanel 생성자 수정하고
            내부 소스 변경해야할듯하다 모르면연락 ㄱㄱ
        */

        int i = 0;
        for(; i<5; i++) {
            feedPanel.add(new ArticlePanel(user1, userNumID));
        }

        //feedPanel.setLayout(null);
        feedPanel.setBackground(Color.WHITE);
        feedPanel.setPreferredSize(new Dimension(600, 855*i));
        scroll = new JScrollPane(feedPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(600, 855));
        total.add(scroll);
        add(total);

        //x눌러서 창 닫게 만드는 구문
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //창 띄워주는 구문
        setVisible(true);

    }
}
