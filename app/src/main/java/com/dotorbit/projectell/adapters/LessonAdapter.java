package com.dotorbit.projectell.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.models.Lesson;
import com.dotorbit.projectell.study.AssessmentActivity;

import java.util.List;

/**
 * Created by Ashish on 28/09/16.
 */
public class LessonAdapter extends ArrayAdapter<Lesson> {

    private final Context context;
    private final int layoutResourceId;
    private final List<Lesson> LessonData;

    public LessonAdapter(Context context, int layoutResourceId, List<Lesson> LessonData) {
        super(context, layoutResourceId, LessonData);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.LessonData = LessonData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_lesson_resource, null);
            TextView txtLessonTitle = (TextView)row.findViewById(R.id.txtLessonTitle);
            TextView txtLessonDescription = (TextView)row.findViewById(R.id.txtLessonDescription);
            TextView txtAssessmentTitle = (TextView)row.findViewById(R.id.txtAssessmentTitle);
            TextView txtQuestionCount = (TextView)row.findViewById(R.id.txtQuestionCount);
            RelativeLayout relBoxFlyerContainer = (RelativeLayout)row.findViewById(R.id.relBoxFlyerContainer);

            Lesson lesson = LessonData.get(position);
            txtLessonTitle .setText(lesson.title);
            txtLessonDescription .setText(lesson.description);
            txtAssessmentTitle .setText(lesson.assessmentName);
            txtQuestionCount.setText(lesson.assessmentQuestions.toString()+" Questions");
            relBoxFlyerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent assessmentIntent = new Intent(context, AssessmentActivity.class);
                    context.startActivity(assessmentIntent);
                }
            });



        }
        return row;
    }

}
