package edu.umd.cs.weshare.group;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.umd.cs.weshare.R;

public class AddMemberActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_member);
    setTitle("Add Member");
  }
}
