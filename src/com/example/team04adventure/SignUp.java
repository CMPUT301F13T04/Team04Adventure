package com.example.team04adventure;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

	private EditText regUsernameText; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		Button Register = (Button) findViewById(R.id.register);

		Register.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v)
			{
				regUsernameText = (EditText) findViewById(R.id.registerusername);
				String Uname = regUsernameText.getText().toString();
				
				System.out.println("Username ===== " + Uname);
				
				Authenticator auth = new Authenticator();
				int yayOrNay = auth.authenticate(Uname);

				String message = "";

				switch (yayOrNay) {
				case -1:
					message = "Connection Failed.";
					break;
				case 0:
					int j = addUsername(Uname);
					if (j == 1) {
						Intent i = new Intent(SignUp.this, MainActivity.class);
						startActivity(i);
					}
					else {
						message = "Add Failed.";
					}
					break;
				case 1:
					message = "Username already existed";
					break;
				}
				
				Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
				
			}
		});
	}
	
	private int addUsername(String name) {
		User u = new User(name);
		JSONparser j = new JSONparser();
		try {
			j.storeUser(u);
		}
		catch (Exception e){
			return -1;
		}
		return 1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}
