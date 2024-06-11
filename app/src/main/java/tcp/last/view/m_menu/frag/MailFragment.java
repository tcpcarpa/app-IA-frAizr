package tcp.last.view.m_menu.frag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tcp.last.R;
import tcp.last.view.m_menu.MenuActivity;

public class MailFragment extends Fragment {
    Button btnEnviar, btcancel;
    EditText EtCorreo, EtMensaje, EtAsunto;

    public MailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_fragment_mail, container, false);

        EtCorreo = v.findViewById(R.id.EtCorreo);
        EtAsunto = v.findViewById(R.id.EtAsunto);
        EtMensaje = v.findViewById(R.id.EtMensaje);
        btnEnviar = v.findViewById(R.id.btnEnviar);
        btcancel = v.findViewById(R.id.butMailCancel);

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), MenuActivity.class));
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = EtCorreo.getText().toString();
                String asunto = EtAsunto.getText().toString();
                String mensaje = EtMensaje.getText().toString();
                if (correo.equals("")) {
                    Toast.makeText(getActivity(), "ingresa un correo", Toast.LENGTH_LONG).show();
                } else if (asunto.equals("")) {
                    Toast.makeText(getActivity(), "ingresa el asunto", Toast.LENGTH_LONG).show();
                } else if (mensaje.equals("")) {
                    Toast.makeText(getActivity(), "ingresa un mensaje", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Escoge un cliente de correo:"));
                }
            }
        });
        return v;
    }
}