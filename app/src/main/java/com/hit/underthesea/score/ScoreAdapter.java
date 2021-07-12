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

    // Returns list size
    @Override
    public int getCount() {
        return scores.size();
    }

    // Returns an object at a specific index
    @Override
    public Object getItem(int i) {
        return scores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Passes the next index we want to show
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_score_table , viewGroup, false);
        }
        Score score = scores.get(i);
        TextView nameTV = view.findViewById(R.id.name_row);
        TextView levelTV = view.findViewById(R.id.level_row);
        TextView scoreTV = view.findViewById(R.id.score_row);

        nameTV.setText(score.getName());
        levelTV.setText(score.getLevel());
        scoreTV.setText(score.getScore()+"");
        return view;
    }
}
