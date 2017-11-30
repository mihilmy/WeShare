package edu.umd.cs.weshare.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.models.GroceryItem;

/**
 * Created by omar on 11/29/17.
 */

public class GroceryItemsAdapter extends ArrayAdapter<GroceryItem>{

  public GroceryItemsAdapter(Context context, ArrayList<GroceryItem> objects) {
    super(context, 0, objects);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // Get the data item for this position
    GroceryItem item = getItem(position);

    // Check if an existing view is being reused, otherwise inflate the view
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_shopping_cell, parent, false);
    }
    // Lookup view for data population
    TextView tvName = convertView.findViewById(R.id.nameTV_ShoppingCell);
    TextView tvQuantity = convertView.findViewById(R.id.quantityTV_ShoopingCell);
    // Populate the data into the template view using the data object
    tvName.setText(item.getName());
    tvQuantity.setText(String.format("(%d)",item.getQuantity()).toString());
    // Return the completed view to render on screen
    return convertView;
  }
}
