package com.hit.underthesea.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hit.underthesea.R;
import com.hit.underthesea.model.InfoData;


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
        View view = inflater.inflate(R.layout.menu, container, false);
        Button howToPlay = view.findViewById(R.id.howToPlay_btn);
        Button credit = view.findViewById(R.id.credit_btn);
        howToPlay.setOnClickListener(this);
        credit.setOnClickListener(this);
        view.findViewById(R.id.exit_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //כאשר לוחצים על האיקס, הוא יוצא מהפרגמנט וחוזר למסך הראשי
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        //אחרי שניפחנו יצרנו והחזרנו למערכת ההפעלה להצגה כמסך של הפרגמנט
        return view;
    }

    @Override
    public void onClick(View v) {
        InfoFragment fragment = new InfoFragment();

        //מברר איזה מידע צריך להעביר
        switch (v.getId()) {
            case R.id.howToPlay_btn:
                fragment.setInfoData(new InfoData("How to play", "alvcflvfvn;vr"));
                break;
            case R.id.credit_btn:
                fragment.setInfoData(new InfoData("Credit", "alvcflvfvn;vr"));
                break;
        }
        //יוצרים פרגמנט חדש ומעבירים את המידע שקיבלנו מהבירור
        getChildFragmentManager().beginTransaction().add(R.id.child_container, fragment, null).commit();
    }
}