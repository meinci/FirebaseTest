package com.mehmet_inci.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mehmet_inci.firebasetest.databinding.ActivityFeedBinding;
import com.mehmet_inci.firebasetest.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {
	private ActivitySignupBinding binding;
	private FirebaseAuth auth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivitySignupBinding.inflate(getLayoutInflater());
		View view = binding.getRoot();
		setContentView(view);

		auth = FirebaseAuth.getInstance();

	}
	public void signup_button(View view){
		String email = binding.emailText.getText().toString();
		String password = binding.passwordText.getText().toString();
		auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
			@Override
			public void onSuccess(AuthResult authResult) {
				Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
				startActivity(intent);
				finish();
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
			}
		});



	}
	public void signin_button(View view){

	}
}