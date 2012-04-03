package com.android.kalenteri;

import java.util.ArrayList;

import com.android.kalenteri.database.DatabaseException;
import com.android.kalenteri.database.User;
import com.android.kalenteri.database.UserCourseDatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AcceptUserCourseActivity extends AndroidKalenteriActivity {
	
	//private TextView loggedAs;
	
	private Spinner courseSpinner;
	private Spinner gradeSpinner;
	private Spinner userSpinner;
	private Button acceptButton;
	private SimpleCursorAdapter spinnerAdapter;
	private Cursor spinnerData;
	private SimpleCursorAdapter userSpinnerAdapter;
	private Cursor userData;
	private TextView loggedAs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acceptcourse);
		
		super.createUserFromBundle();
		
		loggedAs = (TextView) findViewById(R.id.loggedAsAdmin);
		loggedAs.setText("Logged as: "+extras.getString("userName"));
		
		dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
		super.createUserFromBundle();
		
		courseSpinner = (Spinner) findViewById(R.id.Managecourses_spinner);
		gradeSpinner = (Spinner) findViewById(R.id.acceptCourses_GradeSpinner);
		userSpinner = (Spinner) findViewById(R.id.selectUserSpinner);
		acceptButton = (Button) findViewById(R.id.acceptCoursesAcceptButton);
		
		courseSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				listUpdate();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		acceptButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int userId = (int)userSpinner.getSelectedItemId();
				int courseId = (int)courseSpinner.getSelectedItemId();
				int grade = Integer.parseInt(gradeSpinner.getSelectedItem().toString());
				if(dataSource.acceptUsersCourse(courseId, userId, grade)) {
					//listUpdate();
					getToastWithTimelable("Course accepted succesfully!");
									
				}
			}
		});
		
		bindSpinner();
		fillGradeSpinner();
		//bindUserSpinner();
	}
	
	private void listUpdate() {
		bindUserSpinner();
	}
	
	private void bindSpinner() {
		try {
			if(spinnerData == null) {
				spinnerData = dataSource.getAllCourses();
			}
			if(spinnerData.moveToFirst()) {
				spinnerAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, spinnerData, 
						new String [] {"coursename"},
						new int[] {android.R.id.text1});
				spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				courseSpinner.setAdapter(spinnerAdapter);
			}			
		}
		catch(DatabaseException e) {
			announcement = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
			announcement.show();
		}
	}
	private void fillGradeSpinner() {
		String[] values = {"1","2","3","4","5"};
		ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, values);
		gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gradeSpinner.setAdapter(gradeAdapter);
	}
	
	private boolean bindUserSpinner() {
		try {
				
			userData = dataSource.usersOnCourse((int)courseSpinner.getSelectedItemId());
			
			if(userData.moveToFirst()) {
				userSpinnerAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, userData, 
													new String [] {"username"}, 
													new int[] {android.R.id.text1});
				userSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				userSpinner.setAdapter(userSpinnerAdapter);				
			}
		}
		catch(DatabaseException e) {
			announcement = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
			announcement.show();
		}
		return true;
	}
	/*
	protected void onDestroy() {
		super.onStop();
		dataSource.close();
	}
	*/
	
}