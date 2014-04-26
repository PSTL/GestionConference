package com.example.gestionconferences;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.objetConference.Presentation;

public class CalendrierActivity extends Activity {

  public int numeroMois = 4;
  int annee = 2014;
  public String[] mois = new String[12];
  public boolean clicGauche = false;
  public ArrayList<Presentation> listePresentations = new ArrayList<Presentation>();

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_ecran_calendrier);

    mois[0] = getResources().getString(R.string.janvier);
    mois[1] = getResources().getString(R.string.Fevrier);
    mois[2] = getResources().getString(R.string.Mars);
    mois[3] = getResources().getString(R.string.Avril);
    mois[4] = getResources().getString(R.string.Mai);
    mois[5] = getResources().getString(R.string.Juin);
    mois[6] = getResources().getString(R.string.Juillet);
    mois[7] = getResources().getString(R.string.Aout);
    mois[8] = getResources().getString(R.string.Septembre);
    mois[9] = getResources().getString(R.string.Octobre);
    mois[10] = getResources().getString(R.string.Novembre);
    mois[11] = getResources().getString(R.string.Decembre);

    Calendar c = Calendar.getInstance();
    numeroMois = c.get(Calendar.MONTH);
    annee = c.get(Calendar.YEAR);

    miseAjourMois();
    ajouterListenersFleches();

    // Calendar d = new DateTime(2014,3,2,10,30);
    //
    // Calendar df =new DateTime(2014,3,2,11,30);

    Calendar d = Calendar.getInstance();
    d.set(2014, 3, 2, 10, 30);

    Calendar df = Calendar.getInstance();
    df.set(2014, 3, 2, 11, 30);

    listePresentations.add(new Presentation("les nouvelles technos du web", "facebook", "amphi 15",
        d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));
    listePresentations.add(new Presentation("le meilleur des navigateurs internet", "Microsoft",
        "amphi 25", d, df));

  }

  public void ajouterListenersFleches() {
    ImageView flecheGauche = (ImageView) findViewById(R.id.flecheGaucheCalendrier);
    flecheGauche.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        // Toast.makeText(CalendrierActivity.this,getResources().getString(R.string.janvier),
        // Toast.LENGTH_SHORT).show();
        numeroMois--;
        if (numeroMois < 0) {
          numeroMois = 11;
          annee--;
        }
        clicGauche = true;
        miseAjourMois();
        miseAjourPresentations();

      }
    });

    ImageView flecheDroite = (ImageView) findViewById(R.id.flecheDroiteCalendrier);
    flecheDroite.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        numeroMois++;
        if (numeroMois > 11) {
          numeroMois = 0;
          annee++;
        }
        clicGauche = false;
        // ajouterPresentation("3", "14h00", "amphi 15", "Les nouveautés d'android", "Google");
        miseAjourMois();
        miseAjourPresentations();
      }
    });

  }

  public void recupererPresentationsDuMois() {
  }

  public void miseAjourMois() {
    TextView textViewmois = (TextView) findViewById(R.id.textViewMois);
    textViewmois.setText(mois[numeroMois]);

    Animation logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotation);
    textViewmois.startAnimation(logoMoveAnimation);
  }

  public void miseAjourPresentations() {
    LinearLayout presentations =
        (LinearLayout) findViewById(R.id.linearLayoutPresentationsCalendrier);
    presentations.removeAllViewsInLayout();

    for (Presentation p : listePresentations) {
      ajouterPresentation(p.getDateDebut().get(Calendar.DAY_OF_MONTH) + "", p.getDateDebut().get(
          Calendar.HOUR_OF_DAY)
          + "h" + p.getDateDebut().get(Calendar.MINUTE), p.getLieu(), p.getProgramme(), p
          .getAuteur());
    }

  }

  private void ajouterPresentation(String date, String heureDebut, String lieu, String titre,
      String auteur) {

    LinearLayout presentation = new LinearLayout(this);
    presentation.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.WRAP_CONTENT));
    presentation.setPadding(0, 20, 0, 0);
    LinearLayout detailPresentation = new LinearLayout(this);
    detailPresentation.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.WRAP_CONTENT));

    detailPresentation.setOrientation(LinearLayout.VERTICAL);
    presentation.addView(detailPresentation);

    TextView textViewPresentationPrevue = new TextView(this);
    textViewPresentationPrevue.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.WRAP_CONTENT));
    textViewPresentationPrevue.setGravity(Gravity.CENTER);
    textViewPresentationPrevue.setText("Le " + date + ", présentation prévue à " + heureDebut
        + " en " + lieu);
    detailPresentation.addView(textViewPresentationPrevue);

    TextView textViewTitrePresentation = new TextView(this);
    textViewTitrePresentation.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.WRAP_CONTENT));
    textViewTitrePresentation.setGravity(Gravity.CENTER);
    textViewTitrePresentation.setText(titre);
    detailPresentation.addView(textViewTitrePresentation);

    TextView textViewAuteur = new TextView(this);
    textViewAuteur.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.WRAP_CONTENT));
    textViewAuteur.setGravity(Gravity.CENTER);
    textViewAuteur.setText("par : " + auteur);
    detailPresentation.addView(textViewAuteur);

    TextView separateur = new TextView(this);
    LayoutParams lparams3 = new LayoutParams(LayoutParams.MATCH_PARENT, 5);
    separateur.setLayoutParams(lparams3);
    separateur.setBackgroundColor(Color.BLACK);
    separateur.setText(" ");
    detailPresentation.addView(separateur);

    LinearLayout presentations =
        (LinearLayout) findViewById(R.id.linearLayoutPresentationsCalendrier);
    presentations.addView(presentation);

    Animation logoMoveAnimation;
    // Animation
    if (clicGauche)
      logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_gauche);
    else
      logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_droite);

    presentation.startAnimation(logoMoveAnimation);

  }

}
