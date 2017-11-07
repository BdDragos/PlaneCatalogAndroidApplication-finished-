package com.mobilelab.artyomska.planecatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilelab.artyomska.planecatalog.model.Plane;

import java.util.ArrayList;

/**
 * Created by Artyomska on 11/7/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Plane> implements View.OnClickListener{

    private ArrayList<Plane> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtProducer;
        TextView txtCountry;
        ImageView info;
    }

    public ListViewAdapter(ArrayList<Plane> data, Context context) {
        super(context, R.layout.listview_row, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Plane dataModel=(Plane)object;

        /*
        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
        */
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Plane dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.planeName);
            viewHolder.txtProducer = (TextView) convertView.findViewById(R.id.planeProducer);
            viewHolder.txtCountry = (TextView) convertView.findViewById(R.id.planeCountry);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(dataModel.getPlaneName());
        viewHolder.txtProducer.setText(dataModel.getPlaneProducer());
        viewHolder.txtCountry.setText(dataModel.getPlaneCountry());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}