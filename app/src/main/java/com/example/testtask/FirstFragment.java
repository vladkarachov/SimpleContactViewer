package com.example.testtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.db.AppDatabase;
import com.example.testtask.db.Contact;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    public RecyclerView contactView;
    public List<Contact> names=new ArrayList<>();
    public ArrayList<String> contact_img=new ArrayList<>();
    public AppDatabase mDb;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

         View view = inflater.inflate(R.layout.fragment_first, container, false);
         MainActivity mainActivity = (MainActivity) getActivity();
         mDb = mainActivity.mDb;
        return view;
    }
    public void initContacts(){
        names = mDb.contactDao().getAll();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initContacts();
        contactView =view.findViewById(R.id.contacts_view);
        ContactAdapter adapter = new ContactAdapter(names, contact_img, getActivity());
        contactView.setAdapter(adapter);
        contactView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}