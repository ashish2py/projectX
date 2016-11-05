package com.developerbyweekend.bunker.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.models.Assessment;
import com.developerbyweekend.bunker.models.Lesson;
import com.developerbyweekend.bunker.study.AssessmentActivity;
import com.developerbyweekend.bunker.utils.TreeNode;

import java.util.List;

/**
 * Created by Ashish on 28/09/16.
 */
public class LessonAdapter extends ArrayAdapter<TreeNode<Lesson>> {

    private final Context context;
    private final int layoutResourceId;
    private final List<TreeNode<Lesson>> LessonData;

    public LessonAdapter(Context context, int layoutResourceId, List<TreeNode<Lesson>> LessonData) {
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

            TreeNode lessonNode = LessonData.get(position);
            Lesson lesson = (Lesson) lessonNode.getNode();
            Assessment assessment = (Assessment) ((TreeNode)lessonNode.getChildrens().get(0)).getNode();
            int questionCount = ((TreeNode)lessonNode.getChildrens().get(0)).getChildrens().size();
            txtLessonTitle .setText(lesson.getTitle());
            txtLessonDescription .setText(lesson.getDescription());

            txtAssessmentTitle .setText(assessment.getTitle());
            txtQuestionCount.setText(questionCount+" Questions");
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
