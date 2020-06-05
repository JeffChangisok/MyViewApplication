package com.myviewapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.myviewapplication.R;

/**
 * 绘图基础
 */
@SuppressWarnings("ALL")
public class BaseDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_draw);

        boolean living = true;

        while (living) {
            People people = meetPeople();
            people.love = "the one".equals(findHer(people.name));
        }
    }

    People meetPeople() {
        return new People("csr", false);
    }

    String findHer(String name) {
        if (name.equals("csr")) {
            return "the one";
        } else {
            return "don't care";
        }
    }

    static class People {
        People(String name, boolean love) {
            this.name = name;
            this.love = love;
        }

        String name;
        boolean love;
    }
}
