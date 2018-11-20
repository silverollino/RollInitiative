package com.example.dopaminastudios.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dopaminastudios.myapplication.helper.InputValidation;
import com.example.dopaminastudios.myapplication.model.Campaign;
import com.example.dopaminastudios.myapplication.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout constraintLayout;
    private final AppCompatActivity activity = RegisterActivity.this;

    private TextView textView;
    private Button registerButton;

    MediaPlayer mediaPlayer;
    int currentPos;
    private VideoView videoBackground;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //VIDEO BACKGORUND
        videoBackground = findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.register_bkgnd); //Asignar el archivo del video a la videoview

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
        textView = (TextView)findViewById(R.id.nameTextView);
        registerButton = (Button)findViewById(R.id.registerBtn);
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);

    }
    private void initListeners() {
        registerButton.setOnClickListener(this);
    }


    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        campaign = new Campaign();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn:
                postDataSQLite();
                break;
            case R.id.loginLink:
                finish();
                break;
        }
    }

    private void postDataSQLite() {

        Context context = getApplicationContext();
        CharSequence errtext = getText(R.string.error_valid_campaign).toString();
        CharSequence succtext = getText(R.string.succ_valid_campaign).toString();
        int duration = Toast.LENGTH_LONG;

        if(!inputValidation.isInputEditTextFilled(textView, getString(R.string.error_message)))
            return;
        if(!databaseHelper.chkCampaign(textView.getText().toString().trim())){
            campaign.setCampaing(textView.getText().toString().trim());
            databaseHelper.addCampaign(campaign);

            Toast toast = Toast.makeText(context, succtext, duration);
            toast.show();
            emptyInputTextView();
            finish();
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
