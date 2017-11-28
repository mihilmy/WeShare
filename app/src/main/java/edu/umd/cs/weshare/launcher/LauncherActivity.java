package edu.umd.cs.weshare.launcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.umd.cs.weshare.R;
import edu.umd.cs.weshare.group.GroupActivity;
import edu.umd.cs.weshare.login.LoginActivity;
import edu.umd.cs.weshare.signup.SignupActivity;

public class LauncherActivity extends AppCompatActivity {
  private Button signupBTN;
  private Button loginBTN;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);
    skipOnAuthenticated();
    initVariables();
  }

  private void skipOnAuthenticated() {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if(user != null) {
      startActivity(new Intent(this, GroupActivity.class));
    }
  }
  private void initVariables() {
    signupBTN = (Button) findViewById(R.id.SignupBTN_Launcher);
    loginBTN = (Button) findViewById(R.id.LoginBTN_Launcher);
    signupBTN.setOnClickListener(btnListener);
    loginBTN.setOnClickListener(btnListener);
  }

  private View.OnClickListener btnListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if(view.getId() == R.id.SignupBTN_Launcher) {
        //Intent is the object that initiates the movement from one activity to another
        //The first paramater is always CurrentActivity.this
        //The second parameter is always DestinationActivity.class
        Intent intent = new Intent(LauncherActivity.this, SignupActivity.class);
        //Starts the transition to a new activity
        startActivity(intent);
      } else if (view.getId() == R.id.LoginBTN_Launcher) {
        Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
        startActivity(intent);
      }
    }
  };
}
