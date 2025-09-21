import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Bone extends Item {

  public Bone(Cell inLoc) {

    //setting the color and shape of the bone
    super(inLoc, Color.WHITE);
    display = new ArrayList<Polygon>();
    Polygon bone = new Polygon();
    bone.addPoint(loc.x + 10, loc.y + 5);
    bone.addPoint(loc.x + 20, loc.y + 5);
    bone.addPoint(loc.x + 25, loc.y + 10);
    bone.addPoint(loc.x + 25, loc.y + 15);
    bone.addPoint(loc.x + 20, loc.y + 20);
    bone.addPoint(loc.x + 10, loc.y + 20);
    bone.addPoint(loc.x + 5, loc.y + 15);
    bone.addPoint(loc.x + 5, loc.y + 10);
    display.add(bone);
  }  

  //announces when the bone is collected by a dog and adds it to the dog's bag
  @Override
  public void onCollect(Actor collector) {
    if(collector.isOn(this.getLocation())) {
      if(collector instanceof Dog){
        //adds the collected bone to the dog's bag
        ((Dog)collector).boneBag.addItem(this);

        System.out.println("Dog collected the bone!");
      } else {
        System.out.println("Only dogs can collect bones!");
        return;
      }
    }
  }  
}