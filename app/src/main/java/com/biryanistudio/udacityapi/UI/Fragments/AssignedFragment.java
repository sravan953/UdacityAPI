package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateCurrentReviews;
import com.biryanistudio.udacityapi.Models.Submission;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.CurrentReviewsTask;
import com.biryanistudio.udacityapi.UI.Adapters.AssignedAdapter;
import com.biryanistudio.udacityapi.UI.MainActivity;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class AssignedFragment extends Fragment implements IUpdateCurrentReviews,
        SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assigned_reviews, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.colorAccent),
                ContextCompat.getColor(getActivity(),R.color.colorPrimary),
                ContextCompat.getColor(getActivity(),R.color.colorSecondary),
                ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark));
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setEmptyView(view.findViewById(R.id.empty_assigned_review));
        if(MainActivity.API_TOKEN_present) new CurrentReviewsTask().execute(this);
        return view;
    }

    @Override
    public void newData(List data) {
        List<Submission> submissionsList = data;
        try {
            swipeRefreshLayout.setRefreshing(false);

            AssignedAdapter assignedAdapter = new AssignedAdapter(getActivity(), R.layout.item_assigned_review, submissionsList);
            listView.setAdapter(assignedAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRefresh() {
        if(MainActivity.API_TOKEN_present) new CurrentReviewsTask().execute(this);
    }
}
