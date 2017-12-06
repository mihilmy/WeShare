package edu.umd.cs.weshare.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by omar on 11/29/17.
 */
@Getter
public class Category {
  private String name;
  private String color;

  public Category(String name, String color) {
    this.name = name;
    this.color= color;
  }

  public static Category BREADS = new Category("Breads", "#FFEB3B");
  public static Category GRAINS = new Category("Grains", "#795548");
  public static Category CEREALS = new Category("Cereals", "#FF9800");
  public static Category DAIRY = new Category("Dairy", "#2196F3");
  public static Category POULTRY = new Category("Poultry", "#F44336");
  public static Category SNACKS = new Category("Snacks", "#E91E63");
  public static Category SWEETS = new Category("Sweets", "#9C27B0");
  public static Category FRUITS = new Category("Fruits", "#673AB7");
  public static Category VEGETABLES = new Category("Vegetables", "#4CAF50");
  public static Category BEVERAGES = new Category("Beverages", "#3F51B5");
  public static Category CONDIMENTS = new Category("Condiments", "#9E9E9E");
  public static Category UNCATEGORIZED = new Category("Uncategorized", "#000000");
}
