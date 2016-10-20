package com.dotorbit.projectell.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.models.Assessment;
import com.dotorbit.projectell.models.Image;
import com.dotorbit.projectell.models.Question;
import com.dotorbit.projectell.models.Sound;
import com.dotorbit.projectell.study.AssessmentActivity;
import com.dotorbit.projectell.utils.ImageLoadTask;
import com.dotorbit.projectell.utils.LessonJsonParsor;
import com.dotorbit.projectell.utils.Tree;
import com.dotorbit.projectell.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ashish on 29/09/16.
 */
public class AssessmentFragment extends Fragment {

    private Question question;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.question = (Question) getArguments().get("data");

    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assessment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //**************** Title*****************
        TextView txtQuestionTitle =(TextView) getView().findViewById(R.id.txtQuestionTitle);
        ImageView imgQuestionTitle = (ImageView) getView().findViewById(R.id.questionImageView);
        ImageView soundQuestionTitle = (ImageView) getView().findViewById(R.id.questionSoundAudio);

        //Make All GONE
        txtQuestionTitle.setVisibility(View.GONE);
        imgQuestionTitle.setVisibility(View.GONE);
        soundQuestionTitle.setVisibility(View.GONE);

        //ERROR
        if(this.question == null){
            txtQuestionTitle.setVisibility(View.VISIBLE);
            txtQuestionTitle.setText("Cant Get Question");
            return;
        }

        //Parse Title and display
        ArrayList questionTitle =  this.question.getTitle();
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

            //Setup event listener
            this.setOnQuestionOptionClickListener(getView().findViewById(R.id.layoutOptionOne),
                                                    optionOne,(int)options.keySet().toArray()[0]);
            this.setOnQuestionOptionClickListener(getView().findViewById(R.id.layoutOptionTwo),
                    optionTwo,(int)options.keySet().toArray()[1]);
        }

    }

    /**
     * Set proper view for options
     * @param view View of option
     * @param option Hashmap of options
     * @param key current option key
     */
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

    /**
     * Setup event listener for option layout
     * @param optionView Layout of option
     * @param optionTextView Text view of option
     * @param key current key
     */
    private void setOnQuestionOptionClickListener(final View optionView, final TextView optionTextView, final int key){

        //Retain State
        if(this.question.getSelected_option() == key){
            optionView.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
            optionTextView.setTextColor(Color.WHITE);
            optionTextView.setTypeface(null,Typeface.BOLD);
        }

        //Set State change listener
        optionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AssessmentFragment.this.question.getSelected_option() == -1){

                    optionView.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                    optionTextView.setTextColor(Color.WHITE);
                    optionTextView.setTypeface(null,Typeface.BOLD);

                    AssessmentFragment.this.question.setSelected_option(key);
                }
            }
        });

    }
}
