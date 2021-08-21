package com.example.tryapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SportAdapter adapter;
    ArrayList<SportModel> dataSport;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvSport);
        recyclerView.setHasFixedSize(true);
        AndroidNetworking.initialize(getApplicationContext());
        getData();
    }

    private void getData(){
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/all_sports.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: "+ response.toString());
                        {
                            try {
                                dataSport = new ArrayList<>();
                                JSONArray jsonArray = response.getJSONArray("sports");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    dataSport.add(new SportModel(jsonObject.getString("strSport"),
                                            jsonObject.getString("strSportDescription"),
                                            jsonObject.getString("strSportThumb")));
                                }
                                gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                                recyclerView.setLayoutManager(gridLayoutManager);
                                adapter = new SportAdapter(dataSport, new SportAdapter.Callback() {
                                    @Override
                                    public void onClick(int position) {
                                        Toast.makeText(MainActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
                                        Intent move = new Intent(getApplicationContext(), DetailSport.class);
                                        SportModel show = dataSport.get(position);
                                        move.putExtra("name", show.getTvName());
                                        move.putExtra("description", show.getTvDescription());
                                        move.putExtra("image", show.getIvImage());
                                        startActivity(move);
                                    }
                                });
                                recyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "onError: "+ error);
                        // handle error
                    }
                });
    }
}