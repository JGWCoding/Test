package com.example.nightlost.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightlost.home.R;
import com.example.nightlost.home.bean.InquiryExpandListViewGroupData;
import com.example.nightlost.home.bean.InquiryExpandListViewItemData;
import com.example.nightlost.home.utils.LogUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InquiryAdapter extends BaseExpandableListAdapter {

    Context mContext;
    List<InquiryExpandListViewGroupData> mGroupArray = new ArrayList<InquiryExpandListViewGroupData>();
    List<List<InquiryExpandListViewItemData>> mItemArray = new ArrayList<List<InquiryExpandListViewItemData>>();

    public InquiryAdapter(Context context, List<InquiryExpandListViewGroupData> groupArray, List<List<InquiryExpandListViewItemData>> itemArray) {
        mContext = context;
        mGroupArray = groupArray;
        mItemArray = itemArray;
    }

    @Override
    public int getGroupCount() {
        return mGroupArray.size() + 1;
    }

    public void setmGroupArray(List<InquiryExpandListViewGroupData> mGroupArray) {
        this.mGroupArray = mGroupArray;
        notifyDataSetChanged();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return 0;
        }
        return mItemArray.get(groupPosition - 1).size();
    }

    public void setmItemArray(List<List<InquiryExpandListViewItemData>> mItemArray) {
        this.mItemArray = mItemArray;
        notifyDataSetChanged();
    }

    @Override
    public InquiryExpandListViewGroupData getGroup(int groupPosition) {
        if (groupPosition == 0){
            return null;
        }
        return mGroupArray.get(groupPosition - 1);
    }

    @Override
    public InquiryExpandListViewItemData getChild(int groupPosition, int childPosition) {
        if (groupPosition == 0) {
            return null;
        }else{
            LogUtils.d("getChild", groupPosition+"-"+childPosition);
            return mItemArray.get(groupPosition - 1).get(childPosition);
        }

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(groupPosition == 0){
            View view = View.inflate(mContext, R.layout.activity_fastinquiry, null);
            return view;
        }else{
            InquiryExpandListViewGroupData data = getGroup(groupPosition);
            View view = convertView;
            GroupHolder holder = null;
            if (view == null) {
                holder = new GroupHolder();
                view = View.inflate(mContext, R.layout.inquiry_expandablelistview_group, null);
                holder.groupIcon = (ImageView)view.findViewById(R.id.inquiry_expandablelistview_group_icon);
                holder.groupName = (TextView)view.findViewById(R.id.inquiry_expandablelistview_group_name);
                holder.groupDec = (TextView)view.findViewById(R.id.inquiry_expandablelistview_group_dec);
                holder.arrow = (ImageView)view.findViewById(R.id.inquiry_expandablelistview_group_arrow);
                view.setTag(holder);
            }else{
                holder = (GroupHolder)view.getTag();
            }
            if (isExpanded){
                holder.arrow.setImageResource(R.mipmap.down);
            }else{
                holder.arrow.setImageResource(R.mipmap.right);
            }
            holder.groupName.setText(data.getK_name());
            holder.groupDec.setText(data.getDec());
            Picasso.with(mContext).load(data.getUrl()).into(holder.groupIcon);
            return view;
        }

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(groupPosition == 0){
            return null;
        }else{
            InquiryExpandListViewItemData data = getChild(groupPosition, childPosition);

            View view = convertView;
            ItemHolder holder = null;
            if (view == null) {
                holder = new ItemHolder();
                view = View.inflate(mContext, R.layout.inquiry_expandablelistview_item, null);
                holder.itemIcon = (ImageView)view.findViewById(R.id.inquiry_expandablelistview_item_icon);
                holder.itemName = (TextView)view.findViewById(R.id.inquiry_expandablelistview_item_name);
                holder.itemHospital = (TextView)view.findViewById(R.id.inquiry_expandablelistview_item_hospital);
                holder.itemSkill = (TextView)view.findViewById(R.id.inquiry_expandablelistview_item_skill);
                view.setTag(holder);
            }else{
                holder = (ItemHolder)view.getTag();
            }

            Picasso.with(mContext).load(data.getDoctor_head()).into(holder.itemIcon);
            holder.itemName.setText(data.getRealname());
            holder.itemHospital.setText(data.getDoctor_hospital());
            holder.itemSkill.setText(data.getDoctor_skill());
            return view;
        }

    }

    class GroupHolder {
        ImageView groupIcon;
        TextView groupName;
        TextView groupDec;
        ImageView arrow;
    }

    class ItemHolder {
        ImageView itemIcon;
        TextView itemName;
        TextView itemHospital;
        TextView itemSkill;
    }

}
