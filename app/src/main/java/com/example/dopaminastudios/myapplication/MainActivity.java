package com.example.dopaminastudios.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dopaminastudios.myapplication.helper.InputValidation;
import com.example.dopaminastudios.myapplication.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;

    private int STORAGE_PERMISSION_CODE = 1;
    ConstraintLayout constraintLayout;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;


    MediaPlayer mediaPlayer;
    int currentPos;
    private VideoView videoBackground;

    private TextView textView;
    private TextView registerTextView;
    private Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VIDEO BACKGORUND
        videoBackground = findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.video_bkgn); //Asignar el archivo del video a la videoview

        videoBackground.setVideoURI(uri);
        videoBackground.start();

        videoBackground.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                mediaPlayer.setLooping(true);

                if(currentPos != 0){
                    mediaPlayer.seekTo(currentPos);
                    mediaPlayer.start();
                }
            }
        });




        //SQLITE
        //getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentPos = mediaPlayer.getCurrentPosition();
        videoBackground.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoBackground.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void initViews() {
        textView = findViewById(R.id.nameTextView);
        loginButton = findViewById(R.id.registerBtn);
        registerTextView = findViewById(R.id.registerLink);
    }
    private void initListeners() {
        loginButton.setOnClickListener(this);
        registerTextView.setOnClickListener(this);
    }


    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn:
                verifyFromSqLite();
                permission();
                break;
            case R.id.registerLink:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void permission() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,R.string.permissiongranted, Toast.LENGTH_LONG).show();
        }else{
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            new AlertDialog.Builder(this)
                    .setTitle(R.string.needspermision)
                    .setMessage(R.string.permisionmotive)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,R.string.permissiongranted,Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,R.string.needspermision,Toast.LENGTH_LONG).show();
            }
        }
    }

    private void verifyFromSqLite() {

        Context context = getApplicationContext();
        CharSequence errtext = getText(R.string.error_valid_campaign).toString();
        int duration = Toast.LENGTH_LONG;


        if(!inputValidation.isInputEditTextFilled(textView, getString(R.string.error_message)))
            return;
        if(databaseHelper.chkCampaign(textView.getText().toString().trim())){
            Intent campaignIntent = new Intent(activity, CampaignsActivity.class);
            campaignIntent.putExtra(getString(R.string.campaign) , textView.getText().toString().trim());
            emptyInputTextView();
            startActivity(campaignIntent);
        }else {
            Toast toast = Toast.makeText(context, errtext, duration);
            toast.show();
        }
    }

    private void emptyInputTextView() {
        textView.setText(null);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
