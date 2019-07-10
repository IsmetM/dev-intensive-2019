package ru.skillbranch.devintensive.utils

//import kotlin.text.StringBuilder

object Utils {

fun parseFullName(fullName: String?): Pair<String?, String?>{

    val parts: List<String>? = fullName?.trim()?.replace(Regex("\\s{2,}")," ")?.split(" ")

    var firstName = parts?.getOrNull(0)
    var lastName = parts?.getOrNull(1)
    if (firstName.isNullOrEmpty()) firstName = null
    if (lastName.isNullOrEmpty()) lastName = null

    return firstName to lastName
}

    fun transliteration(payload: String, divider: String = " ") = payload.trim().replace(Regex("\\s{2,}")," ")
        .map {
        val isUpper = it.isUpperCase()
        val transLetter = when (it.toLowerCase()) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е', 'ё', 'э' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и', 'й', 'ы' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш', 'щ' -> "sh"
            'ъ' -> ""
            'ь' -> ""
            'ю' -> "yu"
            'я' -> "ya"
            ' ' -> divider
            else -> "$it"
        }
        if (isUpper) transLetter.capitalize() else transLetter
    }.joinToString("")



    fun toInitials(firstName: String?, lastName: String?) = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        firstName.isNullOrBlank() -> "${lastName?.get(0)}".toUpperCase()
        lastName.isNullOrBlank() -> "${firstName[0]}".toUpperCase()
        else -> "${firstName[0]}${lastName[0]}".toUpperCase()
        //TODO("Need to FIX: Иван_Пупок -> И(Иван_Пупок, null)")
    }

}

//    fun transliteration(payload: String, divider:String = " "):String {
//        var transResult: String = payload.toLowerCase().trim().replace(Regex("\\s{2,}")," ")
//            .replace(Regex("[aбвгдёжзиклмнпрстуўфхцшщыэюя\\s]")) {
//            when (it.value) {
//                "а" -> "a"
//                "б" -> "b"
//                "в" -> "v"
//                "г" -> "g"
//                "д" -> "d"
//                "е","ё", "э"  -> "e"
//                "ж" -> "zh"
//                "з" -> "z"
//                "и", "й", "ы" -> "i"
//                "к" -> "k"
//                "л" -> "l"
//                "м" -> "m"
//                "н" -> "n"
//                "о" -> "o"
//                "п" -> "p"
//                "р" -> "r"
//                "с" -> "s"
//                "т" -> "t"
//                "у" -> "u"
//                "ф" -> "f"
//                "х" -> "h"
//                "ц" -> "c"
//                "ч" -> "ch"
//                "ш","щ"  -> "sh"
//                "ъ", "ь" -> ""
//                "ю" -> "yu"
//                "я" -> "ya"
//                " " -> divider
//                else -> it.value
//            }
//        }
//        val resultTrans:List<String> = transResult.split(divider)
//        return "${resultTrans.getOrNull(0)?.capitalize()}$divider${resultTrans.getOrNull(1)?.capitalize()}" // ${if (asd.isNullOrEmpty()) println() else println()}"
//    }


//    fun toInitials(firstName: String?, lastName: String?): String? {
//        var st1 = "${firstName?.getOrNull(0)}".toUpperCase()
//        var st2 = "${lastName?.getOrNull(0)}".toUpperCase()
//        return "${if (st1 == null && st2 == null) null else if (st1 == null) st2 else if (st2 == null) st1 else "$st1$st2"}"
//    }