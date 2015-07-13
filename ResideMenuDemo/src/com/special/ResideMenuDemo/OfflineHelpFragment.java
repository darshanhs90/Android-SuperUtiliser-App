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
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class OfflineHelpFragment extends Fragment implements OnRefreshListener {
    private PullToRefreshLayout mPullToRefreshLayout;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    HospitalListAdapter expListAdapter;
    ArrayList<String[]> arrayList;
    JSONArray jArray=new JSONArray();
    ArrayAdapter<String> arrayAdapter;
    String LOGIN_URL = "https://api.humanapi.co/v1/human?access_token=demo";
    ListView lv;
    View view;
    List<String> lvArray = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.offlinehelp, container, false);

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
                JSONObject jObj=(JSONObject)new JSONArrayParser().getJsonObject(LOGIN_URL);
                //JSONArray results= (JSONArray) j.get("results");
                groupList = new ArrayList<String>();
                arrayList=new ArrayList<String[]>();
                for (int  i=0;i<9;i++)
                {
                    // Log.d("asd",jObj+"");


                    String str[]=new String[10];
                    if(i==0){
                        groupList.add("Blood Glucose");
                       str=new String[3];
                        str[0]="Source : "+((JSONObject)jObj.get("bloodGlucose")).get("source").toString();
                        str[1]="Value : "+((JSONObject)jObj.get("bloodGlucose")).get("value").toString()+((JSONObject)jObj.get("bloodGlucose")).get("unit").toString();
                        str[2]="Time Stamp : "+((JSONObject)jObj.get("bloodGlucose")).get("timestamp").toString();
                    }
                    else if(i==1){
                            groupList.add("Blood Oxygen");
                            str=new String[3];
                            str[0]="Source : "+((JSONObject)jObj.get("bloodOxygen")).get("source").toString();
                            str[1]="Value : "+((JSONObject)jObj.get("bloodOxygen")).get("value").toString()+((JSONObject)jObj.get("bloodOxygen")).get("unit").toString();
                            str[2]="Time Stamp : "+((JSONObject)jObj.get("bloodOxygen")).get("timestamp").toString();

                    }
                    else if(i==2){
                        groupList.add("Blood Pressure");
                        str=new String[5];
                        str[0]="Source : "+((JSONObject)jObj.get("bloodPressure")).get("source").toString();
                        str[1]="Systolic : "+((JSONObject)jObj.get("bloodPressure")).get("systolic").toString()+((JSONObject)jObj.get("bloodPressure")).get("unit").toString();
                        str[2]="Diastolic : "+((JSONObject)jObj.get("bloodPressure")).get("diastolic").toString()+((JSONObject)jObj.get("bloodPressure")).get("unit").toString();
                        str[3]="Heart Rate : "+((JSONObject)jObj.get("bloodPressure")).get("heartRate").toString();
                        str[4]="Time Stamp : "+((JSONObject)jObj.get("bloodPressure")).get("timestamp").toString();
                    }
                    else if(i==3){
                        groupList.add("BMI");
                        str=new String[3];
                        str[0]="Source : "+((JSONObject)jObj.get("bmi")).get("source").toString();
                        str[1]="Value : "+((JSONObject)jObj.get("bmi")).get("value").toString()+((JSONObject)jObj.get("bmi")).get("unit").toString();
                        str[2]="Time Stamp : "+((JSONObject)jObj.get("bmi")).get("timestamp").toString();

                    }
                    else if(i==4){
                        groupList.add("Body Fat");
                        str=new String[3];
                        str[0]="Source : "+((JSONObject)jObj.get("bodyFat")).get("source").toString();
                        str[1]="Value : "+((JSONObject)jObj.get("bodyFat")).get("value").toString()+((JSONObject)jObj.get("bodyFat")).get("unit").toString();
                        str[2]="Time Stamp : "+((JSONObject)jObj.get("bodyFat")).get("timestamp").toString();
                    }
                    else if(i==5){
                        groupList.add("Height");
                        str=new String[3];
                        str[0]="Source : "+((JSONObject)jObj.get("height")).get("source").toString();
                        str[1]="Value : "+((JSONObject)jObj.get("height")).get("value").toString()+((JSONObject)jObj.get("height")).get("unit").toString();
                        str[2]="Time Stamp : "+((JSONObject)jObj.get("height")).get("timestamp").toString();

                    }
                    else if(i==6){
                        groupList.add("Heart Rate");
                        str=new String[3];
                        str[0]="Source : "+((JSONObject)jObj.get("heartRate")).get("source").toString();
                        str[1]="Value : "+((JSONObject)jObj.get("heartRate")).get("value").toString()+((JSONObject)jObj.get("heartRate")).get("unit").toString();
                        str[2]="Time Stamp : "+((JSONObject)jObj.get("heartRate")).get("timestamp").toString();

                    }
                    else if(i==7){
                        groupList.add("Weight");
                        str=new String[3];
                        str[0]="Source : "+((JSONObject)jObj.get("weight")).get("source").toString();
                        str[1]="Value : "+((JSONObject)jObj.get("weight")).get("value").toString()+((JSONObject)jObj.get("weight")).get("unit").toString();
                        str[2]="Time Stamp : "+((JSONObject)jObj.get("weight")).get("timestamp").toString();

                    }
                    else if(i==8){
                        groupList.add("Activity Summary");
                        str=new String[5];
                        str[0]="Source : "+((JSONObject)jObj.get("activitySummary")).get("source").toString();
                        str[1]="Distance : "+((JSONObject)jObj.get("activitySummary")).get("distance").toString();
                        str[2]="Duration : "+((JSONObject)jObj.get("activitySummary")).get("duration").toString();
                        str[3]="Total : "+((JSONObject)jObj.get("activitySummary")).get("total").toString();
                        str[4]="Calories : "+((JSONObject)jObj.get("activitySummary")).get("calories").toString();
                    }

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
