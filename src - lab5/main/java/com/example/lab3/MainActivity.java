package com.example.lab3;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Magazin");

        final ListView productsList = (ListView) findViewById(R.id.productsList);
        final TextView descriere = (TextView) findViewById(R.id.descriere);

        String[] Titles = new String[]{
                "Asus Rog",
                "Macbook air",
                "Lenovo Legion",
                "Dell XPS"
        };

        ArrayList<String> products = new ArrayList<>(Arrays.asList(Titles));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, products);

        productsList.setAdapter(arrayAdapter);
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTitle = (String) productsList.getItemAtPosition(position);
                String numelaptop = "";
                switch (selectedTitle) {
                    case "Asus Rog":
                        numelaptop = "Asus Rog";
                        break;
                    case "Macbook air":
                        numelaptop = "Macbook air";
                        break;
                    case "Lenovo Legion":
                        numelaptop = "Lenovo Legion";
                        break;
                    case "Dell XPS":
                        numelaptop = "Dell XPS";
                        break;
                    default:
                        break;
                }
                descriere.setText("I would buy " + numelaptop);
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart invoked");
    }


    @Override
    protected void onSaveInstanceState(Bundle onSaveState) {

        TextView descriere = (TextView) findViewById(R.id.descriere);
        description = descriere.getText().toString();
        onSaveState.putString("descriere", description);
        super.onSaveInstanceState(onSaveState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle onRestoreState) {
        super.onRestoreInstanceState(onRestoreState);
        TextView descriere = (TextView) findViewById(R.id.descriere);
        description = onRestoreState.getString("descriere");
        descriere.setText(description);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        AlertDialog popup = new AlertDialog.Builder(this).create();

        if (menuItem.getItemId() == R.id.about) {
            popup.setTitle("About");
            popup.setMessage("Cea mai smechera companie");

            popup.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
            });

            popup.show();

        }
        else if (menuItem.getItemId() == R.id.hacking) {
            popup.setTitle("Hacking");
            popup.setMessage("Enter supersecret password to gain access to our hacking tools");

            EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            popup.setView(input);

            popup.setButton(AlertDialog.BUTTON_POSITIVE, "Hack",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
            });

            popup.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            popup.show();

        }
        else if (menuItem.getItemId() == R.id.suggestions) {
            popup.setTitle("Suggestions");
            popup.setMessage("Send any suggestions or complaints to Dave Null");

            popup.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
            });

            popup.show();
        }

        else if (menuItem.getItemId() == R.id.settings) {

            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);

        }


        return true;
    }









}


























