package com.android.kalenteri;


import com.android.kalenteri.database.UserCourseDatabase;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateCourseActivity extends AndroidKalenteriActivity {

	private Button createCourseButton;
	private EditText courseNameBox;
	private EditText coursePointsBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createcourse);
		
		createCourseButton = (Button) findViewById(R.id.CreateCourse_button);
		courseNameBox = (EditText) findViewById(R.id.CreateCourse_courseNameEdit);
		coursePointsBox = (EditText) findViewById(R.id.CreateCourse_PointsEdit);
		
		dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
		createCourseButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean completed = dataSource.createCourse(courseNameBox.getText().toString(), 
						Integer.parseInt(coursePointsBox.getText().toString()));
				if(completed) {
					Toast.makeText(getApplicationContext(), "Course added!", Toast.LENGTH_SHORT);
					finish();
				}
			}
		});
	}

}
