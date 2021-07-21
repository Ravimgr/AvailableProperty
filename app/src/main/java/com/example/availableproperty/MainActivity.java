package com.example.availableproperty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
 RecyclerView recyclerView;
 RecyclerAdapter recyclerAdapter;
 List<PropertyModel> propertyList;
 Toolbar toolbar;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            PropertyModel thisItem = propertyList.get(position);
            Toast.makeText(MainActivity.this, "You Clicked: " + thisItem.getOwnerName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,DetailActivity.class).putExtra("data",propertyList.get(position)));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);


        recyclerView = (RecyclerView)findViewById(R.id.rvProperty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),propertyList);
        recyclerView.setAdapter(recyclerAdapter);



        ApiInterface apiInterface=RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<PropertyModel>> call=apiInterface.getProperty();
        call.enqueue(new Callback<List<PropertyModel>>() {
            @Override
            public void onResponse(Call<List<PropertyModel>> call, Response<List<PropertyModel>> response) {
                propertyList=response.body();
                //Toast.makeText(MainActivity.this, " "+response.body(), Toast.LENGTH_SHORT).show();
                recyclerAdapter.setPropertyList(propertyList);
                recyclerAdapter.setOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onFailure(Call<List<PropertyModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder>{

        Context context;
        List<PropertyModel> propertyList;
        private View.OnClickListener mOnItemClickListener;

        public RecyclerAdapter(Context context, List<PropertyModel> propertyList) {
            this.context = context;
            this.propertyList = propertyList;
        }

        public void setPropertyList(List<PropertyModel> movieList) {
            this.propertyList = movieList;
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public RecyclerAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item,parent,
                    false);
            return new MyviewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.MyviewHolder holder, int position) {
            holder.text.setText(propertyList.get(position).getOwnerName());
            Glide.with(context).load(propertyList.get(position).getPropertyImage()).apply(RequestOptions.centerCropTransform()).into(holder.image);

        }

        @Override
        public int getItemCount() {
            if(propertyList != null){
                return propertyList.size();
            }
            return 0;
        }
        public void setOnItemClickListener(View.OnClickListener itemClickListener) {
            mOnItemClickListener = itemClickListener;
        }


        public class MyviewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView text;
            public MyviewHolder(@NonNull View itemView) {
                super(itemView);
                image=itemView.findViewById(R.id.image);
                text=itemView.findViewById(R.id.title);

                itemView.setTag(this);
                itemView.setOnClickListener(mOnItemClickListener);
            }

        }
    }
}