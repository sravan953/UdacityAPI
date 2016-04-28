package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateAvailableReviews;
import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.AvailableReviewsTask;
import com.biryanistudio.udacityapi.UI.Adapters.AvailableReviewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class AvailableReviewsFragment extends Fragment implements IUpdateAvailableReviews, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private AvailableReviewsAdapter availableReviewsAdapter;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_reviews, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) view.findViewById(R.id.listView);
        new AvailableReviewsTask().execute(this);
        return view;
    }

    @Override
    public void availableReviews(List<Certification> allCertifications) {
        swipeRefreshLayout.setRefreshing(false);

        List<Certification> certifications = getAvailableCertificationsOnly(allCertifications);
        availableReviewsAdapter = new AvailableReviewsAdapter(getActivity(), R.layout.item_available_review, certifications);
        listView.setAdapter(availableReviewsAdapter);
    }

    private List<Certification> getAvailableCertificationsOnly(List<Certification> allCertifications) {
        List<Certification> certifications = new ArrayList<>();
        for(Certification certification: allCertifications) {
            if(certification.getAwaitingReviewCount() > 0) certifications.add(certification);
        }

        return certifications;
    }

    @Override
    public void onRefresh() {
        new AvailableReviewsTask().execute(this);
    }
}
