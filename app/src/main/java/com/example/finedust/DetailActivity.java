package com.example.finedust;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvStation;
    WebView wvPm10, wvPm25;
    StationDAO dao;
    Button btnPm10, btnPm25;

    String pm10url, pm25url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvStation = findViewById(R.id.tvDetailStation);
        wvPm10 = findViewById(R.id.wvPm10);
        wvPm25 = findViewById(R.id.wvPm25);
        btnPm10 = findViewById(R.id.btnDetailPm10);
        btnPm25 = findViewById(R.id.btnDetailPm25);

        Intent getIntent = getIntent();
        String station = getIntent.getStringExtra("station");

        dao = StationDAO.open(this);
        Station s = dao.getStation(station);
        int sCode = s.getsCode();
        String sido = s.getsSido();

        tvStation.setText(sido + "시 " + station);


        pm10url = "https://www.airkorea.or.kr/web/vicinityStation?item_code=10007&station_code=" + sCode;
        pm25url = "https://www.airkorea.or.kr/web/vicinityStation?item_code=10008&station_code=" + sCode;


        wvPm10.setWebViewClient(new webView());
        wvPm25.setWebViewClient(new webView());

        WebSettings webSetPm10 = wvPm10.getSettings();
        webSetPm10.setBuiltInZoomControls(true);
        WebSettings webSetPm25 = wvPm25.getSettings();
        webSetPm25.setBuiltInZoomControls(true);

        wvPm10.loadUrl(pm10url);
        wvPm25.loadUrl(pm25url);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("자세히 보기");



        btnPm10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pm10url));
                startActivity(intent);
            }
        });

        btnPm25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pm25url));
                startActivity(intent);
            }
        });

    }

    class webView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }


}
