package com.android.kalenteri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.text.format.Time;

import com.android.kalenteri.database.*;

public class AndroidKalenteriActivity extends Activity {

	protected User user;
	protected UserCourseDatabase dataSource;
	protected Toast announcement;
	protected Bundle extras;
	protected Time theTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	/*@Override
	protected void onResume() {
		dataSource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		dataSource.close();
		super.onPause();
	}*/
	
	//bundlesta User -olio
	protected void createUserFromBundle() {
		extras = this.getIntent().getExtras();
		user = new User(extras.getInt("userID"), 
				extras.getString("userName"), extras.getInt("userType"));
	}
	/*
	 * 	@pre extras != null
	 */
	protected Intent getIntentWithUserBundle(Class<?> cls) {
		Intent intent = new Intent(getApplicationContext(), cls);
		if(this.extras != null) intent.putExtras(extras);
		return intent;
	}
	
	// Palauttaa p‰iv‰m‰‰r‰n ja kellonajan muodossa: "\nDD/MM/YYYY HH:MM"
	protected String getTime() {
		theTime = new Time();
		theTime.setToNow();
		int offset = Integer.parseInt(theTime.format("%H")) + 3;
		return theTime.format("\n%d/%m/%Y " + ""+offset + ":%M");
	}
	

}
