package tcp.last.view.m_menu.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import tcp.last.R;
import tcp.last.view.h_home.MisRecetasActivity;
import tcp.last.view.p_perfil.PerfilDataActivity;

public class HomeFragment extends Fragment {
    Button cosas, datos;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_fragment_home, container, false);
        cosas = v.findViewById(R.id.homeCosas);
        datos = v.findViewById(R.id.homeDatos);
        cosas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), MisRecetasActivity.class));
            }
        });
        datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), PerfilDataActivity.class));
            }
        });

        return v;
    }
}