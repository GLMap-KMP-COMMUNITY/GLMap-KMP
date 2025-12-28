package map

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class GLMapError {

    /**
     * Retrieves the name of the error domain, similar to the iOS version of the framework.
     *
     * @return The name of the error domain, such as `"HTTP"`, `"CURL"`, `"XZ"`,
     * `"Valhalla"`, or `"ERRNO"` if no specific domain is detected.
     */
    fun getErrorDomain(): String

    /**
     * For available codes [check](https://www.gnu.org/software/libc/manual/html_node/Error-Codes.html)
     *
     * @return true if error is from system domain
     */
    fun isSystemError(): Boolean

    /**
     * For available codes [check](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
     *
     * @return true if error contains HTTP error code
     */
    fun isHTTPError(): Boolean


    /**
     * For available codes [check](https://curl.haxx.se/libcurl/c/libcurl-errors.html)
     *
     * @return true if error contains CURL error code
     */
    fun isCURLError(): Boolean

    /**
     * For available codes [check](https://github.com/torvalds/linux/blob/master/include/linux/xz.h#L58-L118)
     *
     * @return true if error contains XZ error code
     */
    fun isXZError(): Boolean

    /**
     * For available codes [check](https://github.com/valhalla/valhalla-docs/blob/master/turn-by-turn/api-reference.md#internal-error-codes-and-conditions)
     *
     * @return true if error contains Valhalla error code
     */
    fun isValhallaError(): Boolean

}