/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

/**
 *
 * @author Shokhjakhon
 */
public class YogiBearGUI extends JFrame implements KeyListener {
   
    private JFrame frame;
    private GameEngine gameArea;
    
    private JLabel timeLabel;
    private long startTime;
    private Timer timer;
    private long time = 0;
    
    public YogiBearGUI() {
        frame = new JFrame();
        gameArea = new GameEngine();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem resetMenuItem = new JMenuItem("Restart");
        gameMenu.add(resetMenuItem);
        resetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTime = System.currentTimeMillis();
                timer.start();
                gameArea.restart();
            }
        });
        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("Score Table") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScoreTable(gameArea.getHighScores(), frame);
            }
        });
        gameMenu.add(menuHighScores);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        timeLabel = new JLabel(" ");
        frame.setLayout(new BorderLayout(0, 5)); 
        frame.add(timeLabel, BorderLayout.NORTH);
        
        
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameArea.getLives() == 0) {
                    startTime = System.currentTimeMillis();
                    timer.start();
                } else {
                    time = elapsedTime();
                }
                timeLabel.setText(time + " ms, " + "Lives: " + gameArea.getLives() + ", Score: " + gameArea.getScore());
            }
        });
        startTime = System.currentTimeMillis();
        timer.start();
        
        frame.getContentPane().add(gameArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(815, 640));
        
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
    }
    
    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameArea.keyPressed(e);
        frame.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameArea.keyReleased(e);
        frame.repaint();
    }
    
}
