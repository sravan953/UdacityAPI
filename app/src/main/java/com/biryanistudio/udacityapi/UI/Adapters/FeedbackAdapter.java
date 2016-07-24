package com.biryanistudio.udacityapi.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.R;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private List<Feedback> mData;
    private SimpleDateFormat mInputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault());

    public FeedbackAdapter(List data) {
        this.mData = data;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback, parent, false);
        FeedbackViewHolder viewHolder = new FeedbackViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        try {
            Feedback feedback = mData.get(position);
            holder.mProject.setText(feedback.getProject());
            if (feedback.getBody() == null || feedback.getBody().isEmpty()
                    || feedback.getBody().equals("")) holder.mBody.setVisibility(View.GONE);
            else holder.mBody.setText(feedback.getBody());
            holder.mRating.setText(String.format("%s/5", String.valueOf(feedback.getRating())));
            holder.mUpdatedAt.setText(getElapsedTimeForFeedback(feedback));
        } catch (JSONException e) {
            e.printStackTrace();
            holder.mProject.setText(R.string.error_message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private String getElapsedTimeForFeedback(Feedback feedback) throws ParseException {
        Date d = mInputDateFormat.parse(feedback.getUpdatedAt());
        long assignedLong = d.getTime();
        Log.i(TAG, assignedLong + "");
        long currentLong = System.currentTimeMillis();
        long remainingLong = currentLong - assignedLong;
        int remainingHours = (int) remainingLong / (1000 * 60 * 60);
        if (remainingHours >= 24) {
            remainingHours = remainingHours / 24;
            return String.valueOf(remainingHours) + "d ago";
        } else {
            return String.valueOf(remainingHours) + "h ago";
        }
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {
        private TextView mProject;
        private TextView mRating;
        private TextView mUpdatedAt;
        private TextView mBody;

        FeedbackViewHolder(View v) {
            super(v);
            mProject = (TextView) v.findViewById(R.id.project);
            mRating = (TextView) v.findViewById(R.id.rating);
            mUpdatedAt = (TextView) v.findViewById(R.id.updated_at);
            mBody = (TextView) v.findViewById(R.id.body);
        }
    }
}
