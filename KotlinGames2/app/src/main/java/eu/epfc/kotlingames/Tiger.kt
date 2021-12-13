package eu.epfc.kotlingames

import java.lang.Math.abs
import kotlin.random.Random

class Tiger(override val name : String?) : Animal() {
    override val weight : Float = 0.5f;
    override val speed : Float = 0.9f;
    override val agility : Float = 0.9f;
    override val force : Float = 0.7f;
    override fun makeSound() {
        println("$name RRrRrrrrr!")
    }

    fun displayLifePoints(){
        println("$name lifePoints : $lifePoints")
    }

    fun getNameCapitalized() : String{
        return name?.capitalize() ?: "Tiger";
    }
    constructor(name : String?, makeSoundDuringCreation : Boolean) : this(name){
        if(makeSoundDuringCreation){
            //makeSound()
        }
    }
}