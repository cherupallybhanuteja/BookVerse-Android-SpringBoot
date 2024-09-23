package org.androidspringbootfrontend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.androidspringbootfrontend.R;
import org.androidspringbootfrontend.model.Manga;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {
    private List<Manga> mangaList;

    public MangaAdapter(List<Manga> mangaList) {
        this.mangaList = mangaList;
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga, parent, false);
        return new MangaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        Manga currentManga = mangaList.get(position);

        // Load image using Picasso with placeholders and error handling
        Picasso.get()
                .load(currentManga.getUrl())   // Ensure the URL is valid
                .centerCrop()
                .fit()
                .placeholder(R.drawable.placeholder_image)   // Placeholder while loading
                .error(R.drawable.error_image)   // Fallback image if the URL fails
                .into(holder.mangaImage);   // Set to ImageView

        // Set text details
        holder.mangaTitle.setText(currentManga.getTitle());
        holder.mangaAuthor.setText(currentManga.getAuthor());
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public static class MangaViewHolder extends RecyclerView.ViewHolder {
        ImageView mangaImage;
        TextView mangaTitle;
        TextView mangaAuthor;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaImage = itemView.findViewById(R.id.manga_image);
            mangaTitle = itemView.findViewById(R.id.manga_title);
            mangaAuthor = itemView.findViewById(R.id.manga_author);
        }
    }
}
