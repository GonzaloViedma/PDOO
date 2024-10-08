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

    @@GENERADOR= Random.new

    def randomPos (max)
      pos = @GENERADOR.rand(0..max)
      return pos
    end

    def whoStarts (nplayers)
      starts = @GENERADOR.rand(0..nplayers)
      return starts
    end

    def randomIntelligence ()
      return @GENERADOR.rand(0.0...@MAX_INTELLIGENCE)
    end

    def randomStrength ()
      return @GENERADOR.rand(0.0...@MAX_STRENGTH)
    end

    def resurrectPlayer ()
      prob = @GENERADOR.rand(0.0..@RESURRECT_PROB)
      return true if prob <= @RESURRECT_PROB
      return false if prob > @RESURRECT_PROB
    end

    def weaponReward ()
      nweapons = @GENERADOR.rand(0..@WEAPONS_REWARD)
      return nweapons
    end

    def shieldsReward ()
      nshield = @GENERADOR.rand(0..@SHIELDS_REWARD)
      return nshield
    end

    def healthReward ()
      nhealth = @GENERADOR.rand(0..@HEALTH_REWARD)
      return nhealth
    end

    def weaponPower ()
      attack= @GENERADOR.rand(0...@MAX_ATTACK)
      return attack
    end

    def shieldPower ()
      defense = @GENERADOR.rand(0...@MAX_SHIELD)
      return defense
    end

    def usesLeft()
      uses = @GENERADOR.rand(0...@MAX_USES)
      return uses
    end

    def intensity (competence)
      return @GENERADOR.rand(0...competence)
    end

    def discardElement (uses_Left)
      return true if uses_Left == 0
      return false if uses_Left == @MAX_USES

      probabylity = 1.0-(uses_Left.to_f/@MAX_USES)
      return @GENERADOR.rand(0.0..1.0) >= probabylity
    end
  end
end
