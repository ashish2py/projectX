package com.developerbyweekend.bunker.utils;

import android.content.Context;

import com.developerbyweekend.bunker.models.Assessment;
import com.developerbyweekend.bunker.models.Image;
import com.developerbyweekend.bunker.models.Lesson;
import com.developerbyweekend.bunker.models.Question;
import com.developerbyweekend.bunker.models.Sound;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sunit on 15/10/16.
 */

public class LessonJsonParsor {

    /**
     * Public method to parse Lesson JSON
     * @param assestsFileName Assets filename of lesson.json
     * @param context Context of application to read assessts
     * @return Tree Node of given Lesson
     * @throws Exception
     */
    public static Tree parseJson(String assestsFileName, Context context) throws Exception {

        JSONObject lessonjson = LessonJsonParsor.parseAssets(assestsFileName, context);
        Lesson lesson = LessonJsonParsor.getLesson(lessonjson);
        TreeNode<Lesson> rootNode = new TreeNode<>(lesson, null, null);
        ArrayList<TreeNode> assessmentList = LessonJsonParsor.getLessonChildren(lessonjson.getJSONArray("objects"), rootNode);
        rootNode.setChildrens(assessmentList);
        return new Tree(rootNode);

    }

    /**
     * Parse Assessment Node
     * @param assestsFilename Assets filename of lesson.json
     * @param context Context of application to read assessts
     * @return Assessment TreeNode with all children set
     * @throws Exception
     */
    private static JSONObject parseAssets(String assestsFilename, Context context) throws Exception {
        InputStream assestsfile = context.getResources().getAssets().open(assestsFilename);
        BufferedReader filereader = new BufferedReader(new InputStreamReader(assestsfile));
        StringBuffer jsondata = new StringBuffer();

        //Read file
        String line;
        while ((line = filereader.readLine()) != null) {
            jsondata.append(line);
        }

        //Parse JSON
        return new JSONObject(jsondata.toString());
    }

    /**
     * Get Lesson from JSONObject
     * @param jsonObject JSONObject
     * @return Lesson
     * @throws Exception
     */
    private static Lesson getLesson(JSONObject jsonObject) throws Exception {
        JSONObject node = jsonObject.getJSONObject("node");
        return new Lesson(node.optString("id"), node.optString("title"), node.optString("description"));
    }

    /**
     * Get Lesson Children
     * @param objectarray JSON Object of lesson children
     * @param lesson Parent Node (Lesson Node)
     * @return Children of lesson Node
     * @throws Exception
     */
    private static ArrayList<TreeNode> getLessonChildren(JSONArray objectarray, TreeNode lesson) throws Exception {
        ArrayList<TreeNode> assessmentNodes = new ArrayList<>();
        for (int i = 0; i < objectarray.length(); i++) {
            JSONObject assessmentObject = objectarray.getJSONObject(i);
            JSONObject nodeObject = assessmentObject.getJSONObject("node");
            JSONObject typeObject = nodeObject.getJSONObject("type");
            Assessment assessment = new Assessment(nodeObject.getString("id"), nodeObject.getString("title"),
                    nodeObject.getString("description"), typeObject.getString("type"));
            //Tree
            TreeNode<Assessment> treenode = new TreeNode<>(assessment, lesson, null);
            ArrayList<TreeNode> questions = LessonJsonParsor.getAssessmentChildren(assessmentObject.getJSONArray("objects"), treenode);
            treenode.setChildrens(questions);
            assessmentNodes.add(treenode);
        }
        return assessmentNodes;
    }

    /**
     * Get Assessment Children
     * @param objectarray JSONObject of assessment Children
     * @param assessment Parent Node(Assessment)
     * @return Children of assessment
     * @throws Exception
     */
    private static ArrayList<TreeNode> getAssessmentChildren(JSONArray objectarray, TreeNode assessment) throws Exception {

        ArrayList<TreeNode> questionList = new ArrayList<TreeNode>();
        for (int i = 0; i < objectarray.length(); i++) {
            JSONObject nodeObject = objectarray.getJSONObject(i).getJSONObject("node");
            JSONObject typeObject = nodeObject.getJSONObject("type");

            //Get answer
            ArrayList<Integer> answer = new ArrayList<Integer>();
            JSONArray answerJSONArray = typeObject.getJSONArray("answer");
            for (int j = 0; j < answerJSONArray.length(); j++) {
                answer.add(answerJSONArray.getInt(j));
            }

            //Get Titile
            ArrayList title = LessonJsonParsor.parseTitle(nodeObject.getString("title"), typeObject.getJSONObject("content"));

            //Get Options
            HashMap options = LessonJsonParsor.parseOptions(typeObject.getJSONObject("content"));

            Question question = new Question(nodeObject.getString("id"), title, answer,
                    options, typeObject.getString("type"), typeObject.getInt("score"));

            questionList.add(new TreeNode<Question>(question, assessment, null));
        }

        return questionList;
    }

    /**
     * Parse Question Title
     * @param title String Question title.
     * @param content Content of the question.
     * @return ArrayList containing Entry in the title.
     * @throws Exception
     */
    private static ArrayList parseTitle(String title, JSONObject content) throws Exception {
        String tmp = title.trim();
        ArrayList titleList = new ArrayList();
        while (tmp.length() > 0) {
            if (tmp.startsWith("[[img")) {
                String id = tmp.substring("[[img id=".length(), tmp.indexOf("]]"));
                tmp = tmp.substring(tmp.indexOf("]]") + 2);
                String url = content.getJSONObject("widgets").getJSONObject("images").getString(id);
                titleList.add(new Image(url));

            } else if (tmp.startsWith("[[sound")) {
                String id = tmp.substring("[[sound id=".length(), tmp.indexOf("]]"));
                tmp = tmp.substring(tmp.indexOf("]]") + 2);
                String url = content.getJSONObject("widgets").getJSONObject("sounds").getString(id);
                titleList.add(new Sound(url));

            } else {
                int index = (tmp.indexOf("[[") == -1) ? tmp.length() : tmp.indexOf("[[");
                String text = tmp.substring(0, index);
                titleList.add(text);
                tmp = tmp.substring(index);
            }
            tmp = tmp.trim();
        }
        return titleList;
    }

    /**
     * Pares Question Option
     * @param content content of the question.
     * @return HasMap with option key and option objects..
     * @throws Exception
     */
    private static HashMap parseOptions(JSONObject content) throws Exception {

        JSONArray optionsArray = content.getJSONArray("options");
        HashMap options = new HashMap();

        for (int i = 0; i < optionsArray.length(); i++) {
            JSONObject optionJSON = optionsArray.getJSONObject(i);
            String value = optionJSON.getString("option").trim();
            if (value.indexOf("[[img") != -1) {
                String id = value.substring("[[img id=".length(), value.indexOf("]]"));
                String url = content.getJSONObject("widgets").getJSONObject("images").getString(id);
                options.put(optionJSON.getInt("key"), new Image(url));

            } else if (value.indexOf("[[sound") != -1) {
                String id = value.substring("[[sound id=".length(), value.indexOf("]]"));
                String url = content.getJSONObject("widgets").getJSONObject("sounds").getString(id);
                options.put(optionJSON.getInt("key"), new Sound(url));
            } else {
                options.put(optionJSON.getInt("key"), value);
            }

        }
        return options;
    }
}
