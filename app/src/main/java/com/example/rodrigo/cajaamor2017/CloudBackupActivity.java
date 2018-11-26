package com.example.rodrigo.cajaamor2017;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rodrigo.cajaamor2017.Adapters.CajaCursorAdapter;
import com.example.rodrigo.cajaamor2017.Helpers.DatabaseHelper;
import com.example.rodrigo.cajaamor2017.Objects.Caja;
import com.example.rodrigo.cajaamor2017.Objects.CloudCaja;
import com.example.rodrigo.cajaamor2017.databinding.ActivityCloudBackupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class CloudBackupActivity extends AppCompatActivity {

    FirebaseFirestore db;
    ActivityCloudBackupBinding binding;
    DatabaseHelper myDbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cloud_backup);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        binding.btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnBackup.setEnabled(false);
                cloudBackup();
            }
        });

    }

    public void cloudBackup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Caja> mProductList = myDbHelper.getListDevueltas();
                WriteBatch batch = db.batch();

                //Init adapter
                for(int i=0;i<mProductList.size();i++){
                    DocumentReference ref = db.collection("cajas").document(mProductList.get(i).getsId());
                    batch.set(ref,new CloudCaja(mProductList.get(i).getsNumero(),mProductList.get(i).getsStatus(),mProductList.get(i).getsEntregada(),mProductList.get(i).getsTele(),mProductList.get(i).getsRespon()));
                }

                batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        binding.progressBar.setVisibility(View.GONE);
                        binding.btnBackup.setEnabled(true);
                    }
                });

            }
        }).start();

    }
}
