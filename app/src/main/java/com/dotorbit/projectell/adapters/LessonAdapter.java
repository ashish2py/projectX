package com.dotorbit.projectell.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.models.Assessment;
import com.dotorbit.projectell.models.Lesson;

import org.w3c.dom.Text;

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

            Lesson lesson = LessonData.get(position);
            txtLessonTitle .setText(lesson.title);
            txtLessonDescription .setText(lesson.description);
            txtAssessmentTitle .setText(lesson.assessmentName);
            txtQuestionCount.setText(lesson.assessmentQuestions.toString()+" Questions");

        }
        return row;
    }

}
