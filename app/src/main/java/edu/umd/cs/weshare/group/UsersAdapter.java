package edu.umd.cs.weshare.group;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.models.User;

/**
 * Created by omar on 11/28/17.
 */

public class UsersAdapter extends ArrayAdapter<User>{

  public UsersAdapter(Context context, ArrayList<User> users) {
    super(context, 0, users);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // Get the data item for this position
    User user = getItem(position);

    // Check if an existing view is being reused, otherwise inflate the view
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_member_cell, parent, false);
    }
    // Lookup view for data population
    TextView tvName = (TextView) convertView.findViewById(R.id.nameTV_AddCell);
    TextView tvHome = (TextView) convertView.findViewById(R.id.emailTV_AddCell);
    // Populate the data into the template view using the data object
    tvName.setText(user.getName());
    tvHome.setText(user.getEmail());
    // Return the completed view to render on screen
    return convertView;
  }
}
