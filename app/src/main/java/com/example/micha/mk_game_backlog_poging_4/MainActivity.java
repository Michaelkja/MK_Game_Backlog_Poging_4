package com.example.micha.mk_game_backlog_poging_4;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_GAME_REQUEST =1;
    public static final int EDIT_GAME_REQUEST =2;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddGame = findViewById(R.id.fab_add_game);
        fabAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditGameActivity.class);
                startActivityForResult(intent, ADD_GAME_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final GameAdapter adapter = new GameAdapter();
        recyclerView.setAdapter(adapter);

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                adapter.setGames(games);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewHolder.getAdapterPosition();
                gameViewModel.delete(adapter.getGameAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Game deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Game game) {
                Intent intent = new Intent(MainActivity.this, AddEditGameActivity.class);
                intent.putExtra(AddEditGameActivity.EXTRA_ID, game.getId());
                intent.putExtra(AddEditGameActivity.EXTRA_TITLE, game.getGameTitle());
                intent.putExtra(AddEditGameActivity.EXTRA_PLATFORM, game.getGamePlatform());
                intent.putExtra(AddEditGameActivity.EXTRA_DESCRIPTION, game.getGameDescription());
                intent.putExtra(AddEditGameActivity.EXTRA_STATUS, game.getGameStatus());
                startActivityForResult(intent, EDIT_GAME_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_GAME_REQUEST && resultCode == RESULT_OK) {
            String gameTitle = data.getStringExtra(AddEditGameActivity.EXTRA_TITLE);
            String gamePlatform = data.getStringExtra(AddEditGameActivity.EXTRA_PLATFORM);
            String gameDescription = data.getStringExtra(AddEditGameActivity.EXTRA_DESCRIPTION);
            String gameStatus = data.getStringExtra(AddEditGameActivity.EXTRA_STATUS);

            Game game = new Game(gameTitle, gamePlatform, gameDescription, gameStatus);
            gameViewModel.insert(game);
            Toast.makeText(this, "Game saved!!!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_GAME_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditGameActivity.EXTRA_ID, -1);

            if (id == 1) {

                String gameTitle = data.getStringExtra(AddEditGameActivity.EXTRA_TITLE);
                String gamePlatform = data.getStringExtra(AddEditGameActivity.EXTRA_PLATFORM);
                String gameDescription = data.getStringExtra(AddEditGameActivity.EXTRA_DESCRIPTION);
                String gameStatus = data.getStringExtra(AddEditGameActivity.EXTRA_STATUS);

                Game game = new Game (gameTitle, gamePlatform, gameDescription, gameStatus);
                game.setId(id);
                gameViewModel.update(game);


                Toast.makeText(this, "Game updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String gameTitle = data.getStringExtra(AddEditGameActivity.EXTRA_TITLE);
            String gamePlatform = data.getStringExtra(AddEditGameActivity.EXTRA_PLATFORM);
            String gameDescription = data.getStringExtra(AddEditGameActivity.EXTRA_DESCRIPTION);
            String gameStatus = data.getStringExtra(AddEditGameActivity.EXTRA_STATUS);

            Game game = new Game (gameTitle, gamePlatform, gameDescription, gameStatus);
            game.setId(id);
            gameViewModel.update(game);

            Toast.makeText(this, "Game Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Game not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
