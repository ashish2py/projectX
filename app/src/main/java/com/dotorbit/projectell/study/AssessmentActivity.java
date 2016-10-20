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
import android.widget.Toast;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.fragments.AssessmentFragment;
import com.dotorbit.projectell.main.MainActivity;
import com.dotorbit.projectell.models.Question;
import com.dotorbit.projectell.utils.LessonJsonParsor;
import com.dotorbit.projectell.utils.Tree;
import com.dotorbit.projectell.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class AssessmentActivity extends AppCompatActivity {

    private final int NUMBER_OF_QUESTION = 10;

    ScrollerViewPager viewPager;
    ImageView imgSuccessBtn;
    Handler activityHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        TreeNode assmentNode;

        //Get Assessment Tree Node
        try{
            Tree digLesson = LessonJsonParsor.parseJson("diagnosis_test.json", AssessmentActivity.this);
            assmentNode = (TreeNode) digLesson.getRootnode().getChildrens().get(0);

        }catch (Exception e){
            Toast.makeText(AssessmentActivity.this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }
        //Set adapeters
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(AssessmentFragment.class, getQuestionList(assmentNode), getTitles());
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

    /**
     * Generate List for title based on NUMBER_OF_QUESTION
     * @return List<String> : number from 1 to NUMBER_OF_QUESTION
     */
    private List<String> getTitles(){
        ArrayList<String> titleList = new ArrayList<>();
        for(int i=1;i<=this.NUMBER_OF_QUESTION;i++){
            titleList.add(""+i);
        }
        return titleList;
    }

    /**
     * Get random question list based on NUMBER_OF_QUESTION
     * @param assessment Assessment TreeNode
     * @return ArrayList<Question> : Question array list
     */
    private ArrayList<String> getQuestionList(TreeNode assessment){
        ArrayList<String > questionsList = new ArrayList<>();
        ArrayList assessmentQuestion = assessment.getChildrens();
        ArrayList<Integer> randNumber = new ArrayList<>();
        Random randomGenerator = new Random();
        for(int i=0;i<this.NUMBER_OF_QUESTION;i++){
            int randIndex = randomGenerator.nextInt(assessmentQuestion.size());
            //Prevent Duplicate
            while(randNumber.contains(randIndex)){
                randIndex = randomGenerator.nextInt(assessmentQuestion.size());
            }

            //Create Question List
            Question question = (Question) ((TreeNode)assessmentQuestion.get(randIndex)).getNode();
            questionsList.add(question.getId());
            randNumber.add(randIndex);
        }
        return questionsList;
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
