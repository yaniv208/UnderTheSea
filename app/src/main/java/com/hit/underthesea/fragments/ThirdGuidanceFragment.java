package com.hit.underthesea.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import com.google.android.material.button.MaterialButton;
import com.hit.underthesea.R;

import java.util.Objects;

public class ThirdGuidanceFragment extends Fragment implements View.OnClickListener{

    public ThirdGuidanceFragment(){
        // default empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(getActivity());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guidance_third, container, false);
        ImageView stone;
        stone = view.findViewById(R.id.stone_guidance);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.swipe_left);
        animation.setRepeatMode(Animation.INFINITE);
        stone.startAnimation(animation);

        MaterialButton nextBtn = view.findViewById(R.id.finish_guidance);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) { }
}
