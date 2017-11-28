package edu.umd.cs.weshare.group;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.regex.Pattern;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.models.User;

public class AddMemberActivity extends AppCompatActivity {
  private ListView membersLV;
  private UsersAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_member);
    setTitle("Add Member");
    initVariables();
  }

  private void initVariables() {
    adapter = new UsersAdapter(this, Database.getAllUsers());
    membersLV = (ListView) findViewById(R.id.membersLV_AddMember);
    membersLV.setAdapter(adapter);
    membersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Database.getCurrentUser().getGroup().addMember((User) adapterView.getItemAtPosition(i));
        Log.d("AddMemberActivity", Database.getCurrentUser().getGroup().toString());
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_group_search, menu);

    MenuItem item = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) item.getActionView();

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String s) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return false;
      }
    });

    return true;
  }
}
