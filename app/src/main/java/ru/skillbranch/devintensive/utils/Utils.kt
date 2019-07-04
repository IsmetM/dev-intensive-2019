package ru.skillbranch.devintensive.utils

object Utils {

fun parseFullName(fullName: String?): Pair<String?, String?>{

    val parts: List<String>? = fullName?.trim()?.replace(Regex("\\s{2,}")," ")?.split(" ")

    var firstName = parts?.getOrNull(0)
    var lastName = parts?.getOrNull(1)
    if (firstName.isNullOrEmpty()) firstName = null
    if (lastName.isNullOrEmpty()) lastName = null

    return firstName to lastName
}

}