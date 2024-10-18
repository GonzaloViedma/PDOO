# frozen_string_literal: true
require_relative 'weapon'
require_relative 'dice'
require_relative 'player'

module Irrgarten
  class TestP1
    def main
      #p1 = Player.new('1', 4.5, 3.3)
      #w1 = Weapon.new(10,5)
      #p1.new_weapon(w1)
      #puts "fin"
      objeto = Dice.new
      for i in 1..100 do
        res = objeto.random_strength
        puts res
      end

    end
  end
end

t = Irrgarten::TestP1.new
t.main