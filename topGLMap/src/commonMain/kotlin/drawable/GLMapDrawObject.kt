@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package drawable

expect abstract class GLMapDrawObject {

    /**
     * Returns draw order of object
     *
     * @return draw order of object
     */
    fun getDrawOrder(): Int

    /**
     * Sets object hidden
     *
     * @param hidden if hidden is true object will prepare data to draw but will not draw anything
     */
    fun setHidden(hidden: Boolean)

    /**
     * Checks if the object is hidden.
     *
     * @return `true` if the object is hidden, `false` otherwise.
     */
    fun isHidden(): Boolean
}