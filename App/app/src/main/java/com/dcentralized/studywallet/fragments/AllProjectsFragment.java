package com.dcentralized.studywallet.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.activities.ProjectActivity;
import com.dcentralized.studywallet.adapters.ProjectsListAdapter;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.tasks.ProjectsTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;

/**
 * Fragment for viewing all projects
 *
 */
public class AllProjectsFragment extends Fragment implements Observer {
    private View layout;
    private ListView listProjects;
    private SwipeRefreshLayout refreshAllProjects;
    private ProjectsListAdapter adapter;
    private List<Project> projectList;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public AllProjectsFragment() {
        projectList = new ArrayList<>();
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
        refreshAllProjects = layout.findViewById(R.id.refreshAllProjects);
        adapter = new ProjectsListAdapter(getActivity(), R.id.listProjects, projectList);
        refreshAllProjects.setRefreshing(true);
        StudyWallet.getInstance(getActivity()).getAllProjects(this);
        listProjects.setAdapter(adapter);

        listProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProjectActivity.class);
                intent.putExtra("project", (Project)parent.getItemAtPosition(position));
                getActivity().startActivity(intent);
            }
        });

        refreshAllProjects.setColorSchemeResources(R.color.colorPrimary);
        final AllProjectsFragment fragment = this;
        refreshAllProjects.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                projectList.clear();
                adapter.notifyDataSetChanged();
                StudyWallet.getInstance(getActivity()).getAllProjects(fragment);
            }
        });

        return layout;
    }

    @Override
    public void update(Observable observable, final Object o) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (o != null) {
                    Project project = (Project) o;
                    projectList.add(project);
                    adapter.notifyDataSetChanged();
                } else {
                    refreshAllProjects.setRefreshing(false);
                }
            }
        });
    }
}
