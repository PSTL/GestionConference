package com.example.gestionconferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mesconferences.conferences.MesConferencesActivity;

@SuppressLint("NewApi")
public class FragmentEcranUtilisateur extends Fragment {

  public final static String TAG = "fragmentEcranUtilisateur";
  public Button boutonCalendrier;
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
    // Button boutonCalendrier = (Button) findViewById(R.id.boutonCalendrier);
    boutonCalendrier.setOnTouchListener(new MonBoutonClickListener());
    Button boutonMesConferences = (Button) myFragmentView.findViewById(R.id.boutonMesConferences);
    boutonMesConferences.setOnTouchListener(new MonBoutonClickListener());
    // Button boutonNouvelleConference = (Button)
    // myFragmentView.findViewById(R.id.boutonNouvelleConference);
    // boutonNouvelleConference.setOnTouchListener(new MonBoutonClickListener());
    Button boutonConferenceProche =
        (Button) myFragmentView.findViewById(R.id.boutonConferenceProche);
    boutonConferenceProche.setOnTouchListener(new MonBoutonClickListener());
    Button boutonOptions = (Button) myFragmentView.findViewById(R.id.boutonOptions);
    boutonOptions.setOnTouchListener(new MonBoutonClickListener());

  }

  public class MonBoutonClickListener implements View.OnTouchListener {

    @SuppressLint("NewApi")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

      int action = event.getActionMasked();

      if (action == MotionEvent.ACTION_DOWN) {
        v.setBackgroundResource(R.drawable.rounded_button_clicked);

      }
      if (action == MotionEvent.ACTION_UP) {
        v.setBackgroundResource(R.drawable.rounded_button);

        if (v == (Button) myFragmentView.findViewById(R.id.boutonCalendrier)) {

          Intent i = new Intent(getActivity(),CalendrierActivity.class);
          //i.setClassName("com.android.calendar", "com.android.calendar.CalendrierActivity");
          startActivity(i);

        } else if (v == (Button) myFragmentView.findViewById(R.id.boutonMesConferences)) {
          Intent intent = new Intent(getActivity(), MesConferencesActivity.class);
          startActivity(intent);
        } else if (v == (Button) myFragmentView.findViewById(R.id.boutonOptions)) {
          Intent intent = new Intent(getActivity(), Option.class);
          startActivity(intent);
        }
      }

      return true;
    }

  }

}
