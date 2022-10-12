import javax.swing.JFrame;
import java.awt.*;

public class Frame extends JFrame {

    // import java.awt.event.KeyListener;

    Panel p1=new Panel();

    Frame(){
         p1.setBackground(Color.gray);
        this.add(p1);  ///

        this.setSize(1800,1800); /// we have set the frame size
        this.pack(); // set boundry to frame
        this.setTitle("SNAKE GAME");
        this.setVisible(true);  // we have made the frame visble
    }

}

