package com.cridacamo.cetus.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.models.Event;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends BaseAdapter implements Filterable {
    private final LayoutInflater inflater;
    private List<Event> listaEventOut;
    private List<Event> listaEventIn;

    public EventAdapter(Context context, List<Event> listaEvents) {
        listaEventOut = listaEvents;
        listaEventIn = listaEvents;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaEventOut.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaEventOut = (List<Event>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Event> FilteredArrList = new ArrayList<>();
                if (listaEventIn == null) {
                    listaEventIn = new ArrayList<>(listaEventOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = listaEventIn.size();
                    results.values = listaEventIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listaEventIn.size(); i++) {
                        String data = listaEventIn.get(i).getName();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(listaEventIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public Event getItem(int position) {
        return listaEventOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }


        holder.name.setText(listaEventOut.get(position).getName());
        holder.locate.setText(listaEventOut.get(position).getLocation());
        holder.date.setText(listaEventOut.get(position).getDate());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.lbName)
        TextView name;
        @BindView(R.id.lbLocate)
        TextView locate;
        @BindView(R.id.lbDate)
        TextView date;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
