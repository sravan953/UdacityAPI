package com.biryanistudio.udacityapi.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class AvailableAdapter extends RecyclerView.Adapter<AvailableAdapter.AvailableViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private List<Certification> mData;

    public AvailableAdapter(List data) {
        this.mData = data;
        filterAvailableCertificationsOnly();
        Log.i(TAG, mData.toString());
    }

    @Override
    public AvailableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_available_review, parent, false);
        AvailableViewHolder viewHolder = new AvailableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AvailableViewHolder holder, int position) {
        try {
            Certification certification = mData.get(position);
            holder.mProject.setText(certification.getProjectName());
            holder.mAwaitingCount.setText(String.valueOf(certification.getAwaitingReviewCount()));
        } catch (Exception e) {
            e.printStackTrace();
            holder.mProject.setText(R.string.error_message);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void filterAvailableCertificationsOnly() {
        List<Certification> temp= new ArrayList<>();
        for(Certification certification: mData) {
            if(certification.getAwaitingReviewCount() > 0) temp.add(certification);
        }
        mData.clear();
        mData.addAll(temp);
    }

    public class AvailableViewHolder extends RecyclerView.ViewHolder {
        private TextView mAwaitingCount;
        private TextView mProject;

        AvailableViewHolder(View v) {
            super(v);
            mAwaitingCount = (TextView) v.findViewById(R.id.awaiting_review_count);
            mProject = (TextView) v.findViewById(R.id.project);
        }
    }
}
