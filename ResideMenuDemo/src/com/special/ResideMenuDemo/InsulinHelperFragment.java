package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Mail: hsdars@gmail.com
 */
public class InsulinHelperFragment extends Fragment {
    WebView webView;
    String summary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.insulinhelper, container, false);;
        summary = "<html><body><script type='text/javascript' src='https://www.healthtap.com/widget/askdoc.js' ></script><div id='htapWidgetAskdoc' data-color='rgb(246, 246, 246)' style='height: 680px; width: 500px;'></div></body></html>";
        webView= (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(summary, "text/html", null);
        return view;
    }

}
