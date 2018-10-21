package pe.leidy_cs.labcalificado3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import pe.leidy_cs.labcalificado3.R;
import pe.leidy_cs.labcalificado3.activities.MainActivity;
import pe.leidy_cs.labcalificado3.repository.NoteRepository;

public class RegisterNote extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText title_input;
    private EditText content_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);

        title_input = (EditText)findViewById(R.id.title_input);
        content_input = (EditText)findViewById(R.id.content_input);
    }

    public void callRegisterNote(View view){

        String titulo = title_input.getText().toString();
        String content = content_input.getText().toString();
        Date date = new Date();
        Boolean favorite = false;
        Boolean archivate = false;

        if(titulo.isEmpty() || content.isEmpty()){
            Toast.makeText(this, "Debe completar todo los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        NoteRepository.create(titulo, content, date, favorite, archivate);

        Toast.makeText(this, "Nota registrada correctamente", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);

        finish();
    }
}
