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

public class ManageCoursesActivity extends AndroidKalenteriActivity {
	
	private Spinner courseSpinner;
	private Button enrollButton;
	private ListView activeCoursesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managecourses);
		
		courseSpinner = (Spinner) findViewById(R.id.Managecourses_spinner);
		enrollButton = (Button) findViewById(R.id.Managecourses_button);
		activeCoursesList = (ListView) findViewById(R.id.Managecourses_activeCoursesList);
		
		// spinner-kokeilu
		/*ArrayAdapter adapteri = ArrayAdapter.createFromResource(this, R.array.kurssit_array, android.R.layout.simple_spinner_item);
		adapteri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    courseSpinner.setAdapter(adapteri);
	    */
	}

}
