package com.example.gestionconferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mesconferences.conferences.MesConferencesActivity;

public class FragmentEcranUtilisateur extends Fragment {

  public final static String TAG = "fragmentEcranUtilisateur";

  public Button boutonCalendrier, boutonMesConferences, boutonConferenceProche, boutonOptions;
  View myFragmentView;

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    myFragmentView = inflater.inflate(R.layout.layout_ecran_utilisateur, container, false);
    boutonCalendrier = (Button) myFragmentView.findViewById(R.id.boutonCalendrier);
    ajouterListenerBoutons();
    return myFragmentView;
  }

  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

  public void ajouterListenerBoutons() {

    // Selection de tous les boutons
    boutonCalendrier.setOnTouchListener(new MonBoutonClickListener());
    boutonMesConferences = (Button) myFragmentView.findViewById(R.id.boutonMesConferences);
    boutonMesConferences.setOnTouchListener(new MonBoutonClickListener());

    boutonConferenceProche = (Button) myFragmentView.findViewById(R.id.boutonConferenceProche);
    boutonConferenceProche.setOnTouchListener(new MonBoutonClickListener());

    boutonOptions = (Button) myFragmentView.findViewById(R.id.boutonOptions);
    boutonOptions.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(getActivity(), "This feature is not available on this version",
            Toast.LENGTH_SHORT).show();
        return false;
      }
    });
  }

  public class MonBoutonClickListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {

      int action = event.getActionMasked();

      if (action == MotionEvent.ACTION_DOWN) {
        v.setBackgroundResource(R.drawable.rounded_button_clicked);

      }
      if (action == MotionEvent.ACTION_UP) {
        v.setBackgroundResource(R.drawable.rounded_button);

        if (v == (Button) myFragmentView.findViewById(R.id.boutonCalendrier)) {

          Intent i = new Intent(getActivity(), CalendrierActivity.class);
          startActivity(i);

        } else if (v == (Button) myFragmentView.findViewById(R.id.boutonMesConferences)) {
          Intent intent = new Intent(getActivity(), MesConferencesActivity.class);
          startActivity(intent);
        }
      }

      return true;
    }

  }

}
