package org.androidspringbootfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.androidspringbootfrontend.adapter.CustomAdapter;
import org.androidspringbootfrontend.api.MangaService;
import org.androidspringbootfrontend.api.Retrofit2Client;
import org.androidspringbootfrontend.model.Manga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final MangaService service = Retrofit2Client.createService(MangaService.class);
    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting up the RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetching all manga data
        this.findAll();
    }

    // Fetches the list of mangas from the API and updates the RecyclerView
    public void findAll() {
        Call<List<Manga>> call = service.findAll();
        call.enqueue(new Callback<List<Manga>>() {
            @Override
            public void onResponse(Call<List<Manga>> call, Response<List<Manga>> response) {
                if (response.isSuccessful()) {
                    List<Manga> mangas = response.body();
                    if (mangas != null) {
                        customAdapter = new CustomAdapter(mangas, manga -> {
                            // Passing the manga ID to the next activity (SecondaryActivity)
                            Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                            intent.putExtra("id", manga.getId());
                            startActivity(intent);
                        });
                        // Setting the adapter to the RecyclerView
                        recyclerView.setAdapter(customAdapter);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Manga>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Reloads the data when the activity resumes
    @Override
    protected void onResume() {
        super.onResume();
        this.findAll();
    }
}
