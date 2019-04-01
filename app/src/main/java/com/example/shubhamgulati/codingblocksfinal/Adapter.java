package com.example.shubhamgulati.codingblocksfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context cxt;
    ArrayList<articles> articlesArrayList;
    DatabaseReference childreference;

    public Adapter(Context cxt, ArrayList<articles> articlesArrayList, DatabaseReference childreference) {
        this.cxt = cxt;
        this.articlesArrayList = articlesArrayList;
        this.childreference = childreference;
    }

//    public Adapter(Context cxt, ArrayList<articles> articlesArrayList) {
//        this.cxt = cxt;
//        this.articlesArrayList = articlesArrayList;
//    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedview;
        LayoutInflater li = LayoutInflater.from(cxt);
        inflatedview = li.inflate(R.layout.cardview,parent,false);
        return new ViewHolder(inflatedview);

    }

    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, final int position) {
        final articles a = articlesArrayList.get(position);
        holder.tvTitle.setText(Html.fromHtml(a.getTitle()));
        Picasso.get().load(a.getUrlToImage()).placeholder(R.drawable.ic_launcher_background).into(holder.iv);
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt,Main2Activity.class);
                i.putExtra("key",articlesArrayList.get(position));
                cxt.startActivity(i);
            }
        });
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cxt,Main2Activity.class);
                i.putExtra("key",articlesArrayList.get(position));
                cxt.startActivity(i);
            }
        });

        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.tvTitle.setTextColor(Color.BLUE);
                childreference.push().setValue(a);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvDesc,tvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
//            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
