package com.dcentralized.studywallet.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.activities.MainActivity;
import com.dcentralized.studywallet.adapters.TransactionListAdapter;
import com.dcentralized.studywallet.models.StudyWallet;

/**
 * Home fragment for the application
 *
 * @author Tom de Wildt
 */
public class DashboardFragment extends Fragment implements OnClickListener {
    private View layout;
    private TextView textCoins;
    private TextView textRank;
    private ListView listTransactions;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public DashboardFragment() {
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
        layout = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textCoins = layout.findViewById(R.id.textCoins);
        textRank = layout.findViewById(R.id.textRank);
        listTransactions = layout.findViewById(R.id.listTransactions);
        layout.findViewById(R.id.buttonAddProject).setOnClickListener(this);

        // Setup UI
        Activity activity = getActivity();
        textCoins.setText(String.valueOf(StudyWallet.getInstance(activity).getCurrentUser().getBalance()));
        textRank.setText(String.valueOf(StudyWallet.getInstance(activity).getCurrentUser().getRank()));
        listTransactions.setAdapter(new TransactionListAdapter(activity, R.layout.transaction_list_item, StudyWallet.getInstance(activity).getCurrentUser().getTransactions()));

        return layout;
    }

    /**
     * Handles click events
     *
     * @param view
     * @author Tom de Wildt
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddProject:
                ((MainActivity)getActivity()).changeFragment(R.id.nav_projects);
                break;
        }
    }
}
