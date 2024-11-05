# frozen_string_literal: true
require_relative 'player'
require_relative 'monster'
require_relative 'dice'
require_relative 'labyrinth'
require_relative 'game_state'
module Irrgarten
  class Game
    @@MAX_ROUNDS = 10
    @@N_ROWS = 3
    @@N_COLS = 6
    @@EXIT_ROW = 2
    @@EXIT_COL = 5

    def initialize(n_players)

      for i in 0...n_players
        @players[i] = Player.new(i, Dice.random_intelligence, Dice.random_strength)
      end
      @current_player_index = Dice.who_starts(n_players)
      @log = @players[@current_player_index].number
      @monsters[]
      @lab = Labyrinth.new(@@N_ROWS, @@N_COLS, @@EXIT_ROW, @@EXIT_COL)
      @lab.spread_players(@players)
      @rounds
    end

    def finished
      Labyrinth.have_a_winner
    end

    def next_step(preferred_direction)
      @log = ""
      dead = @player[@current_player_index].dead
      if !dead
        direction = self.actual_direction(preferred_direction)

        if direction != preferred_direction
          self.log_player_no_orders
        end

        monster = @lab.put_player(direction, @current_player_index)

        if monster == nil
          self.log_no_monster
        else
          winner = self.combat(monster)
          self.manage_reward(winner)
        end
      else
        self.manage_resurrection
      end

      end_game = self.finished

      if !end_game
        self.next_player
      end
    end

    def get_game_state
      @game_state = GameState.new(@lab, @players, @monsters, @current_player_index, self.finished, @log)
    end

    def mostrar_lab
      @lab.to_s
    end

    private

    def configure_labyrinth

    end

    def next_player
      if @current_player_index == @players.size - 1
        @current_player_index = 0
      else
        @current_player_index += 1
      end
      @log = @players[@current_player_index].number
    end

    def actual_direction(preferred_direction)
      current_row = @player[@current_player_index].row
      current_col = @player[@current_player_index].col
      valid_moves = @lab.valid_moves(current_row, current_col)
      output = move(preferred_direction, valid_moves)
    end

    def combat(monster)
      rounds = 0
      winner = GameCharacter.Player
      player_attack = @player[@current_player_index].attack
      lose = defend(player_attack)

      while(!lose && rounds < @@MAX_ROUNDS)
        winner = GameCharacter.Monster
        rounds += 1
        monster_attack = monster.attack
        lose = @player[@current_player_index].defend(monster_attack)

        if(!lose)
          player_attack = @player[@current_player_index].attack
          winner = GameCharacter.Player
          lose = monster.defend(player_attack)
        end

      end
      self.log_rounds(rounds, @@MAX_ROUNDS)
      winner
    end

    def manage_reward(winner)
      if winner == GameCharacter.Player
        @player[@current_player_index].receive_reward
        self.log_player_won
      else
        self.log_monster_won
      end
    end

    def manage_resurrection
      resurrect = Dice.resurrect_player
      if resurrect
        @player[@current_player_index].resurrect
        self.log_resurrected
      else
        self.log_player_skip_turn
      end
    end

    def log_player_won
      @log = @log + " ha ganado el combate. \n"
    end

    def log_monster_won
      @log = @log + ". El monstruo ha ganado el combate. \n"
    end

    def log_resurrected
      @log = @log + " ha resucitado. \n"
    end

    def log_player_skip_turn
      @log = @log + " ha perdido el turno. Esta muerto. \n"
    end

    def log_player_no_orders
      @log = @log + " no ha seguido las instrucciones. \n"
    end

    def log_no_monster
      @log = @log + " se ha movido a una celda vacia o no se ha movido. \n"
    end

    def log_rounds(rounds, max)
      if rounds > max
        @log = @log + ". Se han producido más rounds del máximo. \n"
      end
    end

  end
end