package com.example.availableproperty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder>implements Filterable {
    Context context;
    List<PropertyModel> prpList;
    //list of filtered search
    List<PropertyModel> filteredFruitList;
    private PropertyAdapterListener listener;

    public PropertyAdapter(Context context, List<PropertyModel> prpList, List<PropertyModel> filteredFruitList, PropertyAdapterListener listener) {
        this.context = context;
        this.prpList = prpList;
        this.filteredFruitList = filteredFruitList;
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.fruits_list_data,null);
        return new FruitViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
