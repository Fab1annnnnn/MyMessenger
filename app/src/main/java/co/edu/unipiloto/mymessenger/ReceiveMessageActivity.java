package co.edu.unipiloto.mymessenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ReceiveMessageActivity extends AppCompatActivity {

    public static final String EXTRA_HISTORIAL = "historial";
    private ArrayList<String> historial = new ArrayList<>();
    private TextView historialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receive_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        historialView = findViewById(R.id.historial);
        historial = getIntent().getStringArrayListExtra(CreateMessageActivity.EXTRA_HISTORIAL);
        mostrarHistorial();
    }

    public void onReplyMessage(View view) {
        EditText messageView = findViewById(R.id.message);
        String messageText = messageView.getText().toString().trim();

        if (!messageText.isEmpty()) {
            historial.add("Estación: " + messageText);
            messageView.setText("");
            mostrarHistorial();
        }
        Intent intent = new Intent(this, CreateMessageActivity.class);
        intent.putStringArrayListExtra(EXTRA_HISTORIAL, historial);
        startActivity(intent);
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (String msg : historial) {
            sb.append(msg).append("\n\n");
        }
        historialView.setText(sb.toString());
    }
}