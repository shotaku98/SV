package com.example.shourya.alphatest;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActionBar toolbar;
    SharedPreferences sp;
    ConstraintLayout constant;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listData;
    private HashMap<String,List<String>> listHash;
    ArrayList<Bitmap> Icons;
    ScrollView Profile;
    TextView naem;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    Profile.setVisibility(View.GONE);
                    toolbar.setTitle("Shop");
                    listView.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_delivery:
                    Profile.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                    toolbar.setTitle("delivery");
                    return true;
                case R.id.navigation_dashboard:
                    Profile.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                    toolbar.setTitle("Plans");
                    return true;
                case R.id.navigation_notifications:
                    Profile.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    toolbar.setTitle("Profile");
                    return true;
            }
            return false;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp=getSharedPreferences("Login", Context.MODE_PRIVATE);
        boolean L_stat=sp.getBoolean("LS",false);
        sp.getString("UserName","NoName");
        naem=findViewById(R.id.Namebox);


        if (!L_stat){
        Intent intent=new Intent(MainActivity.this,Login.class);
        startActivity(intent);}

        else {


            setContentView(R.layout.activity_main);
            listView=findViewById(R.id.Expensive);
            Profile=findViewById(R.id.Profile_Page);
            Profile.setVisibility(View.GONE);
            initData();
            listView.setVisibility(View.VISIBLE);
            listAdapter=new Expandable(MainActivity.this,listData,listHash,Icons);
            listView.setAdapter(listAdapter);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            toolbar = getSupportActionBar();
            toolbar.setTitle("Shop");

        }


    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void initData(){
        listData=new ArrayList<>();
        listHash=new HashMap<>();

        listData.add("Milk");
        listData.add("Eggs");
        listData.add("Bread");
        Icons=new ArrayList<>();
        listData.add("Meats");
        Icons.add(BitmapFactory.decodeResource(getResources(),R.drawable.milk));
        Icons.add(BitmapFactory.decodeResource(getResources(),R.drawable.eggs));
        Icons.add(BitmapFactory.decodeResource(getResources(),R.drawable.bread));
        Icons.add(BitmapFactory.decodeResource(getResources(),R.drawable.meat));


        List<String> Milk=new ArrayList<>();
        Milk.add("Whole Milk");
        Milk.add("Tonned Milk");
        Milk.add("Double Tonned Milk");
        Milk.add("Skimmed Milk");

        List<String> Eggs=new ArrayList<>();
        Eggs.add("White Eggs");
        Eggs.add("Brown Eggs");

        List<String> Breads=new ArrayList<>();
       Breads.add("White Bread");
        Breads.add("Brown Bread");
        Breads.add("Multigrain Bread");
        Breads.add("Oats Bread");

        List<String> Meat=new ArrayList<>();
        Meat.add("Chicken");
        Meat.add("Fish");
        Meat.add("Salami");
        Meat.add("Sausages");

        listHash.put(listData.get(0),Milk);
        listHash.put(listData.get(1),Eggs);
        listHash.put(listData.get(2),Breads);
        listHash.put(listData.get(3),Meat);


    }




}
