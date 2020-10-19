import java.io.File

fun main() {
    print("Enter name and surname: ")
    val name = readLine()!!
    print("Enter person's status: ")
    val status = readLine()!!.toUpperCase()

    printTag(name, status)
}

object Fonts {
    val roman = File("fonts/roman.txt").readLines(Charsets.US_ASCII)
    val medium = File("fonts/medium.txt").readLines(Charsets.US_ASCII)
}

fun printTag(name: String, status: String) {
    val romanLength = mutableMapOf<Char, Int>()
    val romanStringNumber = mutableMapOf<Char, Int>()
    val mediumLength = mutableMapOf<Char, Int>()
    val mediumStringNumber = mutableMapOf<Char, Int>()
    var lengthName = 8
    var lengthStatus = 8

    for (i in 1..Fonts.roman.lastIndex step 11) {
        val letterAndLength = Fonts.roman[i].split(" ")
        romanLength[letterAndLength[0].first()] = letterAndLength[1].toInt()
        romanStringNumber[letterAndLength[0].first()] = i
    }

    for (i in 1..Fonts.medium.lastIndex step 4) {
        val letterAndLength = Fonts.medium[i].split(" ")
        mediumLength[letterAndLength[0].first()] = letterAndLength[1].toInt()
        mediumStringNumber[letterAndLength[0].first()] = i
    }

    for (i in name.indices) {
        if (name[i] == ' ') lengthName += 10
        for (k in romanLength.keys) { if (k == name[i]) lengthName += romanLength[k]!! }
    }

    for (i in status.indices) {
        if (status[i] == ' ') lengthStatus += 5
        for (k in mediumLength.keys) { if (k == status[i]) lengthStatus += mediumLength[k]!! }
    }

    val length = if (lengthStatus > lengthName) lengthStatus else lengthName

    repeat(length) { print("8") }

    print("\n")

    for (j in 1..10) {
        print("88  ")

        if (length == lengthStatus) repeat((lengthStatus - lengthName) / 2) { print(" ") }

        for (i in name.indices) {
            if (name[i] == ' ') repeat(10) { print(" ") }
            for (k in romanStringNumber.keys) { if (k == name[i]) print(Fonts.roman[romanStringNumber[k]!! + j]) }
        }

        if (length == lengthStatus) {
            if ((lengthStatus - lengthName) % 2 == 0) repeat((lengthStatus - lengthName) / 2) { print(" ") }
            else { repeat(((lengthStatus - lengthName) / 2) + 1) { print(" ") } }
        }

        print("  88\n")
    }

    for (j in 1..3) {
        print("88  ")

        if (length == lengthName) repeat((lengthName - lengthStatus) / 2) { print(" ") }

        for (i in status.indices) {
            if (status[i] == ' ') repeat(5) { print(" ") }
            for (k in mediumStringNumber.keys) { if (k == status[i]) print(Fonts.medium[mediumStringNumber[k]!! + j]) }
        }

        if (length == lengthName) {
            if ((lengthName - lengthStatus) % 2 == 0) repeat((lengthName - lengthStatus) / 2) { print(" ") }
            else { repeat(((lengthName - lengthStatus) / 2) + 1) { print(" ") } }
        }

        print("  88\n")
    }

    repeat(length) { print("8") }
}
