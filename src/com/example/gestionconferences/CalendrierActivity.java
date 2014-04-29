package com.example.gestionconferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.objetConference.Presentation;

public class CalendrierActivity extends Activity {

	public int numeroMois = 4;
	int annee = 2014;
	public String[] mois = new String[12];
	public boolean clicGauche = false;
	public static ArrayList<Presentation> listePresentations = new ArrayList<Presentation>();




	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<String> startDates = new ArrayList<String>();
	public static ArrayList<String> endDates = new ArrayList<String>();
	public static ArrayList<String> descriptions = new ArrayList<String>();
	public  ArrayList<Calendar> dateDeb = new ArrayList<Calendar>();
	public static ArrayList<Calendar> dateFin = new ArrayList<Calendar>();






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
				//readCalendarEvent(CalendrierActivity.this);
				recupererPresentationsDuMois(CalendrierActivity.this,numeroMois+1);


				int i=0;
				CalendrierActivity.listePresentations.clear();
				for(String pres : nameOfEvent){

					//ajouterPresentation(startDates.get(i), " ", " ", nameOfEvent.get(i), descriptions.get(i));
					Presentation p = new Presentation(nameOfEvent.get(i),descriptions.get(i),"",getCalendarFromLong(startDates.get(i)),getCalendarFromLong(startDates.get(i)));
					// Toast.makeText(CalendrierActivity.this,dateDeb.get(i).HOUR_OF_DAY,Toast.LENGTH_SHORT).show();
					listePresentations.add(p);
					i++;
				}


				//        Toast.makeText(CalendrierActivity.this,nameOfEvent.get(0)+"",Toast.LENGTH_SHORT).show();
				//        Toast.makeText(CalendrierActivity.this,nameOfEvent.get(1)+"",Toast.LENGTH_SHORT).show();
				// ajouterPresentation("3", "14h00", "amphi 15", "Les nouveautés d'android", "Google");
				miseAjourMois();
				miseAjourPresentations();
			}
		});

	}


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

	public  ArrayList<String> readCalendarEvent(Context context) {
		Cursor cursor = context.getContentResolver()
				.query(
						Uri.parse("content://com.android.calendar/events"),
						new String[] { "calendar_id", "title", "description",
							"dtstart", "dtend", "eventLocation" }, null,
							null, null);
		cursor.moveToFirst();
		// fetching calendars name
		String CNames[] = new String[cursor.getCount()];

		// fetching calendars id
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();
		for (int i = 0; i < CNames.length; i++) {

			nameOfEvent.add(cursor.getString(1));
			startDates.add(getDate(Long.parseLong(cursor.getString(3))));
			//dateDeb.add(getCalendarFromLong(cursor.getString(3)));



			//endDates.add(getDate(Long.parseLong(cursor.getString(4))));
			descriptions.add(cursor.getString(2));
			CNames[i] = cursor.getString(1);
			cursor.moveToNext();

		}
		Toast.makeText(CalendrierActivity.this,startDates.get(155).substring(6,10),Toast.LENGTH_SHORT).show();
		return nameOfEvent;
	}





	public static String getDate(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss a");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	public static String getMois(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}
	
	public static String getAnnee(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}


	public static Date getFormatDate(String string) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(
				"yyyy-MMM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat(
				"yyyy-MMM-dd HH:mm:ss");

		Date temp = new Date(string);

		try {
			try {
				return dateFormatLocal.parse(dateFormatGmt.format(temp));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	} 

	public static Calendar getCalendarFromLong(String m){

		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar cal  = Calendar.getInstance();
		try {
			cal.setTime(df.parse(m));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;
	}













	public void recupererPresentationsDuMois(Context context,int mois) {

		Cursor cursor = context.getContentResolver()
				.query(
						Uri.parse("content://com.android.calendar/events"),
						new String[] { "calendar_id", "title", "description",
							"dtstart", "dtend", "eventLocation" }, null,
							null, null);
		cursor.moveToFirst();
		// fetching calendars name
		String CNames[] = new String[cursor.getCount()];

		// fetching calendars id
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();




		// for (int i = 0; i < CNames.length; i++) {
		while(cursor.moveToNext()){

			/*if(Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(6,10)) <annee){
		    		cursor.moveToNext();
		    		continue;
		    	}


		    	if(Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(3,5)) <mois){
		    		cursor.moveToNext();
		    		continue;
		    	}*/


			//Toast.makeText(CalendrierActivity.this,CalendrierActivity.getDate(Long.parseLong(cursor.getString(3))),Toast.LENGTH_SHORT).show();


			/*if(Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(3,5)) !=mois  &&
		    			Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(6,10)) !=2013
		    			&& Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(6,10)) !=annee){
		    		//cursor.moveToNext();
		    		Toast.makeText(CalendrierActivity.this,"Ca rentre",Toast.LENGTH_SHORT).show();

		    		continue;
		    	}*/
			
			
			if(Integer.parseInt(getAnnee(Long.parseLong(cursor.getString(3)))) != annee   ){
				continue;
			}
			if(Integer.parseInt(getMois(Long.parseLong(cursor.getString(3)))) != mois   ){
				//Toast.makeText(CalendrierActivity.this,Integer.parseInt(getAnnee(Long.parseLong(cursor.getString(3)))) +" "+annee,Toast.LENGTH_SHORT).show();
				continue;
			}




			//Toast.makeText(CalendrierActivity.this,Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(3,5))+"  "+   mois,Toast.LENGTH_SHORT).show();

			// FAUX CAR apparemment , le curseur fait un parcours aléatoire 
			/*if(Integer.parseInt(getDate(Long.parseLong(cursor.getString(3))).substring(6,10)) >annee){
		    		break;
		    	}*/



			nameOfEvent.add(cursor.getString(1));
			startDates.add(getDate(Long.parseLong(cursor.getString(3))));
			//dateDeb.add(getCalendarFromLong(cursor.getString(3)));



			//endDates.add(getDate(Long.parseLong(cursor.getString(4))));
			descriptions.add(cursor.getString(2));
			//CNames[i] = cursor.getString(1);
			// cursor.moveToNext();

		}

		//		    Toast.makeText(CalendrierActivity.this,startDates.get(1),Toast.LENGTH_SHORT).show();
		//		    Toast.makeText(CalendrierActivity.this,startDates.get(2),Toast.LENGTH_SHORT).show();
		//		    Toast.makeText(CalendrierActivity.this,startDates.get(3),Toast.LENGTH_SHORT).show();
		//		    Toast.makeText(CalendrierActivity.this,startDates.get(4),Toast.LENGTH_SHORT).show();
		//		    Toast.makeText(CalendrierActivity.this,startDates.get(5),Toast.LENGTH_SHORT).show();





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

		for (int i = 0;i<listePresentations.size();i++){// p : listePresentations) {
			//    	int jour =p.jourDebut();
			//    	int heure = p.getDateDebut().HOUR_OF_DAY;
			//    	int minute = p.getDateDebut().MINUTE;

			int jour =listePresentations.get(i).jourDebut();
			int heure = listePresentations.get(i).getDateDebut().HOUR_OF_DAY;
			int minute = listePresentations.get(i).getDateDebut().MINUTE;

			//      ajouterPresentation(jour + "", heure
			//          + "h" + minute, p.getLieu(), p.getProgramme(), p
			//          .getAuteur());


			//    	 ajouterPresentation(jour + "", heure
			//    	          + "h" + minute, listePresentations.get(i).getLieu(), listePresentations.get(i).getProgramme(),
			//    	          listePresentations.get(i).getAuteur());
			heure = Integer.parseInt(startDates.get(i).substring(11,13));
			boolean AMouPM= startDates.get(i).substring(startDates.get(i).length()-2,startDates.get(i).length()).equals("PM");
			if(AMouPM) heure+=12;
			//Toast.makeText(CalendrierActivity.this,startDates.get(i).substring(startDates.get(i).length()-3,startDates.get(i).length()-1),Toast.LENGTH_SHORT).show();
			//Toast.makeText(CalendrierActivity.this,startDates.get(i),Toast.LENGTH_SHORT).show();

			ajouterPresentation(startDates.get(i).substring(0,2),heure+"h", listePresentations.get(i).getLieu(), listePresentations.get(i).getProgramme(),
					listePresentations.get(i).getAuteur());

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
