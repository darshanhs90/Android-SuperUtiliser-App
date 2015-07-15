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
public class NutritionFoodFragment extends Fragment implements OnRefreshListener {
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
        view = inflater.inflate(R.layout.nutritionfood, container, false);
        etState= (EditText) view.findViewById(R.id.etState);
        bnGetProvider= (Button) view.findViewById(R.id.getHealth);
        Toast.makeText(getActivity(), "Pull down to refresh the list", Toast.LENGTH_LONG).show();
        // Now give the find the PullToRefreshLayout and set it up
        mPullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptr_layout);
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);
        bnGetProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etState.getText().length()>3) {
                    //Toast.makeText(getActivity(),etState.getText(),Toast.LENGTH_LONG).show();
                    //https://api.edamam.com/search?q=chicken&app_id=8e9d063b&app_key=8657a32df16db3063e61214978826997
                    LOGIN_URL="https://api.edamam.com/search?q="+etState.getText()+"&app_id=8e9d063b&app_key=8657a32df16db3063e61214978826997";
                    new GetList().execute();
                }
                else
                    Toast.makeText(getActivity(),"Food Name too short to get Insight",Toast.LENGTH_LONG).show();
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

        LOGIN_URL="https://api.edamam.com/search?q="+etState.getText()+"&app_id=8e9d063b&app_key=8657a32df16db3063e61214978826997";
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

                JSONArray results = (JSONArray) j.get("hits");
                groupList = new ArrayList<String>();
                arrayList = new ArrayList<String[]>();
                for (int i = 0; i < results.length(); i++) {
                    JSONObject jObj = (JSONObject) results.get(i);
                    // Log.d("asd",jObj+"");
                    JSONObject recipe= (JSONObject) jObj.get("recipe");
                    groupList.add(recipe.get("label").toString());

                    String str[] = new String[34];
                    str[0] = "Diet Labels : " + recipe.get("dietLabels").toString();
                    str[1] = "Health Labels : " + recipe.get("healthLabels").toString();
                    str[2] = "Ingredients : " + recipe.get("ingredients").toString();
                    str[3] = "Calories : " + recipe.get("calories").toString();
                    str[4] = "Total Weight : " + recipe.get("totalWeight").toString();
                    JSONObject nutrients= (JSONObject) recipe.get("totalNutrients");

                    if(nutrients.has("ENERC_KCAL"))
                        str[5] = "Energy : " + ((JSONObject)nutrients.get("ENERC_KCAL")).get("quantity").toString()+((JSONObject)nutrients.get("ENERC_KCAL")).get("unit").toString();
                    else
                        str[5]="Energy : "+"NA";

                    if(nutrients.has("FAT"))
                        str[6] = ((JSONObject)nutrients.get("FAT")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FAT")).get("quantity").toString()+((JSONObject)nutrients.get("FAT")).get("unit").toString();
                    else
                        str[6]="FAT : NA";

                    if(nutrients.has("FASAT"))
                        str[7] = ((JSONObject)nutrients.get("FASAT")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FASAT")).get("quantity").toString()+((JSONObject)nutrients.get("FASAT")).get("unit").toString();
                    else
                        str[7]="Saturated : NA";

                    if(nutrients.has("FATRN"))
                        str[8] = ((JSONObject)nutrients.get("FATRN")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FATRN")).get("quantity").toString()+((JSONObject)nutrients.get("FATRN")).get("unit").toString();
                    else
                        str[8]="Trans : NA";

                    if(nutrients.has("FAMS"))
                        str[9] = ((JSONObject)nutrients.get("FAMS")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FAMS")).get("quantity").toString()+((JSONObject)nutrients.get("FAMS")).get("unit").toString();
                    else
                        str[9]="Monounsaturated : NA";

                    if(nutrients.has("FAPU"))
                        str[10] = ((JSONObject)nutrients.get("FAPU")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FAPU")).get("quantity").toString()+((JSONObject)nutrients.get("FAPU")).get("unit").toString();
                    else
                        str[10]="Polyunsaturated : NA";

                    if(nutrients.has("CHOCDF"))
                        str[11] = ((JSONObject)nutrients.get("CHOCDF")).get("label").toString()+" : " + ((JSONObject)nutrients.get("CHOCDF")).get("quantity").toString()+((JSONObject)nutrients.get("CHOCDF")).get("unit").toString();
                    else
                        str[11]="Carbs : NA";

                    if(nutrients.has("SUGAR"))
                        str[12] = ((JSONObject)nutrients.get("SUGAR")).get("label").toString()+" : " + ((JSONObject)nutrients.get("SUGAR")).get("quantity").toString()+((JSONObject)nutrients.get("SUGAR")).get("unit").toString();
                    else
                        str[12]="Sugar : NA";

                    if(nutrients.has("PROCNT"))
                        str[13] = ((JSONObject)nutrients.get("PROCNT")).get("label").toString()+" : " + ((JSONObject)nutrients.get("PROCNT")).get("quantity").toString()+((JSONObject)nutrients.get("PROCNT")).get("unit").toString();
                    else
                        str[13]="Proteins : NA";

                    if(nutrients.has("CHOLE"))
                        str[14] = ((JSONObject)nutrients.get("CHOLE")).get("label").toString()+" : " + ((JSONObject)nutrients.get("CHOLE")).get("quantity").toString()+((JSONObject)nutrients.get("CHOLE")).get("unit").toString();
                    else
                        str[14]="Cholesterol : NA";

                    if(nutrients.has("NA"))
                        str[15] = ((JSONObject)nutrients.get("NA")).get("label").toString()+" : " + ((JSONObject)nutrients.get("NA")).get("quantity").toString()+((JSONObject)nutrients.get("NA")).get("unit").toString();
                    else
                        str[15]="Sodium : NA";

                    if(nutrients.has("K"))
                        str[16] = ((JSONObject)nutrients.get("K")).get("label").toString()+" : " + ((JSONObject)nutrients.get("K")).get("quantity").toString()+((JSONObject)nutrients.get("K")).get("unit").toString();
                    else
                        str[16]="Potassium : NA";

                    if(nutrients.has("CA"))
                        str[17] = ((JSONObject)nutrients.get("CA")).get("label").toString()+" : " + ((JSONObject)nutrients.get("CA")).get("quantity").toString()+((JSONObject)nutrients.get("CA")).get("unit").toString();
                    else
                        str[17]="Calcium : NA";

                    if(nutrients.has("MG"))
                        str[18] = ((JSONObject)nutrients.get("MG")).get("label").toString()+" : " + ((JSONObject)nutrients.get("MG")).get("quantity").toString()+((JSONObject)nutrients.get("MG")).get("unit").toString();
                    else
                        str[18]="Magnesium : NA";

                    if(nutrients.has("FE"))
                        str[19] = ((JSONObject)nutrients.get("FE")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FE")).get("quantity").toString()+((JSONObject)nutrients.get("FE")).get("unit").toString();
                    else
                        str[19]="Iron : NA";

                    if(nutrients.has("ZN"))
                        str[20] = ((JSONObject)nutrients.get("ZN")).get("label").toString()+" : " + ((JSONObject)nutrients.get("ZN")).get("quantity").toString()+((JSONObject)nutrients.get("ZN")).get("unit").toString();
                    else
                        str[20]="Zinc :NA";

                    if(nutrients.has("P"))
                        str[21] = ((JSONObject)nutrients.get("P")).get("label").toString()+" : " + ((JSONObject)nutrients.get("P")).get("quantity").toString()+((JSONObject)nutrients.get("P")).get("unit").toString();
                    else
                        str[21]="Potassium : NA";

                    if(nutrients.has("VITA_RAE"))
                        str[22] = ((JSONObject)nutrients.get("VITA_RAE")).get("label").toString()+" : " + ((JSONObject)nutrients.get("VITA_RAE")).get("quantity").toString()+((JSONObject)nutrients.get("VITA_RAE")).get("unit").toString();
                    else
                        str[22]="Vitamin A : NA";

                    if(nutrients.has("VITC"))
                        str[23] = ((JSONObject)nutrients.get("VITC")).get("label").toString()+" : " + ((JSONObject)nutrients.get("VITC")).get("quantity").toString()+((JSONObject)nutrients.get("VITC")).get("unit").toString();
                    else
                        str[23]="Vitamin C : NA";

                    if(nutrients.has("THIA"))
                        str[24] = ((JSONObject)nutrients.get("THIA")).get("label").toString()+" : " + ((JSONObject)nutrients.get("THIA")).get("quantity").toString()+((JSONObject)nutrients.get("THIA")).get("unit").toString();
                    else
                        str[24]="Thiamin (B1) : NA";

                    if(nutrients.has("RIBF"))
                        str[25] = ((JSONObject)nutrients.get("RIBF")).get("label").toString()+" : " + ((JSONObject)nutrients.get("RIBF")).get("quantity").toString()+((JSONObject)nutrients.get("RIBF")).get("unit").toString();
                    else
                        str[25]="Riboflavin (B2) : NA";

                    if(nutrients.has("NIA"))
                        str[26] = ((JSONObject)nutrients.get("NIA")).get("label").toString()+" : " + ((JSONObject)nutrients.get("NIA")).get("quantity").toString()+((JSONObject)nutrients.get("NIA")).get("unit").toString();
                    else
                        str[26]="Niacin (B3) : NA";

                    if(nutrients.has("VITB6A"))
                        str[27] = ((JSONObject)nutrients.get("VITB6A")).get("label").toString()+" : " + ((JSONObject)nutrients.get("VITB6A")).get("quantity").toString()+((JSONObject)nutrients.get("VITB6A")).get("unit").toString();
                    else
                        str[27] ="Vitamin B6 : NA";

                    if(nutrients.has("FOL"))
                        str[28] = ((JSONObject)nutrients.get("FOL")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FOL")).get("quantity").toString()+((JSONObject)nutrients.get("FOL")).get("unit").toString();
                    else
                        str[28]="Folic Acid (B9) : NA";



                    if(nutrients.has("VITB12"))
                        str[29] = ((JSONObject)nutrients.get("VITB12")).get("label").toString()+" : " + ((JSONObject)nutrients.get("VITB12")).get("quantity").toString()+((JSONObject)nutrients.get("VITB12")).get("unit").toString();
                    else
                        str[29]="Vitamin B12 : NA";

                    if(nutrients.has("VITD"))
                        str[30] = ((JSONObject)nutrients.get("VITD")).get("label").toString()+" : " + ((JSONObject)nutrients.get("VITD")).get("quantity").toString()+((JSONObject)nutrients.get("VITD")).get("unit").toString();
                    else
                        str[30]="Vitamin D : NA";

                    if(nutrients.has("TOCPHA"))
                        str[31] = ((JSONObject)nutrients.get("TOCPHA")).get("label").toString()+" : " + ((JSONObject)nutrients.get("TOCPHA")).get("quantity").toString()+((JSONObject)nutrients.get("TOCPHA")).get("unit").toString();
                    else
                        str[31] ="Vitamin E : NA";

                    if(nutrients.has("VITK1"))
                        str[32] = ((JSONObject)nutrients.get("VITK1")).get("label").toString()+" : " + ((JSONObject)nutrients.get("VITK1")).get("quantity").toString()+((JSONObject)nutrients.get("VITK1")).get("unit").toString();
                    else
                        str[32] ="Vitamin K : NA";

                    if(nutrients.has("FIBTG"))
                        str[33] = ((JSONObject)nutrients.get("FIBTG")).get("label").toString()+" : " + ((JSONObject)nutrients.get("FIBTG")).get("quantity").toString()+((JSONObject)nutrients.get("FIBTG")).get("unit").toString();
                    else
                        str[33] ="Fiber : NA";


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
