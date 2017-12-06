package edu.umd.cs.weshare.list.shopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.group.GroupActivity;
import edu.umd.cs.weshare.launcher.LauncherActivity;
import edu.umd.cs.weshare.list.pantry.PantryActivity;
import edu.umd.cs.weshare.list.shopping.ShoppingActivity;
import edu.umd.cs.weshare.models.GroceryItem;

public class EditShoppingItemActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
  private EditText nameET;
  private EditText quantityET;
  private Button saveBTN;
  private Button deleteBTN;
  private GroceryItem item;
  private int itemIndex;

  private DrawerLayout drawer;
  private ActionBarDrawerToggle drawerToggle;
  private NavigationView editItemNV;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_item);
    setTitle("Edit Item");
    itemIndex = getIntent().getIntExtra("itemIndex", -1);
    Log.d("DEBUG", String.format("%d", itemIndex).toString());
    initVariables();
    addItemData();
  }

  private void initVariables() {
    nameET = findViewById(R.id.editName);
    quantityET = findViewById(R.id.editQuantity);
    saveBTN = findViewById(R.id.editSaveButton);
    deleteBTN = findViewById(R.id.editDeleteButton);
    item = Database.getCurrentUser().getShoppingList().getItemsArray().get(itemIndex);
    saveBTN.setOnClickListener(btnListener);
    deleteBTN.setOnClickListener(btnListener);

    // Drawer
    drawer = findViewById(R.id.Layout_EditItem);
    drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
    drawer.addDrawerListener(drawerToggle);
    drawerToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    // Drawer Navigation
    editItemNV = findViewById(R.id.EditItemNV);
    editItemNV.setNavigationItemSelectedListener(this);
    setHeader(editItemNV);
  }

  private void addItemData() {
    nameET.setText(item.getName());
    quantityET.setText(String.format("%d",item.getQuantity()));
  }

  private void save() {
    String name = nameET.getText().toString();
    int quantity = Integer.parseInt(quantityET.getText().toString());
    item.setName(name);
    item.setQuantity(quantity);
  }

  private void delete() {
    Database.getCurrentUser().getShoppingList().getItemsArray().remove(itemIndex);
    Toast.makeText(getBaseContext(), "Success! Item deleted.", Toast.LENGTH_SHORT).show();
  }

  private View.OnClickListener btnListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if(view.getId() == R.id.editDeleteButton) {
        delete();
        startActivity(new Intent(getBaseContext(), ShoppingActivity.class));
      } else if (view.getId() == R.id.editSaveButton) {
        save();
        startActivity(new Intent(getBaseContext(), ShoppingActivity.class));
      }
    }
  };

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
