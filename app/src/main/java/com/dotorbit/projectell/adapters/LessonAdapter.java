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
import com.dotorbit.projectell.models.Lesson;

/**
 * Created by Ashish on 28/09/16.
 */
public class LessonAdapter extends ArrayAdapter<Lesson> {

    Context context;
    int layoutResourceId;
    Lesson data[] = null;

    public LessonAdapter(Context context, int layoutResourceId, Lesson[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LessonHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_lesson_resource, null);

            holder = new LessonHolder ();
            holder.txtTitle = (TextView)row.findViewById(R.id.lessonNameTextView);

            row.setTag(holder);
        }
        else
        {
            holder = (LessonHolder )row.getTag();
        }

        Lesson lesson = data[position];
        holder.txtTitle.setText(lesson.title);

        return row;
    }

    static class LessonHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }

}
