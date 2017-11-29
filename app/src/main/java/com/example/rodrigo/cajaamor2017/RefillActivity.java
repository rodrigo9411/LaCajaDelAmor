package com.example.rodrigo.cajaamor2017;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rodrigo.cajaamor2017.Helpers.DatabaseHelper;
import com.example.rodrigo.cajaamor2017.databinding.ActivityRefillBinding;

/**
 * Created by Rodrigo on 11/26/2017.
 */

public class  RefillActivity extends AppCompatActivity {

    private String sCode;
    private String sBoxNo;
    DatabaseHelper myDbHelper = new DatabaseHelper(this);

    private ActivityRefillBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_refill);

        sCode = getIntent().getExtras().getString("code");
        //binding.boxNameTextView.setText(sCode);
        try{
            readFromDB();
        }catch (Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage("Código Incorrecto");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    finish();
                }
            });
            alertDialog.show();
        }

        binding.BTNEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRegister();
            }
        });

    }

    public void readFromDB() {
        binding.tvBoxNo.setText(myDbHelper.getCajaById(sCode).getsNumero());
        binding.tvResponsible.setText(myDbHelper.getCajaById(sCode).getsRespon());
        binding.tvResPhone.setText(myDbHelper.getCajaById(sCode).getsTele());
        binding.tvStatus.setText(myDbHelper.getCajaById(sCode).getsStatus());
        binding.tvDeliveredTo.setText(myDbHelper.getCajaById(sCode).getsEntregada());

    }
    public void updateRegister()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("Caja entregada exitosamente");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDbHelper.updateCaja(sCode);
                dialogInterface.dismiss();
                finish();
            }
        });
        alertDialog.show();

    }
}
