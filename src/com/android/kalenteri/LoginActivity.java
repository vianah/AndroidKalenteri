package com.android.kalenteri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.format.Time;

public class LoginActivity extends AndroidKalenteriActivity {
	
	private static String[] UserInfo = {"u","p"};
	private EditText UsernameBox;
	private EditText PasswordBox;
	private Button LoginButton;
	private TextView LoginInfo;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        UsernameBox = (EditText)findViewById(R.id.Login_usernameEdit);
        PasswordBox = (EditText) findViewById(R.id.Login_passwordEdit);
        LoginButton = (Button) findViewById(R.id.Login_button);
        LoginInfo = (TextView) findViewById(R.id.Login_info);
        
        LoginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(UsernameBox.getText().toString().equals(UserInfo[0]) && 
						PasswordBox.getText().toString().equals(UserInfo[1])) {
					
					Intent flow = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(flow);
					
					//Time today = new Time(Time.getCurrentTimezone());
					//today.setToNow();
					//LoginInfo.setText("Kello on " + today.format("%k:%M:%S") + "!");
				}
				else {
					LoginInfo.setText(R.string.Login_failedtext);
				}
			}
		});
        
    }
}