package edu.umd.cs.weshare.list.pantry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.umd.cs.weshare.R;

public class PantryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pantry);
    setTitle("Pantry List");
  }
}
