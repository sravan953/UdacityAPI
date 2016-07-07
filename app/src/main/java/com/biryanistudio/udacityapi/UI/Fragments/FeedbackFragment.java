package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateFeedback;
import com.biryanistudio.udacityapi.Models.Feedback;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.FeedbackTask;
import com.biryanistudio.udacityapi.UI.Adapters.FeedbackAdapter;
import com.biryanistudio.udacityapi.UI.MainActivity;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class FeedbackFragment extends Fragment implements IUpdateFeedback, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private FeedbackAdapter feedbackAdapter;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        listView = (ListView) view.findViewById(R.id.listView);
        if(MainActivity.API_TOKEN_present) new FeedbackTask().execute(this);
        return view;
    }

    @Override
    public void feedbackUI(List<Feedback> feedbackList) {
        try {
            swipeRefreshLayout.setRefreshing(false);

            feedbackAdapter = new FeedbackAdapter(getActivity(), R.layout.item_feedback, feedbackList);
            listView.setAdapter(feedbackAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        if(MainActivity.API_TOKEN_present) new FeedbackTask().execute(this);
    }
}
