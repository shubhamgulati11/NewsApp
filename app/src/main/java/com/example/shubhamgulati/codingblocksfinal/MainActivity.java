package com.example.shubhamgulati.codingblocksfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv,tvUsername,tvEmail;
    RecyclerView rv;
    ImageView imageView;
    Adapter adapter;
    LinearLayoutManager lm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view =  navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        tv=findViewById(R.id.tv);
        rv=findViewById(R.id.rv);
        NetworkCall("https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=a7e6f3cead7440e088d924a59ff5b388");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Intent intent = new Intent(MainActivity.this,About.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.techCrunch) {
            tv.setText("Tech Crunch");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a7e6f3cead7440e088d924a59ff5b388");
            // Handle the camera action
        } else if(id == R.id.business){
            tv.setText("Business");
            NetworkCall("https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=a7e6f3cead7440e088d924a59ff5b388");
        }else if (id == R.id.espn) {
            tv.setText("ESPN");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=espn&apiKey=a7e6f3cead7440e088d924a59ff5b388");

        } else if (id == R.id.ign) {
            tv.setText("IGN");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=ign&apiKey=a7e6f3cead7440e088d924a59ff5b388");

        } else if (id == R.id.ew) {
            tv.setText("Entertainment Weekly");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=entertainment-weekly&apiKey=a7e6f3cead7440e088d924a59ff5b388");

        } else if(id == R.id.buzzfeed){

            tv.setText("Buzzfeed");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=buzzfeed&apiKey=a7e6f3cead7440e088d924a59ff5b388");

        }else if(id == R.id.espn_cric){

            tv.setText("ESPN Cricket");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=espn-cric-info&apiKey=a7e6f3cead7440e088d924a59ff5b388");

        }else if(id == R.id.ft){

            tv.setText("Financial Times");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=financial-times&apiKey=a7e6f3cead7440e088d924a59ff5b388");

        }
        else if(id == R.id.googlenews){

            tv.setText("Google News");
            NetworkCall("https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=a7e6f3cead7440e088d924a59ff5b388");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void NetworkCall(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.e("TAG",result);
                Gson gson = new Gson();
                final APIResponse apiResponse = gson.fromJson(result,APIResponse.class);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<articles> articles = apiResponse.getArticles();
                        Log.e("TAG",""+apiResponse.getTotalResults());
                        adapter = new Adapter(getBaseContext(),articles);
                        lm = new LinearLayoutManager(getBaseContext());
                        rv.setLayoutManager(lm);
                        rv.setAdapter(adapter);
                        Log.e("Adapter","CALLME");
                    }
                });

            }
        });

    }




}


