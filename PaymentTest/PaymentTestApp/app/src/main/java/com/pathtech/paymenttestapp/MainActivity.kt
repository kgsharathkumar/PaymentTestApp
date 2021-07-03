package com.pathtech.paymenttestapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sharath.upi.payment.UpiPayment
import com.sharath.upi.payment.model.PaymentDetail
import com.sharath.upi.payment.model.TransactionDetails
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id_pay_using_upi_app.setOnClickListener {
            startUpiPayment()
        }
    }


    private fun startUpiPayment(){
        val payment = PaymentDetail(
            vpa="9538555123@ybl",
            name = "Sharathkumar K G",
            payeeMerchantCode = "",
            //txnId = "",
            txnRefId = "",
            description = "description",
            amount = "1.00")


        UpiPayment(this)
            .setPaymentDetail(payment)
            .setUpiApps(UpiPayment.UPI_APPS)
            .setCallBackListener(object : UpiPayment.OnUpiPaymentListener{
                override fun onSubmitted(data: TransactionDetails) {
                    info { "transaction pending: $data" }
                    Toast.makeText(this@MainActivity,"transaction pending: $data",Toast.LENGTH_LONG).show()
                }
                override fun onSuccess(data: TransactionDetails) {
                    info { "transaction success: $data" }
                    Toast.makeText(this@MainActivity,"transaction success: $data",Toast.LENGTH_LONG).show()
                }
                override fun onError(message: String) {
                    info { "transaction failed: $message" }
                    Toast.makeText(this@MainActivity,"transaction failed: $message",Toast.LENGTH_LONG).show()
                }
            }).pay()


        val existingApps = UpiPayment.getExistingUpiApps(this)
        info { "existing app: $existingApps" }
    }
}
