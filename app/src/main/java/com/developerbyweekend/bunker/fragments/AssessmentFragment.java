package com.developerbyweekend.bunker.fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.models.Image;
import com.developerbyweekend.bunker.models.Question;
import com.developerbyweekend.bunker.models.Sound;
import com.developerbyweekend.bunker.utils.ImageLoadTask;
import com.developerbyweekend.bunker.utils.LessonJsonParsor;
import com.developerbyweekend.bunker.utils.Tree;
import com.developerbyweekend.bunker.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ashish on 29/09/16.
 */
public class AssessmentFragment extends Fragment {

    private String questionId;
    private List<TreeNode> questionList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionId = getArguments().getString("data");
        try {
            Tree digLesson = LessonJsonParsor.parseJson("diagnosis_test.json", getActivity().getBaseContext());
            TreeNode assessment = (TreeNode) digLesson.getRootnode().getChildrens().get(0);
            this.questionList = assessment.getChildrens();

        }catch(Exception e){
            Log.e("ERROR",e.toString());
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assessment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Question question = null;
        for(TreeNode qus : this.questionList){
            if(((Question)qus.getNode()).getId().equals(this.questionId)){
                question =  (Question)qus.getNode();
            }
        }

        //**************** Title*****************
        TextView txtQuestionTitle =(TextView) getView().findViewById(R.id.txtQuestionTitle);
        ImageView imgQuestionTitle = (ImageView) getView().findViewById(R.id.questionImageView);
        ImageView soundQuestionTitle = (ImageView) getView().findViewById(R.id.questionSoundAudio);

        //Make All GONE
        txtQuestionTitle.setVisibility(View.GONE);
        imgQuestionTitle.setVisibility(View.GONE);
        soundQuestionTitle.setVisibility(View.GONE);

        //ERROR
        if(question == null){
            txtQuestionTitle.setVisibility(View.VISIBLE);
            txtQuestionTitle.setText("Cant Get Question");
            return;
        }

        //Parse Title and display
        ArrayList questionTitle =  question.getTitle();
        for(int i=0;i<questionTitle.size();i++){
            if(questionTitle.get(i) instanceof String){
                //TEXT
                txtQuestionTitle.setVisibility(View.VISIBLE);
                txtQuestionTitle.setText((String)questionTitle.get(i));
            }else if(questionTitle.get(i) instanceof Image){
                //Image
                imgQuestionTitle.setVisibility(View.VISIBLE);
                String url = getString(R.string.BACKEND_URL)+((Image) questionTitle.get(i)).getUrl();
                new ImageLoadTask(url,imgQuestionTitle).execute();

            }else if(questionTitle.get(i) instanceof Sound){

                //Sound
                soundQuestionTitle.setVisibility(View.VISIBLE);

                final Sound sound = (Sound) questionTitle.get(i);
                soundQuestionTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            //Play audio
                            final MediaPlayer player = new MediaPlayer();
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            player.setDataSource(getString(R.string.BACKEND_URL)+sound.getUrl());
                            player.prepare();
                            player.start();


                        }catch (Exception e){
                            Log.e("ERROR", e.toString());
                        }

                    }
                });

            }else{
                //Error
                txtQuestionTitle.setVisibility(View.VISIBLE);
                txtQuestionTitle.setText("Cant Get instance");
            }

            //*********************** Optiions *******************
            HashMap options = question.getOptions();

            TextView optionOne = (TextView) getView().findViewById(R.id.txtOptionOne);
            TextView optionTwo = (TextView) getView().findViewById(R.id.txtOptionTwo);
            setQuestionOptionView(optionOne,options,(int)options.keySet().toArray()[0]);
            setQuestionOptionView(optionTwo,options,(int)options.keySet().toArray()[1]);

        }

    }

    private void setQuestionOptionView(TextView view, HashMap option ,int key){
        if(option.get(key) instanceof String){
            view.setText((String)option.get(key));
        }else if(option.get(key) instanceof Image){
            view.setText(((Image)option.get(key)).getUrl());
        }else if(option.get(key) instanceof Sound){
            view.setText(((Sound)option.get(key)).getUrl());
        }else{
            view.setText("Error");
        }
    }

}
