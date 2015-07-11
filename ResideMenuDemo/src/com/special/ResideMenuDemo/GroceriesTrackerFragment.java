package com.special.ResideMenuDemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class GroceriesTrackerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Get the Grocery Stores Near You", Toast.LENGTH_SHORT).show();
        Uri locn=Uri.parse("https://www.google.com/maps/search/groceries");
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, locn);

        startActivity(intent);
        return inflater.inflate(R.layout.groceriestracker, container, false);
    }

}
