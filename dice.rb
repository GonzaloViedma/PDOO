# frozen_string_literal: true
module Irrgarten
  class Dice
    @@MAX_USES= 5;
    @@MAX_INTELLIGENCE= 10.0
    @@MAX_STRENGTH= 10.0
    @@RESURRECT_PROB= 0.3
    @@WEAPONS_REWARD= 2
    @@SHIELDS_REWARD= 3
    @@HEALTH_REWARD= 5
    @@MAX_ATTACK= 3
    @@MAX_SHIELD= 2

    @@generator= Random.new

    def random_pos (max)
      pos = @@generator.rand(0...max)
      return pos
    end

    def who_starts (nplayers)
      starts = @@generator.rand(0...nplayers)
      return starts
    end

    def random_intelligence
      return @@generator.rand(0.0...@@MAX_INTELLIGENCE)
    end

    def random_strength
      return @@generator.rand(0.0...@@MAX_STRENGTH)
    end

    def resurrect_player
      prob = @@generator.rand(0.0..1.0)
      prob > @@RESURRECT_PROB ? false : true
      #return true if prob <= @RESURRECT_PROB
      #return false if prob > @RESURRECT_PROB
    end

    def weapon_reward
      nweapons = @@generator.rand(0..@@WEAPONS_REWARD)
      return nweapons
    end

    def shields_reward
      nshield = @@generator.rand(0..@@SHIELDS_REWARD)
      return nshield
    end

    def health_reward
      nhealth = @@generator.rand(0..@@HEALTH_REWARD)
      return nhealth
    end

    def weapon_power
      attack= @@generator.rand(0...@MAX_ATTACK)
      return attack
    end

    def shield_power
      defense = @@generator.rand(0...@@MAX_SHIELD)
      return defense
    end

    def uses_left
      uses = @@generator.rand(0...@@MAX_USES)
      return uses
    end

    def intensity (competence)
      return @@generator.rand(0...competence)
    end

    def discard_element (uses_Left)

      return true if uses_Left == 0
      return false if uses_Left == @@MAX_USES

      probabylity = 1.0-(uses_Left.to_f/@@MAX_USES)
      return @@generator.rand(0.0..1.0) >= probabylity
    end
  end
end
