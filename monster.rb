# frozen_string_literal: true
require_relative 'dice'
module Irrgarten
  class Monster
    @@INITIAL_HEALTH = 5

    def initialize(name, intelligence, strength)
      @name = name
      @intelligence = intelligence
      @health = @@INITIAL_HEALTH
      @strength = strength
      @row = -1
      @col = -1
    end

    def dead
      #podria ponerse solo @health == 0 ???
      @health == 0 ? true : false
    end

    def attack
      damage = Dice.intensity(@strength)
      return damage
    end

    def pos(row, col)
      @row = row
      @col = col
    end

    def to_s
      str = "M[" + @name + "," + @intelligence.to_s + "," + @strength.to_s + "," + @health.to_s + "]"
      return str
    end

    def got_wounded
      @health -= 1
    end

    #def defend(received_attack)
    #end

  end
end