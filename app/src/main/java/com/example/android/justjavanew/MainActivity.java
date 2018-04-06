package com.example.android.justjavanew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.justjavanew.R;

import java.text.NumberFormat;

/**
 *create main activity
 */
public class MainActivity extends AppCompatActivity {


    int qty=1;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

/**
 * increment
 */

   public void increment(View view){
       if (qty<20) {

           qty = qty + 1;

           TextView quantity_text_view = (TextView) findViewById(R.id.quantity_text_view);
           quantity_text_view.setText("" + qty);
       }

       if (qty==20){

           Toast.makeText(this, "Max Order is 20\n need more place a new order", Toast.LENGTH_LONG).show();
           return;
       }
   }

    /**
     * decrement
     */

    public void decrement(View view){

        if (qty>1) {

            qty = qty - 1;
            TextView quantity_text_view = (TextView) findViewById(R.id.quantity_text_view);
            quantity_text_view.setText("" + qty);
        }

        if (qty==1) {

            Toast.makeText(this, "minimum order quantity is one", Toast.LENGTH_LONG).show();
            return;
        }


    }

    /**
     * submitOrder
     */

    public int orderValue(){

        CheckBox checkBox1=(CheckBox) findViewById(R.id.checkBox1);
        CheckBox checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        int price=5;

      if (checkBox1.isChecked()) {
            price=price+5;
                    }


      if (checkBox2.isChecked()) {
            price=price+10;
                    }


            return qty*price;

    }


    /*customer name*/
    public String customerName() {
    EditText name = (EditText) findViewById(R.id.name);
    String customerName=name.getText().toString();
    return (""+customerName);
}

/**
 * Toppings
 */


public String priceMessage(){

    /*finding the checkbox and textview*/


   CheckBox checkBox1=(CheckBox) findViewById(R.id.checkBox1);
    CheckBox checkBox2=(CheckBox) findViewById(R.id.checkBox2);

/* calling the method orderValue */

    int myOrderValue=orderValue();
    String customer=customerName();

/*defining conditions*/

    if (checkBox1.isChecked()) {

        String priceMessage= ("Name: "+customer+" \nOrder Quantity: " + qty + "\nWhipped Cream Topping Added"+"\nOrder Value: "+myOrderValue + "\nThank You for Dining with Us.");
        return priceMessage;
    }

        /*chocolate alone checked*/
    if (checkBox2.isChecked()) {

        String priceMessage=("Name: " + customer + " \nOrder Quantity: " + qty + "\nChocolate Topping Added"+"\nOrder Value: "+myOrderValue + "\nThank You for Dining with Us.");
        return priceMessage;
    }


    else {
        String priceMessage=("Name: " + customer + " \nOrder Quantity: " + qty + "\nAdd Toppings to Spice up"+"\nOrder Value: "+myOrderValue + "\nThank You for Dining with Us.");
    return priceMessage;

    }

}

    public void submitOrder(View view){
    String[] addresses;
    String subject;
    String mailMessage=priceMessage();

       Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "gnanaprakash.r@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "order summary");
        intent.putExtra(Intent.EXTRA_TEXT, mailMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    }
