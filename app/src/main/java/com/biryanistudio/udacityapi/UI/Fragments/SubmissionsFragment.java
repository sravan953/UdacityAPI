package com.biryanistudio.udacityapi.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biryanistudio.udacityapi.Tasks.SubmissionsTask;
import com.biryanistudio.udacityapi.Interfaces.IUpdateSubmissions;
import com.biryanistudio.udacityapi.Models.Submissions;
import com.biryanistudio.udacityapi.R;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public class SubmissionsFragment extends Fragment implements IUpdateSubmissions {
    View view;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.from(container.getContext()).inflate(R.layout.fragment_dummy, container, false);
        new SubmissionsTask().execute(this);
        return view;
    }

    @Override
    public void submissionsUI(List<Submissions> submissions) {
        if(submissions != null) {
            StringBuilder builder = new StringBuilder();
            for (Submissions submission : submissions) {
                builder.append(submission.toString());
                builder.append("\n");
            }
            ((TextView) view.findViewById(R.id.text)).setText(builder.toString());
        }
        else {
            ((TextView) view.findViewById(R.id.text)).setText("No projects available for code reviewing right now, check back later!");
        }
    }
}
