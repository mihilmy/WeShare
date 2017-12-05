package edu.umd.cs.weshare.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.group.GroupActivity;
import edu.umd.cs.weshare.list.shopping.ShoppingActivity;

public class LoginActivity extends AppCompatActivity {
  private EditText emailET;
  private EditText passwordET;
  private Button loginBTN;
  private FirebaseAuth firebaseAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initVariables();
  }

  private void initVariables() {
    emailET = (EditText) findViewById(R.id.EmailET_Login);
    passwordET = (EditText) findViewById(R.id.PasswordET_Login);
    loginBTN = (Button) findViewById(R.id.LoginBTN_Login);
    firebaseAuth = FirebaseAuth.getInstance();
    loginBTN.setOnClickListener(btnListener);
  }

  private View.OnClickListener btnListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if(view.getId() == R.id.LoginBTN_Login) {
        String email = emailET.getText().toString(); //Extract the text entered by the user
        String password = passwordET.getText().toString();
        if(!isBlank(email) && !isBlank(password)) {//Verification that the email and password
          login(email, password);
        } else {
          alert("Email and password can't be blank.");
        }
      }
    }
  };

  private void login(String email, String password) {
    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()) {
          alert("Success logging in.");
          startActivity(new Intent(LoginActivity.this, ShoppingActivity.class));
          setResult(1);
          finish();
        } else {
          alert(task.getException().getMessage());
        }
      }
    });
  }

  private boolean isBlank(String s) {
    return s == null || s.length() == 0 || s.isEmpty();
  }

  private void alert(String message){
    Snackbar s = Snackbar.make(findViewById(R.id.Layout_Login), message, Snackbar.LENGTH_LONG);
    s.show();
  }
}
