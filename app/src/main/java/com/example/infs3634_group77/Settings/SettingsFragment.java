package com.example.infs3634_group77.Settings;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.infs3634_group77.R;

import java.util.Objects;


public class SettingsFragment extends Fragment {

    private SeekBar seekBar;
    private AudioManager audioManager;
    //Adding text so its pushable
    //has text field with dummy data profile name

    public SettingsFragment() { }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        Button button  = v.findViewById(R.id.aboutbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AcknowledgementFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            }
        });

        String [] values =
                {"Select Language","English","Japanese","Spanish"};
        Spinner spinner = v.findViewById(R.id.languages);
        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, values);
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mSortAdapter);

        seekBar = v.findViewById(R.id.seekBar);
        audioManager = (AudioManager) getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        return v;
    }
}
