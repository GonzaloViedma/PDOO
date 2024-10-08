# frozen_string_literal: true
module Irrgarten
  class Weapon
    def initialize(power, uses)
      @power = power
      @uses = uses
    end

    def attack()
      if @uses > 0
        @uses -= 1
        @power
      end
      0
    end

    def to_s()
      str= "W[" + @power.to_s + "," + @uses.to_s + "]"
      return str
    end

  end
end
