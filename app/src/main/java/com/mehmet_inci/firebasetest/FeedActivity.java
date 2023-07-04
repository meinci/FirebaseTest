package com.mehmet_inci.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class FeedActivity extends AppCompatActivity {
	private FirebaseAuth auth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);

		auth = FirebaseAuth.getInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.add_post){
			//Upload Activity
		} else if (item.getItemId() == R.id.signout) {
			//Signout
			auth.signOut();
			Intent intentToSignUp = new Intent(FeedActivity.this, SignUpActivity.class);
			startActivity(intentToSignUp);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}