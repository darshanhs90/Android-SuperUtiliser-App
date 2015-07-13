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
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class IncomeTrackerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "IncomeTrackerFragment", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), Home.class);
        startActivity(intent);
        return inflater.inflate(R.layout.incometracker, container, false);
    }

}
