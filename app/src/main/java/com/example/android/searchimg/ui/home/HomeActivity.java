package com.example.android.searchimg.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;

import java.util.ArrayList;

/**
 * Created by Yomna on 11/30/2016.
 */
public class HomeActivity extends AppCompatActivity implements HomeBaseView, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private EditText search;
    Context mContext;
    ArrayList<String> list;
    HomeAdapter mAdapter;
    HomePresenter albumHomePresenter;

    public static Intent getStartIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        init();
        addTextListener();


    }

    public void addTextListener() {



        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {


                query = query.toString().toLowerCase();

                final ArrayList<String> filteredList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(list.get(i));
                    }
                }

                mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                mAdapter = new HomeAdapter(mContext,filteredList,true );
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                if(search.getText().toString().matches(""))
                {
                    Log.v("zeft", "true");
                    mAdapter = new HomeAdapter(mContext,filteredList,false );
                    mRecyclerView.setAdapter(mAdapter);

                }

            }
        });
    }

    public void init() {
        search = (EditText) findViewById( R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        list = new ArrayList<>();
        mAdapter = new HomeAdapter(this,list,false);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //presenter
        albumHomePresenter = new HomePresenter(this, DataManager.getInstance(null, null, null, null));
        albumHomePresenter.loadImages();

    }

    @Override
    public void homeSuccess() {

    }

    @Override
    public void homeError(String e) {

    }

    @Override
    public void homeComplete() {

    }

    @Override
    public void userImages(ArrayList<String> userImages) {
        list.clear();
        list.addAll(userImages);
        mAdapter.notifyDataSetChanged();
    }
}
