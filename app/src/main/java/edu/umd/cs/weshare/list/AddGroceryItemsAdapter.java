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
 * Created by elisegreen on 12/5/17.
 */

public class AddGroceryItemsAdapter extends ArrayAdapter<GroceryItem> {
    private int layoutID;
    private int tvNameID;

    public AddGroceryItemsAdapter(Context context, ArrayList<GroceryItem> items, int layoutID, int tvNameID) {
        super(context, 0, items);
        this.layoutID = layoutID;
        this.tvNameID = tvNameID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        GroceryItem food = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutID, parent, false);
        }
        // Lookup view for data population
        TextView tvFood = convertView.findViewById(tvNameID);
        // Populate the data into the template view using the data object
        tvFood.setText(food.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
