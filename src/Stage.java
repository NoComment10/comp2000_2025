import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage{
  Grid grid;
  List<Actor> userActor;
  List<Actor> enemyActor;
  List<Item> items;
  Cell selectedCell = null;
  Actor selectedActor = null;

  public Stage() {
    grid = new Grid();
    userActor = new ArrayList<Actor>();
    enemyActor = new ArrayList<Actor>();
    items = new ArrayList<Item>();

    //adding items to the stage
    items.add(new Bone(grid.cellAtColRow(5, 5).get()));
    items.add(new Bone(grid.cellAtColRow(10, 10).get())); 
    items.add(new Bone(grid.cellAtColRow(15, 15).get()));
    items.add(new Fish(grid.cellAtColRow(3, 12).get()));
    items.add(new Fish(grid.cellAtColRow(7, 8).get()));
    items.add(new Fish(grid.cellAtColRow(14, 2).get()));

    //adding user controlled actors to the stage
    userActor.add(new Cat(grid.cellAtColRow(0, 0).get()));
    userActor.add(new Dog(grid.cellAtColRow(19, 19).get()));

    //adding enemy actors to the stage
    enemyActor.add(new Bird(items.get(0).getLocation(), grid, 0));   
    enemyActor.add(new Bird(items.get(1).getLocation(), grid, 4)); 
    enemyActor.add(new Bird(items.get(2).getLocation(), grid, 7)); 
    enemyActor.add(new Bird(items.get(3).getLocation(), grid, 2)); 
    enemyActor.add(new Bird(items.get(4).getLocation(), grid, 3)); 
    enemyActor.add(new Bird(items.get(5).getLocation(), grid, 5)); 


  }

  //handles mouse clicks for selecting and moving user controlled actors
  public void handleClick(Point mousePoint) {
    Optional<Cell> clickedCell = grid.cellAtPoint(mousePoint);
    if(!clickedCell.isPresent()) return;

    Cell cell = clickedCell.get();

    //select an actor if one exists at the clicked cell
    for(Actor a : userActor){
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
        //deselect after move
        selectedActor = null; 
        checkItemCollect();

        for(Actor a: enemyActor) {
          if(a instanceof Bird) {
            //birds patrol, target not needed
            a.move(null); 
          }
        }
      } 

      if(gameOver()) {
        System.exit(0);
      }
    }
  }  

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);

    for(Item i: items) {
      i.paint(g);
    }

    for(Actor a: enemyActor) {
      a.paint(g);
    }

    for(Actor a: userActor) {
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
    for(Actor actor: userActor) {
      for(Item i: new ArrayList<>(items)) {
        if (actor.isOn(i.getLocation())) {
          //calls the onCollect method of the item
          i.onCollect(actor);

          //removes the item from the stage if collected by the correct actor
          if((actor instanceof Dog && i instanceof Bone) ||
            (actor instanceof Cat && i instanceof Fish)) {
            items.remove(i);
          }        
        }
      }    
    }
  }

  //checks for game over conditions
  public boolean gameOver() {
    //bird catches user
    for(Actor user: userActor) {
      for(Actor enemy: enemyActor) {
        if(enemy instanceof Bird && user.isOn(enemy.loc)) {
          System.out.println("Game Over! A bird caught you!");
          System.out.println("Dog collected " + ((Dog)userActor.get(1)).boneBag.getItemCount() + " bones.");
          System.out.println("Cat collected " + ((Cat)userActor.get(0)).fishBag.getItemCount() + " fish.");
          return true;
        }
      }
    }

    //all items collected
    if(items.isEmpty()) {
      System.out.println("Congratulations! You've collected all the items!");
      System.out.println("Dog collected " + ((Dog)userActor.get(1)).boneBag.getItemCount() + " bones.");
      System.out.println("Cat collected " + ((Cat)userActor.get(0)).fishBag.getItemCount() + " fish.");
      return true;
    }
    return false;
  }
}