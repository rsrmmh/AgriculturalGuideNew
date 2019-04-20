package com.example.agriculturalguide;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.nitri.gauge.Gauge;
import me.itangqi.waveloadingview.WaveLoadingView;

public class SensorStatusActivity extends AppCompatActivity implements SensorEventListener {

    //Water Level
    private WaveLoadingView waveLoadingView; //wave
    private Button btnIncrWater, btnDecrWater; //wave
    private int cur_water;

    //Temperature
    private TextView txtTemperature;
    private SensorManager sensorManager;
    private Thermometer thermometer;
    private float temperature;
    private Timer timer;

    //Gauge_Pressure
    private Button btnIncrPressure, btnDecrPressure;
    private Gauge gauge_Pressure ;
    private int curPressure;

    //Gauge_Humidity
    private CustomGauge gauge_Humidity;
    private TextView txtHumidity;
    private Button btnIncrHumidity, btnDecrHumidity;
    private int cur_Humidity;

    //Gauge_Rain
    private Gauge gauge_Rain;
    private Button btnIncrRain, btnDecrRain;
    private int cur_Rain;

    //Gauge_WaterFlow
    private CustomGauge gauge_WaterFlow;
    private TextView txtWaterFlow;
    private Button btnIncrWaterFlow, btnDecrWaterFlow;
    private int cur_WaterFlow;

    //Gauge_Soil_pH
    private CustomGauge gauge_Soil_pH;
    private TextView txtSoilpH;
    private Button btnIncrSoil_pH, btnDecrSoil_pH;
    private int cur_Soil_pH;

    //Gauge_SoilMoisture
    private CustomGauge gauge_SoilMoisture;
    private TextView txtSoilMoisture;
    private Button btnIncrSoilMoisture, btnDecrSoilMoisture;
    private int cur_SoilMoisture;


    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseApp.initializeApp(this);



        // Get a reference to our temperature

        getTempFromFirebase();
        getHumFromFirebase();





                                      //Water Level
        waveLoadingView =(WaveLoadingView) findViewById(R.id.waveLoadingView);
        waveLoadingView.setProgressValue(0);

        btnIncrWater =(Button) findViewById(R.id.btnIncrWater);
        btnDecrWater =(Button) findViewById(R.id.btnDecrWater);
        cur_water = 50;
        update_Water(cur_water);

        btnIncrWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cur_water <= 90){
                    cur_water += 10;
                    update_Water(cur_water);
                }
            }
        });

        btnDecrWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cur_water >= 10){
                    cur_water -= 10;
                    update_Water(cur_water);
                }

            }
        });

        //Temperature
        txtTemperature =(TextView) findViewById(R.id.txtTemperature);
        thermometer = (Thermometer) findViewById(R.id.thermometer);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        //Gauge-Pressure

        btnDecrPressure =(Button) findViewById(R.id.btnDecrPressure);
        btnIncrPressure =(Button) findViewById(R.id.btnIncrPressure);
        gauge_Pressure =(Gauge) findViewById(R.id.gaugePressure);


        //curPressure = 30;
        getPressFromFirebase();


        btnIncrPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curPressure <= 90){
                    curPressure += 10;
                    gauge_Pressure.moveToValue(curPressure);
                }

            }
        });

        btnDecrPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curPressure >= 10){
                    curPressure -= 10;
                    gauge_Pressure.moveToValue(curPressure);
                }

            }
        });

        //Gauge_Humidity
        gauge_Humidity =(CustomGauge) findViewById(R.id.gaugeHumdity);
        txtHumidity =(TextView) findViewById(R.id.txtHumidity);
        btnIncrHumidity =(Button) findViewById(R.id.btnIncrHumidity);
        btnDecrHumidity =(Button) findViewById(R.id.btnDecrHumidity);
        gauge_Humidity.setEndValue(100);
       // cur_Humidity = 50;
        //gauge_Humidity.setValue(cur_Humidity);
        //txtHumidity.setText(gauge_Humidity.getValue()+"");

        btnIncrHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SensorStatusActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                if(cur_Humidity <= 95){
                    cur_Humidity += 5;
                    gauge_Humidity.setValue(cur_Humidity);
                }
                txtHumidity.setText(gauge_Humidity.getValue()+"%");
            }
        });

        btnDecrHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cur_Humidity >= 5){
                    cur_Humidity -= 5;
                    gauge_Humidity.setValue(cur_Humidity);
                }
                txtHumidity.setText(gauge_Humidity.getValue()+"%");
            }
        });

        //Gauge-Rain

        btnDecrRain =(Button) findViewById(R.id.btnDecrRain);
        btnIncrRain =(Button) findViewById(R.id.btnIncrRain);
        gauge_Rain =(Gauge) findViewById(R.id.gaugeRain);


        cur_Rain = 50;
        gauge_Rain.setValue(cur_Rain);

        btnIncrRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cur_Rain <= 90){
                    cur_Rain += 10;
                    gauge_Rain.moveToValue(cur_Rain);
                }

            }
        });

        btnDecrRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cur_Rain >= 10){
                    cur_Rain -= 10;
                    gauge_Rain.moveToValue(cur_Rain);
                }

            }
        });

        //Gauge_WaterFlow
        gauge_WaterFlow =(CustomGauge) findViewById(R.id.gaugeWaterFlow);
        txtWaterFlow =(TextView) findViewById(R.id.txtWaterFlow);
        btnIncrWaterFlow = (Button)findViewById(R.id.btnIncrWaterFlow);
        btnDecrWaterFlow =(Button) findViewById(R.id.btnDecrWaterFlow);
        gauge_Humidity.setEndValue(100);
        cur_WaterFlow = 50;
        gauge_WaterFlow.setValue(cur_WaterFlow);
        txtWaterFlow.setText(gauge_Humidity.getValue()+"");

        btnIncrWaterFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SensorStatusActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                if(cur_WaterFlow <= 95){
                    cur_WaterFlow += 5;
                    gauge_WaterFlow.setValue(cur_WaterFlow);
                }
                txtWaterFlow.setText(gauge_WaterFlow.getValue()+"%");
            }
        });

        btnDecrWaterFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cur_WaterFlow >= 5){
                    cur_WaterFlow -= 5;
                    gauge_WaterFlow.setValue(cur_WaterFlow);
                }
                txtWaterFlow.setText(gauge_WaterFlow.getValue()+"%");
            }
        });

        //Gauge_Soil_pH
        gauge_Soil_pH =(CustomGauge) findViewById(R.id.gaugeSoil_pH);
        txtSoilpH =(TextView) findViewById(R.id.txtSoilpH);
        btnIncrSoil_pH =(Button) findViewById(R.id.btnIncrSoil_pH);
        btnDecrSoil_pH =(Button) findViewById(R.id.btnDecrSoil_pH);
        gauge_Soil_pH.setEndValue(100);
        cur_Soil_pH = 50;
        gauge_Soil_pH.setValue(cur_Soil_pH);
        txtSoilpH.setText(gauge_Soil_pH.getValue()+"");

        btnIncrSoil_pH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SensorStatusActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                if(cur_Soil_pH <= 95){
                    cur_Soil_pH += 5;
                    gauge_Soil_pH.setValue(cur_Soil_pH);
                }
                txtSoilpH.setText(gauge_Soil_pH.getValue()+"%");
            }
        });

        btnDecrSoil_pH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cur_Soil_pH >= 5){
                    cur_Soil_pH -= 5;
                    gauge_Soil_pH.setValue(cur_Soil_pH);
                }
                txtSoilpH.setText(gauge_Soil_pH.getValue()+"%");
            }
        });


        //Gauge_SoilMoisture
        gauge_SoilMoisture =(CustomGauge) findViewById(R.id.gaugeSoilMoisture);
        txtSoilMoisture =(TextView) findViewById(R.id.txtSoilMoisture);
        btnIncrSoilMoisture =(Button) findViewById(R.id.btnIncrSoilMoisture);
        btnDecrSoilMoisture =(Button) findViewById(R.id.btnDecrSoilMoisture);
        gauge_SoilMoisture.setEndValue(100);
        cur_SoilMoisture = 50;
        gauge_SoilMoisture.setValue(cur_SoilMoisture);
        txtSoilMoisture.setText(gauge_Soil_pH.getValue()+"");

        btnIncrSoilMoisture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SensorStatusActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                if(cur_SoilMoisture <= 95){
                    cur_SoilMoisture += 5;
                    gauge_SoilMoisture.setValue(cur_SoilMoisture);
                }
                txtSoilMoisture.setText(gauge_SoilMoisture.getValue()+"%");
            }
        });

        btnDecrSoilMoisture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cur_SoilMoisture >= 5){
                    cur_SoilMoisture -= 5;
                    gauge_SoilMoisture.setValue(cur_SoilMoisture);
                }
                txtSoilMoisture.setText(gauge_SoilMoisture.getValue()+"%");
            }
        });


    }

    private void getPressFromFirebase() {
        reference= FirebaseDatabase.getInstance().getReference().child("Presasure");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String press = dataSnapshot.getValue().toString();
                curPressure = (int) (Float.parseFloat(press));
                curPressure=curPressure/100;
                gauge_Pressure.setValue(curPressure);
                Toast.makeText(SensorStatusActivity.this,""+curPressure,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getHumFromFirebase() {
        reference= FirebaseDatabase.getInstance().getReference().child("Humidity");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                cur_Humidity = Integer.parseInt(hum);
                gauge_Humidity.setValue(cur_Humidity);
                txtHumidity.setText(gauge_Humidity.getValue()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getTempFromFirebase() {
        reference= FirebaseDatabase.getInstance().getReference().child("Temperature");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue().toString();
                temperature = Float.parseFloat(temp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Temperature
    @Override
    protected void onResume() {
        super.onResume();
        //loadAmbientTemperature();
        simulateAmbientTemperature();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterAll();
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values.length > 0) {
            //temperature = sensorEvent.values[0];
            thermometer.setCurrentTemp(temperature);
            //getSupportActionBar().setTitle(getString(R.string.app_name) + " : " + temperature);
            txtTemperature.setText(temperature+ " \u2109");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    //Temperature
    private void simulateAmbientTemperature() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                //temperature = Utils.randInt(-10, 40);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thermometer.setCurrentTemp(temperature);
                        //getSupportActionBar().setTitle(getString(R.string.app_name) + " : " + temperature);
                        txtTemperature.setText(temperature+"°C");
                    }
                });
            }
        }, 0, 3500);
    }

    private void loadAmbientTemperature() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Toast.makeText(this, "No Ambient Temperature Sensor !", Toast.LENGTH_LONG).show();
        }
    }

    private void unregisterAll() {
        //sensorManager.unregisterListener(this);
        timer.cancel();
    }

    //Wave
    private void update_Water(int cur_water) {

        //Toast.makeText(SensorStatusActivity.this, progress+"", Toast.LENGTH_SHORT).show();
        waveLoadingView.setProgressValue(cur_water);
        if(cur_water < 50)
        {
            waveLoadingView.setBottomTitle(String.format("%d%%", cur_water));
            waveLoadingView.setCenterTitle("");
            waveLoadingView.setTopTitle("");
        }
        else if(cur_water < 80)
        {
            waveLoadingView.setBottomTitle("");
            waveLoadingView.setCenterTitle(String.format("%d%%", cur_water));
            waveLoadingView.setTopTitle("");
        }
        else
        {
            waveLoadingView.setBottomTitle("");
            waveLoadingView.setCenterTitle("");
            waveLoadingView.setTopTitle(String.format("%d%%", cur_water));
        }
    }

    /*private int getWaterLv() {
        Random rand = new Random();
        int p = rand.nextInt(100)+1;
        return p;
    }*/

    //Humidity
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }



}
