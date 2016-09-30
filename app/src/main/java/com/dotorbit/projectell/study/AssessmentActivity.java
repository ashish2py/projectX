package com.dotorbit.projectell.study;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.fragments.AssessmentFragment;
import com.dotorbit.projectell.main.MainActivity;
import com.dotorbit.projectell.models.Lesson;
import com.dotorbit.projectell.models.Question;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class AssessmentActivity extends AppCompatActivity {

    ScrollerViewPager viewPager;
    ImageView imgSuccessBtn;
    Question question = new Question();
    List<String> questionIds = new ArrayList<String>();
    Handler activityHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(AssessmentFragment.class, getQuestionList(), questionIds);
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager);
        imgSuccessBtn = (ImageView) findViewById(R.id.imgSuccessBtn);

    }

    int[] pokemonImages= {R.drawable.ic_pikachu, R.drawable.ic_zubat, R.drawable.ic_charmander, R.drawable.ic_pokecoin, R.drawable.ic_jigglypuff, R.drawable.ic_jigglypuff, R.drawable.ic_zubat };

    public void imgSuccessBtnOnClick(View view){
        Random random = new Random(System.currentTimeMillis());
        Log.d("random",random.toString());
        final int posOfImage = random.nextInt(pokemonImages.length-1);

        imgSuccessBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_pokeball));
        imgSuccessBtn.animate().rotationBy(10080).setDuration(2000).setInterpolator(new LinearInterpolator()).start();

        activityHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgSuccessBtn.setImageDrawable(getResources().getDrawable(pokemonImages[posOfImage]));
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }, 1000);


    }

    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4","5", "6", "7", "8", "9");
    }

    private List<String> getBgRes(){
        return Lists.newArrayList(
                "Do you like chocolate?",
                "Do you like chinese food?",
                "Do you have T.V. at home?",
                "Do you have motorcycle?",
                "Do you have cycle?",
                "Do you like ice-cream?",
                "Do you like ice-cream cake?",
                "Do you like chocolate cake?",
                "Do you like banana?"
                );
    }


    private List<String> getQuestionList(){
        questionIds = question.getQuestionIDs();
        Log.d("Question IDS #####", questionIds.subList(1,10).toString());
        return question.getQuestionIDs().subList(2,12);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
