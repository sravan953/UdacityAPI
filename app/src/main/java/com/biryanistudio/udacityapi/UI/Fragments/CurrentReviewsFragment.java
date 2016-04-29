package com.biryanistudio.udacityapi.UI.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateCurrentReviews;
import com.biryanistudio.udacityapi.Models.Submission;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.CurrentReviewsTask;
import com.biryanistudio.udacityapi.UI.Adapters.CurrentReviewsAdapter;
import com.biryanistudio.udacityapi.UI.MainActivity;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class CurrentReviewsFragment extends Fragment implements IUpdateCurrentReviews, SwipeRefreshLayout.OnRefreshListener
{
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private CurrentReviewsAdapter currentReviewsAdapter;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_reviews, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        listView = (ListView) view.findViewById(R.id.listView);
        if(MainActivity.API_TOKEN_present) new CurrentReviewsTask().execute(this);
        return view;
    }

    @Override
    public void currentReviewsUI(List<Submission> submissionsList) {
        swipeRefreshLayout.setRefreshing(false);

        currentReviewsAdapter = new CurrentReviewsAdapter(getActivity(), R.layout.item_current_review, submissionsList);
        listView.setAdapter(currentReviewsAdapter);
    }

    @Override
    public void onRefresh() {
        if(MainActivity.API_TOKEN_present) new CurrentReviewsTask().execute(this);
    }
}
