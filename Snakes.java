import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Snakes extends JFrame {
    /**
     */
    private static final long serialVersionUID = 4686539811470774590L;
    static JFrame frame;
    JButton play,rule,about;
    public Snakes() {
        
        this.setLayout(new FlowLayout());
        JPanel imgJPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JLabel hs=new JLabel("Highscore = 0000000");
        btnPanel.setLayout(new GridBagLayout());  
        play=new JButton("Play");
        rule=new JButton("Rule");
        about=new JButton("About");
        play.setPreferredSize(new Dimension(100,30));
        rule.setPreferredSize(new Dimension(100,30));
        about.setPreferredSize(new Dimension(100,30));
        imgJPanel.setPreferredSize(new Dimension(350,250));
        // imgJPanel.setBackground(Color.CYAN);
        add(imgJPanel);
        add(hs);
        southPanel.setPreferredSize(new Dimension(350,200));
        add(southPanel);
        btnPanel.setPreferredSize(new Dimension(100,150));
        //Panel Ngisor  
        southPanel.add(btnPanel);   
        btnPanel.setLayout(new FlowLayout()); 
        btnPanel.add(play);
        btnPanel.add(rule);
        btnPanel.add(about);
        rule.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    JDialog d = new JDialog(frame,"Rule"); 
                    d.setLocationRelativeTo(null);
                    JLabel l = new JLabel("Rule........."); 
                    // d.setLocation(frame.getHeight(),frame.getWidth() );
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
                    // d.setLocation(frame.getHeight(),frame.getWidth() );
                    d.add(l); 
                    d.setSize(260,190); 
                    d.setVisible(true); 
                } 
        });
    }

    public static void main(String[] args) {
         frame = new Snakes();
        frame.setTitle("Uler");
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setVisible(true);
    }
}
