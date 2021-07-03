package com.hit.underthesea.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.hit.underthesea.R;
import com.hit.underthesea.model.InfoData;

import java.util.Objects;

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
        //מקבלים את המידע מהסטינגס ועורכים את הכותרת ואת הגוף של הפרגמנט ילד
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        TextView title = view.findViewById(R.id.title);
        TextView body = view.findViewById(R.id.body);
        title.setText(infoData.getTitle());
        body.setText(infoData.getBody());
        ImageButton exitInfo = view.findViewById(R.id.exit_info);
        exitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}