package com.android.kalenteri;

import android.os.Bundle;
import com.android.kalenteri.database.*;

import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class ManageCoursesActivity extends AndroidKalenteriActivity {
	
	private Spinner courseSpinner;
	private Button enrollButton;
	private ListView activeCoursesList;
	private SimpleCursorAdapter spinnerAdapter;
	private Cursor spinnerData;
	private SimpleCursorAdapter listAdapter;
	private Cursor courseData;

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
		
		//fills Spinner
		bindSpinner();
		
		//fills ListView
		bindListView();
		
		registerForContextMenu(activeCoursesList);
		
		enrollButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				long courseId = courseSpinner.getSelectedItemId();
				int userId = user.getUserID();
				
				if(courseId == AdapterView.INVALID_ROW_ID) {
					announcement = Toast.makeText(getApplicationContext(), "No course selected", Toast.LENGTH_LONG);
					announcement.show();
				}
				else {
				dataSource.userToCourse(userId, (int)courseId);
				listUpdate();
				
				getToastWithTimelable("Enrollment successful!");
				}
				
			}
		});
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId()==R.id.Managecourses_activeCoursesList) {
		    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		    Cursor cur = (Cursor) activeCoursesList.getItemAtPosition(info.position);
		    menu.setHeaderTitle(cur.getString(1));
		    menu.add(ContextMenu.NONE, (int) cur.getLong(4), ContextMenu.NONE, "DELETE");
		}
	   }
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		boolean success = dataSource.deleteUserFromCourse(user.getUserID(), item.getItemId());
		listUpdate();
		
		getToastWithTimelable("Enrollment cancelled!");
		return success;
	}
	
	private void listUpdate() {
		if(spinnerData != null) {
		
			spinnerData.requery();
			spinnerAdapter.notifyDataSetChanged();
		}
		else {
			bindSpinner();
		}
		if(courseData != null) {
			
			courseData.requery();
			listAdapter.notifyDataSetChanged();
		}
		else {
			bindListView();
		}
	}
	//sets data to the spinner
	private void bindSpinner() {
		try {
			if(spinnerData == null) {
				spinnerData = dataSource.getNonUsersCourses(user);
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
	//sets data to the list
	private void bindListView() {
		try {
			if(courseData == null) {
				courseData = dataSource.getUsersUnfinishedCourses(user);
			}
			if(courseData.moveToFirst()) {
				listAdapter = new SimpleCursorAdapter(this, R.layout.usermanagecoursedbview, courseData, 
					new String [] {"coursename", "points"}, 
					new int[] {R.id.manageNameView, R.id.managePointsView});
				activeCoursesList.setAdapter(listAdapter);
			}
		}
		catch(DatabaseException e) {
			announcement = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
			announcement.show();
		}
	}
	
}
