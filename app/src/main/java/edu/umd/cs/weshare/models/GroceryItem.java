package edu.umd.cs.weshare.models;

import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by omar on 11/29/17.
 */
@Getter
@Setter
public class GroceryItem {
  private String name;
  private Category catergory;
  private int quantity;

  public GroceryItem(String name, Category catergory) {
    this.name = name;
    this.catergory = catergory;
    this.quantity = 0;
  }

  public GroceryItem(GroceryItem item) {
    this.name = item.name;
    this.catergory = item.catergory;
    this.quantity = 1;
  }

  public void incQuantity() {
    quantity++;
  }

  public void decQuantity() {
    quantity--;
  }

  @Override
  public boolean equals(Object obj) {
    GroceryItem other = (GroceryItem) obj;
    return name.equals(other.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    return name;
  }
}
