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
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class MedicaidFragment extends Fragment {
    WebView webView;
    String summary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.medicaid, container, false);;
        summary = "<html><body><script type='text/javascript' src='https://www.healthtap.com/widget/tips.js' ></script><div id='htapWidgetTips' style='height: 680px; width: 500px;'></div></body></html>";
        webView= (WebView) view.findViewById(R.id.webview);
        Button bnRefresh= (Button) view.findViewById(R.id.bnRefresh);
        bnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadData(summary, "text/html", null);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(summary, "text/html", null);
        return view;
    }

}
