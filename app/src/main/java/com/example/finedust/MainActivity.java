package com.example.finedust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    Spinner dropSido, dropStation;
    TextView tvDataTime, tvPm10, tvPm25, tvStation, tvStatus;
    Button btnSearch, btnDetailPm10, btnShare, btnDetailPm25;
    ImageView img;
    ArrayAdapter<CharSequence> sidoAdapter, stationAdapter;
    View Dialog;
    String state = "good";

    StationDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.btnSearch);
        btnDetailPm10 = findViewById(R.id.btnDetailPm10);

        img = findViewById(R.id.imageView);
        tvDataTime = findViewById(R.id.tvDataTime);
        tvPm25 = findViewById(R.id.tvPm25);
        tvPm10 = findViewById(R.id.tvPm10);
        tvStation = findViewById(R.id.tvStaion);
        tvStatus = findViewById(R.id.tvStatus);
        dropSido  = findViewById(R.id.dropSido);
        dropStation = findViewById(R.id.dropStation);
        btnShare = findViewById(R.id.btnShare);
        btnDetailPm25 = findViewById(R.id.btnShare);
        sidoAdapter = ArrayAdapter.createFromResource(this, R.array.sido, R.layout.support_simple_spinner_dropdown_item);
        sidoAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropSido.setAdapter(sidoAdapter);

        dao = StationDAO.open(this);
//        dbHelper.onCreate(db);

        dropSido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sidoAdapter.getItem(position).equals("서울특별시")) {
                    stationAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.sido_seoul, R.layout.support_simple_spinner_dropdown_item);
                    stationAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dropStation.setAdapter(stationAdapter);
                } else if(sidoAdapter.getItem(position).equals("부산시")) {
                    stationAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.sido_Busan, R.layout.support_simple_spinner_dropdown_item);
                    stationAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    dropStation.setAdapter(stationAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyThread().execute();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String station = tvStation.getText().toString();
                String pm10 = tvPm10.getText().toString();
                String pm25 = tvPm25.getText().toString();
                String mesureTime = tvDataTime.getText().toString();
                String nowState = "";

                switch (state) {
                    case "good" :
                        nowState = "좋음";
                        break;
                    case "fine" :
                        nowState = "보통";
                        break;
                    case "not_good" :
                        nowState = "나쁨";
                        break;
                    case "bad" :
                        nowState = "매우나쁨";
                        break;
                    default :
                        nowState = "";
                        break;
                }


                Station s = dao.getStation(tvStation.getText().toString());
                s.setsFavo(true);
                dao.updateStaion(s);

                if((station.equals("") || station == null || station.equals(null)) && !(nowState.equals(""))) {
                    Toast.makeText(getApplicationContext(), "먼저 미세먼지 조회를 해주세요", Toast.LENGTH_SHORT).show();
                } else {

                    String msg = "위치 : " + station + ", 상태 : " + nowState + ", 미세먼지 : " + pm10 + ", 초미세먼지 : " + pm25 + ", 측정 시간 : " + mesureTime;

                    intent.putExtra(Intent.EXTRA_TEXT, msg);

                    Intent chooser = intent.createChooser(intent, "공유");
                    startActivity(chooser);
                }

            }
        });


        btnDetailPm10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String station = tvStation.getText().toString();

                if((station.equals("") || station == null || station.equals(null)) ) {
                    Toast.makeText(getApplicationContext(), "먼저 미세먼지 조회를 해주세요", Toast.LENGTH_SHORT).show();
                } else {

                    Station s = dao.getStation(station);
                    String sCode = String.valueOf(s.getsCode());

                    String pm10url = "https://www.airkorea.or.kr/web/vicinityStation?item_code=10007&station_code=" + sCode;
                    String pm25url = "https://www.airkorea.or.kr/web/vicinityStation?item_code=10008&station_code=" + sCode;

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pm10url));
                    startActivity(intent);
                }

            }
        });
        btnDetailPm25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String station = tvStation.getText().toString();

                if((station.equals("") || station == null || station.equals(null)) ) {
                    Toast.makeText(getApplicationContext(), "먼저 미세먼지 조회를 해주세요", Toast.LENGTH_SHORT).show();
                } else {

                    Station s = dao.getStation(station);
                    String sCode = String.valueOf(s.getsCode());

                    String pm25url = "https://www.airkorea.or.kr/web/vicinityStation?item_code=10008&station_code=" + sCode;

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pm25url));
                    startActivity(intent);
                }

            }
        });

    }

    public void getsdata() {
        dao = new StationDAO(MainActivity.this);
        Station station = dao.getStation(tvStation.getText().toString());

        Toast.makeText(MainActivity.this, station.getsAddr() + ", favorite : " + station.issFavo(), Toast.LENGTH_SHORT).show();
    }

    class MyThread extends AsyncTask {
        String station;
        @Override
        protected Object doInBackground(Object[] params){
            station = dropStation.getSelectedItem().toString();
            //valStation[dropStaion.getSelectedItemPosition()];
//            AirService2 service2 = new AirService2();
            Air air = new AirService().getAir(station);
//            Log.d("air",air.toString());
            return air;
        }
        @Override
        protected void onPostExecute(Object o){
//            Log.d("air","onPostExecute:"+o.toString());
            Air air = (Air)o;

            Log.i("MAIN_PM10", String.valueOf(air.getPm10()));
            tvDataTime.setText(air.getDataTime());
            tvPm10.setText(String.valueOf(air.getPm10()));
            tvPm25.setText(String.valueOf(air.getPm25()));
            tvStation.setText(station);
            String grade = air.getPm10Grade();
            switch (grade) {
                case "-" :
                    switch(air.getPm25Grede()) {
                        case "good" :
                            img.setImageResource(R.drawable.good);
                            tvStatus.setText("좋음");
                            tvStatus.setTextColor(Color.parseColor("#00B4DB"));
                            state = "good";
                            break;
                        case "fine" :
                            img.setImageResource(R.drawable.fine);
                            tvStatus.setText("보통");
                            tvStatus.setTextColor(Color.parseColor("#2F9D27"));
                            state = "fine";
                            break;
                        case "not_good" :
                            img.setImageResource(R.drawable.not_good);
                            tvStatus.setText("나쁨");
                            tvStatus.setTextColor(Color.parseColor("#FF5E00"));
                            state = "not_good";
                            break;
                        case "bad" :
                            img.setImageResource(R.drawable.bad);
                            tvStatus.setText("매우나쁨");
                            tvStatus.setTextColor(Color.parseColor("#B70000"));
                            state = "bad";
                            break;
                        default:
                    }
                    break;
                case "good" :
                    img.setImageResource(R.drawable.good);
                    tvStatus.setText("좋음");
                    tvStatus.setTextColor(Color.parseColor("#00B4DB"));
                    state = "good";
                    break;
                case "fine" :
                    img.setImageResource(R.drawable.fine);
                    tvStatus.setText("보통");
                    tvStatus.setTextColor(Color.parseColor("#2F9D27"));
                    state = "fine";
                    break;
                case "not_good" :
                    img.setImageResource(R.drawable.not_good);
                    tvStatus.setText("나쁨");
                    tvStatus.setTextColor(Color.parseColor("#FF5E00"));
                    state = "not_good";
                    break;
                case "bad" :
                    img.setImageResource(R.drawable.bad);
                    tvStatus.setText("매우나쁨");
                    tvStatus.setTextColor(Color.parseColor("#B70000"));
                    state = "bad";
                    break;
                default:
            }
            tvStatus.setTextSize(30);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.optionItemDustInfo :
                intent = new Intent(this, dialInfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.optionItemAppInfo :
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
