# frozen_string_literal: true
module Irrgarten
  class GameState
    def initialize(labyrinth, players, monsters, current_player, winner, log)
      @labyrinth = labyrinth
      @players = players
      @monsters = monsters
      @current_player = current_player
      @winner = winner
      @log = log
    end

    def labyrinth
      @labyrinth.to_s
    end

    def players
      @players.to_s
    end

    def monsters
      @monsters.to_s
    end

    def current_player
      @current_player.to_s
    end

    def winner
      @winner.to_s
    end

    def log
      @log.to_s
    end
  end
end

