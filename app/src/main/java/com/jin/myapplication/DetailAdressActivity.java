package com.jin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

public class DetailAdressActivity extends AppCompatActivity {

    private EditText mNamaEditText;
    private EditText mPhoneEditText;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_adress);

        mNamaEditText = findViewById(R.id.name_edit);
        mPhoneEditText = findViewById(R.id.phone_edit);
        mImageView = findViewById(R.id.image_view);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            int picture = intent.getIntExtra("picture", R.mipmap.ic_launcher);

            mNamaEditText.setText(name);
            mPhoneEditText.setText(phone);
            mImageView.setImageResource(picture);
        }
    }
}
