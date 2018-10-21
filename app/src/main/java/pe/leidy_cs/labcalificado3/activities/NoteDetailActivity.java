package pe.leidy_cs.labcalificado3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import pe.leidy_cs.labcalificado3.R;
import pe.leidy_cs.labcalificado3.models.Note;
import pe.leidy_cs.labcalificado3.repository.NoteRepository;

public class NoteDetailActivity extends AppCompatActivity {

    private static final String TAG = NoteDetailActivity.class.getSimpleName();

    private Long id;

    private TextView titleText;
    private TextView contentText;
    private CheckBox favoriteStar;
    private CheckBox archiveClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Enable back button with onSupportNavigateUp method

        titleText = (TextView)findViewById(R.id.title_text);
        contentText = (TextView)findViewById(R.id.content_text);
        favoriteStar = (CheckBox)findViewById(R.id.favorite_star);
        archiveClick = (CheckBox)findViewById(R.id.archivar_click);

        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "ID: " + id);

        // Get Note by ID from Repository
        Note note = NoteRepository.read(id);

        titleText.setText(note.getTitle());
        contentText.setText(note.getContent());
        if(note.getFavorite()){
            favoriteStar.setVisibility(View.VISIBLE);   // Hidden view
        }else{
            favoriteStar.setVisibility(View.GONE);  // Show view
        }

        if(note.getArchivate()){
            archiveClick.setVisibility(View.VISIBLE);   // Hidden view
        }else{
            archiveClick.setVisibility(View.GONE);  // Show view
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
