package fr.unice.mbds.maslow.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Utilisateur;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AQuery aq = new AQuery(this);

        txtUsername = (AutoCompleteTextView) aq.id(R.id.activity_login_email).getTextView();
        txtPassword = aq.id(R.id.activity_login_password).getEditText();

        aq.id(R.id.activity_login_sign_in_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(txtUsername.getText().toString()) && !"".equals(txtPassword.getText().toString())) {
                    new UserLoginTask(txtUsername.getText().toString(), txtPassword.getText().toString()).execute();
                }
            }
        });
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Utilisateur> {

        private String username;
        private String password;
        private ProgressDialog progress;

        UserLoginTask(String username, String password) {
            this.username = username;
            this.password = password;
            progress = setProgressDialog();
        }

        @Override
        protected Utilisateur doInBackground(Void... params) {

            ResponseEntity result = null;

            JSONObject credentials = new JSONObject();
            try {
                credentials.put("identifiant", username);
                credentials.put("password", password);
            } catch (JSONException e) {
                Log.e("json parsing", e.getMessage());
            }

            try {
                result = ApiCallService.getInstance().executeForJson(ApiUrlService.AUTH_URL, HttpMethod.POST, credentials, Utilisateur.class);
            } catch (Exception e) {
                Log.e("GET REST", e.getMessage());
            }

            Utilisateur utilisateur = null;

            if (result != null) {
                utilisateur = (Utilisateur) result.getBody();
            }

            return utilisateur;
        }

        @Override
        protected void onPostExecute(final Utilisateur utilisateur) {
            progress.hide();

            if (utilisateur == null) {
                Toast.makeText(LoginActivity.this, "Identifiants erronés", Toast.LENGTH_LONG).show();
            } else {
                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("identifiant", utilisateur.getIdentifiant());
                editor.putString("token", utilisateur.getToken());
                editor.putString("nom", utilisateur.getNom());
                editor.putString("prenom", utilisateur.getNom());

                editor.apply();

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(i);
            }
        }
    }

    private ProgressDialog setProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Patientez...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }
}

