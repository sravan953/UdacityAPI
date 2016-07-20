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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackAdapter extends ArrayAdapter<Feedback> {
    private final String TAG = getClass().getSimpleName();
    private int resource;
    private List<Feedback> feedbackList;
    private ViewHolder holder;
    private final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

    public FeedbackAdapter(final Context context, final int resource, final List<Feedback> feedbackList) {
        super(context, resource, feedbackList);
        this.resource = resource;
        this.feedbackList = feedbackList;
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        try {
            final Feedback feedback = feedbackList.get(position);
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
            //TODO: Feedback comments do not get displayed correctly when scrolling from bottom of list to top.
            if (feedback.getBody() == null || feedback.getBody().isEmpty() || feedback.getBody().equals(""))
                holder.body.setVisibility(View.GONE);
            else {
                holder.body.setVisibility(View.VISIBLE);
                holder.body.setText(feedback.getBody());
            }
            holder.rating.setText(String.format("%s/5", String.valueOf(feedback.getRating())));
            holder.updated_at.setText(getElapsedHoursForFeedback(feedback));
        } catch (final JSONException e) {
            e.printStackTrace();
            holder.project.setText(R.string.error_message);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private String getElapsedHoursForFeedback(final Feedback feedback) throws ParseException {
        final Date d = inputDateFormat.parse(feedback.getUpdatedAt());
        final long assignedLong = d.getTime();
        final long currentLong = System.currentTimeMillis();
        final long remainingLong = currentLong - assignedLong;
        int remainingHours = (int) remainingLong / (1000 * 60 * 60);
        if (remainingHours > 24) {
            remainingHours = remainingHours / 24;
            return String.valueOf(remainingHours) + "d ago";
        } else {
            return String.valueOf(remainingHours) + "h ago";
        }
    }

    private static class ViewHolder {
        TextView project;
        TextView body;
        TextView rating;
        TextView updated_at;
    }
}
