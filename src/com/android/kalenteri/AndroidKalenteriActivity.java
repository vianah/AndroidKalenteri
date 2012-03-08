package com.android.kalenteri;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.format.Time;

public class AndroidKalenteriActivity extends Activity {
	
	private static String[] kayttaja = {"user","password"};
	private EditText kayttajanimikentta;
	private EditText salasanakentta;
	private Button nappula;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        kayttajanimikentta = (EditText)findViewById(R.id.editText1);
        salasanakentta = (EditText) findViewById(R.id.editText2);
        nappula = (Button) findViewById(R.id.button1);
        
        nappula.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(kayttajanimikentta.getText().toString().equals(kayttaja[0]) && 
						salasanakentta.getText().toString().equals(kayttaja[1])) {
					TextView teksti = (TextView) findViewById(R.id.textView4);
					//teksti.setText(R.string.kirjautuminen);
					Time today = new Time(Time.getCurrentTimezone());
					today.setToNow();
					teksti.setText("Kello on " + today.format("%k:%M:%S") + "!");
				}
				else {
					TextView teksti = (TextView) findViewById(R.id.textView4);
					teksti.setText("ei onnistu");
				}
			}
		});
        
    }
}