package com.example.android.searchimg.ui.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Yomna on 11/30/2016.
 */
public class HomeActivity extends AppCompatActivity implements HomeBaseView, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private EditText search;
   // private FloatingActionButton fab;
    Context mContext;
    ArrayList<String> list;
    HomeAdapter mAdapter;
    HomePresenter albumHomePresenter;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString ;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagefromGallery(view);
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("OnActivityResult", "here");
        try {

            if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data){
                //get image from data
                Uri selectedImage = data.getData();

                File file = FileUtils.getFile(this, selectedImage);
                //create requestbody instance from file
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                //send the actual file name
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                /*String descriptionString = "image";
                RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);*/
                albumHomePresenter.uploadImage(body);

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                //File file = new File(getRealPathFromURI(selectedImage));

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                Toast.makeText(this, "You have picked Image lolololy",
                        Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void loadImagefromGallery(View view)
    {
        // create intent to open image apps
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

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
                    mAdapter = new HomeAdapter(mContext,filteredList,false );
                    mRecyclerView.setAdapter(mAdapter);

                }

            }
        });
    }

    public void init() {
        search = (EditText) findViewById( R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //fab = (FloatingActionButton) findViewById(R.id.fab);
        list = new ArrayList<>();
        mAdapter = new HomeAdapter(this,list,false);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //presenter
        albumHomePresenter = new HomePresenter(this, DataManager.getInstance(null, null, null, null));
       // albumHomePresenter.loadImages();

    }

    @Override
    public void homeSuccess() {
        Toast.makeText(this, "Image uploaded successfully",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void homeError(String e) {
        Toast.makeText(this, "Opss try uploading again",
                Toast.LENGTH_LONG).show();
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
