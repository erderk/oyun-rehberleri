package com.example.oyunrehberleri;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private String[] gTitles;
    private String[] gContent;
    private int[] gImages;

    GameAdapter(Context context, String[] titles, String[] contents, int[] images){
        this.inflater = LayoutInflater.from(context);
        this.gTitles = titles;
        this.gContent = contents;
        this.gImages = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = gTitles[position];
        String content = gContent[position];
        int image = gImages[position];
        holder.gameName.setText(title);
        holder.gameDescription.setText(content);
        holder.gameImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return gTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameName, gameDescription;
        ImageView gameImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DetailsActivity.class);
                    intent.putExtra("titleOfGame",gTitles[getAdapterPosition()]);
                    intent.putExtra("contentOfGame",gContent[getAdapterPosition()]);
                    v.getContext().startActivity(intent);
                }
            });
            gameName = itemView.findViewById(R.id.gameName);
            gameDescription = itemView.findViewById(R.id.gameDescription);
            gameImage = itemView.findViewById(R.id.gameImage);
        }
    }
}
