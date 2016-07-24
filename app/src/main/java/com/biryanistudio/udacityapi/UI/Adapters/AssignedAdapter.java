package com.biryanistudio.udacityapi.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private final String TAG = getClass().getSimpleName();
    private List<Submission> mData;
    private SimpleDateFormat mInputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault());
    private SimpleDateFormat mOutputDateFormat = new SimpleDateFormat("HH", Locale.getDefault());

    public AssignedAdapter(List data) {
        this.mData = data;
        mInputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Log.i(TAG, mData.toString());
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
            Submission submission = mData.get(position);
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
        return mData.size();
    }

    private String getRemainingHoursForReview(Submission submission) throws ParseException {
        Date d = mInputDateFormat.parse(submission.getAssignedAt());
        int assignedHours = Integer.parseInt(mOutputDateFormat.format(d));
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
