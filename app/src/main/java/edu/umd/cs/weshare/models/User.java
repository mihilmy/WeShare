package edu.umd.cs.weshare.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by omar on 11/27/17.
 */
@Getter
@Setter
public class User {
  private String name;
  private String email;
  private Group group;
  private List shoppingList;
  private List pantryList;

  public User(String name, String email) {
    this.name = name;
    this.email = email;
    this.shoppingList = new List(ListType.SHOPPING);
    this.pantryList = new List(ListType.PANTRY);
    this.group = new Group();
  }

  @Override
  public String toString() {
    return String.format("%s %s", name, email).toString();
  }
}
