package com.hit.underthesea.score;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hit.underthesea.R;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {
    private List<Score> scores;
    private Context context;

    public ScoreAdapter(List<Score> scores, Context context) {
        this.scores = scores;
        this.context = context;
    }
    //מחזיר את גודל הרשימה
    @Override
    public int getCount() {
        return scores.size();
    }
    //מחזיר את האובייקט במקום מסויים
    @Override
    public Object getItem(int i) {
        return scores.get(i);
    }
    //לא חובה
    @Override
    public long getItemId(int i) {
        return 0;
    }
    //נקראת כל הזמן על ידי הרשימה, מעבירה את המיקום הבא שהיא רוצה להציג
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_score_table , viewGroup, false);
        }
        Score score = scores.get(i);
        TextView nameTV = view.findViewById(R.id.name_scoretable);
        TextView levelTV = view.findViewById(R.id.level_scoretable);
        TextView scoreTV = view.findViewById(R.id.score_scoretable);

        nameTV.setText(score.getName());
        levelTV.setText(score.getLevel());
        scoreTV.setText(score.getScore());
        return view;
    }
}
