package edu.umd.cs.weshare.list.pantry;

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

import org.parceler.Parcels;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.group.GroupActivity;
import edu.umd.cs.weshare.launcher.LauncherActivity;
import edu.umd.cs.weshare.list.AddGroceryItemsAdapter;
import edu.umd.cs.weshare.list.AddGroceryItemsDialog;
import edu.umd.cs.weshare.list.shopping.ShoppingActivity;
import edu.umd.cs.weshare.models.GroceryItem;
import edu.umd.cs.weshare.models.ListType;

/**
 * Created by elisegreen on 12/6/17.
 */

public class AddPantryItemActivity extends AppCompatActivity implements AddGroceryItemsDialog.Listener{
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
    adapter = new AddGroceryItemsAdapter(this, Database.getAllItems(), R.layout.activity_add_item_cell, R.id.foodTV_AddCell, ListType.PANTRY);
    foodsLV = (ListView) findViewById(R.id.foodLV_AddFood);
    foodsLV.setAdapter(adapter);
    foodsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Add food to their Pantry list
        showAddItemAlertDialog((GroceryItem) adapterView.getItemAtPosition(i));
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
    if (item.getItemId() == android.R.id.home)
      this.finish();
    return super.onOptionsItemSelected(item);
  }

  private void showAddItemAlertDialog(GroceryItem item) {
    AddGroceryItemsDialog mDialog = new AddGroceryItemsDialog();
    Bundle b = new Bundle();
    b.putParcelable("groceryItem", Parcels.wrap(item));
    b.putInt("listType", ListType.PANTRY.ordinal());
    mDialog.setArguments(b);
    mDialog.show(getSupportFragmentManager(), "Add Item");
  }

  @Override
  public void updateListView() {
    adapter.notifyDataSetChanged();
  }
}
