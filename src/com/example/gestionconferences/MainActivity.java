package com.example.gestionconferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
  String mFragment;
  FragmentEcranUtilisateur fragmentEcranUtilisateur;
  FragmentEcranAdmin fragmentEcranAdmin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupFragments();
    System.setProperty("org.joda.time.DateTimeZone.Provider",
        "com.example.objetConference.FastDateTimeZoneProvider");
    // affichageAdmin(null);
    // affichageUtilisateur(null);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @SuppressLint("NewApi")
  private void setupFragments() {
    final FragmentManager fm = getSupportFragmentManager();

    this.fragmentEcranUtilisateur =
        (FragmentEcranUtilisateur) fm.findFragmentByTag(FragmentEcranUtilisateur.TAG);

    if (this.fragmentEcranUtilisateur == null) {
      this.fragmentEcranUtilisateur = new FragmentEcranUtilisateur();
    }
    // this.fragmentEcranAdmin.context=this.getBaseContext();

    this.fragmentEcranAdmin = (FragmentEcranAdmin) fm.findFragmentByTag(FragmentEcranAdmin.TAG);
    if (this.fragmentEcranAdmin == null) {
      this.fragmentEcranAdmin = new FragmentEcranAdmin();
    }
    showFragment(this.fragmentEcranUtilisateur);
    // Button b = (Button) findViewById(R.id.boutonCalendrier);

  }

  @SuppressLint("NewApi")
  public void showFragment(final Fragment fragment) {
    if (fragment == null)
      return;

    final FragmentManager fm = getSupportFragmentManager();
    final FragmentTransaction ft = fm.beginTransaction();
    // ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    ft.replace(R.id.frame, fragment);
    ft.addToBackStack(null);
    ft.commit();
    ajouterListenerBoutons();

  }

  public void affichageAdmin(View v) {

    showFragment(this.fragmentEcranAdmin);
  }

  public void affichageUtilisateur(View v) {

    showFragment(this.fragmentEcranUtilisateur);

  }

  public void ajouterListenerBoutons() {

    // Selection de tous les boutons
    // Button boutonCalendrier = (Button) findViewById(R.id.boutonCalendrier);
    // boutonCalendrier.setOnTouchListener(new MonBoutonClickListener());
    // Button boutonMesConferences = (Button) findViewById(R.id.boutonMesConferences);
    // boutonMesConferences.setOnTouchListener(new MonBoutonClickListener());
    // Button boutonNouvelleConference = (Button) findViewById(R.id.boutonNouvelleConference);
    // boutonNouvelleConference.setOnTouchListener(new MonBoutonClickListener());
    // Button boutonConferenceProche = (Button) findViewById(R.id.boutonConferenceProche);
    // boutonConferenceProche.setOnTouchListener(new MonBoutonClickListener());
    // Button boutonOptions = (Button) findViewById(R.id.boutonOptions);
    // boutonOptions.setOnTouchListener(new MonBoutonClickListener());

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

        if (v == (Button) findViewById(R.id.boutonCalendrier)) {

          Intent i = new Intent();
          i.setClassName("com.android.calendar", "com.android.calendar.AgendaActivity");
          startActivity(i);

        }/*
          * else if(v==(Button) findViewById(R.id.boutonNouvelleConference)){ Intent intent = new
          * Intent(MainActivity.this,NouvelleConferenceActivity.class); startActivity(intent); }
          */
      }

      return true;
    }

  }

  /**
   * Back button listener. Will close the application if the back button pressed twice.
   * 
   * public void onBackPressed() { if(backButtonCount >= 1) { Intent intent = new
   * Intent(Intent.ACTION_MAIN); intent.addCategory(Intent.CATEGORY_HOME);
   * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(intent); } else {
   * Toast.makeText(this, "Press the back button once again to close the application.",
   * Toast.LENGTH_SHORT).show(); backButtonCount++; } }
   */
}
