package org.androidspringbootfrontend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.androidspringbootfrontend.api.MangaService;
import org.androidspringbootfrontend.api.Retrofit2Client;
import org.androidspringbootfrontend.model.Manga;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondaryActivity extends Activity {

    final MangaService service = Retrofit2Client.createService(MangaService.class);
    Manga manga;
    Button btnUpdate;
    Button btnDelete;
    EditText textTitle;
    EditText textAuthor;
    EditText textYear;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_details);

        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        textTitle = findViewById(R.id.title);
        textAuthor = findViewById(R.id.author);
        textYear = findViewById(R.id.year);
        imageView = findViewById(R.id.image_view_detail);

        final Intent intent = getIntent();
        final long id = intent.getLongExtra("id", 0);

        // Set update action
        btnUpdate.setOnClickListener(v -> {
            try {
                updateById(id);
                finish(); // Close activity after updating
            } catch (NumberFormatException e) {
                Toast.makeText(SecondaryActivity.this, "Invalid year format!", Toast.LENGTH_SHORT).show();
            }
        });

        // Set delete action
        btnDelete.setOnClickListener(v -> {
            deleteById(id);
            finish(); // Close activity after deletion
        });

        // Fetch manga details by id
        findById(id);
    }

    public void setFindById(Manga manga) {
        textTitle.setText(manga.getTitle());
        textAuthor.setText(manga.getAuthor());
        textYear.setText(String.valueOf(manga.getYear()));
        Picasso.get()
                .load(manga.getUrl())
                .centerCrop()
                .fit()
                .placeholder(R.drawable.placeholder_image)   // Set placeholder image
                .error(R.drawable.error_image)   // Set error image if URL fails
                .into(imageView);
    }

    public void findById(long id) {
        Call<Manga> call = service.findById(id);
        call.enqueue(new Callback<Manga>() {
            @Override
            public void onResponse(Call<Manga> call, Response<Manga> response) {
                if (response.isSuccessful()) {
                    manga = response.body();
                    assert manga != null;
                    setFindById(manga);
                    Toast.makeText(SecondaryActivity.this, "Manga details loaded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondaryActivity.this, "Failed to load manga details!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Manga> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteById(long id) {
        Call<Manga> call = service.delete(id);
        call.enqueue(new Callback<Manga>() {
            @Override
            public void onResponse(Call<Manga> call, Response<Manga> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SecondaryActivity.this, "Manga deleted!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Manga> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateById(long id) {
        int year;
        try {
            year = Integer.parseInt(textYear.getText().toString());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid year format!");
        }
        Manga modified = new Manga(id, textTitle.getText().toString(), textAuthor.getText().toString(), year, true, manga.getUrl());
        Call<Manga> call = service.update(modified);
        call.enqueue(new Callback<Manga>() {
            @Override
            public void onResponse(Call<Manga> call, Response<Manga> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SecondaryActivity.this, "Manga updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondaryActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Manga> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
