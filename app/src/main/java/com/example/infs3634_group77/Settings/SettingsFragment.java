package com.example.infs3634_group77.Settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.infs3634_group77.R;

import java.util.Objects;


public class SettingsFragment extends Fragment {

    //has text field with dummy data profile name

    public SettingsFragment() { }

    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        Button button  = v.findViewById(R.id.aboutbtn);
        button.setOnClickListener(view -> {
            Fragment fragment = new AcknowledgementFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.acknowledgement_frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        String [] values =
                {"Select Language","English","Japanese","Spanish"};
        Spinner spinner = v.findViewById(R.id.languages);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }
}
