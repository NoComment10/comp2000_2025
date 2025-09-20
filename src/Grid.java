import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

public class Grid {
  Cell[][] cells = new Cell[20][20];
  
  public Grid() {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j] = new Cell(colToLabel(i), j, 10+Cell.size*i, 10+Cell.size*j);
      }
    }
  }

  private char colToLabel(int col) {
    return (char) (col + Character.valueOf('A'));
  }

  private int labelToCol(char col) {
    return (int) (col - Character.valueOf('A'));
  }

  public void paint(Graphics g, Point mousePos) {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j].paint(g, mousePos);
      }
    }
  }

  public Optional<Cell> cellAtColRow(int c, int r) {
    if(c >= 0 && c < cells.length && r >=0 && r < cells[c].length) {
      return Optional.of(cells[c][r]);
    } else {
      return Optional.empty();
    }
  }

  public Optional<Cell> cellAtColRow(char c, int r) {
    return cellAtColRow(labelToCol(c), r);
  }

  public Optional<Cell> cellAtPoint(Point p) {
    for(int i=0; i < cells.length; i++) {
      for(int j=0; j < cells[i].length; j++) {
        if(cells[i][j].contains(p)) {
          return Optional.of(cells[i][j]);
        }
      }
    }
    return Optional.empty();
  }

  public ArrayList<Cell> getNeighbors(Cell c) {
    int x = labelToCol(c.col);
    int y = c.row;
    ArrayList<Cell> neighbors = new ArrayList<>();

    for(int dirX = -1; dirX <= 1; dirX++) {
        for(int dirY = -1; dirY <= 1; dirY++) {
            if(dirX == 0 && dirY == 0) continue; // Skip the cell itself
            int newX = x + dirX;
            int newY = y + dirY;
            if(newX >= 0 && newX < cells.length && newY >= 0 && newY < cells[0].length) {
                neighbors.add(cells[newX][newY]);
            }
        }
    }
    return neighbors;
  }
}