import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Dog extends Actor {
  //bag to store collected bones
  public Bag<Bone> boneBag = new Bag<>();
  
  public Dog(Cell inLoc) {
    loc = inLoc;
    color = new Color(210, 125, 45);
    display = new ArrayList<Polygon>();
    Polygon ear1 = new Polygon();
    ear1.addPoint(loc.x + 5, loc.y + 5);
    ear1.addPoint(loc.x + 15, loc.y + 5);
    ear1.addPoint(loc.x + 5, loc.y + 15);
    Polygon ear2 = new Polygon();
    ear2.addPoint(loc.x + 20, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 15);
    Polygon face = new Polygon();
    face.addPoint(loc.x + 8, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 25);
    face.addPoint(loc.x + 8, loc.y + 25);
    display.add(face);
    display.add(ear1);
    display.add(ear2);
  }

  //moves the cat to a neighboring cell
  @Override
  public void move(Cell target) {
    //checks if the target cell is valid
    if(target == null || !isNeighbor(target)) return;
    
    //redraws the cat in the new position
    int dirX = target.x - loc.x;
    int dirY = target.y - loc.y;
    for (Polygon p : display) {
      for (int i = 0; i < p.npoints; i++) {
        p.xpoints[i] += dirX;
        p.ypoints[i] += dirY;
      }
      p.invalidate();
    }
    loc = target;
  }

  //checks if the target cell is a neighbor of the current location
  private boolean isNeighbor(Cell target) {
    int dx = Math.abs((int)target.col - (int)loc.col);
    int dy = Math.abs(target.row - loc.row);
    return (dx <= 1 && dy <= 1) && (dx + dy != 0);
  }
}