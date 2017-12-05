package edu.umd.cs.weshare.models;

import java.util.ArrayList;
import java.util.HashSet;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by omar on 11/27/17.
 */
@Getter
@ToString
public class Group {
  private HashSet<User> usersHash = new HashSet<>();
  private ArrayList<User> usersArray = new ArrayList<>();

  public boolean addMember(User u) {
    if(usersHash.add(u)) {
      usersArray.add(u);
      return true;
    }

    return false;
  }
}
