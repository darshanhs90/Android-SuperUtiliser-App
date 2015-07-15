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
public class ReminderFragment extends Fragment implements OnRefreshListener {
    private PullToRefreshLayout mPullToRefreshLayout;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    HospitalListAdapter expListAdapter;
    ArrayList<String[]> arrayList;
    JSONArray jArray=new JSONArray();
    ArrayAdapter<String> arrayAdapter;
    String LOGIN_URL = "https://api.humanapi.co/v1/human/medical/medications?access_token=demo";
    ListView lv;
    int offset=30;
    View view;
    List<String> lvArray = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.reminder, container, false);
        Toast.makeText(getActivity(), "Pull down to refresh the list", Toast.LENGTH_LONG).show();
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
                groupList = new ArrayList<String>();
                arrayList=new ArrayList<String[]>();
                //Log.d("asd", j + "");
                JSONArray jsonArray= (JSONArray) j.get("data");
                for (int  i=0;i<jsonArray.length();i++)
                {
                    JSONObject jObj= (JSONObject) jsonArray.get(i);
                    if(jObj.has("name"))
                        groupList.add(jObj.get("name").toString());
                    else
                        groupList.add(jObj.get("commonBrandName").toString());
                    String str[]=new String[13];
                    str[0]="Source : "+jObj.get("source").toString();
                    str[1]="Prescribed Timestamp : "+jObj.get("createdAt").toString();
                    if(jObj.has("instructions"))
                        str[2]="Instructions : "+jObj.get("instructions").toString();
                    else
                        str[2]="Instructions : "+"NA";
                    str[3]="Provider : "+jObj.get("provider").toString();
                    str[4]="Provider ID : "+jObj.get("providerID").toString();
                    if(jObj.has("ndcCode"))
                        str[5]="NDC Code : "+jObj.get("ndcCode").toString();
                    else
                        str[5]="NDC Code : "+"NA";
                    if(jObj.has("productName"))
                        str[6]="Product Name : "+jObj.get("productName").toString();
                    else
                        str[6]="Product Name : "+"NA";

                    if(jObj.has("commonBrandName"))
                        str[7]="Common Brand Name : "+jObj.get("commonBrandName").toString();
                    else
                        str[7]="Common Brand Name : "+"NA";
                    if(jObj.has("dosageInfo"))
                        str[8]="Dosage Info : "+jObj.get("dosageInfo").toString();
                    else
                        str[8]="Dosage Info : "+"NA";
                    JSONObject jobj1;
                    if(jObj.has("details"))
                     jobj1=((JSONObject)jObj.get("details"));
                    else
                        jobj1= (JSONObject) jObj.get("organization");
                    if(jobj1.has("route"))
                        str[9]="Route : "+jobj1.get("route");
                    else
                        str[9]="Route : "+"NA";
                    if(jobj1.has("frequency"))
                        str[10]="Dosage Info : "+((JSONObject)((JSONObject)jObj.get("details")).get("frequency")).get("number").toString();
                    else
                        str[10]="Dosage Info : "+"NA";
                    if(jobj1.has("frequency"))
                        str[11]="Dosage Unit : "+((JSONObject)((JSONObject)jObj.get("details")).get("frequency")).get("unit").toString();
                    else
                        str[11]="Dosage Unit : "+"NA";
                    str[12]="Organisation : "+(((JSONObject)jObj.get("organization")).get("name")).toString();
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
