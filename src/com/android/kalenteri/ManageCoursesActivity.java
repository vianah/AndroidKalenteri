package com.android.kalenteri;

import android.os.Bundle;
import com.android.kalenteri.database.*;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class ManageCoursesActivity extends AndroidKalenteriActivity {
	
	private Spinner courseSpinner;
	private Button enrollButton;
	private ListView activeCoursesList;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managecourses);
		
		dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
		super.createUserFromBundle();
		
		courseSpinner = (Spinner) findViewById(R.id.Managecourses_spinner);
		enrollButton = (Button) findViewById(R.id.Managecourses_button);
		activeCoursesList = (ListView) findViewById(R.id.Managecourses_activeCoursesList);
		
		try {
			
			Cursor courseData = dataSource.getNonUsersCourses(user);
			if(courseData.moveToFirst()) {
				adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, courseData, 
						new String [] {"coursename"}, 
						new int[] {android.R.id.text1});
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				courseSpinner.setAdapter(adapter);
				dataSource.close();
				}
			}
				catch(DatabaseException e) {
					announcement = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
					announcement.show();
				}
		
		enrollButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int item = (int) courseSpinner.getSelectedItemId();
				announcement = Toast.makeText(getApplicationContext(), ""+item, Toast.LENGTH_LONG);
				announcement.show();
			}
		});
	}

}
