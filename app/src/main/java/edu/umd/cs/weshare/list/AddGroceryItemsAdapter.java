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
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.models.GroceryItem;
import edu.umd.cs.weshare.models.List;
import edu.umd.cs.weshare.models.ListType;

/**
 * Created by elisegreen on 12/5/17.
 */

public class AddGroceryItemsAdapter extends ArrayAdapter<GroceryItem> {
    private int layoutID;
    private int tvNameID;
    private ListType listType;

    public AddGroceryItemsAdapter(Context context, ArrayList<GroceryItem> items, int layoutID, int tvNameID, ListType listType) {
        super(context, 0, items);
        this.layoutID = layoutID;
        this.tvNameID = tvNameID;
        this.listType = listType;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        GroceryItem food = getItem(position);
        ArrayList<GroceryItem> theList;
        // get food name then find index in shopping/pantry list
        // determine where are based on layoutID
        // if equal to pantry cell then get from pantry list

        if (listType == ListType.PANTRY) {
            theList = Database.getCurrentUser().getPantryList().getItemsArray();
        } else {
            theList = Database.getCurrentUser().getShoppingList().getItemsArray();
        }

        int quant = 0;
        int index = theList.indexOf(food);
        if (index >= 0) {
            quant = theList.get(index).getQuantity();
        }


        int quantityID = R.id.quantityTV_AddCell;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutID, parent, false);
        }
        // Lookup view for data population
        TextView tvFood = convertView.findViewById(tvNameID);
        TextView tvQuantity = convertView.findViewById(quantityID);
        tvQuantity.setText(String.format("(%d)",quant).toString());
        // Populate the data into the template view using the data object
        tvFood.setText(food.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
