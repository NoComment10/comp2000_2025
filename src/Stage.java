
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;
  List<Item> items;

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

  public void removeItem(Item item, Actor actor) {
    for(Item i: new ArrayList<>(items)) {
      if (actor.isOn(i.getLocation())) {
        item.onCollect(actor);
        if(actor instanceof Dog) {
          items.remove(item);
        }        
      }
    }
    
    System.out.println("Item removed from the stage.");
  }
}