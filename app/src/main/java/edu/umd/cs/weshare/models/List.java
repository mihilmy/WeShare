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
    if(index < 0) {
      itemsArray.add(new GroceryItem(item));
    } else {
      itemsArray.get(index).incQuantity();
    }
  }
}
