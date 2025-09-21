import java.util.List;
import java.util.ArrayList;

public class Bag<T extends Collectible> {
  public List<T> items;

  public Bag() {
      items = new ArrayList<>();
  }

  //adds an item to the bag
  public void addItem(T item) {
      items.add(item);
  }

  //returns a copy of the items in the bag
  public List<T> getItems() {
    return new java.util.ArrayList<>(items);
  }

  //returns the number of items in the bag
  public int getItemCount() {
        return items.size();
    }
}
