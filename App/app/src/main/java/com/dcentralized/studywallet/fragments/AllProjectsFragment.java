package com.dcentralized.studywallet.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.adapters.ProjectsListAdapter;
import com.dcentralized.studywallet.models.StudyWallet;

/**
 * Fragment for viewing all projects
 *
 */
public class AllProjectsFragment extends Fragment {
    private View layout;
    private ListView listProjects;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public AllProjectsFragment() {
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
        layout = inflater.inflate(R.layout.fragment_all_projects, container, false);
        listProjects = layout.findViewById(R.id.listAllProjects);
        listProjects.setAdapter(new ProjectsListAdapter(getActivity(), R.id.listProjects, StudyWallet.getInstance(getActivity()).getAllProjects()));
        return layout;
    }
}
