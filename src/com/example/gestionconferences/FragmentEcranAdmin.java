package com.example.gestionconferences;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentEcranAdmin extends Fragment {
  public final static String TAG = "fragmentEcranAdmin";
  View myFragmentView;
  Context context;

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    myFragmentView = inflater.inflate(R.layout.layout_ecran_admin, container, false);
    ajouterListenerBoutons();
    // context=MainActivity.getBaseContext();

    return myFragmentView;
  }

  public void ajouterListenerBoutons() {

    // Selection de tous les boutons
    // Button boutonCalendrier = (Button) findViewById(R.id.boutonCalendrier);

    Button boutonNouvelleConference =
        (Button) myFragmentView.findViewById(R.id.boutonNouvelleConference);
    // boutonNouvelleConference.setOnTouchListener(new MonBoutonClickListener());
    boutonNouvelleConference.setOnTouchListener(new View.OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN) {
          v.setBackgroundResource(R.drawable.rounded_button_clicked);

        }
        if (action == MotionEvent.ACTION_UP) {
          Intent intent = new Intent(getActivity(), NouvelleConferenceActivity.class);
          getActivity().startActivity(intent);
        }
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

        if (v == (Button) myFragmentView.findViewById(R.id.boutonNouvelleConference)) {

          Intent intent = new Intent(getActivity(), NouvelleConferenceActivity.class);
          startActivity(intent);
        }
      }

      return true;
    }

  }
}
