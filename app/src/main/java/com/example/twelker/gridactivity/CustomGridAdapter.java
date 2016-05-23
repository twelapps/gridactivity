package com.example.twelker.gridactivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twelh on 5/22/16.
 */
public class CustomGridAdapter extends BaseAdapter {

    private MainActivity context;
    private ArrayList<String> items;
    private List<Integer> images;
    private static LayoutInflater inflater=null;

    public CustomGridAdapter(MainActivity context, ArrayList<String> items, List<Integer> images) {

        this.context = context;
        this.items = items;
        this.images = images;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {return items.size();}

    public Object getItem(int position) {return items.get(position);}

    public long getItemId(int position) {return position;}

    public class Holder //Data class containing one 'window' of the grid
    {
        TextView tv;
        ImageView img;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_item, null);

        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(items.get(position));
        holder.img.setImageResource(images.get(position));

        return rowView;
    }
}
