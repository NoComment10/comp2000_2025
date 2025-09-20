import java.util.ArrayList;
import java.awt.Color;
import java.awt.Polygon;

public class Fish extends Item {
  public Fish(Cell inLoc) {
    super(inLoc, Color.CYAN);
    display = new ArrayList<Polygon>();
    Polygon tail = new Polygon();
    tail.addPoint(loc.x + 5, loc.y + 15);
    tail.addPoint(loc.x + 15, loc.y + 10);
    tail.addPoint(loc.x + 15, loc.y + 20);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 15, loc.y + 7);
    body.addPoint(loc.x + 30, loc.y + 7);
    body.addPoint(loc.x + 30, loc.y + 23);
    body.addPoint(loc.x + 15, loc.y + 23);
    display.add(body);
    display.add(tail);
  }

  @Override
  public void onCollect(Actor collector) {
    //need bone to disappear from the grid when touched by dog
    if(collector.isOn(this.getLocation())) {
      if(collector instanceof Cat){
        //add counter of item collects to end game eventually
        System.out.println("Cat collected the fish!");
      } else {
        System.out.println("Only cats can collect fish!");
        return;
      }
    }
  } 
}