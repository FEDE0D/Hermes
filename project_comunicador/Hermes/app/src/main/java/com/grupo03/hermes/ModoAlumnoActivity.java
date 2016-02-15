package com.grupo03.hermes;

import android.app.FragmentTransaction;
import android.content.Intent;
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

import com.grupo03.hermes.db.Database;

import java.util.ArrayList;
import java.util.List;

import layout.AlumnoTab;
import layout.EmocionesTab;
import layout.EstabloTab;
import layout.NecesidadesTab;
import layout.PistaTab;

public class ModoAlumnoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_alumno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        // TABS
        Database database = new Database(getApplicationContext());
        String solapas= database.getSolapasHabilitadas(getIntent().getIntExtra("alumno_id", 0));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        if(solapas.contains("Necesidades"))
            mSectionsPagerAdapter.addFragment(NecesidadesTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mAlumno"), "Necesidades");
        if(solapas.contains("Emociones"))
            mSectionsPagerAdapter.addFragment(EmocionesTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mAlumno"), "Emociones");
        if(solapas.contains("Establo"))
            mSectionsPagerAdapter.addFragment(EstabloTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mAlumno"), "Establo");
        if(solapas.contains("Pista"))
            mSectionsPagerAdapter.addFragment(PistaTab.newInstance(getIntent().getIntExtra("alumno_id", 0), "mAlumno"), "Pista");

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // SI-NO
        Pictograma psi = new Pictograma(0, 0, 0, "Si", "opciones", "si.png", "Si.m4a",false);
        Pictograma pno = new Pictograma(0, 0, 0, "No", "opciones", "no.png", "No.m4a",false);

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
        toolbar.setTitle("Modo alumno");
        menu.findItem(R.id.edit_mode).setTitle("Modo terapeuta");

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
        Intent intent = new Intent(this, AlumnoActivity.class);
        intent.putExtra("alumno_id", alumno_id);
        intent.putExtra("alumno_nombre", alumno_nombre);
        startActivity(intent);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        /**
         * Contains all the fragments.
         */
        private List<Fragment> fragments = new ArrayList<>();

        /**
         * Contains all the tab titles.
         */
        private List<String> tabTitles = new ArrayList<>();

        /**
         * Creates a new PagerAdapter instance.
         *
         * @param fragmentManager The FragmentManager.
         */
        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }

        /**
         * Adds the fragment to the list, also adds the fragment's tab title.
         *
         * @param fragment New instance of the Fragment to be associated with this tab.
         * @param tabTitle A String containing the tab title for this Fragment.
         */
        public void addFragment(Fragment fragment, String tabTitle) {
            fragments.add(fragment);
            tabTitles.add(tabTitle);
        }

    }

}
