package com.example.dopaminastudios.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dopaminastudios.myapplication.R;
import com.example.dopaminastudios.myapplication.model.Player;
import com.example.dopaminastudios.myapplication.sql.DatabaseAdapter;

public class EnemyFragment extends Fragment {

    ListView listView;
    Button refreshButton;
    ArrayAdapter<Player> adapter;

    public static EnemyFragment newInstance(){
        return new EnemyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.enemy, null);

        initializeViews(rootView);
        loadData();

        return rootView;
    }

    private void initializeViews(View rootView){
        listView = (ListView)rootView.findViewById(R.id.enemy_LV);
        refreshButton = (Button)rootView.findViewById(R.id.enemRefresh);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        DatabaseAdapter db = new DatabaseAdapter(getActivity());
        adapter = new ArrayAdapter<Player>(getActivity(),android.R.layout.simple_list_item_1,db.retrievePlayer(this.toString()));

    }

    @Override
    public String toString() {
        return "Enemigo";
    }
}
