package co.geeksempire.link.click.Utils.Operations

import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.extractUrl() : String {

    val linkRegex = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,2083}\\.){1,4}([a-zA-Z]){2,6}(\\/(([a-zA-Z-_\\/\\.0-9#:?=&;,]){0,2083})?){0,2083}?[^ \\n]*)"

    val links = ArrayList<String>()

    val pattern: Pattern = Pattern.compile(linkRegex, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this@extractUrl)

    while (matcher.find()) {

        links.add(matcher.group())

    }

    return links.last()
}

fun String.extractUrls() : ArrayList<String> {

    val linkRegex = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,2083}\\.){1,4}([a-zA-Z]){2,6}(\\/(([a-zA-Z-_\\/\\.0-9#:?=&;,]){0,2083})?){0,2083}?[^ \\n]*)"

    val links = ArrayList<String>()

    val pattern: Pattern = Pattern.compile(linkRegex, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this@extractUrls)

    while (matcher.find()) {

        links.add(matcher.group())

    }

    return links
}