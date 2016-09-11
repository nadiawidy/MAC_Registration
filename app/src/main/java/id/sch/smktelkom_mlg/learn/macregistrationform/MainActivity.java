package id.sch.smktelkom_mlg.learn.macregistrationform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText isinama;
    RadioButton rbL, rbP;
    Button bOK;
    TextView tvHasil, tvJk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isinama = (EditText) findViewById(R.id.editTextNama);
        rbL = (RadioButton) findViewById(R.id.radioButtonLaki);
        rbP = (RadioButton) findViewById(R.id.radioButtonPerempuan);
        bOK = (Button) findViewById(R.id.buttonSubmit);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvJk = (TextView) findViewById(R.id.textView2);

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });
    }

    private void doClick() {
        if (isValid()) {
            //edit text
            String nama = isinama.getText().toString();

            //radio button
            String jk = "(Not choosen)";

            if (rbL.isChecked()) {
                jk = rbL.getText().toString();
            } else if (rbP.isChecked()) {
                jk = rbP.getText().toString();
            }

            //hasil
            tvHasil.setText("Name        : " + nama + "\n" + "Gender      : " + jk + "\n");

        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = isinama.getText().toString();
        String jk = "";

        if (nama.isEmpty()) {
            isinama.setError("Name must be filled!");
            valid = false;
        } else if (nama.length() < 5) {
            isinama.setError("Name min have 5 characters");
            valid = false;
        } else {
            isinama.setError(null);
        }

        if (jk == null) {
            tvJk.setError("");
            valid = false;
        } else {
            tvJk.setError(null);
        }
        return valid;
    }

}
