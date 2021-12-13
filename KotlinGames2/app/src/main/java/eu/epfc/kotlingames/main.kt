package eu.epfc.kotlingames

    fun main(){
        val tiger = Tiger("WhiteTiger", true)
        val elephant = Elephant("Kondo")
        elephant.attack(tiger)
        println("tiger ${tiger.name}: ${tiger.lifePoints}, elephant ${elephant.name}: ${elephant.lifePoints}")
        var litteTiger = Tiger(null, true);
    }