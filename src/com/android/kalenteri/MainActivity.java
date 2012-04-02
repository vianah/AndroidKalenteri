package com.android.kalenteri;

import com.android.kalenteri.database.DatabaseException;
import com.android.kalenteri.database.DbSQLiteHelper;
import com.android.kalenteri.database.User;
import com.android.kalenteri.database.UserCourseDatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
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
		pointsInfo.setText("CP: " + dataSource.getUserPoints(user)); //t�h�n pisteet tietokannasta
		
		manageCoursesButton = (Button) findViewById(R.id.Main_manageCoursesButton);
		
		loggedAs = (TextView) findViewById(R.id.menu_loggedAs);
		loggedAs.setText("Logged as: "+extras.getString("userName"));
		
		manageCoursesButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//Intent intent = new Intent(getApplicationContext(), ManageCoursesActivity.class);
				Intent intent = getIntentWithUserBundle(ManageCoursesActivity.class);
				startActivity(intent);
				
				
			}
		});
		
		//n�kym� k�ytt�j�n kursseille
		try {
			courseData = dataSource.getUsersCourses(user);
			if(courseData.moveToFirst()) {
				adapter = new SimpleCursorAdapter(this, R.layout.usermaindbview, courseData, 
						new String [] {"coursename", "finished", "points"}, 
						new int[] {R.id.courseNameView, R.id.CourseGradeView,R.id.CoursePointsView});
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
	    	
	    	announcement = Toast.makeText(getApplicationContext(), "Logging out..." + getTime(), Toast.LENGTH_SHORT);
	    	announcement.show();
	    	startActivity(getIntentWithUserBundle(LoginActivity.class));
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
	
	

}
