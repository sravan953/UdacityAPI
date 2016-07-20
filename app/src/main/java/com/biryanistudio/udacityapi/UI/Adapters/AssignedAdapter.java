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
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class AssignedAdapter extends ArrayAdapter<Submission> {
    private final String TAG = getClass().getSimpleName();
    private int resource;
    private List<Submission> submissionsList;
    private ViewHolder holder;
    private final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    private final SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH", Locale.getDefault());

    public AssignedAdapter(final Context context, final int resource, final List<Submission> submissionsList) {
        super(context, resource, submissionsList);
        this.resource = resource;
        this.submissionsList = submissionsList;
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public View getView (final int position, View convertView, final ViewGroup parent) {
        try {
            final Submission submission = submissionsList.get(position);
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
            holder.price.setText(String.format("$%s", submission.getRate()));
            //TODO: Time time left shows 35h instead of 11h. Need to fix this immediately.
            holder.timeLeft.setText(String.format("%sh left", getRemainingHoursForReview(submission)));
        } catch (final JSONException e) {
            e.printStackTrace();
            holder.project.setText(R.string.error_message);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private String getRemainingHoursForReview(final Submission submission) throws ParseException {
        final Date d = inputDateFormat.parse(submission.getAssignedAt());
        final int assignedHours = Integer.parseInt(outputDateFormat.format(d));
        final int currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return String.valueOf(12 - (currentHours - assignedHours));
    }

    private static class ViewHolder {
        TextView project;
        TextView price;
        TextView timeLeft;
    }
}
