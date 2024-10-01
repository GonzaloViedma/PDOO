# frozen_string_literal: true
module Irrgarten
  class Shield
    attr_accessor :protection, :uses

    def initialize(protection, uses)
      @protection = protection
      @uses = uses
    end

    def protect()
      if @uses > 0
        @uses--
        return @protection
      end
      return 0
    end

    def to_s()
      str = "S[" @protection "," @uses "]"
      return str
    end
  end
end
