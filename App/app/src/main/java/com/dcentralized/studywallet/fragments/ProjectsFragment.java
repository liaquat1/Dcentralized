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
import com.dcentralized.studywallet.activities.MyProjectActivity;
import com.dcentralized.studywallet.adapters.ProjectsListAdapter;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.tasks.ProjectsTask;
import com.dcentralized.studywallet.tasks.TransactionsTask;

import java.util.concurrent.CyclicBarrier;

/**
 * Fragment to view projects
 *
 * @author Tom de Wildt
 */
public class ProjectsFragment extends Fragment {
    private View layout;
    private ListView listProjects;
    private SwipeRefreshLayout refreshProjects;
    private ProjectsListAdapter adapter;
    private ProjectsTask task;

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
        refreshProjects = layout.findViewById(R.id.refreshProjects);
        adapter = new ProjectsListAdapter(getActivity(), R.id.listProjects, StudyWallet.getInstance(getActivity()).getCurrentUser().getProjects());

        listProjects.setAdapter(adapter);
        listProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MyProjectActivity.class);
                intent.putExtra("project", (Project)parent.getItemAtPosition(position));
                getActivity().startActivity(intent);
            }
        });

        refreshProjects.setColorSchemeResources(R.color.colorPrimary);
        refreshProjects.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CyclicBarrier barrier = new CyclicBarrier(1, new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StudyWallet.getInstance(getActivity()).getCurrentUser().setProjects(task.getResult());
                                adapter.notifyDataSetChanged();
                                refreshProjects.setRefreshing(false);
                            }
                        });
                    }
                });
                task = new ProjectsTask(StudyWallet.getInstance(getActivity()).getCurrentUser().getId(), barrier);
                new Thread(task).start();
            }
        });

        return layout;
    }
}
