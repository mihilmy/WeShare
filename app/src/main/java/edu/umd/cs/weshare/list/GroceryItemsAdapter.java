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
import edu.umd.cs.weshare.models.ListType;

/**
 * Created by omar on 11/29/17.
 */

public class GroceryItemsAdapter extends ArraySwipeAdapter<GroceryItem> {
  private Class editActivityClass;
  private ListType listType;

  public GroceryItemsAdapter(Context context, ArrayList<GroceryItem> objects, Class activityClass,ListType type) {
    super(context, 0, objects);
    this.editActivityClass = activityClass;
    this.listType = type;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // Get the data item for this position
    final GroceryItem item = (GroceryItem) getItem(position);
    final int itemIndex = position;
    // Check if an existing view is being reused, otherwise inflate the view
    if (convertView == null) {
      int resID = listType == ListType.SHOPPING? R.layout.activity_shopping_cell: R.layout.activity_pantry_cell;
      convertView = LayoutInflater.from(getContext()).inflate(resID, parent, false);
    }

    int nameID = R.id.nameTV_ShoppingCell;
    int quantityID = R.id.quantityTV_ShoopingCell;
    int categoryID = R.id.categoryLine_ShoppingCell;
    int editID = R.id.editBTN_ShoppingCell;
    int moveID = R.id.moveBTN_ShoppingCell;

    if(listType == ListType.PANTRY) {
      nameID = R.id.nameTV_PantryCell;
      quantityID = R.id.quantityTV_PantryCell;
      categoryID = R.id.categoryLine_PantryCell;
      editID = R.id.editBTN_PantryCell;
      moveID = R.id.moveBTN_PantryCell;
    }


    // Lookup view for data population
    TextView tvName = convertView.findViewById(nameID);
    TextView tvQuantity = convertView.findViewById(quantityID);
    View line = convertView.findViewById(categoryID);
    LinearLayout edit = convertView.findViewById(editID);
    LinearLayout move = convertView.findViewById(moveID);

    edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(getContext(), editActivityClass);
        i.putExtra("itemIndex", itemIndex);
        getContext().startActivity(i);
      }
    });

    move.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(listType == ListType.SHOPPING) {
          Database.getCurrentUser().getPantryList().addItem(item);
          Toast.makeText(getContext(), String.format("%s moved to pantry.", item.getName()), Toast.LENGTH_LONG).show();
        } else {
          Database.getCurrentUser().getShoppingList().addItem(item);
          Toast.makeText(getContext(), String.format("%s moved to shopping.", item.getName()), Toast.LENGTH_LONG).show();
        }
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
