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
      @health == 0
    end

    def attack
      Dice.intensity(@strength)
    end

    def defend(received_attack)
      is_dead = self.dead
      if !is_dead
        defensive_energy = Dice.intensity(@intelligence)
        if defensive_energy < received_attack
          self.got_wounded
          is_dead = self.dead
        end
      end
      is_dead
    end

    def pos(row, col)
      @row = row
      @col = col
    end

    def to_s
      "M[" + @name + "," + @intelligence.to_s + "," + @strength.to_s + "," + @health.to_s + "]"
    end

    private
    def got_wounded
      @health -= 1
    end

  end
end