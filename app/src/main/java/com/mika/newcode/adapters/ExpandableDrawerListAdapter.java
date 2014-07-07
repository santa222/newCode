package com.mika.newcode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mika.newcode.R;
import com.mika.newcode.models.Meeting;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hugo on 3/25/14.
 */
public class ExpandableDrawerListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Meeting> parentsList;
    private HashMap<Meeting, List<Meeting>> childsList;

    public ExpandableDrawerListAdapter(Context context, List<Meeting> parentsList, HashMap<Meeting, List<Meeting>> childsList) {
        this.context = context;
        this.parentsList = parentsList;
        this.childsList = childsList;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_list, null);
        /*convertView.setbackground*/

        Meeting parentItem = parentsList.get(groupPosition);

        if(parentItem != null) {
            if (convertView != null) {
                ((TextView) convertView.findViewById(R.id.navigation_drawer_title)).setText(parentItem.getName());
             //   ((ImageView)convertView.findViewById(R.id.navigation_drawer_icon)).setImageResource(parentItem.getIcon());
            }
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_list_child, null);

        Meeting child = (Meeting) getChild(groupPosition, childPosition);

        if(child != null) {
            if (convertView != null) {
                ((TextView) convertView.findViewById(R.id.navigation_drawer_child_title)).setText(child.getName());
               /* RoundedImageView icon = (RoundedImageView) convertView.findViewById(R.id.navigation_drawer_child_icon);
                if (child.getTags() != null && child.getTags().size() > 0) {
                    icon.setBackgroundColor(ColorHelper.getBackgroundColor(icon, child.getTags().get(0).getId()));
                } else {
                    icon.setBackgroundColor(ColorHelper.getBackgroundColor(icon, 0));
                }
                convertView.setTag(child);*/
            }
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childsList.get(this.parentsList.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parentsList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parentsList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
            return this.childsList.get(this.parentsList.get(groupPosition))
                .get(childPosition);
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
