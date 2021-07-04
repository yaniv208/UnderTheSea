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

public class SecondGuidanceFragment extends Fragment implements View.OnClickListener{

    public SecondGuidanceFragment(){
        // default empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(getActivity());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guidance_second, container, false);

        ImageView guidanceFood;
        guidanceFood = view.findViewById(R.id.food_guidance);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.swipe_left);
        animation.setRepeatMode(Animation.INFINITE);
        guidanceFood.startAnimation(animation);

        MaterialButton nextBtn = view.findViewById(R.id.second_guidance_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerplay, new ThirdGuidanceFragment(), null).addToBackStack("Second Guidance").commit();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) { }
}
