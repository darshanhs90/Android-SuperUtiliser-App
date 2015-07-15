package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class AppointmentPhonenumberFragment extends Fragment {
//http://www.avvo.com/?prefer_mobile=true
WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"AppointmentPhonenumberFragment",Toast.LENGTH_SHORT).show();
        View view=inflater.inflate(R.layout.appointmentphonenumber, container, false);
        webView= (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.avvo.com/?prefer_mobile=true");
        return view;
    }

}
