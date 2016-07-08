package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.biryanistudio.udacityapi.Interfaces.IUpdateAvailableReviews;
import com.biryanistudio.udacityapi.Models.Certification;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.Tasks.AssignProjectTask;
import com.biryanistudio.udacityapi.Tasks.AvailableReviewsTask;
import com.biryanistudio.udacityapi.UI.Adapters.AvailableAdapter;
import com.biryanistudio.udacityapi.UI.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class AvailableFragment extends Fragment implements IUpdateAvailableReviews, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private final String TAG = getClass().getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private AvailableAdapter availableAdapter;
    public int projectID;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_reviews, container, false);
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
        listView.setEmptyView(view.findViewById(R.id.empty_available_review));
        listView.setOnItemClickListener(this);
        if(MainActivity.API_TOKEN_present) new AvailableReviewsTask().execute(this);
        return view;
    }

    @Override
    public void availableReviewsUI(List<Certification> allCertifications) {
        try {
            swipeRefreshLayout.setRefreshing(false);
            List<Certification> certifications = getAvailableCertificationsOnly(allCertifications);
            availableAdapter = new AvailableAdapter(getActivity(), R.layout.item_available_review, certifications);
            listView.setAdapter(availableAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        if(MainActivity.API_TOKEN_present) new AvailableReviewsTask().execute(this);
    }

    private List<Certification> getAvailableCertificationsOnly(List<Certification> allCertifications) {
        List<Certification> certifications = new ArrayList<>();
        for(Certification certification: allCertifications) {
            if(certification.getAwaitingReviewCount() > 0) certifications.add(certification);
        }

        return certifications;
    }

    @Override
    public void refreshAvailableReviewsUI(int responseCode) {
        Log.d(TAG, String.valueOf(responseCode));
        if(responseCode == 201) {
            swipeRefreshLayout.setRefreshing(true);
            if (MainActivity.API_TOKEN_present) new AvailableReviewsTask().execute(this);
            Snackbar.make(listView, "Project succesfully assigned!", Snackbar.LENGTH_SHORT).show();
        }
        else {
            Snackbar.make(listView, "Error code: " + responseCode, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        projectID = ((Certification) availableAdapter.getItem(position)).getProjectID();
        Log.d(TAG, "Clicked project_id: " + String.valueOf(projectID));
        new AssignProjectTask().execute(this);
    }
}
