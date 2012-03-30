package com.android.kalenteri;

import com.android.kalenteri.database.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AdminMainActivity extends AndroidKalenteriActivity {

	private Button addCourse;
	private ListView courseListView;
	private TextView loggedAs;
	private Bundle extras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminmain);
		
		extras = this.getIntent().getExtras();
		user = new User(extras.getInt("userID"), 
				extras.getString("userName"), extras.getInt("userType"));
		loggedAs = (TextView) findViewById(R.id.loggedAsAdmin);
		loggedAs.setText("Logged as: "+extras.getString("userName"));
		
		addCourse = (Button) findViewById(R.id.AdminMain_manageCoursesButton);
		addCourse.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), CreateCourseActivity.class);
				startActivity(intent);
			}
		});
		
	}

	

}
