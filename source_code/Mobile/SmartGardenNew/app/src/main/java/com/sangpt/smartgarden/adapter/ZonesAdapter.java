package com.sangpt.smartgarden.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.model.model.Zone;

import java.util.List;

public class ZonesAdapter extends ArrayAdapter<Zone> {
    private Context mContext;
    private List<Zone> zones;

    public ZonesAdapter(Context context, int resource, List<Zone> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.zones = objects;
    }

    @Override
    public int getCount() {
         return zones.size();
    }

    @Override
    public Zone getItem(int position) {
        return zones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return zones.get(position).getZoneId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_zone,parent,false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txt_zone_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Zone zone = getItem(position);
        viewHolder.txtName.setText(zone.getZoneName());
        return convertView;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView txtName;
    }
}
