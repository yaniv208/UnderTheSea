package com.hit.underthesea.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hit.underthesea.R;


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
        View view = inflater.inflate(R.layout.menu, container, false);
        Button howToPlay = view.findViewById(R.id.howToPlay_btn);
        howToPlay.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.howToPlay_btn:
                getChildFragmentManager().beginTransaction().add(R.id.child_container, new HowToPlayFragment(), null).commit();
                break;
        }
    }
}