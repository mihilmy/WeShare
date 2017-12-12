package edu.umd.cs.weshare.list;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.parceler.Parcels;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.models.GroceryItem;
import edu.umd.cs.weshare.models.List;
import edu.umd.cs.weshare.models.ListType;

/**
 * Created by omar on 12/10/17.
 */

public class AddGroceryItemsDialog extends AppCompatDialogFragment {
  private EditText quantityET;
  private GroceryItem groceryItem;
  private ListType listType;
  private Listener listener;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
    LayoutInflater mInflater = getActivity().getLayoutInflater();
    View mView = mInflater.inflate(R.layout.activity_add_item_dialog, null);

    mBuilder.setView(mView).setTitle("Set Quantity").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {

      }
    }).setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        int newQuantity = Integer.parseInt(quantityET.getText().toString());
        GroceryItem newItem = new GroceryItem(groceryItem);
        newItem.setQuantity(newQuantity);
        if(listType == ListType.SHOPPING)
          Database.getCurrentUser().getShoppingList().addItem(newItem);
        else
          Database.getCurrentUser().getPantryList().addItem(newItem);
        Toast.makeText(getContext(), "Success! Item Added.", Toast.LENGTH_SHORT).show();
        listener.updateListView();
      }
    });

    quantityET = mView.findViewById(R.id.quantityET_AddItem_Dialog);
    groceryItem = (GroceryItem) Parcels.unwrap(getArguments().getParcelable("groceryItem"));
    listType = getArguments().getInt("listType") == ListType.SHOPPING.ordinal()? ListType.SHOPPING: ListType.PANTRY;
    Log.d("ITEM", String.format("%b\n", groceryItem == null));
    return mBuilder.create();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    try {
      listener =  (Listener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + " must implement listener");
    }

  }

  public interface Listener {
    void updateListView();
  }





}
