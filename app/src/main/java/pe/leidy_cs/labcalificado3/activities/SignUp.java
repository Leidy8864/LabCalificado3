package pe.leidy_cs.labcalificado3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.leidy_cs.labcalificado3.R;
import pe.leidy_cs.labcalificado3.repository.UserRepository;

public class SignUp extends AppCompatActivity {

    private EditText usernameInput;
    private EditText nameInput;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = (EditText)findViewById(R.id.username_input);
        nameInput = (EditText)findViewById(R.id.name_input);
        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
    }

    public void callRegisterUser(View view) {
        String username = usernameInput.getText().toString().trim();
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if(username.isEmpty() || name.isEmpty()|| email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Necesita completar los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.create(username, name, email, password);

        Toast.makeText(this, "Nota registrada correctamente", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);

        finish();
    }
}
