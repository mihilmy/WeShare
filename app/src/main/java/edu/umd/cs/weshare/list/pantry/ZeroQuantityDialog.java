package edu.umd.cs.weshare.list.pantry;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import edu.umd.cs.weshare.database.Database;
import edu.umd.cs.weshare.models.GroceryItem;

/**
 * Created by omar on 12/11/17.
 */

public class ZeroQuantityDialog extends AppCompatDialogFragment {
  private int mItemIndex;
  private GroceryItem mItem;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    mItemIndex = getArguments().getInt("itemIndex");
    mItem = Database.getCurrentUser().getPantryList().getItemsArray().get(mItemIndex);

    String message = String.format("Quantity of %s is zero. Would you like to add it to your shopping list?", mItem.getName());
    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

    mBuilder.
      setTitle("Warning!").
      setMessage(message).
      setNegativeButton("cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {

      }
    }).setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        GroceryItem newItem = new GroceryItem(mItem.getName(), mItem.getCatergory(), 1);
        Database.getCurrentUser().getShoppingList().addItem(newItem);
        Toast.makeText(getContext(), "Success! Item added to shopping.", Toast.LENGTH_SHORT).show();
      }
    });

    return mBuilder.create();
  }
}
