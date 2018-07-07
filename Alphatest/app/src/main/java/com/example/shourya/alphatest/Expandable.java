package com.example.shourya.alphatest;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expandable extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listData;
    private HashMap<String,List<String>> listHashMap;
    ArrayList<Bitmap> dp;

    public Expandable(Context context, List<String> listData, HashMap<String, List<String>> listHashMap,ArrayList<Bitmap> dp) {
        this.context = context;
        this.listData = listData;
        this.listHashMap = listHashMap;
        this.dp = dp;

    }

    @Override
    public int getGroupCount() {
        return listData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listData.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header=(String)getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }
        TextView lbl=convertView.findViewById(R.id.lblListHeader);
        ImageView icon=convertView.findViewById(R.id.Icon);
        icon.setImageBitmap(dp.get(groupPosition));
        lbl.setText(header);


        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);
       }
        TextView txtListChild=convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
       return convertView;

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
