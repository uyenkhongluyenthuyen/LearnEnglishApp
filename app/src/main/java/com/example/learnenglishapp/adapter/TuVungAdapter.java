package com.example.learnenglishapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.RelativeDateTimeFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglishapp.Interface.IClickItemTuVungListener;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.model.TuVung;

import java.util.List;

public class TuVungAdapter extends RecyclerView.Adapter<TuVungAdapter.TuVungViewHolder> {

    private List<TuVung> listTV;
    private IClickItemTuVungListener iClickItemTuVungListener;

    public TuVungAdapter(List<TuVung> listTV, IClickItemTuVungListener iClickItemTuVungListener) {
        this.listTV = listTV;
        this.iClickItemTuVungListener = iClickItemTuVungListener;
    }

    @NonNull
    @Override
    public TuVungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuvung_item, parent, false);
        return new TuVungViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TuVungViewHolder holder, int position) {
        TuVung tuVung = listTV.get(position);
        if(tuVung == null){
            return;
        }
        holder.tvTuTA.setText(tuVung.getTuTA());
        holder.tvNghiaTV.setText(tuVung.getNghiaTV());
        Bitmap img= BitmapFactory.decodeByteArray(tuVung.getHinhAnh(),0,tuVung.getHinhAnh().length);
        holder.imgHinh.setImageBitmap(img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTuVungListener.onClickItemTuVung(tuVung);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listTV !=null){
            return listTV.size();
        }
        return 0;
    }

    public static class TuVungViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTuTA;
        private TextView tvNghiaTV;
        private ImageView imgHinh;


        public TuVungViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTuTA = itemView.findViewById(R.id.tvTuTA);
            tvNghiaTV = itemView.findViewById(R.id.tvNghiaTV);
            imgHinh = itemView.findViewById(R.id.imgHinh);

        }
    }
}
