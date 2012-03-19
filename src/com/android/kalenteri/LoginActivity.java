package com.android.kalenteri;

import com.android.kalenteri.database.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.format.Time;

public class LoginActivity extends AndroidKalenteriActivity {
	
	private static String[] userInfo = {"u","p"};
	private EditText usernameBox;
	private EditText passwordBox;
	private Button loginButton;
	private TextView loginInfo;
	private Button createUserButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        usernameBox = (EditText)findViewById(R.id.Login_usernameEdit);
        passwordBox = (EditText) findViewById(R.id.Login_passwordEdit);
        loginButton = (Button) findViewById(R.id.Login_button);
        loginInfo = (TextView) findViewById(R.id.Login_info);
        createUserButton = (Button) findViewById(R.id.Login_createUserButton);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(usernameBox.getText().toString().equals(userInfo[0]) && 
						passwordBox.getText().toString().equals(userInfo[1])) {
					//v‰liaikainen muutos t‰h‰n kohtaan, ett‰ ohjelma k‰‰ntyi
					//1 lis‰tty User-olion luontiin, koska sen konstruktori muuttui
					user = new User(1,userInfo[0],1);
					Intent flow = new Intent(getApplicationContext(), MainActivity.class);
					flow.putExtra("userName", user.getUsername());
					flow.putExtra("userType", user.getUserType());
					startActivity(flow);
					finish();
					
					//Time today = new Time(Time.getCurrentTimezone());
					//today.setToNow();
					//LoginInfo.setText("Kello on " + today.format("%k:%M:%S") + "!");
				}
				else {
					loginInfo.setText(R.string.Login_failedtext);
				}
			}
		});
        
        createUserButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), CreateUserActivity.class);
				startActivity(intent);
			}
		});
        
    }
}