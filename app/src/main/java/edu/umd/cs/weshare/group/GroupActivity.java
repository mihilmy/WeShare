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
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.launcher.LauncherActivity;
import edu.umd.cs.weshare.list.pantry.PantryActivity;
import edu.umd.cs.weshare.list.shopping.ShoppingActivity;


public class GroupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  private ListView membersLV;
  private UsersAdapter adapter;
  private DrawerLayout drawer;
  private ActionBarDrawerToggle drawerToggle;
  private NavigationView groupNV;
  private int currentID = R.id.GroupBTN_Drawer;

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
    // Drawer
    drawer = findViewById(R.id.Layout_Group);
    drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
    drawer.addDrawerListener(drawerToggle);
    drawerToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    // Drawer Navigation
    groupNV = findViewById(R.id.GroupNV);
    groupNV.setNavigationItemSelectedListener(this);
    groupNV.getMenu().getItem(2).setChecked(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_group, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_add_member) {
      startActivity(new Intent(GroupActivity.this, AddMemberActivity.class));
      return true;
    } else if (drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    adapter.notifyDataSetChanged();
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.ShoppingBTN_Drawer && currentID != R.id.ShoppingBTN_Drawer) {
      startActivity(new Intent(getBaseContext(), ShoppingActivity.class));
    } else if(item.getItemId() == R.id.PantryBTN_Drawer && currentID != R.id.PantryBTN_Drawer) {
      startActivity(new Intent(getBaseContext(), PantryActivity.class));
    } else if(item.getItemId() == R.id.GroupBTN_Drawer && currentID != R.id.GroupBTN_Drawer) {
      startActivity(new Intent(getBaseContext(), GroupActivity.class));
    } else if(item.getItemId() == R.id.LogoutBTN_Drawer && currentID != R.id.LogoutBTN_Drawer) {
      FirebaseAuth.getInstance().signOut();
      startActivity(new Intent(getBaseContext(), LauncherActivity.class));
    }

    drawer.closeDrawers();

    return true;
  }
}
