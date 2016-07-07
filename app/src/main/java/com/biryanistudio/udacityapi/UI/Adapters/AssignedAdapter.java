package com.biryanistudio.udacityapi.UI.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Models.Submission;
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
public class AssignedAdapter extends ArrayAdapter {
    private final String TAG = getClass().getSimpleName();
    private int resource;
    private List<Submission> submissionsList;
    private ViewHolder holder;
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH");
    private Date d;

    public AssignedAdapter(Context context, int resource, List<Submission> submissionsList) {
        super(context, resource, submissionsList);
        this.resource = resource;
        this.submissionsList = submissionsList;
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        try {
            Submission submission = submissionsList.get(position);
            Log.d(TAG, submission.toString());
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
                holder = new ViewHolder();
                holder.project = (TextView) convertView.findViewById(R.id.project);
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.timeLeft = (TextView) convertView.findViewById(R.id.time_left);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            holder.project.setText(submission.getProject());
            holder.price.setText("$" + submission.getRate());
            holder.timeLeft.setText(getRemainingHoursForReview(submission) + "h left");
        } catch (JSONException e) {
            e.printStackTrace();
            holder.project.setText("Oops! Looks like something went wrong!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView project;
        TextView price;
        TextView timeLeft;
    }

    private String getRemainingHoursForReview(Submission submission) throws ParseException {
        d = inputDateFormat.parse(submission.getAssignedAt());
        int assignedHours = Integer.parseInt(outputDateFormat.format(d));
        int currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return String.valueOf(12 - (currentHours - assignedHours));
    }
}
