package com.example.dopaminastudios.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dopaminastudios.myapplication.adapters.encountersAdapter;
import com.example.dopaminastudios.myapplication.model.Encounter;

import java.util.ArrayList;

public class CampaignsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private ImageButton addEncBtn;

    private ListView encItems;
    private encountersAdapter encountersadapter;

    public CampaignsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns);

        textView = (TextView)findViewById(R.id.textView2);
        String nameFromIntent = getIntent().getStringExtra(getString(R.string.campaign));
        textView.setText(R.string.play_now + nameFromIntent);

        encItems = (ListView)findViewById(R.id.encItems);
        encountersadapter = new encountersAdapter(this,GetArrayListItems() );
        encItems.setAdapter(encountersadapter);

        addEncBtn = findViewById(R.id.addEncBtn);
        addEncBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addEncBtn:
                Intent intentNEncounter = new Intent(getApplicationContext(), NewEncounter.class);
                startActivity(intentNEncounter);
                break;
        }
    }

    private ArrayList<Encounter> GetArrayListItems(){
        ArrayList<Encounter> listItems = new ArrayList<>();
        listItems.add(new Encounter());

        return listItems;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
