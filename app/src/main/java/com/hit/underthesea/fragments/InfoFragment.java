package com.hit.underthesea.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hit.underthesea.R;
import com.hit.underthesea.model.InfoData;


public class InfoFragment extends Fragment {

    private InfoData infoData;

    public void setInfoData(InfoData infoData) {
        this.infoData = infoData;
    }

    public InfoFragment() {
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
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        TextView title = view.findViewById(R.id.title);
        TextView body = view.findViewById(R.id.body);
        title.setText(infoData.getTitle());
        body.setText(infoData.getBody());

        return view;
    }
}