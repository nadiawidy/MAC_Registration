package id.sch.smktelkom_mlg.learn.macregistrationform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText isinama;
    RadioButton rbL, rbP;
    Button bOK;
    TextView tvHasil, tvJk;
    CheckBox cbD, cbA, cbT;
    Spinner spJurusan, spKelas;
    String arKelas[][] = {{"X-RPL1", "X-RPL2", "X-RPL3", "X-RPL4", "X-RPL5", "X-RPL6"},
            {"X-TKJ1", "X-TKJ2", "X-TKJ3", "X-TKJ4", "X-TKJ5", "X-TKJ6"}};
    ArrayList<String> listKelas = new ArrayList<>();
    ArrayAdapter<String> adapter;

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
        spJurusan = (Spinner) findViewById(R.id.spinnerJurusan);
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        cbD = (CheckBox) findViewById(R.id.checkBoxDance);
        cbA = (CheckBox) findViewById(R.id.checkBoxAkustik);
        cbT = (CheckBox) findViewById(R.id.checkBoxTeather);

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKelas.setAdapter(adapter);

        spJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKelas.clear();
                listKelas.addAll(Arrays.asList(arKelas[pos]));
                adapter.notifyDataSetChanged();
                spKelas.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

            //spinner
            String kelas = spKelas.getSelectedItem().toString();

            //checkbox
            String bidang = "Object Of Interest in MAC :\n";
            int startlen = bidang.length();
            if (cbD.isChecked()) bidang += cbD.getText() + "\n";
            if (cbA.isChecked()) bidang += cbA.getText() + "\n";
            if (cbT.isChecked()) bidang += cbT.getText() + "\n";

            if (bidang.length() == startlen) bidang += "(No object was choosen)";

            //hasil
            tvHasil.setText("Name        : " + nama + "\n" + "Gender      : " + jk + "\n"
                    + "Class         : " + kelas + "\n" + bidang);
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
