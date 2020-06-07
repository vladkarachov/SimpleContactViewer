package com.example.testtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtask.db.AppDatabase;
import com.example.testtask.db.Contact;

public class SecondFragment extends Fragment {
    public Contact mContact;
    public AppDatabase mDb;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mDb = mainActivity.mDb;
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }
    public SecondFragment setContact(Contact contact){
        mContact = contact;
        return this;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView nameField = getView().findViewById(R.id.editTextTextPersonName);
        nameField.setText(mContact.name);
        final TextView surnameField = getView().findViewById(R.id.editTextTextPersonSurname);
        surnameField.setText(mContact.surname);
        final TextView emailField = getView().findViewById(R.id.editTextTextPersonEmail);
        emailField.setText(mContact.email);
        view.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                Fragment myFragment = new FirstFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment myFragment = new FirstFragment();
                MainActivity activity = (MainActivity) getActivity();
                mDb.contactDao().delete(mContact);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });
        view.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment myFragment = new FirstFragment();
                MainActivity activity = (MainActivity) getActivity();
                Contact newContact = new Contact(mContact.id, ""+ nameField.getText(),  ""+  surnameField.getText(),  ""+  emailField.getText());
                mDb.contactDao().update(newContact);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });
    }
}