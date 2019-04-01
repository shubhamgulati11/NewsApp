package com.example.shubhamgulati.codingblocksfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    TextView tvT,tvD;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final articles a  = getIntent().getParcelableExtra("key");
        tvD = findViewById(R.id.tvD);
        tvT = findViewById(R.id.tvT);
        iv = findViewById(R.id.iv);
        tvD.setText(a.getDescription()+ "\n \nPublished At: "+a.getPublishedAt());
        tvT.setText(a.getTitle());
        Picasso.get().load(a.getUrlToImage()).into(iv);
        tvT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(a.getUrl()));
                startActivity(intent);
            }
        });
    }
}
