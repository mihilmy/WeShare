package edu.umd.cs.weshare.group;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.launcher.LauncherActivity;
import edu.umd.cs.weshare.list.pantry.PantryActivity;
import edu.umd.cs.weshare.list.shopping.ShoppingActivity;
import edu.umd.cs.weshare.models.User;

public class AddMemberActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
  private ListView membersLV;
  private UsersAdapter adapter;
  private DrawerLayout drawer;
  private ActionBarDrawerToggle drawerToggle;
  private NavigationView addMemberNV;

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
        User user = (User) adapterView.getItemAtPosition(i);
        boolean result = Database.getCurrentUser().getGroup().addMember(user);
        adapter.remove(user);
        Toast.makeText(getApplicationContext(),"Success! Member added.", Toast.LENGTH_LONG).show();
      }
    });

    // Drawer
    drawer = findViewById(R.id.Layout_AddMember);
    drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
    drawer.addDrawerListener(drawerToggle);
    drawerToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    // Drawer Navigation
    addMemberNV = findViewById(R.id.AddMemberNV);
    addMemberNV.setNavigationItemSelectedListener(this);
    setHeader(addMemberNV);
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

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(drawerToggle.onOptionsItemSelected(item))
      return true;
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.ShoppingBTN_Drawer) {
      startActivity(new Intent(getBaseContext(), ShoppingActivity.class));
    } else if(item.getItemId() == R.id.PantryBTN_Drawer) {
      startActivity(new Intent(getBaseContext(), PantryActivity.class));
    } else if(item.getItemId() == R.id.GroupBTN_Drawer) {
      startActivity(new Intent(getBaseContext(), GroupActivity.class));
    } else if(item.getItemId() == R.id.LogoutBTN_Drawer) {
      Database.clearCurrentUser();
      startActivity(new Intent(getBaseContext(), LauncherActivity.class));
    }

    drawer.closeDrawers();

    return true;
  }

  private void setHeader(NavigationView nv) {
    TextView tvName = nv.getHeaderView(0).findViewById(R.id.nameTV_Header);
    TextView tvEmail = nv.getHeaderView(0).findViewById(R.id.emailTV_Header);
    tvName.setText(Database.getCurrentUser().getName());
    tvEmail.setText(Database.getCurrentUser().getEmail());
  }
}
