# frozen_string_literal: true
require_relative 'weapon'
module Irrgarten

  class Player
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@HITS2LOSE = 3

    def initialize(number, intelligence, strenght)
      @name = "Player #" + number.to_s
      @number = number
      @intelligence = intelligence
      @strenght = strenght
      @health = @@INITIAL_HEALTH
      @row = -1
      @col = -1
      @consecutive_hits = 0

      # #atributos de relaciones
      #se ponen corchetes para crear un array y hay que inicializarlo
      @weapons = []
      @shields =[]
    end

    private
    def sum_weapons
      sum = 0.0
      for weapon in @weapons
        sum += weapon.attack
      end
      sum
    end
  end
end
