# frozen_string_literal: true
module Irrgarten
  class Weapon
    attr_accessor :power, :uses

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
      str= "W[" + @power + "," + @uses + "]"
      return str
    end

  end
end
