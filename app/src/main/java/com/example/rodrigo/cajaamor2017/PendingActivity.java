package com.example.rodrigo.cajaamor2017;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.rodrigo.cajaamor2017.Adapters.CajaCursorAdapter;
import com.example.rodrigo.cajaamor2017.Helpers.DatabaseHelper;
import com.example.rodrigo.cajaamor2017.Objects.Caja;
import com.example.rodrigo.cajaamor2017.databinding.ActivityPendingBinding;

import java.util.List;

/**
 * Created by Rodrigo on 11/26/2017.
 */

public class PendingActivity extends AppCompatActivity {

    private ActivityPendingBinding binding;
    DatabaseHelper myDbHelper = new DatabaseHelper(this);
    private List<Caja> mProductList;
    ListView cajas;
    private CajaCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pending);
//        binding.tvRespen.setText(myDbHelper.getCountPendientes());
//        binding.TVresen.setText(myDbHelper.getCountEntregadas());


        binding.BTNEntre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDelivered();
                binding.BTNEntre.setBackgroundColor(ContextCompat.getColor(PendingActivity.this,R.color.colorPrimaryDark));
                binding.BTNPen.setBackgroundColor(ContextCompat.getColor(PendingActivity.this,R.color.colorAccent));

            }
        });

        binding.BTNPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPendending();
                binding.BTNEntre.setBackgroundColor(ContextCompat.getColor(PendingActivity.this,R.color.colorAccent));
                binding.BTNPen.setBackgroundColor(ContextCompat.getColor(PendingActivity.this,R.color.colorPrimaryDark));

            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvPending.setLayoutManager(mLayoutManager);
        binding.rvPending.setItemAnimator(new DefaultItemAnimator());
//
        DividerItemDecoration divider = new DividerItemDecoration(binding.rvPending.getContext(),mLayoutManager.getOrientation());
        binding.rvPending.addItemDecoration(divider);


    }

    public void showPendending() {
        //Get product list in db when db exists
        new Thread(new Runnable() {
            @Override
            public void run() {
                mProductList = myDbHelper.getListPendientes();
                //Init adapter
                adapter = new CajaCursorAdapter(mProductList);
                //Set adapter for listview
                binding.rvPending.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.rvPending.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();




    }

    public void showDelivered() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Get product list in db when db exists
                mProductList = myDbHelper.getListDevueltas();
                //Init adapter
                adapter = new CajaCursorAdapter(mProductList);
                binding.rvPending.post(new Runnable() {
                    @Override
                    public void run() {
                        //Set adapter for listview
                        binding.rvPending.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();




    }
}
