package com.pm.primeerp.ui.views.fragments.Estimates;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pm.primeerp.R;
import com.pm.primeerp.data.model.Items;

import java.util.ArrayList;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class ItemsCustomListAdapter extends BaseAdapter {
    Activity context;
    private ArrayList<Items> itemslist;
    private LayoutInflater inflater = null;

    public ItemsCustomListAdapter(Activity context, ArrayList<Items> teaPackerChecklistlist) {
        this.itemslist = teaPackerChecklistlist;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemslist.size();
    }


    @Override
    public Object getItem(int position) {
        return itemslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View contentview, ViewGroup parent) {
        View itemview = contentview;

        itemview = (itemview == null) ? inflater.inflate(R.layout.custom_list, null) : itemview;
        TextView textviewdocument_no = (TextView) itemview.findViewById(R.id.textviewdocument_no_no);
        TextView textviewdocument_date = (TextView) itemview.findViewById(R.id.textviewdocument_date_date);
        TextView textviewlicence_number = (TextView) itemview.findViewById(R.id.textviewlicence_number);
        TextView textviewapplicant_name = (TextView) itemview.findViewById(R.id.textviewname_of_applicant);

        Items selecteditem = itemslist.get(position);
        textviewdocument_no.setText("Quantity: " + selecteditem.getQuantity());
        textviewdocument_date.setText("Amount: " + selecteditem.getQuantity());
        textviewlicence_number.setText("Quantity: " + selecteditem.getAmount());
        textviewapplicant_name.setText("Amount: " + selecteditem.getAmount());

        return itemview;

    }
}
