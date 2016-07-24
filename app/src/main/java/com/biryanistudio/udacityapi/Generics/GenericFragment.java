package com.biryanistudio.udacityapi.Generics;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.FragmentType;
import com.biryanistudio.udacityapi.Models.EmptyRecyclerView;
import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.Adapters.AssignedAdapter;
import com.biryanistudio.udacityapi.UI.Adapters.AvailableAdapter;
import com.biryanistudio.udacityapi.UI.Adapters.FeedbackAdapter;
import com.biryanistudio.udacityapi.UI.MainActivity;

import java.util.List;

/**
 * Created by Sravan on 20-Jul-16.
 */
public class GenericFragment extends Fragment implements IGenericUpdateUI,
        SwipeRefreshLayout.OnRefreshListener {
    private FragmentType mFragmentType;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EmptyRecyclerView mRecyclerView;

    public static GenericFragment newInstance(final FragmentType fragmentType) {
        final Bundle args = new Bundle();
        args.putSerializable("TYPE", fragmentType);
        final GenericFragment fragment = new GenericFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentType = (FragmentType) getArguments().getSerializable("TYPE");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment, container, false);
        SwipeRefreshLayout swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorSecondary),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        final TextView emptyList = (TextView) view.findViewById(R.id.emptyList);
        emptyList.setText(getEmptyText());
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setEmptyView(emptyList);
        if (MainActivity.API_TOKEN_present) new GenericTask().execute(this);
        return view;
    }

    @Override
    public void newData(final List data) {
        try {
            mSwipeRefreshLayout.setRefreshing(false);
            final RecyclerView.Adapter mRecyclerAdapter = getRecyclerAdapter(data);
            mRecyclerView.setAdapter(mRecyclerAdapter);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        if (MainActivity.API_TOKEN_present) new GenericTask().execute(this);
    }

    private RecyclerView.Adapter getRecyclerAdapter(final List data) {
        switch(getFragmentType()) {
            case ASSIGNED:
                return new AssignedAdapter(data);
            case AVAILABLE:
                return new AvailableAdapter(data);
            case FEEDBACK:
                return new FeedbackAdapter(data);
            default:
                return null;
        }
    }

    private String getEmptyText() {
        switch (getFragmentType()) {
            case ASSIGNED:
                return getString(R.string.empty_assigned_review);
            case AVAILABLE:
                return getString(R.string.empty_available_review);
            case FEEDBACK:
                return getString(R.string.empty_feedback);
            default:
                return null;
        }
    }

    public FragmentType getFragmentType() {
        return mFragmentType;
    }
}
