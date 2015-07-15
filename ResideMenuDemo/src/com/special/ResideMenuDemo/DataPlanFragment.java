package com.special.ResideMenuDemo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Mail: hsdars@gmail.com
 */
public class DataPlanFragment extends Fragment implements OnRefreshListener {
    private PullToRefreshLayout mPullToRefreshLayout;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    HospitalListAdapter expListAdapter;
    ArrayList<String[]> arrayList;
    JSONArray jArray=new JSONArray();
    ArrayAdapter<String> arrayAdapter;
    String LOGIN_URL = "http://api.bluebuttonconnector.healthit.gov/stage2?state=TX&limit=30&offset=0";
    ListView lv;
    int offset=30;
    int limit=0;
    View view;
    List<String> lvArray = new ArrayList<String>();
    EditText etState;
    Button bnGetProvider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        Toast.makeText(getActivity(), "Pull down to refresh the list", Toast.LENGTH_LONG).show();
        view = inflater.inflate(R.layout.dataplan, container, false);
        etState= (EditText) view.findViewById(R.id.etState);
        bnGetProvider= (Button) view.findViewById(R.id.getHealth);

        // Now give the find the PullToRefreshLayout and set it up
        mPullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptr_layout);
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);
        bnGetProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etState.getText().length()==2) {
                    //Toast.makeText(getActivity(),etState.getText(),Toast.LENGTH_LONG).show();
                    LOGIN_URL="http://api.bluebuttonconnector.healthit.gov/stage2?state="+etState.getText()+"&limit=30&offset=0";
                    new GetList().execute();
                }
                else
                    Toast.makeText(getActivity(),"Enter State in the Format xx ex:TX",Toast.LENGTH_LONG).show();
            }
        });


        //createGroupList();

        //createCollection();



        return view;
    }
    private void createGroupList() {

    }

    private void createCollection() {
        // preparing laptops collection(child)


        laptopCollection = new LinkedHashMap<String, List<String>>();

        for (int i=0;i<groupList.size();i++) {
            loadChild(arrayList.get(i));
            //load child correesponding to its index

            laptopCollection.put(groupList.get(i), childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
    @Override
    public void onRefreshStarted(View view) {

        Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();

        LOGIN_URL = "http://api.bluebuttonconnector.healthit.gov/stage2?state="+etState.getText()+"&limit=30&offset="+offset;
        //http://api.bluebuttonconnector.healthit.gov/stage2?state=TX&limit=30&offset=0
        offset+=30;
        if(offset>limit)
            offset=0;
        new GetList().execute();
        mPullToRefreshLayout.setRefreshComplete();
    }


    class GetList extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... args) {
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                Log.d("asd",LOGIN_URL);
                JSONObject j=(JSONObject)new JSONArrayParser().getJsonObject(LOGIN_URL);

                    JSONArray results = (JSONArray) j.get("results");
                    limit = Integer.parseInt(((JSONObject) j.get("meta")).get("total_results").toString()) - 30;
                    groupList = new ArrayList<String>();
                    arrayList = new ArrayList<String[]>();
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject jObj = (JSONObject) results.get(i);
                        // Log.d("asd",jObj+"");

                        groupList.add(jObj.get("name").toString());

                        String str[] = new String[7];
                        str[0] = "State : " + jObj.get("state").toString();
                        str[1] = "City : " + jObj.get("city").toString();
                        str[2] = "Address : " + jObj.get("address").toString();
                        str[3] = "Zip : " + jObj.get("zip").toString();
                        str[4] = "Phone : " + jObj.get("phone").toString();
                        str[5] = "Payment : " + jObj.get("payment").toString();
                        str[6] = "NPI : " + jObj.get("npi").toString();

                        arrayList.add(i, str);
                    }


                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        createCollection();
                        expListView = (ExpandableListView) view.findViewById(R.id.laptop_list);
                        expListAdapter = new HospitalListAdapter(getActivity(), groupList, laptopCollection);

                        expListView.setAdapter(expListAdapter);

                        //setGroupIndicatorToRight();

                        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                            public boolean onChildClick(ExpandableListView parent, View v,
                                                        int groupPosition, int childPosition, long id) {
                                String selected = (String) expListAdapter.getChild(
                                        groupPosition, childPosition);
                                // Toast.makeText(getActivity().getBaseContext(), selected, Toast.LENGTH_LONG.show();

                                return true;
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {



        }

    }






}
