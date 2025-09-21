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

  //announces when the fish is collected by a cat and adds it to the cat's bag
  @Override
  public void onCollect(Actor collector) {
    if(collector.isOn(this.getLocation())) {
      if(collector instanceof Cat){
        //adds the collected bone to the dog's bag
        ((Cat)collector).fishBag.addItem(this);

        System.out.println("Cat collected the fish!");
      } else {
        System.out.println("Only cats can collect fish!");
        return;
      }
    }
  } 
}