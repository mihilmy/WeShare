package edu.umd.cs.weshare.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.list.shopping.ShoppingActivity;


public class SignupActivity extends AppCompatActivity {
  private EditText emailET;
  private EditText passwordET;
  private Button signupBTN;
  private FirebaseAuth firebaseAuth;
  private DatabaseReference databaseRef;
  /**
   * This method is called by default in every Activity class you create and its the start point of
   * the activity.
   * @param savedInstanceState - a bundle contains some info from the previous screen it was called from.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    initVariables();
  }

  /**
   * Initializes all the variables these are objects found in the res/layout/activity_signup.xml file.
   * findViewByID simply assigns the button in the xml to a variable here so you can get the text if
   * it's an input field or change the text and so on.
   */
  private void initVariables() {
    emailET = (EditText) findViewById(R.id.EmailET_Signup);
    passwordET = (EditText) findViewById(R.id.PasswordET_Signup);
    signupBTN = (Button) findViewById(R.id.SignupBTN_Signup);
    firebaseAuth = FirebaseAuth.getInstance();
    databaseRef = FirebaseDatabase.getInstance().getReference("users");
    signupBTN.setOnClickListener(btnListener);
  }

  /**
   * Adds an action to the button. Here we are calling the signup once the button is clicked.
   */
  private View.OnClickListener btnListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if(view.getId() == R.id.SignupBTN_Signup) { //Check if the click was on the button
        String email = emailET.getText().toString(); //Extract the text entered by the user
        String password = passwordET.getText().toString();

        if(!isBlank(email) && !isBlank(password)) {//Verification that the email and password
          signup(email, password);
        } else {
          alert("Email and password can't be blank.");
        }
      }
    }
  };

  private boolean isBlank(String s) {
    return s == null || s.length() == 0 || s.isEmpty();
  }

  /**
   * Calls the firebase create method to signup the user. Adds error handling to the UI.
   * @param email email of the user attained from the layout file.
   * @param password password of the user attained from the layout file.
   */
  private void signup(final String email, String password) {
    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()) {
          databaseRef.child(firebaseAuth.getCurrentUser().getUid()).child("email").setValue(email);
          startActivity(new Intent(getBaseContext(), ShoppingActivity.class));
          setResult(1);
          finish();
        } else {
          alert(task.getException().getMessage());
        }
      }
    });
  }

  private void alert(String message){
    Snackbar s = Snackbar.make(findViewById(R.id.Layout_Signup), message, Snackbar.LENGTH_LONG);
    s.show();
  }
}
