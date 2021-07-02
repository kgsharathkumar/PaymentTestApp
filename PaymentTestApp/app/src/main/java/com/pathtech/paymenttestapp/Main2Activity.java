package com.pathtech.paymenttestapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pathtech.paymenttestapp.R;
import com.sharath.upi.payment.UpiPayment;
import com.sharath.upi.payment.model.PaymentDetail;
import com.sharath.upi.payment.model.TransactionDetails;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        findViewById(R.id.id_pay_using_upi_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUpiPayment();

            }
        });
    }


    private void startUpiPayment() {

        ArrayList<String> existingApps = UpiPayment.getExistingUpiApps(this);

        PaymentDetail payment = new PaymentDetail("9538555123@ybl","Sharathkumar K G",
                "","", "description","1.00");

        new UpiPayment(this)
                .setPaymentDetail(payment)
                .setUpiApps(UpiPayment.getUPI_APPS())
                .setCallBackListener(new UpiPayment.OnUpiPaymentListener() {
                    @Override
                    public void onSubmitted(@NotNull TransactionDetails data) {
                        Toast.makeText(Main2Activity.this, "transaction pending: " + data, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NotNull String message) {
                        Toast.makeText(Main2Activity.this, "transaction failed: " + message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(@NotNull TransactionDetails data) {
                        Toast.makeText(Main2Activity.this, "transaction success: " + data.toString(), Toast.LENGTH_LONG).show();
                    }
                }).pay();
    }
}
