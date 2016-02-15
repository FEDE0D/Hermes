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
import android.view.MenuItem;
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

    public static AlumnoActivity _instance = null;
    private SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        setSupportActionBar(toolbar);

        _instance = this;
        Intent intent = getIntent();

        // TABS

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }
     boolean activa;
    public boolean getActiva(){
        return activa;
    }

    @Override
    public void onResume(){
        this.activa= true;
        super.onResume();
    }
    @Override
    public void onStop(){
        this.activa= false;
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alumno, menu);

        String alumn_name = getIntent().getStringExtra("alumno_nombre");
        menu.findItem(R.id.alumn_name).setTitle(alumn_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        toolbar.setTitle("Modo terapeuta");

        return true;
    }
    public void mostrarAjustes(MenuItem view)
    {

        Intent intent = new Intent(this, AjustesActivity.class);
        int alumno_id = getIntent().getIntExtra("alumno_id", 0);
        intent.putExtra("alumno_id", alumno_id);
        intent.putExtra("tipo", "editarAlumno");
        startActivity(intent);
    }

    public void mostrarModoAlumno(MenuItem view)
    {
        int alumno_id = getIntent().getIntExtra("alumno_id", 0);
        String alumno_nombre = getIntent().getStringExtra("alumno_nombre");
        Intent intent = new Intent(this, ModoAlumnoActivity.class);
        intent.putExtra("alumno_nombre", alumno_nombre);
        intent.putExtra("alumno_id", alumno_id);
        startActivity(intent);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return PistaTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mTerapeuta");
                case 1:
                    return EstabloTab.newInstance(getIntent().getIntExtra("alumno_id", 0),"mTerapeuta");
                case 2:
                    return EmocionesTab.newInstance(getIntent().getIntExtra("alumno_id", 0),"mTerapeuta");
                case 3:
                    return NecesidadesTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mTerapeuta");
                case 4:
                    return AlumnoTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mTerapeuta");
            }

            return AlumnoTab.newInstance(1, "asd2");

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
