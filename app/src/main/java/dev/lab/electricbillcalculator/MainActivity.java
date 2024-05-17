package dev.lab.electricbillcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCalcBill, btnClear;
    private EditText etUnits;
    private EditText etRebate;
    private TextView tvTotalCharges;
    private TextView tvFinalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUnits = findViewById(R.id.etUnits);
        etRebate = findViewById(R.id.etRebate);
        btnClear = findViewById(R.id.btnClear);
        btnCalcBill = findViewById(R.id.btnCalcBill);
        tvTotalCharges = findViewById(R.id.tvTotalCharges);
        tvFinalCost = findViewById(R.id.tvFinalCost);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        btnCalcBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUnits.length() == 0) {
                    etUnits.setError("Enter Value");
                } else if (etRebate.length() == 0) {
                    etRebate.setError("Enter Value of Percentage");
                } else {
                    calculateBill();
                    Toast.makeText(MainActivity.this, "Calculating Bill", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUnits.setText("");
                etRebate.setText("");
                tvTotalCharges.setText("Total Charges: RM 0.00");
                tvFinalCost.setText("Final Cost: RM 0.00");
                Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuAbout) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateBill() {
        int units = Integer.parseInt(etUnits.getText().toString());
        double rebate = Double.parseDouble(etRebate.getText().toString());
        if (rebate < 0 || rebate > 100) {
            etRebate.setError("Enter a valid percentage (0-100)");
            return;
        }
        rebate /= 100;

        double totalCharges;
        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            totalCharges = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        double finalCost = totalCharges - (totalCharges * rebate);
        tvTotalCharges.setText("Total Charges: RM " + String.format("%.2f", totalCharges));
        tvFinalCost.setText("Final Cost: RM " + String.format("%.2f", finalCost));
    }
}

