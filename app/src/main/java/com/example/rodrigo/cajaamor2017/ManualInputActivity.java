package com.example.rodrigo.cajaamor2017;

import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.rodrigo.cajaamor2017.Helpers.DatabaseHelper;
import com.example.rodrigo.cajaamor2017.databinding.ActivityManualUpdateBinding;

/**
 * Created by Rodrigo on 11/26/2017.
 */

public class ManualInputActivity  extends AppCompatActivity {

    private ActivityManualUpdateBinding binding;

    DatabaseHelper myDbHelper = new DatabaseHelper(this);

    private String sCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_manual_update);

        binding.btnManualSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) ManualInputActivity.this.getSystemService( ManualInputActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.tvBoxNo.getWindowToken(), 0);
                try{
                    sCode = binding.tvBoxNo.getText().toString().trim();
                    readFromDB(sCode);
                }catch (Exception e){
                    AlertDialog alertDialog = new AlertDialog.Builder(ManualInputActivity.this).create();
                    alertDialog.setMessage("Numero de caja incorrecto");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        });

        binding.BTNEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRegister(sCode);
            }
        });


    }

    public void readFromDB(String code) {
        binding.tvResponsible.setText(myDbHelper.getCajaByNumber(code).getsRespon());
        binding.tvResPhone.setText(myDbHelper.getCajaByNumber(code).getsTele());
        binding.tvStatus.setText(myDbHelper.getCajaByNumber(code).getsStatus());
        binding.tvDeliveredTo.setText(myDbHelper.getCajaByNumber(code).getsEntregada());
        binding.BTNEntrega.setVisibility(View.VISIBLE);

    }
    public void updateRegister(final String code)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(ManualInputActivity.this).create();
        alertDialog.setMessage("Caja entregada exitosamente");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDbHelper.manualUpdateCaja(code);
                dialogInterface.dismiss();
                binding.tvResponsible.setText(myDbHelper.getCajaByNumber(code).getsRespon());
                binding.tvResPhone.setText(myDbHelper.getCajaByNumber(code).getsTele());
                binding.tvStatus.setText(myDbHelper.getCajaByNumber(code).getsStatus());
                binding.tvDeliveredTo.setText(myDbHelper.getCajaByNumber(code).getsEntregada());
                binding.BTNEntrega.setVisibility(View.GONE);

            }
        });
        alertDialog.show();

    }
}
