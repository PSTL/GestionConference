import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gestionconferences.CalendrierActivity;
import com.example.gestionconferences.Option;
import com.example.gestionconferences.R;
import com.example.mesconferences.conferences.MesConferencesActivity;

public class FragmentEcranUtilisateur extends Fragment {

  public void ajouterListenerBoutons() {
    original();
    
    boutonOptions.setOnTouchListener(new OptionClickListener());
    
  }
  
  public class OptionClickListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {

      int action = event.getActionMasked();

      if (action == MotionEvent.ACTION_DOWN) {
        v.setBackgroundResource(R.drawable.rounded_button_clicked);

      }
      if (action == MotionEvent.ACTION_UP) {
        v.setBackgroundResource(R.drawable.rounded_button);

        if (v == (Button) myFragmentView.findViewById(R.id.boutonOptions)) {
          Intent intent = new Intent(getActivity(), Option.class);
          startActivity(intent);
        }
      }

      return true;
    }

  }

}
