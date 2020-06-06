package com.example.testtask;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.db.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact>  contactNames;
    private ArrayList<String> images;
    private Context mContext;

    public ContactAdapter(List<Contact> contactNames, ArrayList<String> images, Context mContext) {
        this.contactNames =  (ArrayList<Contact>)contactNames;
        this.images = images;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contact_name.setText(contactNames.get(position).name);
        final Contact item = contactNames.get(position);

        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new SecondFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.include, myFragment).addToBackStack(null).commit();



                //todo onclick
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView contact_im;
        TextView contact_name;
        RelativeLayout relative_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contact_im=itemView.findViewById(R.id.contact_img);
            contact_name=itemView.findViewById(R.id.contact_name);
            relative_layout=itemView.findViewById(R.id.relative_layout);

        }
    }
}
