package com.android.kalenteri;

import com.android.kalenteri.database.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateUserActivity extends AndroidKalenteriActivity {

	private EditText usernameBox;
	private EditText passwordBox;
	private EditText reTypeBox;
	private Button createUserButton;
	private TextView createUserInfo;
	private CheckBox checkAdmin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createuser);
		
		usernameBox = (EditText)findViewById(R.id.Createuser_usernameEdit);
        passwordBox = (EditText) findViewById(R.id.Createuser_passwordEdit);
        reTypeBox = (EditText) findViewById(R.id.Createuser_retypeEdit);
        createUserButton = (Button) findViewById(R.id.Createuser_button);
        createUserInfo = (TextView) findViewById(R.id.Createuser_info);
        checkAdmin = (CheckBox) findViewById(R.id.Createuser_checkBox);
        
        dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
        createUserButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String password = passwordBox.getText().toString();
				String rePassword = reTypeBox.getText().toString();
				String username = usernameBox.getText().toString();
				
				if (username.equals("")) {
					announcement = Toast.makeText(getApplicationContext(), "username equals null", 
							Toast.LENGTH_SHORT);
					announcement.show();
				}
				if (dataSource.doesUserExist(username)) {
					announcement = Toast.makeText(getApplicationContext(), "username is already used", 
							Toast.LENGTH_SHORT);
					announcement.show();
				}
				else if (password.equals("")) {
					announcement = Toast.makeText(getApplicationContext(), "password equals null", 
							Toast.LENGTH_SHORT);
					announcement.show();
				}
				else if (!(password.equals(rePassword))) {
					announcement = Toast.makeText(getApplicationContext(), "passwords are different", 
							Toast.LENGTH_SHORT);
					announcement.show();
				}
				else {
					
					createUserInfo.setText("");
					
					if(checkAdmin.isChecked()) {
							dataSource.createUser(usernameBox.getText().toString(), 
							passwordBox.getText().toString(), 1);
					}
					else {
							dataSource.createUser(usernameBox.getText().toString(), 
							passwordBox.getText().toString(), 0);
					}

					
					announcement = Toast.makeText(getApplicationContext(), R.string.Createuser_success + getTime(), 
							Toast.LENGTH_SHORT);
					announcement.show();
					
					finish();
				}
			}
		});
		
	}

}
