package com.example.afternooncontacts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceAdapter.ContactsViewHolder> {


    ChoiceModel choiceModel;
    public ChoiceAdapter() {
        super();
        choiceModel = ChoiceModel.getSingleton();
    }

//creating the viewholder to the list
    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);
        ContactsViewHolder vh = new ContactsViewHolder(v);
        return vh;
    }

    //binding the data  to the list_item
    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        TextView nameTV = holder.convienceViewReference.findViewById(R.id.nameTV);
        nameTV.setText(choiceModel.choiceList.get(position).name);
    }

    //get the item count in the list
    @Override
    public int getItemCount() {


        return choiceModel.choiceList.size();

    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout convienceViewReference;
        public ContactsViewHolder(LinearLayout a)
        {
            super(a);
            convienceViewReference = a;
        }
    }
}