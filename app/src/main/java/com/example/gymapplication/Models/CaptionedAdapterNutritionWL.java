package com.example.gymapplication.Models;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gymapplication.R;
import com.example.gymapplication.WeightLossNutritionActivity;

public class CaptionedAdapterNutritionWL extends RecyclerView.Adapter<CaptionedAdapterNutritionWL.ViewHolder>{

    private String[] names;
    private String[] types;
    private String[] URL;

    public CaptionedAdapterNutritionWL(String[] names, String[] types, String[] URL){
        this.names = names;
        this.types = types;
        this.URL = URL;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_loss_nutrition_captioned_adapter,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
       /* ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(dr);*/
        TextView txtNames = (TextView)cardView.findViewById(R.id.txtNames);
        txtNames.setText(names[position]);

        TextView txtTypes = (TextView)cardView.findViewById(R.id.txtTypes);
        txtTypes.setText(types[position]);

        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageFromURL);
        Glide.with(holder.cardView).load(URL[position]).centerCrop().placeholder(R.drawable.ic_launcher_background).into(imageView);


        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}