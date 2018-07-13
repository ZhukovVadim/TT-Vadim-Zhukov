package com.vadim.ttvadimzhukov.utils

object RestConstants {

    object RESPONSE {
        const val RESPONSE_200 = 200
        const val RESPONSE_404 = 404
    }

    object JsonKeys {
        const val ID = "id"
        const val NAME = "name"
        const val TYPE = "type"
        const val PREVIEW = "preview"
        const val SOURCE = "source"
        const val LIST = "list"
        const val WELCOME = "welcome"
        const val CATALOG = "catalog"
    }

    object Get {
        const val GET_WELCOME = "test/data"
        const val GET_JS = "{path}"
        const val GET_GIFT = "gift/{id}"
    }
}