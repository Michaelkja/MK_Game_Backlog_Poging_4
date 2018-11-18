package com.example.micha.mk_game_backlog_poging_4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameHolder> {
    private List<Game> games = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_card, parent, false);
        return new GameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        Game currentGame = games.get(position);
        holder.textViewGameTitle.setText(currentGame.getGameTitle());
        holder.textViewGamePlatform.setText(currentGame.getGamePlatform());
        holder.textViewGameDescription.setText(currentGame.getGameDescription());
        holder.textViewGameStatus.setText(currentGame.getGameStatus());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(List<Game> games) {
        this.games = games;
        notifyDataSetChanged();
    }

    public Game getGameAt(int position) {
        return games.get(position);
    }

    class GameHolder extends RecyclerView.ViewHolder {
        private TextView textViewGameTitle;
        private TextView textViewGamePlatform;
        private TextView textViewGameDescription;
        private TextView textViewGameStatus;

        public GameHolder(View itemView) {
            super(itemView);
            textViewGameTitle = itemView.findViewById(R.id.game_title);
            textViewGamePlatform = itemView.findViewById(R.id.game_platform);
            textViewGameDescription = itemView.findViewById(R.id.game_description);
            textViewGameStatus = itemView.findViewById(R.id.game_status);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(games.get(position));
                    }
                }

            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Game game);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
