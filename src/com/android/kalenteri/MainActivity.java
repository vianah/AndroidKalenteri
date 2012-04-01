package com.android.kalenteri;

import com.android.kalenteri.database.DatabaseException;
import com.android.kalenteri.database.DbSQLiteHelper;
import com.android.kalenteri.database.User;
import com.android.kalenteri.database.UserCourseDatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AndroidKalenteriActivity {
	
	private TextView pointsInfo;
	private TextView loggedAs;
	private Button manageCoursesButton;
	private ListView userCourseView;
	private SimpleCursorAdapter adapter;
	private Cursor courseData;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		dataSource = new UserCourseDatabase(this);
		dataSource.open();
		
		super.createUserFromBundle();
		
		userCourseView = (ListView) findViewById(R.id.Main_courseList);
		
		pointsInfo = (TextView) findViewById(R.id.Main_pointsView);
		pointsInfo.setText("CP: " + dataSource.getUserPoints(user)); //tähän pisteet tietokannasta
		
		manageCoursesButton = (Button) findViewById(R.id.Main_manageCoursesButton);
		
		loggedAs = (TextView) findViewById(R.id.menu_loggedAs);
		loggedAs.setText("Logged as: "+extras.getString("userName"));
		
		manageCoursesButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//Intent intent = new Intent(getApplicationContext(), ManageCoursesActivity.class);
				Intent intent = makeIntentWithUserBundle(ManageCoursesActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		//näkymä käyttäjän kursseille
		try {
			
			courseData = dataSource.getUsersCourses(user);
			if(courseData.moveToFirst()) {
				adapter = new SimpleCursorAdapter(this, R.layout.usermaindbview, courseData, 
						new String [] {"coursename", "points"}, 
						new int[] {R.id.courseNameView, R.id.CoursePointsView});
				userCourseView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				dataSource.close();
			}
		}
				catch(DatabaseException e) {
					announcement = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
					announcement.show();
				}		
		
	}

	
	
	
	

}
