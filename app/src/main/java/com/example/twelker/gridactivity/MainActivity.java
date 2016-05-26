package com.example.twelker.gridactivity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Local variable representing the datamodel that will contain the user provided information
        final ArrayList<String> items = new ArrayList<String> ();
        final List<Integer> images = new ArrayList<Integer>();

        //Local variables that represent the user interface elements ('widgets') as created in the xml file
        GridView gridView = (GridView) findViewById(R.id.gridview);
        final EditText gridInput = (EditText) findViewById(R.id.gridinput);
        final Button button = (Button) findViewById(R.id.button);
        final Spinner iconSpinner = (Spinner) findViewById(R.id.spinner);

        //Create the spinner items
        String[] spinnerItems = {"Nokia", "Galaxy S5", "Galaxy S7", "iPhone 5S", "iPhone 6S",
                "iPhone 7", "Galaxy Tab4", "iPad 2", "iPad 3", "iPad 4",
                "iWatch", "Spectacles", "Other"};

        //Create the spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerItems);

        //Set the adapter for the spinner
        iconSpinner.setAdapter(spinnerAdapter);

        //Create the adapter (custom grid adapter is needed in this case) and connect it to the datamodel ('items')
        //An adapter is the link between datamodel and UI and ensures that datamodel is not involved in UI complexity v.v.
        final CustomGridAdapter gridAdapter = new CustomGridAdapter(MainActivity.this, items, images);

        //Connect the adapter to the UI element 'gridview'
        gridView.setAdapter(gridAdapter);

        //Register the gridview for listening to short clicks
        //When an item is short clicked, display a toast message
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "Grid item: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Register the gridview for listening to long clicks
        //When an item is long clicked, remove it and display a toast message
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                items.remove(pos); //Remove the item that was clicked long
                images.remove(pos); //And remove the corresponding image
                gridAdapter.notifyDataSetChanged(); //Redisplay the modified grid
                Toast.makeText(MainActivity.this, "Grid item: " + pos + " removed.",
                    Toast.LENGTH_SHORT).show();
                return true; //Meaning no other routines will be invoked by Android
            }
        });

        //When button 'add item' clicked, add item to the data model and redisplay modified grid
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the field has text
                if (!TextUtils.isEmpty(gridInput.getText().toString())) {
                    items.add(gridInput.getText().toString()); //Add the new item

                    //Get the resource value for the selected icon from the spinner
                    switch (iconSpinner.getSelectedItemPosition()) {
                        case 0:
                            images.add(R.drawable.oude_nokia);
                            break;
                        case 1:
                            images.add(R.drawable.samsung_galaxy_s5);
                            break;
                        case 2:
                            images.add(R.drawable.samsung_galaxy_s7);
                            break;
                        case 3:
                            images.add(R.drawable.iphone_5s);
                            break;
                        case 4:
                            images.add(R.drawable.iphone_6s);
                            break;
                        case 5:
                            images.add(R.drawable.iphone_7);
                            break;
                        case 6:
                            images.add(R.drawable.samsung_galaxy_tab4);
                            break;
                        case 7:
                            images.add(R.drawable.ipad_2);
                            break;
                        case 8:
                            images.add(R.drawable.ipad_3);
                            break;
                        case 9:
                            images.add(R.drawable.ipad_4);
                            break;
                        case 10:
                            images.add(R.drawable.iwatch);
                            break;
                        case 11:
                            images.add(R.drawable.google_spectacles);
                            break;
                        default:
                            images.add(R.mipmap.ic_launcher);
                    }
                    gridAdapter.notifyDataSetChanged(); //Redisplay the modified grid
                } else {
                    //Show a message to the user
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();Toast.makeText(MainActivity.this, "Please enter some text in the field", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
