package edu.umd.cs.weshare.list.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.group.GroupActivity;
import edu.umd.cs.weshare.launcher.LauncherActivity;
import edu.umd.cs.weshare.list.AddGroceryItemsAdapter;
import edu.umd.cs.weshare.list.pantry.PantryActivity;
import edu.umd.cs.weshare.models.GroceryItem;

public class AddShoppingItemActivity extends AppCompatActivity {
  private ListView foodsLV;
  private AddGroceryItemsAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_item);
    setTitle("Add Groceries");
    initVariables();
  }

  private void initVariables() {
    adapter = new AddGroceryItemsAdapter(this, Database.getAllItems(), R.layout.activity_add_item_cell, R.id.foodTV_AddCell);
    foodsLV = (ListView) findViewById(R.id.foodLV_AddFood);
    foodsLV.setAdapter(adapter);
    foodsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Add food to their shopping list
        Database.getCurrentUser().getShoppingList().addItem((GroceryItem) adapterView.getItemAtPosition(i));
        Toast.makeText(getBaseContext(), "Success! Item Added.", Toast.LENGTH_SHORT).show();
      }
    });
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_search, menu);

    MenuItem item = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) item.getActionView();
    searchView.setIconifiedByDefault(false);
    searchView.setQueryHint("Search");

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
    if(item.getItemId() == android.R.id.home)
      this.finish();
    return super.onOptionsItemSelected(item);
  }
}
