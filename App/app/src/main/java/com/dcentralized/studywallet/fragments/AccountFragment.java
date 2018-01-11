package com.dcentralized.studywallet.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.StudyWallet;
import com.dcentralized.studywallet.models.User;

/**
 * Fragment to view account
 *
 * @author Tom de Wildt
 */
public class AccountFragment extends Fragment {
    private View layout;

    /**
     * Empty constructor for creating intents
     *
     * @author Tom de Wildt
     */
    public AccountFragment() {
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
        layout = inflater.inflate(R.layout.fragment_account, container, false);
        setUserInfo(StudyWallet.getInstance(getActivity()).getCurrentUser());
        return layout;
    }

    @SuppressLint("SetTextI18n")
    public void setUserInfo(User user){
        ((TextView)layout.findViewById(R.id.tvAccountName)).setText(user.getFirstname() + " " + user.getLastname());
        ((TextView)layout.findViewById(R.id.tvAccountEmail)).setText(user.getEmail());
        ((TextView)layout.findViewById(R.id.tvAccountStudentId)).setText(user.getId());
        ((TextView)layout.findViewById(R.id.tvBalance)).setText(user.getBalance());
        ((TextView)layout.findViewById(R.id.tvTotalCoins)).setText(user.getTotalCoins());
        ((TextView)layout.findViewById(R.id.tvType)).setText(user.getType().toString());
    }
}
