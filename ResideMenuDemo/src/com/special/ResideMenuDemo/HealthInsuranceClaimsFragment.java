package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class HealthInsuranceClaimsFragment extends Fragment implements OnRefreshListener {


        private PullToRefreshLayout mPullToRefreshLayout;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout
            View view = inflater.inflate(R.layout.healthinsuranceclaims, container, false);

            // Now give the find the PullToRefreshLayout and set it up
            mPullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptr_layout);
            ActionBarPullToRefresh.from(getActivity())
                    .allChildrenArePullable()
                    .listener(this)
                    .setup(mPullToRefreshLayout);


            return view;
        }

        @Override
        public void onRefreshStarted(View view) {

            Toast.makeText(getActivity(),"Refresh started",Toast.LENGTH_SHORT).show();
            mPullToRefreshLayout.setRefreshComplete();
    }
}
