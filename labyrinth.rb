# frozen_string_literal: true
require_relative 'directions'
require_relative 'orientation'
require_relative 'dice'
module Irrgarten
  class Labyrinth
    @@BLOCK_CHAR = 'X'
    @@EMPTY_CHAR = '-'
    @@MONSTER_CHAR = 'M'
    @@COMBAT_CHAR = 'C'
    @@EXIT_CHAR = 'E'
    @@ROW = 0
    @@COL = 1

    def initialize(n_rows, n_cols, exit_row, exit_col)
      @n_rows = n_rows
      @n_cols = n_cols
      @exit_row = exit_row
      @exit_col = exit_col

      #si pongo Array.new(4, 5), me hace un array de 4 con cincos. por eso me sirve para inicializar con empty char
      @labyrinth = Array.new(@n_rows){Array.new(@n_cols, @@EMPTY_CHAR)}
      #@labyrinth = []
      #for i in 0...@n_rows
      #  @labyrinth[i] = []
      #  for j in 0...@n_cols
      #    @labyrinth[i][j] = @@EMPTY_CHAR
      #  end
      #end
      @players = Array.new(@n_rows){Array.new(@n_cols, nil)}
      @monsters = Array.new(@n_rows){Array.new(@n_cols, nil)}

      @labyrinth[@exit_row][@exit_col] = @@EXIT_CHAR
    end

    def spread_players(players)

    end

    def have_a_winner
      @players[@exit_row][@exit_col] != nil ? true : false
    end

    def to_s
      cad = ""
      for i in 0...@n_rows
        for j in 0...@n_cols
          if j == @n_cols - 1
            cad += @labyrinth[i][j] + "\n"
          else
            cad += @labyrinth[i][j]
          end
        end
      end
      cad
    end

    def add_monster(row, col, monster)
      if pos_OK(row,col) && empty_pos(row,col)
      @labyrinth[row][col] = @@MONSTER_CHAR
        @monsters[row][col] = monster
      end
    end

    def put_player(directions, player)

    end

    def add_block(orientation, start_row, start_col, length)

    end

    def valid_moves(row, col)

    end

    private

    def pos_OK(row, col)
      if((row < @n_rows) && (row >= 0) && (col < @n_cols) && (col >= 0))
        true
      else
        false
      end
    end

    def empty_pos(row, col)
      @labyrinth[row][col] == @@EMPTY_CHAR
    end

    def monster_pos(row,col)
      @labyrinth[row][col] == @@MONSTER_CHAR
    end

    def exit_pos(row,col)
      @labyrinth[row][col] == @@EXIT_CHAR
    end

    def combat_pos(row,col)
      if @labyrinth[row][col] == @@COMBAT_CHAR && @players[row][col] != nil
        true
      else
        false
      end
    end

    def can_step_on(row,col)
      if pos_OK(row,col) && empty_pos(row,col) && monster_pos(row,col) && exit_pos(row,col)
        true
      else
        false
      end
    end

    def update_old_pos(row,col)
      if pos_OK(row,col)
        if combat_pos(row,col)
          @labyrinth[row][col] = @@MONSTER_CHAR
        else
          @labyrinth[row][col] = @@EMPTY_CHAR
        end
      end
    end

    def dir_2_pos(row,col,direction)
      aux = [row,col]

      case direction
      when Directions::UP
        aux[0]=row-1
      when Directions::DOWN
        aux[0]=row+1
      when Directions::LEFT
        aux[1]=col-1
      when Directions::RIGHT
        aux[1]=col+1
      end
      aux
    end

    def random_empty_pos
      vacia = false
      while !vacia
        pos = []
        pos[0] = Dice.random_pos(@n_rows)
        pos[1] = Dice.random_pos(@n_cols)

        if Labyrinth.empty_pos(pos[0], pos[1])
          vacia = true
        end
      end
      pos
    end

    def put_player_2D(old_row, old_col, row, col, player)

    end
  end
end
