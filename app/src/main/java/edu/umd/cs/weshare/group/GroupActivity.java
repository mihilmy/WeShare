package edu.umd.cs.weshare.group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;


public class GroupActivity extends AppCompatActivity {
  private ListView membersLV;
  private UsersAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_group);
    setTitle("Members");
    initVariables();
  }

  private void initVariables() {
    adapter = new UsersAdapter(this, Database.getCurrentUser().getGroup().getUsersArray());
    membersLV = (ListView) findViewById(R.id.membersLV_Group);
    membersLV.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_group, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.action_add_member) {
      Intent i = new Intent(GroupActivity.this, AddMemberActivity.class);
      startActivity(i);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    adapter.notifyDataSetChanged();
  }

}
