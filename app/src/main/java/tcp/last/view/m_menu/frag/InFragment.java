package tcp.last.view.m_menu.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import tcp.last.R;
import tcp.last.view.b_busca.BuscaActivity;
import tcp.last.view.c_crea.CreaActivity;


public class InFragment extends Fragment {
    Button bt_crea, bt_inventa, bt_busca;

    public InFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_fragment_in, container, false);
        bt_busca = v.findViewById(R.id.buttonHallBusca);
        bt_crea = v.findViewById(R.id.buttonHallCrea);
        bt_inventa = v.findViewById(R.id.buttonHallIa);

        bt_busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BuscaActivity.class));
            }
        });
        bt_crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreaActivity.class));
            }
        });
        bt_inventa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), InventaActivity.class));
            }
        });
    return v;
    }
}