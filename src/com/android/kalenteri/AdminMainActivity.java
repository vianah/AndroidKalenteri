package com.android.kalenteri;

import com.android.kalenteri.database.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminMainActivity extends AndroidKalenteriActivity {

	private Button addCourse;
	private Button acceptCourse;
	private ListView courseListView;
	private TextView loggedAs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminmain);
		
		super.createUserFromBundle();
		
		loggedAs = (TextView) findViewById(R.id.loggedAsAdmin);
		loggedAs.setText("Logged as: "+extras.getString("userName"));
		
		addCourse = (Button) findViewById(R.id.AdminMain_manageCoursesButton);
		addCourse.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = getIntentWithUserBundle(CreateCourseActivity.class);
				startActivity(intent);
			}
		});
		
		acceptCourse = (Button) findViewById(R.id.AdminMain_acceptCourseButton);
		acceptCourse.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = getIntentWithUserBundle(AcceptUserCourseActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
	
	//back napppulasta takaisin LoginActivityyn
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	
	    	announcement = Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT);
	    	announcement.show();
	    	startActivity(getIntentWithUserBundle(LoginActivity.class));
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
}