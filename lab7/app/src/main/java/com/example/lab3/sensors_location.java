package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class sensors_location extends AppCompatActivity implements SensorEventListener {



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int type = sensorEvent.sensor.getType();

        TextView sensorView = (TextView) findViewById(R.id.sensors);
        if (type == Sensor.TYPE_ACCELEROMETER)
            sensorView.setText("accelerometer: " + sensorEvent.values[0]);


    }

    @Override
    public void onAccuracyChanged(Sensor sens, int y){
        int x = 1;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_location);


        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        List<Sensor> sensorList  = sensorManager.getSensorList(Sensor.TYPE_ALL);
//
//
//        String sensors = "";
//
//        for ( Sensor i : sensorList){
//            sensors += i.getName();
//            sensors += "\n";
//        }
//
//        TextView sensorView = (TextView) findViewById(R.id.sensors);
//        sensorView.setText(sensors);

        Sensor sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL);


        TextView gpsView = (TextView) findViewById(R.id.gps);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 22);



        try{
            Location location = locationManager.getLastKnownLocation("gps");
            gpsView.setText("Lat: " + location.getLatitude() + "\n Long: " + location.getLongitude());
        }
        catch (SecurityException e){
            gpsView.setText("Location permission error. Daca apare asta dupa ce apasati pe 'allow', dati back si accesati acest activity din nou");
        }


    }



}
