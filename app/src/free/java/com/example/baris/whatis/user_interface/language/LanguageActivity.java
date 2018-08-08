package com.example.baris.whatis.user_interface.language;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baris.whatis.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageActivity extends AppCompatActivity {

    @BindView(R.id.language_layout_1)
    ConstraintLayout constraintLayout1;
    @BindView(R.id.selected_iv1)
    ImageView imageView1;

    @BindView(R.id.language_layout_2)
    ConstraintLayout constraintLayout2;
    @BindView(R.id.selected_iv2)
    ImageView imageView2;

    @BindView(R.id.language_layout_3)
    ConstraintLayout constraintLayout3;
    @BindView(R.id.selected_iv3)
    ImageView imageView3;

    @BindView(R.id.language_layout_4)
    ConstraintLayout constraintLayout4;
    @BindView(R.id.selected_iv4)
    ImageView imageView4;

    @BindView(R.id.language_layout_5)
    ConstraintLayout constraintLayout5;
    @BindView(R.id.selected_iv5)
    ImageView imageView5;

    @BindView(R.id.language_layout_6)
    ConstraintLayout constraintLayout6;
    @BindView(R.id.selected_iv6)
    ImageView imageView6;

    @BindView(R.id.language_layout_7)
    ConstraintLayout constraintLayout7;
    @BindView(R.id.selected_iv7)
    ImageView imageView7;

    private String chosen_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);

        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    chosen_language =getString(R.string.spanish);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.GONE);
                    imageView3.setVisibility(View.GONE);
                    imageView4.setVisibility(View.GONE);
                    imageView5.setVisibility(View.GONE);
                    imageView6.setVisibility(View.GONE);
                    imageView7.setVisibility(View.GONE);

                    Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),getString(R.string.get_premium),Toast.LENGTH_LONG).show();
            }
        });

        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),getString(R.string.get_premium),Toast.LENGTH_LONG).show();
            }
        });

        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),getString(R.string.get_premium),Toast.LENGTH_LONG).show();
            }
        });

        constraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),getString(R.string.get_premium),Toast.LENGTH_LONG).show();
            }
        });

        constraintLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),getString(R.string.get_premium),Toast.LENGTH_LONG).show();
            }
        });

        constraintLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),getString(R.string.get_premium),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
    }
}
