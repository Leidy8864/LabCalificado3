package pe.leidy_cs.labcalificado3.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import pe.leidy_cs.labcalificado3.R;
import pe.leidy_cs.labcalificado3.models.Note;
import pe.leidy_cs.labcalificado3.models.User;
import pe.leidy_cs.labcalificado3.repository.NoteRepository;
import pe.leidy_cs.labcalificado3.repository.UserRepository;

public class SignIn extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private ProgressBar progressBar;

    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private View loginPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        loginPanel = findViewById(R.id.login_panel);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goListNote();
        }

    }

    public void callLogInUser(View view) {
        loginPanel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Necesita completar los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        List<User> users = UserRepository.list();
        for (User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                Intent i = new Intent(SignIn.this, ListNoteActivity.class);
                i.putExtra("name", user.getName());
                startActivity(i);
            }else{
                Toast.makeText(this, "Campos incorrectos", Toast.LENGTH_SHORT).show();
                loginPanel.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                return;
            }

            Toast.makeText(this, "Bienvenido " + user.getName(), Toast.LENGTH_SHORT).show();

            // Save to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean success = editor
                    .putString("username", user.getUsername())
                    .putBoolean("islogged", true)
                    .commit();

            // Go to Dashboard
            goListNote();
        }
    }

    private void goListNote(){
        startActivity(new Intent(this, ListNoteActivity.class));
        finish();
    }
}
