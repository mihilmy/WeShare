package edu.umd.cs.weshare.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by omar on 11/29/17.
 */
@Getter
@ToString
public class List {
  private ListType type;
  private ArrayList<GroceryItem> itemsArray;

  public List(ListType type) {
    this.type = type;
    itemsArray = new ArrayList<>();
  }

  public void addItem(GroceryItem item) {
    int index = itemsArray.indexOf(item);
    Category c = item.getCatergory();
    if(index < 0) {
      int ii = findSpot(c,item.getName());
      itemsArray.add(ii, new GroceryItem(item));
    } else {
      itemsArray.get(index).incQuantity();
    }
  }

  private int findSpot(Category c, String n) {
    boolean foundCategory = false;
    for (int i = 0; i < itemsArray.size(); i++) {
      // This spot currently has correct category
      if (itemsArray.get(i).getCatergory() == c) {
        foundCategory = true;
        String name = itemsArray.get(i).getName();
        // Would putting it here be in alphabetical order?
        if (name.compareTo(n) > 0) {
          return i;
        }
      } else if (foundCategory) {
        return i;
      }

    }
    return itemsArray.size();
  }
}
