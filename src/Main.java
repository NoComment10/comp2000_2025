import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;

public class Main extends JFrame {

  Canvas canvas;
    public static void main(String[] args) throws Exception {      
      SwingUtilities.invokeLater(() -> {
        new Main().startGame();
      });
    }

    class Canvas extends JPanel {
      Stage stage = new Stage();

      public Canvas() {
        setPreferredSize(new Dimension(1024, 720));

        addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e) {
            stage.handleClick(e.getPoint());
            repaint();
          }
        });
      }

      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        stage.paint(g, getMousePosition());
      }
    }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    private void startGame() {
      Timer timer = new Timer(30, e -> {
        canvas.repaint();
      });
      timer.start();
    }
}