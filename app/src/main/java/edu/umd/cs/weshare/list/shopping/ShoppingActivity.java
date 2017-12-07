package edu.umd.cs.weshare.list.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.group.GroupActivity;
import edu.umd.cs.weshare.launcher.LauncherActivity;
import edu.umd.cs.weshare.list.GroceryItemsAdapter;
import edu.umd.cs.weshare.list.pantry.PantryActivity;
import edu.umd.cs.weshare.models.GroceryItem;
import edu.umd.cs.weshare.models.ListType;

;


public class ShoppingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  private ListView shoppingLV;
  private DrawerLayout drawer;
  private ActionBarDrawerToggle drawerToggle;
  private NavigationView shoppingNV;
  private GroceryItemsAdapter adapter;
  private FloatingActionButton addItemFAB;
  private int currentID = R.id.ShoppingBTN_Drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping);
    setTitle("Shopping List");
    initVariables();
  }

  private void initVariables() {
    // Shopping List
    ArrayList<GroceryItem> array = Database.getCurrentUser().getShoppingList().getItemsArray();
    adapter = new GroceryItemsAdapter(this, array, EditShoppingItemActivity.class, ListType.SHOPPING);
    shoppingLV = findViewById(R.id.groceryLV_Shopping);
    shoppingLV.setAdapter(adapter);
    // Drawer
    drawer = findViewById(R.id.Layout_Shopping);
    drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
    drawer.addDrawerListener(drawerToggle);
    drawerToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    // Drawer Navigation
    shoppingNV = findViewById(R.id.ShoppingNV);
    shoppingNV.setNavigationItemSelectedListener(this);
    shoppingNV.getMenu().getItem(0).setChecked(true);
    setHeader(shoppingNV);
    // Add Item
    addItemFAB = findViewById(R.id.action_add_shopping_item);
    addItemFAB.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(view.getId() == R.id.action_add_shopping_item) {
          startActivity(new Intent(ShoppingActivity.this, AddShoppingItemActivity.class));
        }
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(drawerToggle.onOptionsItemSelected(item))
      return true;
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
