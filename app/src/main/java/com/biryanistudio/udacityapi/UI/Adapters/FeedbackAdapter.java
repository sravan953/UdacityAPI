package com.biryanistudio.udacityapi.UI.Adapters;

import android.content.Context;
import android.util.Log;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackAdapter extends ArrayAdapter {
    private final String TAG = getClass().getSimpleName();
    private int resource;
    private List<Feedback> feedbackList;
    private ViewHolder holder;
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private SimpleDateFormat outputDateFormatHours = new SimpleDateFormat("HH");
    private SimpleDateFormat outputDateFormatDays = new SimpleDateFormat("dd");
    private SimpleDateFormat outputDateFormatMonth = new SimpleDateFormat("MM");
    private Date d;

    public FeedbackAdapter(Context context, int resource, List<Feedback> feedbackList) {
        super(context, resource, feedbackList);
        this.resource = resource;
        this.feedbackList = feedbackList;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        try {
            Feedback feedback = feedbackList.get(position);
            Log.d(TAG, feedback.toString());
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
                holder = new ViewHolder();
                holder.project = (TextView) convertView.findViewById(R.id.project);
                holder.body = (TextView) convertView.findViewById(R.id.body);
                holder.rating = (TextView) convertView.findViewById(R.id.rating);
                holder.updated_at = (TextView) convertView.findViewById(R.id.updated_at);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            holder.project.setText(feedback.getProject());
            holder.body.setText(feedback.getBody());
            holder.rating.setText(String.valueOf(feedback.getRating()) + "/5");
            holder.updated_at.setText(String.valueOf(getElapsedHoursForFeedback(feedback)) + "h ago");
        } catch (JSONException e) {
            e.printStackTrace();
            holder.project.setText("Oops! Looks like something went wrong!");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch(Exception e) {
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

    private int getElapsedHoursForFeedback(Feedback feedback) throws ParseException {
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        d = inputDateFormat.parse(feedback.getUpdatedAt());
        int assignedHours = Integer.parseInt(outputDateFormatHours.format(d));
        int currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int remainingHours = currentHours - assignedHours;
        if(remainingHours > 24) {
            int assignedDate = Integer.parseInt(outputDateFormatDays.format(d));
            int currentDay = Calendar.getInstance().get(Calendar.DATE);
            int remainingDate = currentDay - assignedDate;
            if(remainingDate < 0) {
                int maximumNumberOfDays = Calendar.getInstance().getActualMaximum(Calendar.MONTH);
                remainingDate = maximumNumberOfDays - remainingDate;
            }
            return remainingDate;
        }
        else {
            return remainingHours;
        }
    }
}
