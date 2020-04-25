package com.example.infs3634_group77.Settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.infs3634_group77.R;


public class AcknowledgementFragment extends Fragment {

    public AcknowledgementFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_acknowledgement, container, false);

        Button button  = v.findViewById(R.id.backbtn);
        button.setOnClickListener(v1 -> {
            Fragment fragment = new SettingsFragment();
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
        });

        return v;
    }
}
