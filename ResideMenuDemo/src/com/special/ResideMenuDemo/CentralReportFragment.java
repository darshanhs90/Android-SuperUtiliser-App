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
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class CentralReportFragment extends Fragment implements OnRefreshListener {
    private PullToRefreshLayout mPullToRefreshLayout;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    HospitalListAdapter expListAdapter;
    ArrayList<String[]> arrayList;
    JSONArray jArray=new JSONArray();
    ArrayAdapter<String> arrayAdapter;
    String LOGIN_URL = "https://api.betterdoctor.com/2015-01-27/doctors?query=Toothache&skip=0&limit=20&user_key=6f42bffd9790301ebe03ab1ec20d9b93";
    ListView lv;
    int offset=10;
    int limit=0;
    View view;
    List<String> lvArray = new ArrayList<String>();
    EditText etState;
    Button bnGetProvider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        view = inflater.inflate(R.layout.centralreport, container, false);
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
                if (etState.getText().length() > 4) {
                    //Toast.makeText(getActivity(),etState.getText(),Toast.LENGTH_LONG).show();
                    //https://api.edamam.com/search?q=chicken&app_id=8e9d063b&app_key=8657a32df16db3063e61214978826997
                    LOGIN_URL = "https://api.betterdoctor.com/2015-01-27/doctors?query=" + etState.getText() + "&skip=0&limit=20&user_key=6f42bffd9790301ebe03ab1ec20d9b93";
                    new GetList().execute();
                } else
                    Toast.makeText(getActivity(), "Query too short to get Insight", Toast.LENGTH_LONG).show();
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
        offset+=20;
        if(offset>60)
            offset=0;
        LOGIN_URL = "https://api.betterdoctor.com/2015-01-27/doctors?query=" + etState.getText() + "&skip="+offset+"&limit=20&user_key=6f42bffd9790301ebe03ab1ec20d9b93";
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

                JSONArray results = (JSONArray) j.get("data");
                groupList = new ArrayList<String>();
                arrayList = new ArrayList<String[]>();
                for (int i = 0; i < results.length(); i++) {
                    JSONObject jObj = (JSONObject) results.get(i);
                    String name=((JSONObject)jObj.get("profile")).get("first_name").toString()+" "+((JSONObject)jObj.get("profile")).get("last_name").toString();
                    groupList.add(name);
                    JSONArray practices= (JSONArray) jObj.get("practices");
                    ArrayList<String> st=new ArrayList<String>();
                    for (int k=0;k<practices.length();k++){
                        JSONObject tempObj= (JSONObject) practices.get(k);
                        st.add("Name : "+tempObj.get("name").toString());
                        String city=((JSONObject)tempObj.get("visit_address")).get("city").toString();
                        String state=((JSONObject)tempObj.get("visit_address")).get("state_long").toString();
                        String street1=((JSONObject)tempObj.get("visit_address")).get("street").toString();
                        String zip=((JSONObject)tempObj.get("visit_address")).get("zip").toString();
                        st.add("Address : "+street1+","+city+","+state+","+zip);
                        JSONArray phones= (JSONArray) tempObj.get("phones");
                        for (int l=0;l<phones.length();l++){
                            JSONObject phoneObj= (JSONObject) phones.get(l);
                            st.add(phoneObj.get("type").toString().toUpperCase()+" : "+phoneObj.get("number").toString());
                        }
                       st.add("Language : " + ((JSONObject) ((JSONArray) tempObj.get("languages")).get(0)).get("name").toString());
                    }

                    st.add("Slug : " + ((JSONObject) jObj.get("profile")).get("slug").toString());
                    st.add("Title : " + ((JSONObject) jObj.get("profile")).get("title").toString());
                    st.add("Image URL : " + ((JSONObject) jObj.get("profile")).get("image_url").toString());
                    st.add("Gender : " + ((JSONObject) jObj.get("profile")).get("gender").toString());
                    JSONArray ratings= (JSONArray) jObj.get("ratings");
                    if(ratings.length()>0) {
                        JSONObject rating = (JSONObject) ratings.get(0);
                        st.add("Rating : " + rating.get("rating").toString());
                        st.add("Provider : " + rating.get("provider").toString());
                        st.add("Provider URL : " + rating.get("provider_url").toString());
                    }
                    JSONArray specialties= (JSONArray) jObj.get("specialties");
                    if(specialties.length()>0) {
                        JSONObject speciality = (JSONObject) specialties.get(0);
                        st.add("Speciality : " + speciality.get("name").toString());
                        st.add("Description : " + speciality.get("description").toString());
                        st.add("Category : " + speciality.get("category").toString());
                    }
                    String str[]=new String[st.size()];
                    for(int m=0;m<st.size();m++)
                    {
                        str[m]=st.get(m).toString();
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
