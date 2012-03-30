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
	private Button manageCoursesButton;
	private ListView userCourseView;
	private Bundle extras;
	private SimpleCursorAdapter adapter;
	// listView:n esittely

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		userCourseView = (ListView) findViewById(R.id.Main_courseList);
		
		pointsInfo = (TextView) findViewById(R.id.Main_pointsView);
		// pointsInfo.setText(""); tähän pisteet tietokannasta
		
		manageCoursesButton = (Button) findViewById(R.id.Main_manageCoursesButton);
		
		//bundlesta User -olio
		extras = this.getIntent().getExtras();
		user = new User(extras.getInt("userID"), 
				extras.getString("userName"), extras.getInt("userType"));
		
		manageCoursesButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Intent intent = new Intent(getApplicationContext(), "kurssienhallinnointi".class);
				// startActivity(intent);
				
			}
		});
		
		//näkymä käyttäjän kursseille
		try {
			dataSource = new UserCourseDatabase(this);
			dataSource.open();
			
			Cursor courseData = dataSource.getUsersCourses(user);
			if(courseData.moveToFirst()) {
				adapter = new SimpleCursorAdapter(this, R.layout.usermaindbview, courseData, 
						new String [] {"coursename", "points"}, 
						new int[] {R.id.courseNameView, R.id.CoursePointsView});
				userCourseView.setAdapter(adapter);
				dataSource.close();
			}
					}
				catch(DatabaseException e) {
					announcement = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
					announcement.show();
				}		
		
	}

	/*@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.notifyDataSetChanged();
	}*/
	
	
	

}
