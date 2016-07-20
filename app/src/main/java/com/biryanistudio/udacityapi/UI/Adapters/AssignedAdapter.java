package com.biryanistudio.udacityapi.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class AssignedAdapter extends RecyclerView.Adapter<AssignedAdapter.AssignedViewHolder> {
    private List mData;
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    private SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH", Locale.getDefault());

    public AssignedAdapter(List data) {
        this.mData = data;
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public AssignedAdapter.AssignedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assigned_review, parent, false);
        AssignedViewHolder viewHolder = new AssignedViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AssignedAdapter.AssignedViewHolder holder, int position) {
        try {
            Submission submission = (Submission) mData.get(position);
            holder.mProject.setText(submission.getProject());
            holder.mPrice.setText(String.format("$%s", submission.getRate()));
            //TODO: Time time left shows 35h instead of 11h. Need to fix this immediately.
            holder.mTimeLeft.setText(String.format("%sh left", getRemainingHoursForReview(submission)));
        } catch (JSONException e) {
            e.printStackTrace();
            holder.mProject.setText(R.string.error_message);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(mData != null) return mData.size();
        else return 0;
    }

    private String getRemainingHoursForReview(Submission submission) throws ParseException {
        Date d = inputDateFormat.parse(submission.getAssignedAt());
        int assignedHours = Integer.parseInt(outputDateFormat.format(d));
        int currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return String.valueOf(12 - (currentHours - assignedHours));
    }

    public class AssignedViewHolder extends RecyclerView.ViewHolder {
        private TextView mTimeLeft;
        private TextView mProject;
        private TextView mPrice;

        AssignedViewHolder(View v) {
            super(v);
            mTimeLeft = (TextView) v.findViewById(R.id.time_left);
            mProject = (TextView) v.findViewById(R.id.project);
            mPrice = (TextView) v.findViewById(R.id.price);
        }
    }
}
