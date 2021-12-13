package eu.epfc.kotlingames

class Snake(override val name: String) : Animal() {
    override val weight: Float = 0.4f;
    override val speed: Float = 0.5f;
    override val agility: Float = 0.8f;
    override val force: Float = 0.4f;

    override fun makeSound() {
        TODO("Not yet implemented")
    }
}