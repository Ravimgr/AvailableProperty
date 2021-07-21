package com.example.availableproperty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    PropertyModel propertyModel;
    Button propType;
    ImageView profileImage;
    TextView propBedNo,propBathNo,propLocation,propSize,ownerName,postedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        propType=findViewById(R.id.propType);
        propBedNo=findViewById(R.id.propBedNo);
        propBathNo=findViewById(R.id.propBathNo);
        propLocation=findViewById(R.id.propLocation);
        propSize=findViewById(R.id.propSize);
        profileImage=findViewById(R.id.ivProf);
        ownerName=findViewById(R.id.ownerName);
        postedDate=findViewById(R.id.postDate);



        Intent intent=getIntent();
        if(intent.getExtras()!=null){
            propertyModel= (PropertyModel) intent.getSerializableExtra("data");
            String msg="Name : "+propertyModel.getId();
            Log.e("data",msg);
            ownerName.setText(propertyModel.getOwnerName());
            propType.setText(propertyModel.getPropertyType());
            propLocation.setText(propertyModel.getPropertyLocation());

            String postDate=propertyModel.getPostedDate();
            postedDate.setText(postDate);

//           ViewPager mViewPager = findViewById(R.id.vpImage);
//            ImageAdapter adapterView = new ImageAdapter(this);
//            mViewPager.setAdapter(adapterView);
        }
    }

//    private class ImageAdapter extends PagerAdapter {
//        Context context;
//        private int[] sliderImage=new int[]{
//
//        }
//        public ImageAdapter(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public int getCount() {
//            return sliderImage.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return view==object;
//        }
//        @NonNull
//        @Override
//        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            ImageView imageView = new ImageView(context);
//            Glide.with(context).load(propertyModel.getPropertyImage()).into(imageView);
//            container.addView(imageView, 0);
//
//            return (imageView);
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            ((ViewPager) container).removeView((ImageView) object);
//        }
//    }
}