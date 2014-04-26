package org.leanpoker.player

import java.text.SimpleDateFormat

/**
 * Created by bnc on 4/26/14.
 */
class Log {
    static print = true

    static def log(message) {
        if (!print) return

        println "${currentTimestamp()} $message"
    }

    private static String currentTimestamp() {
        new Date().format("yyyyy-mm-dd hh:mm:ss")
    }
}
