import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Snakes  {
    static JFrame frame;
    private JPanel panelmenu,panelplay; 
    private JButton btnplay,rule,about;
    private JButton btnup,btndown,btnleft,btnright,btnpause,btntomenu,btnreset,btnresume;
    private JLabel labelscore;
    private JLabel labeltime;
    private Uler uler; //class uler ng panel utama play 
    private int hh,mm,ss;//kanggo angka label time
    private int score;
    private String text; //Kanggo set label time
    private final int B_WIDTH = 430;
    private final int B_HEIGHT = 350;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 1505;
    private final int RAND_POS = 10;
    private final int DELAY = 500;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = false;
    private Timer timer,dtk;
    private Image ball;
    private Image apple;
    private Image head;

    public Snakes() {
        frame=new JFrame();
        CreateMenu();
        CreatePlay();
        frame.setTitle("Uler");
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setVisible(true);
        frame.setContentPane(panelmenu);
        frame.repaint();
        frame.revalidate();          
    }
    //Panel utama 1 Menu utama 
    public void CreateMenu(){
        panelmenu=new JPanel();
        panelmenu.setLayout(new FlowLayout());
        JPanel imgJPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel southPanel = new JPanel();
        //Panel tengah
        JLabel hs=new JLabel("Highscore = 0000000");
        btnPanel.setLayout(new GridBagLayout());  
        btnplay=new JButton("Play");
        rule=new JButton("Rule");
        about=new JButton("About");
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
                    labeltime.setText("Time  = 00:00:00");
                    dtk.start();
                } 
        });
        rule.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    JDialog d = new JDialog(frame,"Rule"); 
                    d.setLocationRelativeTo(null);
                    JLabel l = new JLabel("Rule........."); 
                    d.add(l); 
                    d.setSize(260,190); 
                    d.setVisible(true); 
                } 
        });
        about.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    JDialog d = new JDialog(frame,"About"); 
                    d.setLocationRelativeTo(null);
                    JLabel l = new JLabel("About.........");
                    d.add(l); 
                    d.setSize(260,190); 
                    d.setVisible(true); 
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
        labelscore = new JLabel("Score = 00000000");
        labeltime = new JLabel ("Time  = 00:00:00");
        labeltime.setHorizontalAlignment(SwingConstants.RIGHT);
        btnpause=new JButton("PAUSE");
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
                labeltime.setText("Time  = "+text);
            }
        });
        dtk.start();
        //Panel ngisor
        btnup=new JButton("UP");
        btndown=new JButton("DOWN");
        btnleft=new JButton("LEFT");
        btnright=new JButton("RIGHT");
        btnPanelplay.setLayout(new BorderLayout());
        btnPanelplay.add(btnup,BorderLayout.NORTH);
        btnPanelplay.add(btndown,BorderLayout.SOUTH);
        btnPanelplay.add(btnleft,BorderLayout.WEST);
        btnPanelplay.add(btnright,BorderLayout.EAST);
        btnup.addActionListener(new ButtonListener());
        btndown.addActionListener(new ButtonListener());
        btnleft.addActionListener(new ButtonListener());
        btnright.addActionListener(new ButtonListener());
        //tambah uler ng tengah
        uler=new Uler();
        uler.addKeyListener(new TAdapter());
        
        uler.setFocusable(true);
        snakesPanel.add(uler);
        //tambah panel ngisor
        panelplay.add(statusPanel,BorderLayout.NORTH);
        panelplay.add(snakesPanel,BorderLayout.CENTER);
        panelplay.add(btnPanelplay,BorderLayout.SOUTH);
    }
    
    class Uler extends JPanel implements ActionListener {
        
        public Uler() {
            //Papan uler
            initBoard();
        }
        private void initBoard() {
            setBackground(Color.black);
            // requestFocus();
            setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
            loadImages();
        }
        //load gambar
        private void loadImages() {
            ImageIcon iid = new ImageIcon("dot.png");
            ball = iid.getImage();
            // ImageIcon iia = new ImageIcon("apple.png");
            ImageIcon iih = new ImageIcon("head.png");
            head = iih.getImage();
            apple = iih.getImage();
        }
        //mulai game
        private void initGame() {
            dots = 3;
            for (int z = 0; z < dots; z++) {
                x[z] = 50 - z * 10;
                y[z] = 50;
            }
            timer = new Timer(DELAY, this);
            locateApple();
            timer.start();
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing(g);
        }
        private void doDrawing(Graphics g) {
            if (inGame) {
                g.drawImage(apple, apple_x, apple_y, this);
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
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = getFontMetrics(small);
            dtk.stop();
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        }
        private void checkApple() {
            if ((x[0] == apple_x) && (y[0] == apple_y)) {
                dots++;
                score=(dots-3);
                labelscore.setText("Score = "+score);
                locateApple();
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
        private void locateApple() {
            int r = (int) (Math.random() * RAND_POS);
            apple_x = ((r * DOT_SIZE));
            r = (int) (Math.random() * RAND_POS);
            apple_y = ((r * DOT_SIZE));
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (inGame) {
                checkApple();
                checkCollision();
                move();
            }
            repaint();
        }
        //Key adapter iseh not respon
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
                d.setLayout(new GridLayout(1,2));
                
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
                        labeltime.setText("Time  = 00:00:00");
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
        } 
    }
    public static void main(String[] args) {
         new Snakes();
    }
}