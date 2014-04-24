package se.skanskan.mittskanskan;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	List<String> groupList;
    List<String> childList;
    Map<String, List<String>> groupCollection;
    ExpandableListView expListView;

    //Fragment settingspage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_container);

        //openSettingsFragment();

        createGroupList();

        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.settings_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, groupCollection);
        expListView.setAdapter(expListAdapter);

        setGroupIndicatorToRight();



        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });

    }

    /*
    // Open Fragments
    public void openSettingsFragment() {
        if (settingspage == null)
            settingspage = new SettingsPage();

        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, settingspage, "settingspage");
        transaction.commit();
    }
    */

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Ort");
        groupList.add("Sektion");
        groupList.add("Tema");
        groupList.add("Bloggar");
    }

    private void createCollection() {
        // preparing category collection(child)
        String[] ortItems = { "Malmo", "Sjobo", "Eslov" };
        String[] sektionItems = { "Kultur & Noje", "Sport" };
        String[] temaItems = { "Valet", "Melodifestivalen" };
        String[] bloggarItems = { "Eva Emmelin", "Dan Jeppsson", "Miklas Njor" };

        groupCollection = new LinkedHashMap<String, List<String>>();

        for (String group : groupList) {
            if (group.equals("Ort"))
                loadChild(ortItems);
            else if (group.equals("Sektion"))
                loadChild(sektionItems);
            else if (group.equals("Tema"))
                loadChild(temaItems);
            else
                loadChild(bloggarItems);

            groupCollection.put(group, childList);
        }
    }

    private void loadChild(String[] groupItems) {
        childList = new ArrayList<String>();
        for (String model : groupItems)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}


