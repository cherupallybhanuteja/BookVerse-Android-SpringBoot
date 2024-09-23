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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MangaViewHolder> {
    private List<Manga> mangaList;
    private final OnMangaClickListener listener;

    public CustomAdapter(List<Manga> mangaList, OnMangaClickListener listener) {
        this.mangaList = mangaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga, parent, false);
        return new MangaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        Manga manga = mangaList.get(position);

        // Load image using Picasso with placeholders and error images
        Picasso.get()
                .load(manga.getUrl())   // Get image URL from backend
                .centerCrop()
                .fit()
                .placeholder(R.drawable.placeholder_image)   // Set placeholder image
                .error(R.drawable.error_image)   // Set error image if URL fails
                .into(holder.image);   // Set to imageView

        // Set text details
        holder.title.setText(manga.getTitle());
        holder.author.setText(manga.getAuthor());

        holder.itemView.setOnClickListener(v -> listener.onMangaClick(manga));
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public class MangaViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView author;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.manga_image);
            title = itemView.findViewById(R.id.manga_title);
            author = itemView.findViewById(R.id.manga_author);
        }
    }

    // Define the OnMangaClickListener interface
    public interface OnMangaClickListener {
        void onMangaClick(Manga manga);
    }
}
