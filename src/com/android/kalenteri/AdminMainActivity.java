package com.android.kalenteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class AdminMainActivity extends AndroidKalenteriActivity {

	private Button addCourse;
	private ListView courseListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminmain);
		
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
