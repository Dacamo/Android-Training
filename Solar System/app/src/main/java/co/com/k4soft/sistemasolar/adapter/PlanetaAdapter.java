package co.com.k4soft.sistemasolar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.sistemasolar.R;
import co.com.k4soft.sistemasolar.entity.Planeta;

public class PlanetaAdapter  extends BaseAdapter implements Filterable {

    private final LayoutInflater inflater;
    private  List<Planeta> listaPlanetasOut;
    private  List<Planeta> listaPlanetasIn;

    public PlanetaAdapter(Context context, List<Planeta> listaPlanetas){
        listaPlanetasOut = listaPlanetas;
        listaPlanetasIn = listaPlanetas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listaPlanetasOut.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaPlanetasOut = (List<Planeta>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Planeta> FilteredArrList = new ArrayList<>();
                if (listaPlanetasIn == null) {
                    listaPlanetasIn = new ArrayList<>(listaPlanetasOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = listaPlanetasIn.size();
                    results.values = listaPlanetasIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listaPlanetasIn.size(); i++) {
                        String data = listaPlanetasIn.get(i).getNombre();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(listaPlanetasIn.get(i));
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
    public Planeta getItem(int position) {
        return listaPlanetasOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.planeta_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.imagen.setImageResource(listaPlanetasOut.get(position).getImagen());
        holder.txtNombre.setText(listaPlanetasOut.get(position).getNombre());
        holder.txtDescripcion.setText(listaPlanetasOut.get(position).getDescripcion());
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.imagen)
        ImageView imagen;
        @BindView(R.id.txtNombre)
        TextView txtNombre;
        @BindView(R.id.txtDescripcion)
        TextView txtDescripcion;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
