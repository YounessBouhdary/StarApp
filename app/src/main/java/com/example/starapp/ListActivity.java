package com.example.starapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.starapp.adapter.StarAdapter;
import com.example.starapp.beans.Star;
import com.example.starapp.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private static final String TAG="StarAdapter";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_list);
        stars = new ArrayList<>();
        StarService service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void init(){
        StarService service = StarService.getInstance();
        service.create(new Star("decart", "https://cdn28.modele-lettre-gratuit.com/citations/img/auteurs/250/381.jpg", 3.5f));
        service.create(new Star("george clooney", "http://www.starsphotos.com/resize.php?id=1191", 3));
        service.create(new Star("michelle rodriguez",
                "http://www.starsphotos.com/resize.php?id=1120", 5));
        service.create(new Star("george clooney", "http://www.starsphotos.com/resize.php?id=1193", 1));
        service.create(new Star("louise bouroin", "http://www.starsphotos.com/resize.php?id=1185", 5));
        service.create(new Star("louise bouroin", "http://www.starsphotos.com/resize.php?id=1184", 1));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, newText);
                return true;
            }
        });
        return true;
    }
}
