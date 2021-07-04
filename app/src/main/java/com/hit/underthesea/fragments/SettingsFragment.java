package com.hit.underthesea.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.hit.underthesea.MusicPlayer;
import com.hit.underthesea.R;
import com.hit.underthesea.model.InfoData;
import java.util.Objects;

public class SettingsFragment extends Fragment implements View.OnClickListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(getActivity());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //הוא מוסיף את האבא כי יש תכונות באקסמל שמותאמים לאבא, לכן ברגע שהוא יקבל את ההורה הם יוכלו להתאים את עצמם
        View view = inflater.inflate(R.layout.settings, container, false);
        Button howToPlay = view.findViewById(R.id.howToPlay_btn);
        Button credit = view.findViewById(R.id.credit_btn);
        SwitchMaterial music = view.findViewById(R.id.switch_music_btn);

        // Saving "off" state of switch
        AudioManager manager = (AudioManager) requireActivity().getSystemService(Context.AUDIO_SERVICE);
        if(!manager.isMusicActive())
            music.setChecked(false);

        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bool) {
                if(bool)
                    MusicPlayer.getInstance().play(true);
                else{
                    MusicPlayer.getInstance().pause(true);
                }
            }
        });
        howToPlay.setOnClickListener(this);
        credit.setOnClickListener(this);
        view.findViewById(R.id.exit_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When X pressed, we exit from fragment into the main screen
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        // After the inflation, we return the view to show it as the fragment screen
        return view;
    }

    @Override
    public void onClick(View v) {
        InfoFragment fragment = new InfoFragment();

        // Which data would be shown
        switch (v.getId()) {
            case R.id.howToPlay_btn:
                fragment.setInfoData(new InfoData(getString(R.string.how_to_play), getString(R.string.howtoplay_explaination)));
                break;
            case R.id.credit_btn:
                fragment.setInfoData(new InfoData(getString(R.string.credits), getString(R.string.credits_explaination)));
                break;
        }
        // Creating new fragment and transferring the chosen information
        getChildFragmentManager().beginTransaction().add(R.id.child_container, fragment, null).commit();
    }


}