package edu.umd.cs.weshare.models;

import lombok.AllArgsConstructor;

/**
 * Created by omar on 11/29/17.
 */

public class Category {
  private String name;
  private String color;

  public Category(String name, String color) {
    this.name = name;
    this.color= color;
  }

  public static Category BREADS = new Category("Breads", "HEX CODE");
  public static Category GRAINS = new Category("Grains", "HEX CODE");
  public static Category CEREALS = new Category("Cereals", "HEX CODE");
  public static Category DAIRY = new Category("Dairy", "HEX CODE");
  public static Category POULTRY = new Category("Poultry", "HEX CODE");
  public static Category SNACKS = new Category("Snacks", "HEX CODE");
  public static Category SWEETS = new Category("Sweets", "HEX CODE");
  public static Category FRUITS = new Category("Fruits", "HEX CODE");
  public static Category VEGETABLES = new Category("Vegetables", "HEX CODE");
  public static Category BEVERAGES = new Category("Beverages", "HEX CODE");
  public static Category CONDIMENTS = new Category("Condiments", "HEX CODE");
  public static Category UNCATEGORIZED = new Category("Uncategorized", "HEX CODE");
}
