package edu.umd.cs.weshare.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.ArraySwipeAdapter;

import java.util.ArrayList;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.list.shopping.EditShoppingItemActivity;
import edu.umd.cs.weshare.models.GroceryItem;

/**
 * Created by omar on 11/29/17.
 */

public class GroceryItemsAdapter extends ArraySwipeAdapter<GroceryItem> {

  public GroceryItemsAdapter(Context context, ArrayList<GroceryItem> objects) {
    super(context, 0, objects);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // Get the data item for this position
    final GroceryItem item = (GroceryItem) getItem(position);
    final int itemIndex = position;
    // Check if an existing view is being reused, otherwise inflate the view
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_shopping_cell, parent, false);
    }
    // Lookup view for data population
    TextView tvName = convertView.findViewById(R.id.nameTV_ShoppingCell);
    TextView tvQuantity = convertView.findViewById(R.id.quantityTV_ShoopingCell);
    View line = convertView.findViewById(R.id.categoryLine_ShoppingCell);
    LinearLayout edit = convertView.findViewById(R.id.editBTN_ShoppingCell);
    LinearLayout move = convertView.findViewById(R.id.moveBTN_ShoppingCell);

    edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(getContext(), EditShoppingItemActivity.class);
        i.putExtra("itemIndex", itemIndex);
        getContext().startActivity(i);
      }
    });

    move.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Database.getCurrentUser().getPantryList().addItem(item);
        Toast.makeText(getContext(), String.format("Moved %s to pantry.", item.getName()), Toast.LENGTH_LONG).show();
        remove(item);
        notifyDataSetChanged();
      }
    });

    // Populate the data into the template view using the data object
    tvName.setText(item.getName());
    tvQuantity.setText(String.format("(%d)",item.getQuantity()).toString());
    line.setBackgroundColor(Color.parseColor(item.getCatergory().getColor()));
    // Return the completed view to render on screen
    return convertView;
  }


  @Override
  public int getSwipeLayoutResourceId(int position) {
    return R.id.Swipe_Shopping;
  }
}
