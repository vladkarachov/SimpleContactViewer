package com.example.testtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.db.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contactNames;
    //картинок пока что нет
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.contact_name.setText(contactNames.get(position).name);
        final Contact item = contactNames.get(position);

        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AppCompatActivity activity = (AppCompatActivity) mContext;
                SecondFragment myFragment = new SecondFragment();
                myFragment.setContact(item);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
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
