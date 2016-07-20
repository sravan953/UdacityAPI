package com.biryanistudio.udacityapi.UI.Adapters;

import android.support.v7.widget.RecyclerView;
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
    private List<Feedback> mData;
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

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
            Feedback feedback = (Feedback) mData.get(position);
            holder.mProject.setText(feedback.getProject());
            //TODO: Feedback comments do not get displayed correctly when scrolling from bottom of list to top.
            if (feedback.getBody() == null || feedback.getBody().isEmpty() || feedback.getBody().equals(""))
                holder.mBody.setVisibility(View.GONE);
            else holder.mBody.setText(feedback.getBody());
            holder.mRating.setText(String.format("%s/5", String.valueOf(feedback.getRating())));
            holder.mUpdatedAt.setText(getElapsedHoursForFeedback(feedback));
        } catch (JSONException e) {
            e.printStackTrace();
            holder.mProject.setText(R.string.error_message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(mData != null) return mData.size();
        else return 0;
    }

    private String getElapsedHoursForFeedback(Feedback feedback) throws ParseException {
        Date d = inputDateFormat.parse(feedback.getUpdatedAt());
        long assignedLong = d.getTime();
        long currentLong = System.currentTimeMillis();
        long remainingLong = currentLong - assignedLong;
        int remainingHours = (int) remainingLong / (1000 * 60 * 60);
        if (remainingHours > 24) {
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
