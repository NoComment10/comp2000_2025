import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Bird extends Actor {
  public ArrayList<Cell> patrolRoute;
  public int startIndex;
  public int routeIndex;

  public Bird(Cell itemCell, Grid grid, int startIndex){
    this.startIndex = startIndex;
    this.patrolRoute = generateOrderedRoute(itemCell, grid);

    if(!patrolRoute.isEmpty()){
      routeIndex = (startIndex - 1 + patrolRoute.size()) % patrolRoute.size();
      loc = patrolRoute.get((routeIndex + 1) % patrolRoute.size());
    }

    color = Color.GREEN;
    display = new ArrayList<Polygon>();
    Polygon wing1 = new Polygon();
    wing1.addPoint(loc.x + 5, loc.y + 5);
    wing1.addPoint(loc.x + 15, loc.y + 17);
    wing1.addPoint(loc.x + 5, loc.y + 17);
    Polygon wing2 = new Polygon();
    wing2.addPoint(loc.x + 30, loc.y + 5);
    wing2.addPoint(loc.x + 20, loc.y + 17);
    wing2.addPoint(loc.x + 30, loc.y + 17);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 15, loc.y + 10);
    body.addPoint(loc.x + 20, loc.y + 10);
    body.addPoint(loc.x + 20, loc.y + 25);
    body.addPoint(loc.x + 15, loc.y + 25);
    display.add(body);
    display.add(wing1);
    display.add(wing2);
  }

  @Override
  public void move(Cell ignore){
    if(patrolRoute == null || patrolRoute.isEmpty()) return;

    Cell next = patrolRoute.get(routeIndex);

    int dirX = next.x - loc.x;
    int dirY = next.y - loc.y;

    for(Polygon p: display){
      for(int i = 0; i < p.npoints; i++){
        p.xpoints[i] += dirX;
        p.ypoints[i] += dirY;
      }
      p.invalidate();
    }

    loc = next;
    routeIndex = (routeIndex + 1) % patrolRoute.size();
  }

  public ArrayList<Cell> generateOrderedRoute(Cell loc, Grid grid){
    int x = grid.labelToCol(loc.col);
    int y = loc.row;
    ArrayList<Cell> route = new ArrayList<>();

    route.add(grid.getCell(x, y - 1)); //left
    route.add(grid.getCell(x + 1, y - 1)); //bottom left
    route.add(grid.getCell(x + 1, y)); //bottom
    route.add(grid.getCell(x + 1, y + 1)); //bottom right
    route.add(grid.getCell(x, y + 1)); //right
    route.add(grid.getCell(x - 1, y + 1)); //top right
    route.add(grid.getCell(x - 1, y)); //top
    route.add(grid.getCell(x - 1, y - 1)); //top left
    return route;
  }
}