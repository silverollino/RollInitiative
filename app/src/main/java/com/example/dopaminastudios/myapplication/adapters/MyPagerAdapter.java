package com.example.dopaminastudios.myapplication.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> pages = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public CharSequence getPageTitle(int position){
        return pages.get(position).toString();
    }

    public void addPage(Fragment fragment){
        pages.add(fragment);
    }
}
