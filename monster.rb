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
      rand = Dice.new()

      rand.intensity(@strength)
    end

    def defend(received_attack)
      is_dead = dead
      rand = Dice.new()

      if !is_dead
        defensive_energy = rand.intensity(@intelligence)
        if defensive_energy < received_attack
          got_wounded
          is_dead = dead
        end
      end
      is_dead
    end

    def pos(row, col)
      @row = row
      @col = col
    end

    def to_s
      "M[" + @name + "," + @intelligence.to_s + "," + @strength.to_s + "," + @health.to_s + "] \n"
    end

    private
    def got_wounded
      @health -= 1
    end

  end
end