package com.biryanistudio.udacityapi.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.R;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackAdapter extends ArrayAdapter {
    private final String TAG = getClass().getSimpleName();
    private int resource;
    private List<Feedback> feedbackList;
    private ViewHolder holder;
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yy");
    private Date d;

    public FeedbackAdapter(Context context, int resource, List<Feedback> feedbackList) {
        super(context, resource, feedbackList);
        this.resource = resource;
        this.feedbackList = feedbackList;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        Feedback feedback = feedbackList.get(position);
        //Log.d(TAG, feedback.toString());
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.project = (TextView) convertView.findViewById(R.id.project);
            holder.body = (TextView) convertView.findViewById(R.id.body);
            holder.rating = (TextView) convertView.findViewById(R.id.rating);
            holder.updated_at = (TextView) convertView.findViewById(R.id.updated_at);
            convertView.setTag(holder);
        }
        try {
            holder = (ViewHolder) convertView.getTag();
            holder.project.setText(feedback.getProject());
            holder.body.setText(feedback.getBody());
            holder.rating.setText(String.valueOf(feedback.getRating()) + "/5");
            d = inputDateFormat.parse(feedback.getUpdatedAt());
            holder.updated_at.setText(outputDateFormat.format(d));
        } catch (JSONException e) {
            e.printStackTrace();
            holder.project.setText("Oops! Looks like something went wrong!");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView project;
        TextView body;
        TextView rating;
        TextView updated_at;
    }
}
