package com.example.gestionconferences;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
  String mFragment;
  FragmentEcranUtilisateur fragmentEcranUtilisateur;
  FragmentEcranAdmin fragmentEcranAdmin;

  private String accountName;
  public static boolean calenderCreated = false;

  public static final String MyPREFERENCES = "MyPrefs";
  private SharedPreferences sharedpreferences;
  private Editor editor;
  private boolean firstTime;
  long calid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupFragments();
    System.setProperty("org.joda.time.DateTimeZone.Provider",
        "com.example.objetConference.FastDateTimeZoneProvider");

    accountName = getAccountName();

    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    firstTime = sharedpreferences.getBoolean("First Time", true);

    calid = calenderId();

    if (firstTime || (calid == -1)) {
      editor = sharedpreferences.edit();
      editor.putBoolean("First Time", false);
      Toast.makeText(this, "first time", Toast.LENGTH_SHORT).show();

      // calenderCreated = sharedpreferences.getBoolean("ConferenceCalendar", false);
      // Toast.makeText(this, " " + calenderCreated, Toast.LENGTH_SHORT).show();

      calid = calenderId();
      Toast.makeText(this, " " + calid, Toast.LENGTH_SHORT).show();

      if (calid == -1) { // if shared prefs holds no calender values check the phone
        Toast.makeText(this, "entered  if", Toast.LENGTH_SHORT).show();
        calid = createCalendar();
      }

      Toast.makeText(this, "calid : " + calid, Toast.LENGTH_SHORT).show();
      editor.putBoolean("ConferenceCalendar", true);
      editor.putLong("CalendarID", calid);
      editor.commit();

      long id = sharedpreferences.getLong("CalendarID", -1);
      boolean b = sharedpreferences.getBoolean("ConferenceCalendar", false);
      Toast.makeText(this, id + ":" + b, Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "not first time", Toast.LENGTH_SHORT).show();
      long id = sharedpreferences.getLong("CalendarID", -1);
      boolean b = sharedpreferences.getBoolean("ConferenceCalendar", false);
      Toast.makeText(this, id + ":" + b, Toast.LENGTH_LONG).show();
    }

    // affichageAdmin(null);
    // affichageUtilisateur(null);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

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

  private long createCalendar() {
    ContentValues values = new ContentValues();
    values.put(Calendars.ACCOUNT_NAME, accountName);
    values.put(Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
    values.put(Calendars.NAME, "Conference Calendar");
    values.put(Calendars.CALENDAR_DISPLAY_NAME, "Conference Calendar");
    values.put(Calendars.CALENDAR_COLOR, 0xffff0000);
    values.put(Calendars.CALENDAR_ACCESS_LEVEL, Calendars.CAL_ACCESS_OWNER);
    values.put(Calendars.OWNER_ACCOUNT, accountName);
    values.put(Calendars.CALENDAR_TIME_ZONE, "Europe/Paris");
    values.put(Calendars.VISIBLE, 1);
    values.put(Calendars.SYNC_EVENTS, 1);

    Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
    builder.appendQueryParameter(Calendars.ACCOUNT_NAME, "com.conference.calendar");
    builder.appendQueryParameter(Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
    builder.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true");
    Uri uri = getContentResolver().insert(builder.build(), values);
    return Long.parseLong(uri.getLastPathSegment());
  }

  @SuppressWarnings("unused")
  private long getCalendarId() {
    String[] projection = new String[] {Calendars._ID};
    String selection =
        "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" + Calendars.ACCOUNT_TYPE + " = ?))";

    String[] selArgs = new String[] {accountName, CalendarContract.ACCOUNT_TYPE_LOCAL};
    Cursor cursor =
        getContentResolver().query(Calendars.CONTENT_URI, projection, selection, selArgs, null);
    if (cursor.moveToFirst()) {
      return cursor.getLong(0);
    }
    return -1;
  }

  private long calenderId() {
    Cursor cursor =
        getContentResolver().query(Uri.parse("content://com.android.calendar/calendars"),
            new String[] {"_id", "calendar_displayName"}, null, null, null);

    cursor.moveToFirst();
    // fetching calendars name
    String names[] = new String[cursor.getCount()];
    // fetching calendars id
    int[] id = new int[cursor.getCount()];
    for (int i = 0; i < names.length; i++) {
      id[i] = cursor.getInt(0);
      names[i] = cursor.getString(1);
      if (names[i].equalsIgnoreCase("Conference Calendar")) {
        return id[i];
      }
      cursor.moveToNext();
    }

    return -1;
  }

  private String getAccountName() {
    AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
    Account[] list = manager.getAccounts();
    String gmail = null;

    for (Account account : list) {
      if (account.type.contains("com.google")) {
        gmail = account.name;
        break;
      }
    }
    return gmail;
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
