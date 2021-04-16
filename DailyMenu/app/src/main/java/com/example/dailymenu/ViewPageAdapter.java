package com.example.dailymenu;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override

    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new FavoriteFragment();
            case 2:
                return new NoteFragment();
            case 0:
            default:
                return new HomeFragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
