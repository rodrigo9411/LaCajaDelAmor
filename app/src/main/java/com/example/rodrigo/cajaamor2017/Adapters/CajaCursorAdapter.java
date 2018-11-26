package com.example.rodrigo.cajaamor2017.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rodrigo.cajaamor2017.Objects.Caja;
import com.example.rodrigo.cajaamor2017.R;

import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Rodrigo on 11/26/2017.
 */

public class CajaCursorAdapter extends RecyclerView.Adapter<CajaCursorAdapter.ViewHolder> {
    private List<Caja> mCajaList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvCode, tvRespons, tvPhone, tvEntregado, tvPhoneTitle, tvEntregadoTitle;
        public ViewHolder(View v) {
            super(v);
            tvCode =  v.findViewById(R.id.TVCode);
            tvRespons =  v.findViewById(R.id.TVRespon);
            tvEntregado =  v.findViewById(R.id.tvEntregado);
            tvPhone =  v.findViewById(R.id.TVPhone);
            tvPhoneTitle = v.findViewById(R.id.tvPhoneTitle);
            tvEntregadoTitle = v.findViewById(R.id.tvEntregadoTitle);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CajaCursorAdapter(List<Caja> myDataset) {
        mCajaList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CajaCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.caja_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvCode.setText(mCajaList.get(position).getsNumero());
        holder.tvEntregado.setText(mCajaList.get(position).getsEntregada());
        holder.tvRespons.setText(mCajaList.get(position).getsRespon());
        holder.tvPhone.setText(mCajaList.get(position).getsTele());

        if(mCajaList.get(position).getsStatus().trim().equals("Devuelta")){
            holder.tvPhoneTitle.setVisibility(GONE);
            holder.tvEntregadoTitle.setVisibility(GONE);
            holder.tvPhone.setVisibility(GONE);
            holder.tvEntregado.setVisibility(GONE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCajaList.size();
    }
}
