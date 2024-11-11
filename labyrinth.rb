# frozen_string_literal: true
require_relative 'directions'
require_relative 'orientation'
require_relative 'dice'
require_relative 'player'
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
      for player in players
        p = player
        pos = random_empty_pos
        put_player_2d(-1, -1, pos[0], pos[1], p)
      end
    end

    def have_a_winner
      @players[@exit_row][@exit_col] != nil
    end

    def to_s
      cad = ""
      for i in 0...@labyrinth.size
        for j in 0...@labyrinth[i].size
          cad = cad + @labyrinth[i][j].to_s + " "
        end
        cad = cad + " \n"
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
      old_row = player.row
      old_col = player.col
      new_pos = self.dir_2_pos(old_row, old_col, directions)
      monster = self.put_player_2d(old_row, old_col, new_pos[0], new_pos[1], player)
    end

    def add_block(orientation, start_row, start_col, length)
      if orientation == Orientation::VERTICAL
        inc_row = 1
        inc_col = 0
      else
        inc_row = 0
        inc_col = 1
      end

      row = start_row
      col = start_col

      while pos_OK(row, col) && empty_pos(row, col) && (length > 0)
        @labyrinth[row][col] = @@BLOCK_CHAR
        length -=1
        row += inc_row
        col += inc_col
      end
    end

    def valid_moves(row, col)
      output = new.Directions
      if can_step_on(row+1, col)
        output.add(Directions::DOWN)
      end
      if can_step_on(row-1, col)
        output.add(Directions::UP)
      end
      if can_step_on(row, col+1)
        output.add(Directions::RIGHT)
      end
      if can_step_on(row, col-1)
        output.add(Directions::LEFT)
      end
      output
    end

    private

    def pos_OK(row, col)
      if(row < @n_rows) && (row >= 0) && (col < @n_cols) && (col >= 0)
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
      if (@labyrinth[row][col] == @@COMBAT_CHAR) && (@players[row][col] != nil)
        true
      else
        false
      end
    end

    def can_step_on(row,col)
      can = false
      if pos_OK(row,col)
        if empty_pos(row,col) || monster_pos(row,col) || exit_pos(row,col)
          can = true
        end
      end
      can
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
      rand = Dice.new()
      vacia = false
      while !vacia
        pos = []
        pos.push(rand.random_pos(@n_rows))
        pos.push(rand.random_pos(@n_cols))

        if empty_pos(pos[0], pos[1])
          vacia = true
        end
      end
      return pos
    end

    def put_player_2d(old_row, old_col, row, col, player)
      output = nil

      if can_step_on(row,col)
        if pos_OK(old_row,old_col)
          p = @players[old_row][old_col]
          if p == player
            update_old_pos(old_row,old_col)
            @players[old_row][old_col] = nil
          end
        end

        monster_pos = monster_pos(old_row,old_col)

        if monster_pos
          @labyrinth[row][col] = @@COMBAT_CHAR
          output = @monsters[row][col]
        else
          number = player.number
          @labyrinth[row][col] = number.to_s
        end

        @players[row][col] = player
        player.pos(old_row, old_col)
      end

      output
    end
  end
end