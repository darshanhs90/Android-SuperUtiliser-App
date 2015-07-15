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
public class HospitalListFragment extends Fragment implements OnRefreshListener {
    private PullToRefreshLayout mPullToRefreshLayout;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    HospitalListAdapter expListAdapter;
    ArrayList<String[]> arrayList;
    JSONArray jArray=new JSONArray();
    ArrayAdapter<String> arrayAdapter;
    String LOGIN_URL = "http://api.bluebuttonconnector.healthit.gov/organizations?limit=30&offset=0";
    ListView lv;
    int offset=30;
    View view;
    List<String> lvArray = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.hospitallist, container, false);

        // Now give the find the PullToRefreshLayout and set it up
        mPullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptr_layout);
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);
        new GetList().execute();
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

        LOGIN_URL = "http://api.bluebuttonconnector.healthit.gov/organizations?limit=30&offset="+offset;
        offset+=30;
        if(offset>150)
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
                JSONObject j=(JSONObject)new JSONArrayParser().getJsonObject(LOGIN_URL);
                JSONArray results= (JSONArray) j.get("results");
                groupList = new ArrayList<String>();
                arrayList=new ArrayList<String[]>();
                for (int  i=0;i<results.length();i++)
                {
                    JSONObject jObj= (JSONObject) results.get(i);
                   // Log.d("asd",jObj+"");

                    groupList.add(jObj.get("organization").toString());
                    Log.d("asd", groupList.get(i)+"");
                    String str[]=new String[19];
                    str[0]="Description : "+jObj.get("description").toString();
                    str[1]="State : "+((JSONArray)jObj.get("states")).toString();
                    str[2]="URL : "+((JSONObject)jObj.get("url")).get("login").toString();
                    str[3]="Services";
                    str[4]="Refills : "+((JSONObject)jObj.get("services")).get("refills").toString();
                    str[5]="Automatic Refills : "+((JSONObject)jObj.get("services")).get("automatic_refills").toString();
                    str[6]="Transfer Prescriptions : "+((JSONObject)jObj.get("services")).get("transfer_prescriptions").toString();
                    str[7]="Bill Pay : "+((JSONObject)jObj.get("services")).get("bill_pay").toString();
                    str[8]="Caregiving : "+((JSONObject)jObj.get("services")).get("caregiving").toString();
                    str[9]="Dispute : "+((JSONObject)jObj.get("services")).get("dispute").toString();
                    str[10]="Family Prescriptions : "+((JSONObject)jObj.get("services")).get("family_prescriptions").toString();
                    str[11]="New Prescriptions : "+((JSONObject)jObj.get("services")).get("new_prescriptions").toString();
                    str[12]="Open Notes : "+((JSONObject)jObj.get("services")).get("open_notes").toString();
                    str[13]="Reminders : "+((JSONObject)jObj.get("services")).get("reminders").toString();
                    str[14]="Scheduling : "+((JSONObject)jObj.get("services")).get("scheduling").toString();
                    str[15]="Search : "+((JSONObject)jObj.get("services")).get("search").toString();
                    str[16]="Secure Messaging : "+((JSONObject)jObj.get("services")).get("secure_messaging").toString();
                    str[17]="Self Entered : "+((JSONObject)jObj.get("services")).get("self_entered").toString();
                    str[18]="Shop : "+((JSONObject)jObj.get("services")).get("shop").toString();

                    arrayList.add(i,str);
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
