import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage{
  Grid grid;
  List<Actor> actors;
  List<Item> items;
  Cell selectedCell = null;
  Actor selectedActor = null;

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
  }

  public void handleClick(Point mousePoint) {
    Optional<Cell> clickedCell = grid.cellAtPoint(mousePoint);
    if(!clickedCell.isPresent()) return;

    Cell cell = clickedCell.get();

    //select an actor if one exists at the clicked cell
    for(Actor a : actors){
      if(a.isOn(cell) && (a instanceof Dog || a instanceof Cat)) {
        selectedActor = a;
        return;
      }
    }

    //move selected actor to clicked cell if it's a neighbor
    if(selectedActor != null) {
      ArrayList<Cell> neighbors = grid.getNeighbors(selectedActor.loc);
      boolean contains = neighbors.contains(cell);

      if(contains) {
        selectedActor.move(cell);
        selectedActor = null; //deselect after move
        checkItemCollect();
      } 
    }
  }  

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);

    for(Item i: items) {
      i.paint(g);
    }

    for(Actor a: actors) {
      a.paint(g);
    }

    //hover highlight
    if(mouseLoc != null){
      Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
      if(underMouse.isPresent()) {
        Cell hoverCell = underMouse.get();
        g.setColor(Color.DARK_GRAY);
        g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
      }
    }
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

  public boolean gameOver() {
    return items.isEmpty();
  }
}