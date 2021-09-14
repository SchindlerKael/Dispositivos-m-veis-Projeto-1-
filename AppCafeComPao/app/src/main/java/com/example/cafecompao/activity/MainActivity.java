package com.example.cafecompao.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.cafecompao.fragment.ListaBebidasFragment;
import com.example.cafecompao.fragment.ListaInsumosFragment;

import com.example.cafecompao.fragment.HorarioFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.example.cafecompao.R;


public class MainActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartTabLayout = findViewById(R.id.viewPagerTab);
        viewPager = findViewById(R.id.viewPager);

        getSupportActionBar().setElevation(0);

        //Configurar adapter para abas
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Comidas", ListaInsumosFragment.class )
                        .add("Bebidas", ListaBebidasFragment.class )
                        .add("Horário", HorarioFragment.class )
                .create()
        );

        viewPager.setAdapter( adapter );
        smartTabLayout.setViewPager( viewPager );

    }
}
