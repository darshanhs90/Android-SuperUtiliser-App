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
public class HealthInsuranceClaimsFragment extends Fragment implements OnRefreshListener {
    private PullToRefreshLayout mPullToRefreshLayout;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    HospitalListAdapter expListAdapter;
    ArrayList<String[]> arrayList;
    JSONArray jArray=new JSONArray();
    ArrayAdapter<String> arrayAdapter;
    String LOGIN_URL = "https://api.humanapi.co/v1/human/medical/test_results?access_token=demo";
    ListView lv;
    int offset=30;
    View view;
    List<String> lvArray = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.medicalcostoptimisation, container, false);
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
                    groupList.add(jObj.get("name").toString());
                    String str[]=new String[15];
                    str[0]="Source : "+jObj.get("source").toString();
                    str[1]="Tested Date : "+jObj.get("createdAt").toString().substring(0, 10);
                    str[2]="Hospital Name : "+((JSONObject)jObj.get("organization")).get("name").toString();


                    JSONArray components= (JSONArray) jObj.get("components");
                    JSONObject componentsObj;
                    if(components.length()>0)
                        componentsObj= (JSONObject) components.get(0);
                    else
                        componentsObj= jObj;

                    if(componentsObj.has("componentComments"))
                        str[3]="Comments : "+componentsObj.get("componentComments").toString();
                    else
                        str[3]="Comments : "+"NA";

                    if(componentsObj.has("high"))
                        str[4]="High : "+componentsObj.get("high").toString();
                    else
                        str[4]="High : "+"NA";

                    if(componentsObj.has("low"))
                        str[5]="Low : "+componentsObj.get("low").toString();
                    else
                        str[5]="Low : "+"NA";

                    if(componentsObj.has("name"))
                        str[6]="Component Name : "+componentsObj.get("name").toString();
                    else
                        str[6]="Component Name : "+"NA";

                    if(componentsObj.has("value"))
                        str[7]="Value : "+componentsObj.get("value").toString();
                    else
                        str[7]="Value : "+"NA";

                    if(jObj.has("orderedBy"))
                        str[8]="Ordered By : "+jObj.get("orderedBy").toString();
                    else
                        str[8]="Ordered By : "+"NA";

                    JSONArray recipients= (JSONArray) jObj.get("recipients");
                    JSONObject recipientsObj;
                    if(recipients.length()>0)
                         recipientsObj= (JSONObject) recipients.get(0);
                    else
                         recipientsObj= jObj;

                    if(recipientsObj.has("name"))
                        str[9]="Recipients Name : "+recipientsObj.get("name").toString();
                    else
                        str[9]="Recipients Name : "+"NA";

                    if(recipientsObj.has("objectID"))
                        str[10]="Object Id : "+recipientsObj.get("objectID").toString();
                    else
                        str[10]="Object Id : "+"NA";

                    if(recipientsObj.has("isPCP"))
                        str[11]="PCP : "+recipientsObj.get("isPCP").toString();
                    else
                        str[11]="PCP : "+"NA";

                    JSONArray allResults= (JSONArray) jObj.get("allResults");
                    JSONObject allResultsObj;
                    if(allResults.length()>0)
                        allResultsObj= (JSONObject) allResults.get(0);
                    else
                        allResultsObj= jObj;

                    if(allResultsObj.has("value"))
                        str[12]="Result Name : "+allResultsObj.get("name").toString();
                    else
                        str[12]="Result Name : "+"NA";

                    if(allResultsObj.has("value"))
                        str[13]="Result Value : "+allResultsObj.get("value").toString();
                    else
                        str[13]="Result Value : "+"NA";

                    if(allResultsObj.has("resultDateTime"))
                        str[14]="Result Date : "+allResultsObj.get("resultDateTime").toString();
                    else
                        str[14]="Result Date : "+"NA";

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
