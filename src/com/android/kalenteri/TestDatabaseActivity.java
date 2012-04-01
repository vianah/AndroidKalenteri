package com.android.kalenteri;

import com.android.kalenteri.database.*;

import java.util.List;

import org.w3c.dom.Text;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TestDatabaseActivity extends ListActivity {
	
	private UserCourseDatabase datasource;
	private Button addCourse;
	private Button addUserToCourse;
	private TextView failureInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.databasetest);

		datasource = new UserCourseDatabase(this);
		datasource.open();

		List<User> values = datasource.getAllUsers();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
	    failureInfo = (TextView) findViewById(R.id.course_failure);
		
		addCourse = (Button) findViewById(R.id.test_addCourse);
		addCourse.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 failureInfo.setText(new Boolean(datasource.createCourse("Matikka", 3)).toString());
				
			}
		});
		addUserToCourse = (Button) findViewById(R.id.test_addUserToCourse);
		addUserToCourse.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				failureInfo.setText(new Boolean(datasource.userToCourse(1, 2)).toString());
			}
		});
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
		User user = null;
		/*switch (view.getId()) {
		case R.id.add:*/
			String[] userDetails = new String[] { "anssi", "pass", "1" };
			// Save the new comment to the database
			user = datasource.createUser(userDetails[0], userDetails[1], Integer.parseInt(userDetails[2]));
			adapter.add(user);
			/*break;*/
		/*case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				comment = (Comment) getListAdapter().getItem(0);
				datasource.deleteComment(comment);
				adapter.remove(comment);
			}
			break;*/
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}