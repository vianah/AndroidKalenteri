package com.android.kalenteri;


import com.android.kalenteri.database.UserCourseDatabase;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCourseActivity extends AndroidKalenteriActivity {

	private TextView loggedAs;
	private Button createCourseButton;
	private EditText courseNameBox;
	private EditText coursePointsBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createcourse);
		
		super.createUserFromBundle();
		
		loggedAs = (TextView) findViewById(R.id.loggedAsAdmin);
		loggedAs.setText("Logged as: "+extras.getString("userName"));
		
		createCourseButton = (Button) findViewById(R.id.CreateCourse_button);
		courseNameBox = (EditText) findViewById(R.id.CreateCourse_courseNameEdit);
		coursePointsBox = (EditText) findViewById(R.id.CreateCourse_PointsEdit);
		
		dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
		createCourseButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String courseName = courseNameBox.getText().toString();
				int coursePoints;
				
				//checks if coursePointsBox is empty.
				try {
					coursePoints = Integer.parseInt(coursePointsBox.getText().toString());
				
				    //courseName null
					if(courseName.equals("")) {
						announcement = Toast.makeText(getApplicationContext(), "Course name must be defined!", Toast.LENGTH_SHORT);
						announcement.show();
					}
					//course exists
					else if(dataSource.doesCourseExist(courseName)) {
						announcement = Toast.makeText(getApplicationContext(), "Course already added!", Toast.LENGTH_SHORT);
						announcement.show();
					}
					//points are wrong
					else if (coursePoints < 1 || coursePoints > 9 ) {
						announcement = Toast.makeText(getApplicationContext(), 
								"Course points must be between 1 and 9!", Toast.LENGTH_SHORT);
						announcement.show();
					}
					
					//everything goes well
					else {
						boolean completed = dataSource.createCourse(courseNameBox.getText().toString(), 
							Integer.parseInt(coursePointsBox.getText().toString()));
						
						if(completed) {
							announcement =Toast.makeText(getApplicationContext(), "Course added!", Toast.LENGTH_SHORT);
							announcement.show();
							finish();
						}
					}
				}
				//if points field is empty
				catch (NumberFormatException e) {
					announcement = Toast.makeText(getApplicationContext(), "Points for course must be defined!", Toast.LENGTH_SHORT);
					announcement.show();
				}
			}
		});
	}

}
