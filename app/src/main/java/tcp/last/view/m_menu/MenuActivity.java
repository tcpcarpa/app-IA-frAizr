package tcp.last.view.m_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import tcp.last.R;
import tcp.last.view.m_menu.frag.AboutFragment;
import tcp.last.view.m_menu.frag.DeletUserFragment;
import tcp.last.view.m_menu.frag.HomeFragment;
import tcp.last.view.m_menu.frag.InFragment;
import tcp.last.view.m_menu.frag.LogOutFragment;
import tcp.last.view.m_menu.frag.PerfilFragment;
import tcp.last.view.m_menu.frag.SettingsFragment;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_activity_menu);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        if(id == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new HomeFragment()).commit();
            return true;
        }
        if(id == R.id.nav_perfil){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new PerfilFragment()).commit();
            return true;
        }
        if(id == R.id.nav_about){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new AboutFragment()).commit();
            return true;
        }
        if(id == R.id.nav_settins){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new SettingsFragment()).commit();
            return true;
        }
        if(id == R.id.nav_delete){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new DeletUserFragment()).commit();
            return true;
        }
        if(id == R.id.nav_logout){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new LogOutFragment()).commit();
            return true;
        }
        return false;
    }
}