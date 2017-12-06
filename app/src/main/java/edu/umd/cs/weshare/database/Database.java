package edu.umd.cs.weshare.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Random;

import edu.umd.cs.weshare.models.Category;
import edu.umd.cs.weshare.models.GroceryItem;
import edu.umd.cs.weshare.models.User;

/**
 * Created by omar on 11/28/17.
 */

public class Database {
  private static ArrayList<GroceryItem> allItems = null;
  private static ArrayList<User> allUsers = null;
  private static User currentUser = null;

  public static ArrayList<User> getAllUsers() {
    if(allUsers == null)
      populateUsers();

    return  allUsers;
  }
  public static ArrayList<GroceryItem> getAllItems() {
    if(allItems == null)
      populateItems();

    return allItems;
  }
  public static User getCurrentUser() {
    if(currentUser == null)
      populateCurrentUser();

    return currentUser;
  }
  public static void clearCurrentUser() {
    FirebaseAuth.getInstance().signOut();
    currentUser = null;
  }

  /*Helpers*/
  private static void populateUsers() {
    allUsers = new ArrayList<>();
    allUsers.add(new User("Adam Perex", "adamperez@example.com"));
    allUsers.add(new User("Patrick Russell", "patrick85@example.com"));
    allUsers.add(new User("Ethan Payne", "ethan_payne@example.com"));
    allUsers.add(new User("Adam Edwards", "adam_edwards@example.com"));
    allUsers.add(new User("Marie Rios", "marie-rios@example.com"));
    allUsers.add(new User("Ashley Thomas", "ashley84@example.com"));
    allUsers.add(new User("Angela Carter", "angela-82@example.com"));
    allUsers.add(new User("Daniel Sandoval", "daniel86@example.com"));
    allUsers.add(new User("Anna Burke", "annaburke@example.com"));
    allUsers.add(new User("Dylan Newman", "dylan-newman@example.com"));
    allUsers.add(new User("Billy Johnson", "billy_83@example.com"));
    allUsers.add(new User("Sean Payne", "sean.payne@example.com"));
  }
  private static void populateItems() {
    allItems = new ArrayList<>();
    allItems.add(new GroceryItem("Apples", Category.FRUITS));
    allItems.add(new GroceryItem("Bananas", Category.FRUITS));
    allItems.add(new GroceryItem("Grapes", Category.FRUITS));
    allItems.add(new GroceryItem("Peaches", Category.FRUITS));
    allItems.add(new GroceryItem("Berries", Category.FRUITS));
    allItems.add(new GroceryItem("Water Melon", Category.FRUITS));
    allItems.add(new GroceryItem("Oranges", Category.FRUITS));
    allItems.add(new GroceryItem("Rice", Category.GRAINS));
    allItems.add(new GroceryItem("Pasta", Category.GRAINS));
    allItems.add(new GroceryItem("Noodles", Category.GRAINS));
    allItems.add(new GroceryItem("Bagels", Category.BREADS));
    allItems.add(new GroceryItem("Tortillas", Category.BREADS));
    allItems.add(new GroceryItem("Rolls", Category.BREADS));
    allItems.add(new GroceryItem("Toast", Category.BREADS));
    allItems.add(new GroceryItem("Cheerios", Category.CEREALS));
    allItems.add(new GroceryItem("Frosties", Category.CEREALS));
    allItems.add(new GroceryItem("Capn Crunch", Category.CEREALS));
    allItems.add(new GroceryItem("Cinnamon Toast Crunch", Category.CEREALS));
    allItems.add(new GroceryItem("Milk", Category.DAIRY));
    allItems.add(new GroceryItem("Cheese", Category.DAIRY));
    allItems.add(new GroceryItem("Eggs", Category.DAIRY));
    allItems.add(new GroceryItem("Beef", Category.POULTRY));
    allItems.add(new GroceryItem("Ground Beef", Category.POULTRY));
    allItems.add(new GroceryItem("Pork", Category.POULTRY));
    allItems.add(new GroceryItem("Lamb", Category.POULTRY));
    allItems.add(new GroceryItem("Chicken", Category.POULTRY));
    allItems.add(new GroceryItem("Turkey", Category.POULTRY));
    allItems.add(new GroceryItem("Fish", Category.POULTRY));
  }
  private static void populateCurrentUser() {
    currentUser = new User("John Doe", FirebaseAuth.getInstance().getCurrentUser().getEmail());
    populateItems();
    for(int i = 0; i < 15; i++) {
      int j = (int) (Math.random() * allItems.size());
      currentUser.getShoppingList().addItem(allItems.get(j));
    }
  }

}
