package com.android.kalenteri;

import com.android.kalenteri.database.DatabaseException;
import com.android.kalenteri.database.User;
import com.android.kalenteri.database.UserCourseDatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.format.Time;

public class LoginActivity extends AndroidKalenteriActivity {
	
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
        dataSource = new UserCourseDatabase(this);
        dataSource.open();
        
        loginButton.setOnClickListener(new View.OnClickListener() {
			
        	/*
        	 * Login-toiminnallisuus:
        	 * Haetaan tekstikentist‰ usernameBox:n ja passwordBox:n vastaavat tiedot String-muuttujiin
        	 * Luodaan yhteys tietokantaan ja tallennetaan User-olioon n‰m‰ tiedot
        	 * Tarkastetaan onko k‰ytt‰j‰ admin: 0 tarkoittaa (t‰ss‰) adminia ja kaikki muu normaalia k‰ytt‰j‰‰
        	 * Admin-k‰ytt‰j‰ ohjataan AdminMainActivity -aktiviteettiin, muut (normaalit) MainActivity:n
        	 * Suljetaan viel‰ tietokantayhteys kaikissa tapauksissa :)
        	 */
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = usernameBox.getText().toString();
				String password = passwordBox.getText().toString();
				Intent flow;
				
				try {
					user = dataSource.loginUserMake(username, password);
					
					if(user.getUserType()==0) {
						flow = new Intent(getApplicationContext(), MainActivity.class);
				
					}
					else {
						flow = new Intent(getApplicationContext(), AdminMainActivity.class);
						
					}
					flow.putExtra("userName", user.getUsername());
					flow.putExtra("userType", user.getUserType());
					flow.putExtra("userID", user.getUserID());
					dataSource.close();
					getToastWithTimelable("Login successful!");
					startActivity(flow);
					finish();
				}
				catch (DatabaseException e) {
					loginInfo.setText(e.toString());
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