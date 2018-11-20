package com.example.dopaminastudios.myapplication;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dopaminastudios.myapplication.adapters.MyPagerAdapter;
import com.example.dopaminastudios.myapplication.fragments.EnemyFragment;
import com.example.dopaminastudios.myapplication.fragments.InitiativeOrderFragment;
import com.example.dopaminastudios.myapplication.fragments.PlayerFragment;
import com.example.dopaminastudios.myapplication.model.Player;
import com.example.dopaminastudios.myapplication.sql.DatabaseAdapter;

public class NewEncounter extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    int currentPos = 0;

    EditText nameEditText;
    Button saveBtn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_encounter);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        addPages();

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
    }

    private void displayDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setTitle("PARTICIPANTE NUEVO");
        dialog.setContentView(R.layout.dialog_layout);

        nameEditText = (EditText)dialog.findViewById(R.id.nameEditTxt);
        saveBtn = (Button)dialog.findViewById(R.id.saveBtn);
        spinner = (Spinner)dialog.findViewById(R.id.category_SP);

        String[] categories = {PlayerFragment.newInstance().toString(),
        EnemyFragment.newInstance().toString()};

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player();
                player.setPlayerName(nameEditText.getText().toString().trim());
                player.setCategory(spinner.getSelectedItem().toString().trim());

                if(new DatabaseAdapter(NewEncounter.this).savePlayer(player)){
                    nameEditText.setText("");
                    spinner.setSelection(0);
                    Toast.makeText(NewEncounter.this, "Nuevo PC creado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(NewEncounter.this, "No guardado", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    private void addPages(){

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addPage(PlayerFragment.newInstance());
        myPagerAdapter.addPage(EnemyFragment.newInstance());
        myPagerAdapter.addPage(InitiativeOrderFragment.newInstance());

        viewPager.setAdapter(myPagerAdapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(currentPos = tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.addMenu){
            displayDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
