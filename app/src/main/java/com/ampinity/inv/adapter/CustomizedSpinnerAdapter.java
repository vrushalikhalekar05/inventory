package com.ampinity.inv.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ampinity.inv.R;


/**
 * Created by MayurMali on 5/24/2017.
 */

public class CustomizedSpinnerAdapter extends ArrayAdapter<String> {

    private Activity context;
    String[] data = null;

    public CustomizedSpinnerAdapter(Activity context, int resource, String[] data2)
    {
        super(context, resource, data2);
        this.context = context;
        this.data = data2;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if(row == null)
        {
            //inflate your customlayout for the textview
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.spinner_layout, parent, false);
        }
        //put the data in it
        String item = data[position];
        if(item != null)
        {
//            Typeface editTextFace = Typeface.createFromAsset(context.getAssets(),
//                    FONT_EDIT_TEXT);
            TextView text1 = (TextView) row.findViewById(R.id.rowText);
          //  text1.setTypeface(editTextFace);
            text1.setText(item);
        }

        return row;
    }

}
