package com.example.shubhamgulati.codingblocksfinal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    Context cxt;
    ArrayList<articles> articlesArrayList;
    DatabaseReference childreference;

    public Adapter2(Context cxt, ArrayList<articles> articlesArrayList, DatabaseReference childreference) {
        this.cxt = cxt;
        this.articlesArrayList = articlesArrayList;
        this.childreference = childreference;
    }

    @NonNull
    @Override
    public Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedview;
        LayoutInflater li = LayoutInflater.from(cxt);
        inflatedview = li.inflate(R.layout.cardview2,parent,false);
        return new Adapter2.ViewHolder(inflatedview);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.ViewHolder holder, final int position) {
        final articles a = articlesArrayList.get(position);
        holder.tvTitle.setText(""+articlesArrayList.get(position).getTitle());
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

        holder.Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query applesQuery = childreference.orderByChild("id").equalTo(a.id);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                        articlesArrayList.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
        Button Remove;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
//            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            Remove = itemView.findViewById(R.id.Remove);
        }
    }
}
