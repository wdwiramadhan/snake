import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Snakes {
    static JFrame frame;
    private JPanel panelmenu, panelplay, panelabout, panelrule, panelpause;
    private JButton btnplay, rule, about;
    private JButton btnup, btndown, btnleft, btnright, btnpause, btntomenu, btnreset, btnresume;
    private JLabel labelscore;
    private JLabel labeltime;
    private JLabel hs;// label highscore
    private Uler uler; // class uler ng panel utama play
    private int hh, mm, ss;// kanggo angka label time
    private int score;
    private int highscore = 0;
    private String text; // Kanggo set label time
    private final int B_WIDTH = 430;
    private final int B_HEIGHT = 350;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 1505;
    private final int RAND_POS = 10;
    private final int DELAY = 500;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private int dots;
    private int pakan_x;
    private int pakan_y;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = false;
    private Timer timer, dtk;
    private Image ball;
    private Image pakan;
    private Image head;

    public Snakes() {
        frame = new JFrame();
        CreateMenu();
        CreatePlay();
        CreateRule();
        CreateAbout();
        frame.addKeyListener(new TAdapter());
        frame.setFocusable(true);
        frame.setTitle("Uler");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setVisible(true);
        frame.setContentPane(panelmenu);
        frame.repaint();
        frame.revalidate();
    }

    public void CreateRule() {
        panelrule = new JPanel();
        panelrule.setLayout(new BorderLayout());
        JLabel l = new JLabel("Rules", SwingConstants.CENTER);
        JLabel desc = new JLabel(
                "<html><div style='text-align: center;'>This game has some rules there are : </div><br/>1.<br/>2.<br/>3.<br/>4.<br/>5.<br/></html>",
                SwingConstants.CENTER);
        JButton back = new JButton("Back to Main Menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panelmenu);
                frame.repaint();
                frame.revalidate();
            }
        });
        panelrule.add(l, BorderLayout.NORTH);
        panelrule.add(desc, BorderLayout.CENTER);
        panelrule.add(back, BorderLayout.SOUTH);
        panelrule.setVisible(true);
    }

    public void CreateAbout() {
        panelabout = new JPanel();
        panelabout.setLayout(new BorderLayout());
        JLabel l = new JLabel("About Us", SwingConstants.CENTER);
        JLabel desc = new JLabel(
                "<html><div style='text-align: center;'>This project is created by Tim 4<br/><br/>Analyst<br/>A11.2018.11359 Wahid Amaludin<br/>A11.2018.11390 Muhamad Gani Damar Mulya<br/><br/>GUI Designer<br/>A11.2018.11368 Andika Suriya Bagus Saputra<br/>A11.2018.11366 Muhamad Baharudin Yusuf<br/><br/>Programmer<br/>A11.2018.11347 Moh. Mustaghfirin Al Farizi<br/>A11.2018.11396 Wahyu Dwi Ramadhan<br/><br/>Documentator<br/>A11.2018.11409 Muhammad Farhan Azky<br/>A11.2018.11270 Rosa Paramitha</div></html>",
                SwingConstants.CENTER);
        JButton back = new JButton("Back to Main Menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panelmenu);
                frame.repaint();
                frame.revalidate();
            }
        });
        panelabout.add(l, BorderLayout.NORTH);
        panelabout.add(desc, BorderLayout.CENTER);
        panelabout.add(back, BorderLayout.SOUTH);
        panelabout.setVisible(true);
    }

    // Panel utama 1 Menu utama
    public void CreateMenu() {
        panelmenu = new JPanel();
        panelmenu.setLayout(new FlowLayout());
        JPanel imgJPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel southPanel = new JPanel();
        // panel gambar
        JLabel picLabel = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("gambarutama.png"));
            picLabel.setIcon(new ImageIcon(img));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        imgJPanel.add(picLabel);
        //Panel tengah
        hs=new JLabel("<html>Welcome to Snake<br/>Highscore = "+highscore+"</html>",SwingConstants.CENTER);
        btnPanel.setLayout(new GridBagLayout());  
        btnplay=new JButton();
        rule=new JButton();
        about=new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("play.png"));
            btnplay.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("rule.png"));
            rule.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("about.png"));
            about.setIcon(new ImageIcon(img));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        btnplay.setPreferredSize(new Dimension(100,30));
        rule.setPreferredSize(new Dimension(100,30));
        about.setPreferredSize(new Dimension(100,30));
        imgJPanel.setPreferredSize(new Dimension(350,250));
        panelmenu.add(imgJPanel);
        panelmenu.add(hs);
        southPanel.setPreferredSize(new Dimension(350,200));
        panelmenu.add(southPanel);
        btnPanel.setPreferredSize(new Dimension(100,150));
        //Panel Ngisor  
        southPanel.add(btnPanel);   
        btnPanel.setLayout(new FlowLayout()); 
        btnPanel.add(btnplay);
        btnPanel.add(rule);
        btnPanel.add(about);
        btnplay.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    inGame=true;
                    // detik.start();
                    frame.setContentPane(panelplay);
                    frame.repaint();
                    frame.revalidate();
                    uler.initGame();
                    hh=0;mm=0;ss=0;
                    
                    uler.setFocusable(true);
                    labeltime.setText(" = 00:00:00");
                    dtk.start();
                } 
        });
        rule.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.setContentPane(panelrule);
                    frame.repaint();
                    frame.revalidate();
                } 
        });
        about.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.setContentPane(panelabout);
                    frame.repaint();
                    frame.revalidate();
                } 
        });
    }
    //Panel utama 2 play
    public void CreatePlay(){
        panelplay=new JPanel();
        panelplay.setLayout(new BorderLayout());
        JPanel statusPanel = new JPanel();
        JPanel snakesPanel = new JPanel();
        JPanel btnPanelplay = new JPanel();
        //Panel duwur
        labelscore = new JLabel(" = 0");
        labelscore.setIcon(new ImageIcon("score.png"));
        labeltime = new JLabel (" = 00:00:00");
        labeltime.setIcon(new ImageIcon("time.png"));
        labeltime.setHorizontalAlignment(SwingConstants.RIGHT);
        btnpause=new JButton();
        btnpause.setActionCommand("PAUSE");
        try {
            Image img = ImageIO.read(getClass().getResource("pause.png"));
            btnpause.setIcon(new ImageIcon(img));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        btnpause.setFocusable(false);
        statusPanel.setLayout(new GridLayout(1,3));
        statusPanel.add(labelscore);
        statusPanel.add(btnpause);
        statusPanel.add(labeltime);
        btnpause.addActionListener(new ButtonListener());
        //Ngitung time
        dtk=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ss==60){
                    mm=mm+1;
                    ss=00;
                }
                
                if(mm==60){
                    hh=hh+1;
                    mm=00;
                }
                ss=ss+1;
                text = "";
                if(hh<10) text=text+"0"+hh;
                else text=text+""+hh;
                if(mm<10) text=text+":0"+mm;
                else text=text+":"+mm;
                if(ss<10) text=text+":0"+ss;
                else text=text+":"+ss;
                // text=text+">";
                labeltime.setText(" = "+text);
            }
        });
        dtk.start();
        //Panel ngisor
        btnup=new JButton();
        btndown=new JButton();
        btnleft=new JButton();
        btnright=new JButton();
        btnup.setActionCommand("UP");
        btndown.setActionCommand("DOWN");
        btnleft.setActionCommand("LEFT");
        btnright.setActionCommand("RIGHT");
        try {
            Image img = ImageIO.read(getClass().getResource("up.png"));
            btnup.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("down.png"));
            btndown.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("left.png"));
            btnleft.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("right.png"));
            btnright.setIcon(new ImageIcon(img));
          } catch (Exception ex) {
            System.out.println(ex);
          }
        JPanel btncenter=new JPanel();
        btncenter.setLayout(new GridLayout(1,2));
        btnPanelplay.setLayout(new BorderLayout());
        btncenter.add(btnleft);
        btncenter.add(btnright);
        btnPanelplay.add(btnup,BorderLayout.NORTH);
        btnPanelplay.add(btncenter,BorderLayout.CENTER);
        btnPanelplay.add(btndown,BorderLayout.SOUTH);
        btnup.setFocusable(false);
        btndown.setFocusable(false);
        btnleft.setFocusable(false);
        btnright.setFocusable(false);
        btnup.addActionListener(new ButtonListener());
        btndown.addActionListener(new ButtonListener());
        btnleft.addActionListener(new ButtonListener());
        btnright.addActionListener(new ButtonListener());
        //tambah uler ng tengah
        uler=new Uler();
        snakesPanel.add(uler);
        //tambah panel ngisor
        panelplay.add(statusPanel,BorderLayout.NORTH);
        panelplay.add(snakesPanel,BorderLayout.CENTER);
        panelplay.add(btnPanelplay,BorderLayout.SOUTH);
    }
    
    
    
    class Uler extends JPanel implements ActionListener {
        
        /**
         *
         */
        private static final long serialVersionUID = -3105031904880398687L;

        public Uler() {
            //Papan uler
            initBoard();
        }
        private void initBoard() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
            loadImages();
        }
        //load gambar
        private void loadImages() {
            ImageIcon ftail = new ImageIcon("ekor.png");
            ImageIcon fhead = new ImageIcon("kepala.png");
            ball = ftail.getImage();
            head = fhead.getImage();
            pakan = fhead.getImage();
        }
        //mulai game
        private void initGame() {
            dots = 3;
            for (int z = 0; z < dots; z++) {
                x[z] = 50 - z * 10;
                y[z] = 50;
            }
            timer = new Timer(DELAY, this);
            locatepakan();
            timer.start();
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing(g);
        }
        private void doDrawing(Graphics g) {
            if (inGame) {
                g.drawImage(pakan, pakan_x, pakan_y, this);
                for (int z = 0; z < dots; z++) {
                    if (z == 0) {//draw ndas
                        g.drawImage(head, x[z], y[z], this);
                    } 
                     else {
                        g.drawImage(ball, x[z], y[z], this);
                    }
                }
                Toolkit.getDefaultToolkit().sync();
            } 
            else {
                gameOver(g);
            }
        }
        private void gameOver(Graphics g) {
            String msg = "Game Over. Your Score : "+score+", Played Time : "+hh+":"+mm+":"+ss;
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = getFontMetrics(small);
            dtk.stop();
            g.setColor(Color.BLACK);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
            try {
                Image img = ImageIO.read(getClass().getResource("gomenu.png"));
                btnpause.setIcon(new ImageIcon(img));
              } catch (Exception ex) {
                System.out.println(ex);
              }
              btnpause.setActionCommand("MENU");
        }
        private void checkpakan() {
            if ((x[0] == pakan_x) && (y[0] == pakan_y)) {
                dots++;
                score=(dots-3);
                labelscore.setText(" = "+score);
                if(highscore<=score){
                    highscore=score;
                    hs.setText("<html>Welcome to Snake<br/>Highscore = "+highscore+"</html>");
                }
                locatepakan();
            }
        }
        private void move() {
            for (int z = dots; z > 0; z--) {
                x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }
            if (leftDirection) x[0] -= DOT_SIZE;
            if (rightDirection) x[0] += DOT_SIZE;
            if (upDirection) y[0] -= DOT_SIZE;
            if (downDirection) y[0] += DOT_SIZE;
        }
        private void checkCollision() {
            for (int z = dots; z > 0; z--) {
                if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                    inGame = false;
                }
            }
            if (y[0] >= B_HEIGHT) inGame = false;
            if (y[0] < 0) inGame = false;
            if (x[0] >= B_WIDTH) inGame = false;
            if (x[0] < 0) inGame = false;
            
            //Free mode tembus tembok
            // if (y[0] >= B_HEIGHT-9) y[0]=0;
            // if (y[0] < 0) y[0]=B_HEIGHT-9;
            // if (x[0] >= B_WIDTH-9) x[0]=0;
            // if (x[0] < 0) x[0]=B_WIDTH-9;
            if (!inGame) timer.stop();
        }
        //Random pakanan
        private void locatepakan() {
            int r = (int) (Math.random() * RAND_POS);
            pakan_x = ((r * DOT_SIZE));
            r = (int) (Math.random() * RAND_POS);
            pakan_y = ((r * DOT_SIZE));
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (inGame) {
                checkpakan();
                checkCollision();
                move();
            }
            repaint();
        }
        
    public void CreatePause(){
        panelpause=new JPanel();
        panelpause.setLayout(new FlowLayout());
        JPanel d=new JPanel();
        d.setLayout(new GridLayout(1,2));
        timer.stop();
        dtk.stop();
        btntomenu=new JButton("Main Menu");
        btnreset=new JButton("Replay");
        btnresume=new JButton("Resume");
        btnreset.setActionCommand("AGAIN");
        btntomenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panelmenu);
                frame.repaint();
                frame.revalidate(); 
                
            }
        });
        btnreset.addActionListener(new ButtonListener());
        btnresume.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panelplay);
                frame.repaint();
                frame.revalidate(); 
                timer.start();
                dtk.start();
            }
        });
        d.add(btntomenu);
        d.add(btnreset);
        d.add(btnresume);
        panelpause.add(d);
    }
    }
    
    
    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
    //llstener menu utama 2 play
    public class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("PAUSE")){
                JDialog d = new JDialog(frame,"PAUSE"); 
                d.setLocationRelativeTo(null);
                d.setLayout(new GridLayout(3,1));
                
                timer.stop();
                dtk.stop();
                btntomenu=new JButton("Main Menu");
                btnreset=new JButton("Replay");
                btnresume=new JButton("Resume");
                btntomenu.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        d.dispose();
                        frame.setContentPane(panelmenu);
                        frame.repaint();
                        frame.revalidate(); 
                        
                    }
                });
                btnreset.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        d.dispose();
                        inGame=true;
                        hh=0;mm=0;ss=0;
                        labeltime.setText(" = 00:00:00");
                        dtk.start();
                        uler.initGame();
                    }
                });
                btnresume.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        d.dispose();
                        timer.start();
                        dtk.start();
                    }
                });
                d.add(btntomenu);
                d.add(btnreset);
                d.add(btnresume);
                d.setSize(260,190); 
                d.setVisible(true); 
            }
            if ((e.getActionCommand().equals("LEFT")) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((e.getActionCommand().equals("RIGHT")) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((e.getActionCommand().equals("UP")) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((e.getActionCommand().equals("DOWN")) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((e.getActionCommand().equals("AGAIN"))) {
                // try {
                //     Image img = ImageIO.read(getClass().getResource("pause.png"));
                //     btnpause.setIcon(new ImageIcon(img));
                //   } catch (Exception ex) {
                //     System.out.println(ex);
                //   }
                  btnpause.setActionCommand("PAUSE");
                    inGame=true;
                    hh=0;mm=0;ss=0;
                    labeltime.setText(" = 00:00:00");
                    dtk.start();
                    uler.initGame();
                    frame.setContentPane(panelplay);
                    frame.repaint();
                    frame.revalidate(); 
                }
            if ((e.getActionCommand().equals("MENU"))) {
                
                try {
                    Image img = ImageIO.read(getClass().getResource("pause.png"));
                    btnpause.setIcon(new ImageIcon(img));
                  } catch (Exception ex) {
                    System.out.println(ex);
                  }
                    frame.setContentPane(panelmenu);
                    frame.repaint();
                    frame.revalidate(); 
                }
        } 
    }
    public static void main(String[] args) {
         new Snakes();
    }
}