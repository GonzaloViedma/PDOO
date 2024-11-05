# frozen_string_literal: true
require_relative 'weapon'
require_relative 'dice'
module Irrgarten

  class Player
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@HITS2LOSE = 3

    def initialize(number, intelligence, strength)
      @name = "Player #" + number.to_s
      @number = number
      @intelligence = intelligence
      @strength = strength
      @health = @@INITIAL_HEALTH
      @row = -1
      @col = -1
      @consecutive_hits = 0

      # #atributos de relaciones
      #se ponen corchetes para crear un array y hay que inicializarlo
      @weapons = []
      @shields =[]
    end

    def resurrect
      @weapons = 0
      @shields = 0
      @health = @@INITIAL_HEALTH
      @consecutive_hits = 0
    end

    def row
      @row
    end

    def col
      @col
    end

    def number
      @name
    end

    def pos(row, col)
      @row = row
      @col = col
    end

    def dead
      @health == 0 ? true : false
    end

    def move

    end

    def attack
      sum = 0.0
      sum += @strength
      sum += self.sum_weapons
      return sum
    end

    def defend(received_attack)

    end

    def receive_reward

    end

    def to_s
      str = "P[" + @name + "," + @number.to_s + "," + @intelligence.to_s + "," + @strength.to_s + "," + @health.to_s + "]"
      return str
    end

    private

    def receive_weapon

    end

    def receive_shield

    end
    #preguntar al profesor si hay que añadirlo al array de weapons
    def new_weapon
      w1 = Weapon.new(Dice.weapon_power, Dice.uses_left)
      @weapons.push(w1)
    end

    def new_shield
      s1 = Shield.new(Dice.shield_power, Dice.uses_left)
      @shields.push(s1)
    end

    def sum_weapons
      sum = 0.0
      for weapon in @weapons
        sum += weapon.attack
      end
      sum
    end

    def sum_shields
      sum = 0.0
      for shield in @shields
        sum += shield.protect
      end
    end

    def defensive_energy
      sum = 0.0
      sum += @intelligence
      sum += self.sum_shields
    end

    def manage_hit

    end

    def reset_hits
      @consecutive_hits = 0
    end

    def got_wounded
      @health -= 1
    end

    def inc_consecutive_hits
      @consecutive_hits += 1
    end

  end
end