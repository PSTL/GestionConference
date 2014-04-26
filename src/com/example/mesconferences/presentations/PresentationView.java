package com.example.mesconferences.presentations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.gestionconferences.R;
import com.example.objetConference.Presentation;

public class PresentationView extends FragmentActivity {

  /** Variables needed to manipulate calendar */

  public static final String[] EVENT_PROJECTION = new String[] {Calendars._ID, // 0
      Calendars.ACCOUNT_NAME, // 1
      Calendars.CALENDAR_DISPLAY_NAME, // 2
      Calendars.OWNER_ACCOUNT// 3
      };

  /** end variable for calendar */

  private TextView title;
  private TextView programme;
  private TextView author;
  private TextView startDate;
  private TextView endDate;
  private TextView location;

  private Button addToCalendarButton;
  private OnClickListener calenderOnClickListener = null;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

  private final SimpleDateFormat minuteFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

  private StringBuilder sb = new StringBuilder();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.presentation_view);

    title = (TextView) findViewById(R.id.tvPresentation);
    programme = (TextView) findViewById(R.id.tvPresDesc);
    author = (TextView) findViewById(R.id.tvAuthorval);
    startDate = (TextView) findViewById(R.id.tvDatedebval);
    endDate = (TextView) findViewById(R.id.tvDatefinval);
    location = (TextView) findViewById(R.id.tvLieuval);
    addToCalendarButton = (Button) findViewById(R.id.addToCalendar);

    Intent intent = getIntent();
    final Presentation presentation =
        (Presentation) intent.getSerializableExtra("PresentationObject");

    title.setText(presentation.getProgramme());
    programme.setText(presentation.getProgramme());
    author.setText(presentation.getAuteur());

    Calendar cal = presentation.getDateDebut();
    sb.append(dateFormat.format(cal.getTime()));
    sb.append(" à ");
    sb.append(minuteFormat.format(cal.getTime()));
    startDate.setText(sb.toString());

    cal = presentation.getDateFin();
    sb = new StringBuilder();
    sb.append(dateFormat.format(cal.getTime()));
    sb.append(" à ");
    sb.append(minuteFormat.format(cal.getTime()));
    endDate.setText(sb.toString());

    location.setText(presentation.getLieu());

    // Add calendar listener
    calenderOnClickListener = new OnClickListener() {

      @Override
      public void onClick(View v) {
        Calendar begintime = Calendar.getInstance();
        begintime = presentation.getDateDebut();

        Calendar endtime = Calendar.getInstance();
        endtime = presentation.getDateFin();

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(Events.CONTENT_URI);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begintime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endtime.getTimeInMillis());
        intent.putExtra(Events.TITLE, presentation.getProgramme());
        intent.putExtra(Events.DESCRIPTION, presentation.getProgramme());
        intent.putExtra(Events.EVENT_LOCATION, presentation.getLieu());
        intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);

        startActivity(intent);

        // Calendar beginTime = Calendar.getInstance();
        // beginTime.set(2012, 0, 19, 7, 30);
        // Calendar endTime = Calendar.getInstance();
        // endTime.set(2012, 0, 19, 11, 30);
        // Intent intent =
        // new Intent(Intent.ACTION_INSERT).setData(Events.CONTENT_URI).setType(
        // "vnd.android.cursor.item/event").putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
        // beginTime.getTimeInMillis()).putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
        // endTime.getTimeInMillis()).putExtra(Events.TITLE, "Yoga").putExtra(
        // Events.DESCRIPTION, "Group class").putExtra(Events.EVENT_LOCATION, "The gym")
        // .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY).putExtra(
        // Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        // startActivity(intent);

      };

    };

    addToCalendarButton.setOnClickListener(calenderOnClickListener);

  }
}
