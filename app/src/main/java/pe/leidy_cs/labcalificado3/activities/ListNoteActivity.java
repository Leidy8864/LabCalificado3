package pe.leidy_cs.labcalificado3.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.leidy_cs.labcalificado3.R;
import pe.leidy_cs.labcalificado3.adapter.NoteAdapter;
import pe.leidy_cs.labcalificado3.models.User;
import pe.leidy_cs.labcalificado3.repository.NoteRepository;
import pe.leidy_cs.labcalificado3.models.Note;
import pe.leidy_cs.labcalificado3.repository.UserRepository;

public class ListNoteActivity extends AppCompatActivity {

    private static final String TAG = ListNoteActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    private RecyclerView notesList;
    private TextView bienvenida;

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);

        bienvenida = (TextView)findViewById(R.id.bienvenida);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);
        bienvenida.setText("Bienvenido(a) "+user.getName());

        notesList = (RecyclerView) findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(this));

        List<Note> notes = NoteRepository.list();
        notesList.setAdapter(new NoteAdapter(notes));

        //menu inferior
        BottomNavigationView bottomNavigationView  = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(ListNoteActivity.this, "Go home section...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_favoritos:
                        Toast.makeText(ListNoteActivity.this, "Go favoritos section...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_archivados:
                        Toast.makeText(ListNoteActivity.this, "Go favoritos section...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_salir:
                        showDialog();
                        break;
                }
                return true;
            }
        });

        refreshData();
    }

    public void refreshData(){
        NoteAdapter adapter = (NoteAdapter) notesList.getAdapter();
        List<Note> notes = NoteRepository.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged(); // Refrezca los cambios en el RV
    }

    public void showRegister(View view) {
        startActivityForResult(new Intent(this, RegisterNote.class), REGISTER_FORM_REQUEST);
    }

    // return from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        NoteAdapter adapter = (NoteAdapter)notesList.getAdapter();

        List<Note> notes = NoteRepository.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }

    public void showDialog(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog);
        // Custom Android Allert Dialog Title
        dialog.setTitle("Custom Dialog Example");

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
        Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);
        // Click cancel to dismiss android custom dialog box
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Your android custom dialog ok action
        // Action for custom dialog ok button click
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                boolean success = editor.putBoolean("islogged", false).commit();
                //boolean success = editor.clear().commit(); // not recommended
                Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }

}
