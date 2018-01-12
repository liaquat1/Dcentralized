package com.dcentralized.studywallet.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.activities.MyProjectActivity;
import com.dcentralized.studywallet.adapters.ProjectsListAdapter;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.StudyWallet;

/**
 * Fragment to view projects
 *
 * @author Tom de Wildt
 */
public class ProjectsFragment extends Fragment {
    private View layout;
    private ListView listProjects;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public ProjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Sets the view for the fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     * @author Tom de Wildt
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_projects, container, false);
        listProjects = layout.findViewById(R.id.listProjects);
        listProjects.setAdapter(new ProjectsListAdapter(getActivity(), R.id.listProjects, StudyWallet.getInstance(getActivity()).getCurrentUser().getProjects()));

        listProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MyProjectActivity.class);
                intent.putExtra("project", (Project)parent.getItemAtPosition(position));
                getActivity().startActivity(intent);
            }
        });

        return layout;
    }
}
