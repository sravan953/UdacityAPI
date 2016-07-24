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
    private final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    private List<Feedback> mData;

    public FeedbackAdapter(final List<Feedback> data) {
        this.mData = data;
        Log.i(TAG, mData.toString());
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FeedbackViewHolder holder, final int position) {
        try {
            final Feedback feedback = mData.get(position);
            holder.mProject.setText(feedback.getProject());
            if (feedback.getBody() == null || feedback.getBody().isEmpty() || feedback.getBody().equals(""))
                holder.mBody.setVisibility(View.GONE);
            else {
                holder.mBody.setVisibility(View.VISIBLE);
                holder.mBody.setText(feedback.getBody());
            }
            holder.mRating.setText(String.format("%s/5", String.valueOf(feedback.getRating())));
            holder.mUpdatedAt.setText(getElapsedHoursForFeedback(feedback));
        } catch (final JSONException e) {
            e.printStackTrace();
            holder.mProject.setText(R.string.error_message);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
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

    class FeedbackViewHolder extends RecyclerView.ViewHolder {
        private TextView mProject;
        private TextView mRating;
        private TextView mUpdatedAt;
        private TextView mBody;

        FeedbackViewHolder(final View v) {
            super(v);
            mProject = (TextView) v.findViewById(R.id.project);
            mRating = (TextView) v.findViewById(R.id.rating);
            mUpdatedAt = (TextView) v.findViewById(R.id.updated_at);
            mBody = (TextView) v.findViewById(R.id.body);
        }
    }
}
