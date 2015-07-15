package com.special.ResideMenuDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vinsol.expensetracker.Home;

/**
 * Mail: hsdars@gmail.com
 */
public class IncomeTrackerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Income Tracker", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), Home.class);
        startActivity(intent);
        return inflater.inflate(R.layout.incometracker, container, false);
    }

}
