package dev.lab.electricbillcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutActivity extends AppCompatActivity {

    Button btnOpenCalc;
    TextView txtOpenGithub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnOpenCalc = findViewById(R.id.btnOpenCalc);
        txtOpenGithub = findViewById(R.id.txtOpenGithub);

        btnOpenCalc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Add the action to open the calculator here
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                // You might also want to finish the current activity so that when the user presses
                // the back button from the CalculatorActivity, it goes back to the previous activity.
                finish();
            }
        });

        txtOpenGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the website URL in a browser
                String url = "https://github.com/MuhammadFikri-main/MyElectricBill.git"; // Replace with your actual website URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
