package com.mehmet_inci.firebasetest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.mehmet_inci.firebasetest.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {
	Uri imageData;
	ActivityResultLauncher<Intent> activityResultLauncher;
	ActivityResultLauncher<String> permissionLauncer;
	private ActivityUploadBinding binding;
	Bitmap selectedImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityUploadBinding.inflate(getLayoutInflater());
		View view = binding.getRoot();
		setContentView(view);
	}

	private void upload(View view){

	}
	private void selectimage(View view){
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
				Snackbar.make(view, "Permission needed for Gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give permission", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//Ask Permission
					}
				}).show();
			}else {
				//Ask permission
			}
		} else {
			Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		}
	}

	private void registerLauncher(){
		activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
			@Override
			public void onActivityResult(ActivityResult result) {
				if (result.getResultCode() == RESULT_OK) {
					Intent intentFromResult = result.getData();
					if (intentFromResult != null) {
						imageData = intentFromResult.getData();
						binding.idSelectImage.setImageURI(imageData);

						//Bitmap version alternativ if needed
						/*try {
							if (Build.VERSION.SDK_INT >= 28) {
								ImageDecoder.Source source = ImageDecoder.createSource(UploadActivity.this.getContentResolver(),imageData);
								 selectedImage = ImageDecoder.decodeBitmap(source);
								binding.idSelectImage.setImageBitmap(selectedImage);
							} else {
								selectedImage = MediaStore.Images.Media.getBitmap(UploadActivity.this.getContentResolver(),imageData);
								binding.idSelectImage.setImageBitmap(selectedImage);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}*/
					}
				}
			}
		});
	}
}