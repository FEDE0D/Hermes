package com.grupo03.hermes;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import layout.AlumnoTab;
import layout.EmocionesTab;
import layout.EstabloTab;
import layout.NecesidadesTab;
import layout.PistaTab;

public class AlumnoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        // TABS

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // SI-NO
        Pictograma psi = new Pictograma("Si", "opciones", "si.png", "Si.m4a");
        Pictograma pno = new Pictograma("No", "opciones", "no.png", "No.m4a");

        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutOpciones);
        ImageView iSi = psi.getView(getApplicationContext());
        ImageView iNo = pno.getView(getApplicationContext());

        layout.addView(iSi);
        layout.addView(iNo);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iSi.getLayoutParams();
        params.weight = 0.5f;
        iSi.setLayoutParams(params);

        params = (LinearLayout.LayoutParams) iNo.getLayoutParams();
        params.weight = 0.5f;
        iNo.setLayoutParams(params);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alumno, menu);

        String alumn_name = getIntent().getStringExtra("alumno_nombre");
        menu.findItem(R.id.alumn_name).setTitle(alumn_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        toolbar.setTitle("Alumno");

        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return PistaTab.newInstance("", "");
                case 1:
                    return EstabloTab.newInstance("","");
                case 2:
                    return EmocionesTab.newInstance("","");
                case 3:
                    return NecesidadesTab.newInstance("", "");
                case 4:
                    return AlumnoTab.newInstance("", "");
            }

            return AlumnoTab.newInstance("asd1", "asd2");

            //return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pista";
                case 1:
                    return "Establo";
                case 2:
                    return "Emociones";
                case 3:
                    return "Necesidades";
                case 4:
                    return "Alumno";
            }
            return null;
        }

    }

}
