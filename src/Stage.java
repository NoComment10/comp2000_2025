import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stage extends JPanel{
  Grid grid;
  List<Actor> actors;
  List<Item> items;
  Cell selectedCell = null;

  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    items = new ArrayList<Item>();

    //adding actors to the stage
    actors.add(new Cat(grid.cellAtColRow(0, 0).get()));
    actors.add(new Dog(grid.cellAtColRow(0, 15).get()));
    actors.add(new Bird(grid.cellAtColRow(12, 9).get()));   

    //adding items to the stage
    items.add(new Bone(grid.cellAtColRow(5, 5).get()));
    items.add(new Bone(grid.cellAtColRow(10, 10).get())); 
    items.add(new Bone(grid.cellAtColRow(15, 15).get()));
    items.add(new Fish(grid.cellAtColRow(3, 12).get()));
    items.add(new Fish(grid.cellAtColRow(7, 8).get()));
    items.add(new Fish(grid.cellAtColRow(14, 2).get()));

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Optional<Cell> clickedCell = grid.cellAtPoint(e.getPoint());
        if(clickedCell.isPresent()) {
          selectedCell = clickedCell.get();
          System.out.println("Clicked on cell: " + (char)('A' + selectedCell.col) + (selectedCell.row + 1));
        } else {
          selectedCell = null;
        }
      }
    });
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);

    for(Item i: items) {
      i.paint(g);
    }

    for(Actor a: actors) {
      a.paint(g);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }

  public void update() {
    for(Actor a: actors) {
      a.move(selectedCell);
    }
    selectedCell = null; //reset selected cell after move
    checkItemCollect();
    repaint();
  }

  //removes item from the stage
  public void removeItem(Item item) {
    items.remove(item);
  }
  
  //checks whether an item needs to be removed based on actor's position
  public void checkItemCollect() {
    for(Actor actor: actors) {
      for(Item i: new ArrayList<>(items)) {
        if (actor.isOn(i.getLocation())) {
          i.onCollect(actor);
          if((actor instanceof Dog && i instanceof Bone) ||
            (actor instanceof Cat && i instanceof Fish)) {
            items.remove(i);
          }        
        }
      }    
    }
  }
}