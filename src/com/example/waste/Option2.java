package com.example.waste;
// package com.example.gestionconferences;
//
// import java.util.Locale;
//
// import android.app.Activity;
// import android.content.res.Configuration;
// import android.os.Bundle;
// import android.view.View;
// import android.view.View.OnClickListener;
// import android.widget.Button;
// import android.widget.RadioGroup;
// import android.widget.RadioGroup.OnCheckedChangeListener;
// import android.widget.Toast;
//
// public class Option2 extends Activity implements OnCheckedChangeListener, OnClickListener {
//
// private RadioGroup languages;
// private Button button;
// private Locale myLocale;
//
// private String lang = "en";
//
// @Override
// public void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.option);
//
// Button btn = (Button) findViewById(R.id.valideLanguage);
// btn.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // change language by onclick a button
// Configuration newConfig = new Configuration();
// newConfig.locale = Locale.FRENCH;
// onConfigurationChanged(newConfig);
// }
// });
// }
//
// @Override
// public void onConfigurationChanged(Configuration newConfig) {
// super.onConfigurationChanged(newConfig);
//
// getBaseContext().getResources().updateConfiguration(newConfig,
// getBaseContext().getResources().getDisplayMetrics());
// setContentView(R.layout.option);
// setTitle(R.string.app_name);
//
// // Checks the active language
// if (newConfig.locale == Locale.ENGLISH) {
// Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
// } else if (newConfig.locale == Locale.FRENCH) {
// Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
// }
// }
//
// // @Override
// // protected void onCreate(Bundle savedInstanceState) {
// // super.onCreate(savedInstanceState);
// // setContentView(R.layout.option);
// // lang = "en";
// //
// // languages = (RadioGroup) findViewById(R.id.languageRadioGroup);
// // button = (Button) findViewById(R.id.valideLanguage);
// //
// // languages.setOnCheckedChangeListener(this);
// // button.setOnClickListener(this);
// // loadLocale();
// //
// // }
// //
// // public void loadLocale() {
// // String langPref = "Language";
// // SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
// // String language = prefs.getString(langPref, "");
// // changeLang(language);
// // }
// //
// // public void saveLocale(String lang) {
// // String langPref = "Language";
// // SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
// // SharedPreferences.Editor editor = prefs.edit();
// // editor.putString(langPref, lang);
// // editor.commit();
// // }
// //
// // public void changeLang(String lang) {
// // if (lang.equalsIgnoreCase(""))
// // return;
// // myLocale = new Locale(lang);
// // saveLocale(lang);
// // Locale.setDefault(myLocale);
// // android.content.res.Configuration config = new android.content.res.Configuration();
// // config.locale = myLocale;
// // getBaseContext().getResources().updateConfiguration(config,
// // getBaseContext().getResources().getDisplayMetrics());
// // Toast.makeText(getApplicationContext(), lang, Toast.LENGTH_SHORT).show();
// //
// // }
// //
// // @Override
// // public void onConfigurationChanged(Configuration newConfig) {
// // super.onConfigurationChanged(newConfig);
// //
// // if (myLocale != null) {
// // newConfig.locale = myLocale;
// // Locale.setDefault(myLocale);
// // getBaseContext().getResources().updateConfiguration(newConfig,
// // getBaseContext().getResources().getDisplayMetrics());
// // }
// // }
// //
// // @Override
// // public void onCheckedChanged(RadioGroup group, int checkedId) {
// // if (checkedId == R.id.rbEnglish) {
// // lang = "en";
// // } else if (checkedId == R.id.rbFrench) {
// // lang = "fr";
// // }
// // changeLang(lang);
// // }
// //
// // @Override
// // public void onClick(View v) {
// //
// // }
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void onCheckedChanged(RadioGroup group, int checkedId) {
// // TODO Auto-generated method stub
//
// }
// }
