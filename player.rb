# frozen_string_literal: true
require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'directions'
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
      @shields = []
    end

    def resurrect
      @weapons.clear
      @shields.clear
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
      @number
    end

    def pos(row, col)
      @row = row
      @col = col
    end

    def dead
      @health == 0
    end

    def move(direction, valid_moves)
      size = valid_moves.size
      contained = valid_moves.include?(direction)
      if (size > 0) && !contained
        first_element = valid_moves[0]
      else
        direction
      end
    end

    def attack
      sum = 0.0
      sum += @strength
      sum += sum_weapons
    end

    def defend(received_attack)
      manage_hit(received_attack)
    end

    def receive_reward
      rand = Dice.new
      w_reward = rand.weapon_reward
      s_reward = rand.shields_reward

      for i in 1..w_reward
        w_new = new_weapon
        receive_weapon(w_new)
      end

      for i in 1..s_reward
        s_new = new_shield
        receive_shield(s_new)
      end

      rand = Dice.new
      extra_health = rand.health_reward
      @health += extra_health
    end

    def to_s
      "P[" + @name + "," + @number.to_s + "," + @intelligence.to_s + "," + @strength.to_s + "," + @health.to_s + "] \n"
    end

    private

    def receive_weapon(w)
      for i in 0...@@MAX_WEAPONS
        if @weapons.empty?
          wi = @weapons[i]
          discard = wi.discard

          if discard
            @weapons.delete(wi)
          end
        end
      end

      size = @weapons.size

      if size < @@MAX_WEAPONS
        @weapons.push(w)
      end
    end

    def receive_shield(s)
      for i in 0...@@MAX_SHIELDS
        if @shields.empty?
          si = @shields[i]
          discard = si.discard
          if discard
            @shields.delete(si)
          end
        end
      end
      size = @shields.size

      if size < @@MAX_SHIELDS
        @shields.push(s)
      end
    end

    def new_weapon
      rand = Dice.new
      w1 = Weapon.new(rand.weapon_power, rand.uses_left)
      @weapons << w1
    end

    def new_shield
      rand = Dice.new
      s1 = Shield.new(rand.shield_power, rand.uses_left)
      @shields << s1
    end

    def sum_weapons
      sum = 0.0
      if @weapons.empty?
        for weapon in @weapons
          sum += weapon.attack
        end
      end
      sum
    end

    def sum_shields
      sum = 0.0
      if @shields.empty?
        for shield in @shields
          sum += shield.protect.to_f
        end
      end
      sum
    end

    def defensive_energy
      sum = 0.0
      sum += @intelligence
      sum += sum_shields
    end

    def manage_hit(received_attack)
      defense = defensive_energy

      if defense < received_attack
        got_wounded
        inc_consecutive_hits
      else
        reset_hits
      end
      
      if @consecutive_hits == @@HITS2LOSE || dead
        reset_hits
        lose = true
      else
        lose = false
      end

      lose
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
