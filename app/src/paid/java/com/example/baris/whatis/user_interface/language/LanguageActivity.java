package com.example.baris.whatis.user_interface.language;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baris.whatis.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
    private String selected_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);

        readFromFile(this);
        selected_language=readFromFile(this);

        switch (selected_language){
            case "es":
                imageView1.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                break;
            case "en":
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                break;
            case "lv":
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                break;
            case "hi":
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                break;
            case "sw":
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                break;
            case "ta":
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.VISIBLE);
                imageView7.setVisibility(View.GONE);
                break;
            case "gu":
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

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
                    writeToFile("es",v.getContext());
                    Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chosen_language =getString(R.string.english);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                writeToFile("en",v.getContext());
                Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chosen_language =getString(R.string.latvian);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                writeToFile("lv",v.getContext());
                Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chosen_language =getString(R.string.hindi);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.VISIBLE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                writeToFile("hi",v.getContext());
                Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chosen_language =getString(R.string.swahili);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.VISIBLE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.GONE);
                writeToFile("sw",v.getContext());
                Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chosen_language =getString(R.string.tamil);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.VISIBLE);
                imageView7.setVisibility(View.GONE);
                writeToFile("ta",v.getContext());
                Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
            }
        });

        constraintLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                chosen_language =getString(R.string.gujarati);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
                imageView5.setVisibility(View.GONE);
                imageView6.setVisibility(View.GONE);
                imageView7.setVisibility(View.VISIBLE);
                writeToFile("gu",v.getContext());
                Snackbar.make(constraintLayout1, chosen_language + " " + getString(R.string.selected), Snackbar.LENGTH_SHORT).show();
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

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("what_is.config", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(getString(R.string.ioexception), getString(R.string.file_write_fail)+ e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("what_is.config");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            writeToFile("es",this);
        } catch (IOException e) {
            Log.e(getString(R.string.ioexception), getString(R.string.cannot_read_file) + e.toString());
        }
        return ret;
    }
}
