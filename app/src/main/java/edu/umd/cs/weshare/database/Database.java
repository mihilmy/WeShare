package edu.umd.cs.weshare.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import edu.umd.cs.weshare.models.User;

/**
 * Created by omar on 11/28/17.
 */

public class Database {
  private static ArrayList<User> allUsers = null;
  private static User currentUser = null;


  public static ArrayList<User> getAllUsers() {
    if(allUsers == null)
      populateUsers();

    return  allUsers;
  }

  public static User getCurrentUser() {
    if(currentUser == null) {
      Log.d(Database.class.getSimpleName(), "null");
      currentUser = new User("Name", FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    return currentUser;
  }


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

}
