package sg.edu.rp.c346.id20045435.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd, btnClear, btnDelete;
    ListView lvThings;
    ArrayList<String> alThings;
    ArrayAdapter<String> aaThings;
    Spinner spnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvThings = findViewById(R.id.ListViewThings);
        spnTask = findViewById(R.id.spinner);

        alThings = new ArrayList<>();
        aaThings = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alThings);
        lvThings.setAdapter(aaThings);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etToDo.getText().toString();


                alThings.add(newTask);
                aaThings.notifyDataSetChanged();
                etToDo.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alThings.clear();
                aaThings.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alThings.size() == 0) {
                    Toast.makeText(MainActivity.this, getString(R.string.removeTask), Toast.LENGTH_LONG).show();
                    return;
                }
                int pos = Integer.parseInt(etToDo.getText().toString());

                if (pos > alThings.size()-1) {
                    Toast.makeText(MainActivity.this, getString(R.string.removeIndex), Toast.LENGTH_LONG).show();
                    return;
                }
                alThings.remove(pos);
                aaThings.notifyDataSetChanged();
                etToDo.setText("");
            }
        });

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etToDo.setHint(getString(R.string.hint1));
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etToDo.setHint(getString(R.string.hint2));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}