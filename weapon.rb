# frozen_string_literal: true
require_relative 'player'
require_relative 'dice'

module Irrgarten
  class Weapon
    def initialize(power, uses)
      @power = power
      @uses = uses
    end

    def attack
      if @uses > 0
        @uses -= 1
        @power
      end
      0
    end

    def to_s
      "W[" + @power.to_s + "," + @uses.to_s + "]"
    end

    def discard
      Dice.discard_element(@power)
    end
  end
end
