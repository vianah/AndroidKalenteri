package com.android.kalenteri;

import android.os.Bundle;
import android.widget.TextView;

public class AcceptUserCourseActivity extends AndroidKalenteriActivity {
	
	private TextView loggedAs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acceptcourse);
		
		//super.createUserFromBundle();
		
		/*loggedAs = (TextView) findViewById(R.id.loggedAsAdmin);
		loggedAs.setText("Logged as: "+extras.getString("userName"));*/
	}
	
}
