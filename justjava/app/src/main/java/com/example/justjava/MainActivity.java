package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void increment(View view) {
        quantity++;
        display(quantity);
    }


    public void decrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
            Toast.makeText(this, "you can't buy less than 1 cup of coffee", Toast.LENGTH_LONG).show();
        }
        display(quantity);

    }

    public void submitOrder(View view) {
        CheckBox whippedcreamcheckbox = (CheckBox) findViewById(R.id.checkbox_cream);
        boolean haswhippedcream = whippedcreamcheckbox.isChecked();
        CheckBox choco = (CheckBox) findViewById(R.id.checkbox_choco);
        boolean addchoco = choco.isChecked();
        EditText text = (EditText) findViewById(R.id.plain_text_input);
        String name = text.getText().toString();
        price = calculatePrice(5);
        String summary = createOrderSummary(name, price, haswhippedcream, addchoco);
        displayMessage(summary);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "coffee ordering app");

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            Toast.makeText(MainActivity.this, "No App is installed", Toast.LENGTH_SHORT).show();
        }
    }

    private String createOrderSummary(String name, int price, boolean whippedcream, boolean addchoco) {
        if (whippedcream && addchoco) {
            price += 30;
            return ("Name:" + name + "\nQuantity:" + quantity + "\nTotal:" + price + "\nThankyou!! \nWhippedcream added \nChocolate added");
        } else if (whippedcream) {
            price += 10;
            return ("Name:Atharv Mishra\nQuantity:" + quantity + "\nTotal:" + price + "\nThankyou!! \n whippedcream added");

        } else if (addchoco) {
            price += 20;
            return ("Name:Atharv Mishra\nQuantity:" + quantity + "\nTotal:" + price + "\nThankyou!! \n Chocolate added");

        }
        return ("Name:Atharv Mishra\nQuantity:" + quantity + "\nTotal:" + price + "\nThankyou!!");


    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }

    private int calculatePrice(int priceforcup) {
        int price = quantity * priceforcup;
        return price;

    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


}


