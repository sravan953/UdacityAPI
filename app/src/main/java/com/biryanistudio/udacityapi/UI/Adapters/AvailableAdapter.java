package com.biryanistudio.udacityapi.UI.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.R;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class AvailableAdapter extends ArrayAdapter {
    private final String TAG = getClass().getSimpleName();
    private Context context;
    private int resource;
    private List<Certification> certificationsList;
    private ViewHolder holder;

    public AvailableAdapter(Context context, int resource, List<Certification> certificationsList) {
        super(context, resource, certificationsList);
        this.context = context;
        this.resource = resource;
        this.certificationsList = certificationsList;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        try {
            Certification certification = certificationsList.get(position);
            Log.d(TAG, certification.toString());
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(resource, parent, false);
                holder = new ViewHolder();
                holder.project = (TextView) convertView.findViewById(R.id.project);
                holder.awaitingCount = (TextView) convertView.findViewById(R.id.awaiting_review_count);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            holder.project.setText(certification.getProjectName());
            holder.awaitingCount.setText(String.valueOf(certification.getAwaitingReviewCount()));
        } catch (Exception e) {
            e.printStackTrace();
            holder.project.setText(R.string.error_message);
        }

        convertView.setAlpha(0.0f);
        convertView.animate().alpha(1.0f).setStartDelay(position * 50).start();
        return convertView;
    }

    private static class ViewHolder {
        TextView project;
        TextView awaitingCount;
    }
}
