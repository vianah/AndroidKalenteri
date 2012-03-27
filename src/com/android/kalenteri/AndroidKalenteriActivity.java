package com.android.kalenteri;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.kalenteri.database.*;

public class AndroidKalenteriActivity extends Activity {

	protected User user;
	protected UserCourseDatabase dataSource;
	protected Toast announcement;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

}
