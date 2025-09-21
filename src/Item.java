import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Item implements Collectible {
  Color color;
  Cell loc;
  List<Polygon> display;

  public Item(Cell loc, Color color) {
    this.loc = loc;
    this.color = color;    
  }

  //paints the item on the grid
  public void paint(Graphics g) {
    for(Polygon p: display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.GRAY);
      g.drawPolygon(p);
    }
  }

  //returns the location of the item
  public Cell getLocation() {
    return loc;
  }
}