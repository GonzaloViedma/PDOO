# frozen_string_literal: true
require_relative 'dice'

module Irrgarten
  class Shield
    def initialize(protection, uses)
      @protection = protection
      @uses = uses
    end

    def protect

      if @uses > 0
        @uses -=1
        return @protection
      end
      0
    end

    def to_s
      "S[" + @protection.to_s + "," + @uses.to_s + "]"
    end

    def discard
      rand = Dice.new
      rand.discard_element(@uses)
    end

  end
end
