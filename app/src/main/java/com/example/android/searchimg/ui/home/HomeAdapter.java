package com.example.android.searchimg.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.searchimg.R;

import java.util.ArrayList;

/**
 * Created by Yomna on 11/30/2016.
 */
public class HomeAdapter  extends RecyclerView.Adapter <HomeAdapter.CustomViewHolder> {
    private ArrayList<String> albumItemList;
    private Context mContext;
    private boolean condition;
    public HomeAdapter(Context context, ArrayList<String> albumItemList, boolean condition) {
        this.albumItemList = albumItemList;
        this.mContext = context;
        this.condition = condition;

    }
    @Override
    public HomeAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.CustomViewHolder holder, int position) {
        holder.image_name.setText(albumItemList.get(position));


    }

    @Override
    public int getItemCount() {
        return (null != albumItemList ? albumItemList.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // protected ImageView imageView;
        protected TextView image_name;

        public CustomViewHolder(View view) {
            super(view);

            this.image_name = (TextView) view.findViewById(R.id.image_name);
            if(condition==true){
                image_name.setVisibility(View.VISIBLE);
            }
            else
                image_name.setVisibility(View.GONE);
        }
    }

}
