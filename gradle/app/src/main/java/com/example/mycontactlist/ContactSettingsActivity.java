package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);
        initListButton();
        initMapButton();
        initSettingsButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();
        initBackgroundColorClick();
        String bckGrndColor = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("backgrndColor", "white");
        setBackgroundColor(bckGrndColor);
    }

    private void initListButton(){
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initMapButton(){
        ImageButton ibMap = findViewById(R.id.imageButtonMap);
        ibMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initSettingsButton(){
        ImageButton ibSettings = findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }

    private void initSettings()
    {
        String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield","contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder","ASC");
        String bckGrndColor = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("backgrndColor", "white");

        RadioButton rbName = findViewById(R.id.radioName);
        RadioButton rbCity = findViewById(R.id.radioCity);
        RadioButton rbBirthday = findViewById(R.id.radioBirthday);
        if(sortBy.equalsIgnoreCase("contactname"))
        {
            rbName.setChecked(true);
        }
        else if(sortBy.equalsIgnoreCase("city"))
        {
            rbCity.setChecked(true);
        }
        else
        {
            rbBirthday.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);
        if(sortOrder.equalsIgnoreCase("ASC"))
        {
            rbAscending.setChecked(true);
        }
        else
        {
            rbDescending.setChecked(true);
        }

        RadioButton rbWhite = findViewById(R.id.radioWhiteBckGrnd);
        RadioButton rbBlue = findViewById(R.id.radioBlueBckGrnd);
        RadioButton rbGreen = findViewById(R.id.radioGreenBckGrnd);
        RadioButton rbGrey = findViewById(R.id.radioGreyBckGrnd);
        if(bckGrndColor.equalsIgnoreCase("white"))
        {
            rbWhite.setChecked(true);
        }
        else if(bckGrndColor.equalsIgnoreCase("blue"))
        {
            rbBlue.setChecked(true);
        }
        else if(bckGrndColor.equalsIgnoreCase("green"))
        {
            rbGreen.setChecked(true);
        }
        else
        {
            rbGrey.setChecked(true);
        }
    }

    private void initSortByClick()
    {
        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton rbName = findViewById(R.id.radioName);
                RadioButton rbCity = findViewById(R.id.radioCity);
                if(rbName.isChecked())
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield","contactname").apply();
                }
                else if(rbCity.isChecked())
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield","city").apply();
                }
                else
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield","birthday").apply();
                }
            }
        });
    }

    private void initSortOrderClick()
    {
        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortBy);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton rbAscending = findViewById(R.id.radioAscending);
                if(rbAscending.isChecked())
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder","ASC").apply();
                }
                else
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder","DESC").apply();
                }
            }
        });
    }

    private void initBackgroundColorClick()
    {
        RadioGroup rgBackgroundColor = findViewById(R.id.radioGroupBackgroundColor);
        rgBackgroundColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton rbWhite = findViewById(R.id.radioWhiteBckGrnd);
                RadioButton rbBlue = findViewById(R.id.radioBlueBckGrnd);
                RadioButton rbGreen = findViewById(R.id.radioGreenBckGrnd);
                if(rbWhite.isChecked())
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("backgrndColor","white").apply();
                }
                else if(rbBlue.isChecked())
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("backgrndColor","blue").apply();
                }
                else if(rbGreen.isChecked())
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("backgrndColor","green").apply();
                }
                else
                {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("backgrndColor","grey").apply();
                }
            }
        });
    }

    public void setBackgroundColor(String color)
    {
        ScrollView scrollView = findViewById(R.id.settingsScrView);
        if(color.equals("white"))
            scrollView.setBackgroundResource(R.color.white);
        if(color.equals("blue"))
            scrollView.setBackgroundResource(R.color.purple_500);
        if(color.equals("green"))
            scrollView.setBackgroundResource(R.color.green);
        else
            scrollView.setBackgroundResource(R.color.gray);
    }
}