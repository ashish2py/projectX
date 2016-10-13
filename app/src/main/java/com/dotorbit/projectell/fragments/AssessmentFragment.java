package com.dotorbit.projectell.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.models.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 29/09/16.
 */
public class AssessmentFragment extends Fragment {

    private String questionData;
    private String questionId;
    private TextView txtQuestionTitle;
    Question question = new Question();
    List<Question> questionList = new ArrayList<Question>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionId = getArguments().getString("data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assessment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Question _question = new Question();

        question = _question.getQuestionList().get(Integer.valueOf(questionId));
        Log.e("Question id ", questionId);

        txtQuestionTitle = (TextView) getView().findViewById(R.id.txtQuestionTitle);
        txtQuestionTitle.setText(question .title);
        txtQuestionTitle.setMovementMethod(new ScrollingMovementMethod());


    }
}
