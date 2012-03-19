package com.android.kalenteri;

import com.android.kalenteri.database.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateUserActivity extends AndroidKalenteriActivity {

	private EditText usernameBox;
	private EditText passwordBox;
	private EditText reTypeBox;
	private Button createUserButton;
	private TextView createUserInfo;
	
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
        
        dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
        createUserButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (passwordBox.getText().toString().equals(reTypeBox.getText().toString())) {
					createUserInfo.setText("");
					
					dataSource.createUser(usernameBox.getText().toString(), 
							passwordBox.getText().toString(), 0);
					
					Toast.makeText(getApplicationContext(), R.string.Createuser_success, 
							Toast.LENGTH_SHORT).show();
					
					finish();
				}
				else {
					createUserInfo.setText(R.string.Createuser_failed);
				}
			}
		});
		
	}

}
