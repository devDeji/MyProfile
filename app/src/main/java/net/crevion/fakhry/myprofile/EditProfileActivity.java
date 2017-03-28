package net.crevion.fakhry.myprofile;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutName, inputLayoutTelp, inputLayoutLink, inputLayoutEmail;
    private EditText inputName, inputTelp, inputLink, inputEmail;
    private String nama, telp, link, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        telp = intent.getStringExtra("telp");
        link = intent.getStringExtra("link");
        email = intent.getStringExtra("email");

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutTelp = (TextInputLayout) findViewById(R.id.input_layout_telp);
        inputLayoutLink = (TextInputLayout) findViewById(R.id.input_layout_link);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);

        inputName = (EditText) findViewById(R.id.input_name);
        inputTelp = (EditText) findViewById(R.id.input_telp);
        inputLink = (EditText) findViewById(R.id.input_link);
        inputEmail = (EditText) findViewById(R.id.input_email);

        inputName.setText(nama);
        inputTelp.setText(telp);
        inputLink.setText(link);
        inputEmail.setText(email);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));

    }

    public void saveBtnClick(View view) {
        if (!validateName()) {
            return;
        }

        if (!validateTelp()) {
            return;
        }

        if (!validateLink()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("nama", inputName.getText().toString());
        returnIntent.putExtra("telp", inputTelp.getText().toString());
        returnIntent.putExtra("link", inputLink.getText().toString());
        returnIntent.putExtra("email", inputEmail.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

//        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTelp() {
        if (inputTelp.getText().toString().trim().isEmpty()) {
            inputLayoutTelp.setError(getString(R.string.err_msg_telp));
            requestFocus(inputTelp);
            return false;
        } else {
            inputLayoutTelp.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLink() {
        if (inputLink.getText().toString().trim().isEmpty()) {
            inputLayoutLink.setError(getString(R.string.err_msg_link));
            requestFocus(inputLink);
            return false;
        } else {
            inputLayoutLink.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_telp:
                    validateTelp();
                    break;
                case R.id.input_link:
                    validateLink();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
            }
        }
    }
}
