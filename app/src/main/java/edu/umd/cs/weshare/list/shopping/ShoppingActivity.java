package edu.umd.cs.weshare.list.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.list.GroceryItemsAdapter;
import edu.umd.cs.weshare.models.GroceryItem;

public class ShoppingActivity extends AppCompatActivity {
  private ListView shoppingLV;
  private GroceryItemsAdapter adapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping);
    setTitle("Shopping List");
    initVariables();
  }

  private void initVariables() {
    shoppingLV = (ListView) findViewById(R.id.groceryLV_Shopping);
    // adds the users's current shopping list to the adapter
    ArrayList<GroceryItem> array = Database.getCurrentUser().getShoppingList().getItemsArray();
    adapter = new GroceryItemsAdapter(this, array);
    shoppingLV.setAdapter(adapter);
  }
}
