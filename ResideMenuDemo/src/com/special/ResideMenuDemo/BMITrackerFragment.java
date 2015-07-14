package com.special.ResideMenuDemo;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class BMITrackerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String DEBUG_TAG = "NetworkStatusExample";
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(getActivity().getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();

        if(isMobileConn==true){
            Toast.makeText(getActivity(), "Using Cellular Data", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(), "Using Wifi Data", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getActivity(), TrafficMonitorActivity.class);
        startActivity(intent);
        return inflater.inflate(R.layout.bmitracker, container, false);
    }

}
