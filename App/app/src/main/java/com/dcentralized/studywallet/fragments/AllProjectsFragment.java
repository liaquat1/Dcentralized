package com.dcentralized.studywallet.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.adapters.ProjectsListAdapter;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.StudyWallet;

import java.util.List;

/**
 * Fragment for viewing all projects
 *
 */
public class AllProjectsFragment extends Fragment {
    private View layout;
    private ListView listProjects;
    private List<Project> projectList;

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
        projectList = StudyWallet.getInstance(getActivity()).getAllProjects();
        listProjects.setAdapter(new ProjectsListAdapter(getActivity(), R.id.listProjects, projectList));

        listProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Testerino", "memefcdas");
            }
        });
        return layout;
    }
}
