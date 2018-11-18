package com.example.micha.mk_game_backlog_poging_4;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditGameActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.micha.mk_game_backlog_poging_4.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.micha.mk_game_backlog_poging_4.EXTRA_TITLE";
    public static final String EXTRA_PLATFORM =
            "com.example.micha.mk_game_backlog_poging_4.EXTRA_PLATFORM";
    public static final String EXTRA_DESCRIPTION =
            "com.example.micha.mk_game_backlog_poging_4.EXTRA_DESCRIPTION";
    public static final String EXTRA_STATUS =
            "com.example.micha.mk_game_backlog_poging_4.EXTRA_STATUS";

    private EditText editTextGameTitle;
    private EditText editTextGamePlatform;
    private EditText editTextGameDescription;
    private EditText editTextGameStatus;
    private Spinner     status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        editTextGameTitle = findViewById(R.id.edit_game_title);
        editTextGamePlatform = findViewById(R.id.edit_game_platform);
        editTextGameDescription = findViewById(R.id.edit_game_description);
        editTextGameStatus = findViewById(R.id.edit_game_status);
        status = findViewById(R.id.edit_spin_status);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Game.getStatus() );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        status.setAdapter(adapter);

        Intent intent = getIntent();

          // title if it is edit or add
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Game");
            editTextGameTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextGamePlatform.setText(intent.getStringExtra(EXTRA_PLATFORM));
            editTextGameDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextGameStatus.setText(intent.getStringExtra(EXTRA_STATUS));
        } else {
            setTitle("Add Game");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_game);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameTitle = editTextGameTitle.getText().toString();
                String gamePlatform = editTextGamePlatform.getText().toString();
                String gameDescription = editTextGameDescription.getText().toString();
                String gameStatus = editTextGameStatus.getText().toString();

                if (gameTitle.trim().isEmpty() || gamePlatform.trim().isEmpty() || gameDescription.trim().isEmpty() || gameStatus.trim().isEmpty() ) {
                    Toast.makeText(AddEditGameActivity.this, "Please fill in al Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent data = new Intent();
                data.putExtra(EXTRA_TITLE, gameTitle);
                data.putExtra(EXTRA_PLATFORM, gamePlatform);
                data.putExtra(EXTRA_DESCRIPTION, gameDescription);
                data.putExtra(EXTRA_STATUS, gameStatus);

                int id = getIntent().getIntExtra(EXTRA_ID, -1);
                if (id != -1){
                    data.putExtra(EXTRA_ID, id);
                }

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }


}