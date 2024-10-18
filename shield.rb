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
      str = "S[" + @protection.to_s + "," + @uses.to_s + "]"
      return str
    end

    def discard
      Dice.discard_element(@uses)
    end

  end
end
