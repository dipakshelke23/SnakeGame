import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


import javax.swing.Action;
        import javax.swing.JPanel;
        import javax.swing.Timer;
        import javax.swing.event.*;
        import javax.swing.plaf.DimensionUIResource;
        import java.awt.*;
        import java.awt.event.ActionEvent;
 //import java.awt.event.KeyAdapter;
        import  java.awt.event.KeyListener;
        import java.awt.event.KeyEvent;
// import java.security.Key;
        import java.util.Random;



public class Panel extends JPanel implements Action, KeyListener {


    static final int screen_width = 600;
    static final int screen_height = 600;
    static final int ball_size = 25;
    static final int game_unit = (screen_width * screen_height) / ball_size;
    static final int delay = 250;
    final int x[] = new int[game_unit];
    final int y[] = new int[game_unit];
    int bodyparts = 3;
    int applesEaten=0;
    int applex;
    int appley;
    char direction = 'R';
    boolean running = false;
    Random random;
    Timer timer;

    Panel() {
        random = new Random();
        this.setPreferredSize(new Dimension(screen_width, screen_height)); 


        this.addKeyListener(this);
        this.setFocusable(true);
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void newApple() {
        applex = random.nextInt((int) (screen_width / ball_size)) * ball_size;
        appley = random.nextInt((int) (screen_height / ball_size)) * ball_size;
    }

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < screen_height / ball_size; i++) {
                g.drawLine(i * ball_size, 0, i * ball_size, screen_height);
                g.drawLine(0, i * ball_size, screen_width, i * ball_size);
            }
            g.setColor(Color.red);
            g.fillOval(applex, appley, ball_size, ball_size);
            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    g.setColor(Color.red);
                    g.fillRect(x[i], y[i], ball_size, ball_size);
                } else {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], ball_size, ball_size);

                }
                g.setColor(Color.orange);
                g.setFont(new Font("Ink Free", Font.BOLD, 40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("SCORE" + applesEaten, (screen_width - metrics.stringWidth("SCORE" + applesEaten)) / 2, g.getFont().getSize());
            }
        } else {
            gameover(g);
        }

    }

    public void move() {
        for (int i = bodyparts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - ball_size;
                break;
            case 'D':
                y[0] = y[0] + ball_size;
                break;
            case 'L':
                x[0] = x[0] - ball_size;
                break;
            case 'R':
                x[0] = x[0] + ball_size;
                break;

        }
    }

    public void checkapple() {
        if (x[0] == applex && y[0] == appley) {
            bodyparts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkcollession() {
        // if head collaps with body
        for (int i = bodyparts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        //
        if (x[0] < 0) {
            running = false;
        }

        if (x[0] > screen_width) {
            running = false;
        }

        if (y[0] > screen_height) {
            running = false;
        }

        if (y[0] < 0) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }

    }

    public void gameover(Graphics g) {

        g.setColor(Color.orange);
        g.setFont(new Font("Arial", Font.BOLD, 70));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("SCORE " + applesEaten, (screen_width - metrics1.stringWidth("score" + applesEaten)) / 2, g.getFont().getSize());

        g.setColor(Color.orange);
        g.setFont(new Font("Arial", Font.BOLD, 45));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER ", (screen_width - metrics2.stringWidth("game over")) / 2, screen_height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkapple();
            checkcollession();

        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object getValue(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putValue(String key, Object value) {
        // TODO Auto-generated method stub

    }




}


