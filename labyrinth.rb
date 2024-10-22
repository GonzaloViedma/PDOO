# frozen_string_literal: true
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
      @players = Array.new(@n_rows){Array.new(@n_cols, nil)}
      @monsters = Array.new(@n_rows){Array.new(@n_cols, nil)}

      @labyrinth[@exit_row][@exit_col] = @@EXIT_CHAR
    end

    #atributos de relaciones


    #def have_a_winner
    # if
    # end
  end
end
