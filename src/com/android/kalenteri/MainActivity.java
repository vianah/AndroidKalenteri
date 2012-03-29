package com.android.kalenteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AndroidKalenteriActivity {
	
	private TextView pointsInfo;
	private Button manageCoursesButton;
	// listView:n esittely

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		pointsInfo = (TextView) findViewById(R.id.Main_pointsView);
		// pointsInfo.setText(""); tähän pisteet tietokannasta
		
		manageCoursesButton = (Button) findViewById(R.id.Main_manageCoursesButton);
		
		manageCoursesButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Intent intent = new Intent(getApplicationContext(), "kurssienhallinnointi".class);
				// startActivity(intent);
				
			}
		});
	}
	

}
