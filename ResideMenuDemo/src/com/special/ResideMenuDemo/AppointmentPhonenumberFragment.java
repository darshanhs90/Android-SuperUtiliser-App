package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Mail: hsdars@gmail.com
 */
public class AppointmentPhonenumberFragment extends Fragment {
//http://www.avvo.com/?prefer_mobile=true
WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.appointmentphonenumber, container, false);
        webView= (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.avvo.com/?prefer_mobile=true");
        return view;
    }

}
