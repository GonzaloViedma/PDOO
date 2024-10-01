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
        @uses--
        @power
      end
      0
    end

    def to_s()
      str= "W["@power","@usesg"]"
      return str
    end

  end
end
