# frozen_string_literal: true
module Irrgarten
  class Shield
    def initialize(protection, uses)
      @protection = protection
      @uses = uses
    end

    def protect()
      if @uses > 0
        @uses -=1
        return @protection
      end
      return 0
    end

    def to_s()
      str = "S[" + @protection.to_s + "," + @uses.to_s + "]"
      return str
    end
  end
end
