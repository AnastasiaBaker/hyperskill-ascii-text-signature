package signature

import java.io.File

class Roman {
    companion object {
        private const val fileName = "filePath\\roman.txt"
        val lines = File(fileName).readLines(Charsets.US_ASCII)
    }
}

class Medium {
    companion object {
        private const val fileName = "filePath\\medium.txt"
        val lines = File(fileName).readLines(Charsets.US_ASCII)
    }
}

fun printTag(name: String, status: String) {
    val mapRomanLength = mutableMapOf<Char, Int>()
    val mapRomanStringNumber = mutableMapOf<Char, Int>()

    for (i in 1..Roman.lines.lastIndex step 11) {
        val letterAndLength = Roman.lines[i].split(" ")
        mapRomanLength[letterAndLength[0].first()] = letterAndLength[1].toInt()
        mapRomanStringNumber[letterAndLength[0].first()] = i
    }

    val mapMediumLength = mutableMapOf<Char, Int>()
    val mapMediumStringNumber = mutableMapOf<Char, Int>()

    for (i in 1..Medium.lines.lastIndex step 4) {
        val letterAndLength = Medium.lines[i].split(" ")
        mapMediumLength[letterAndLength[0].first()] = letterAndLength[1].toInt()
        mapMediumStringNumber[letterAndLength[0].first()] = i
    }

    var lengthName = 8

    for (i in name.indices) {
        if (name[i] == ' ') lengthName += 10
        for (k in mapRomanLength.keys) {
            if (k == name[i]) lengthName += mapRomanLength[k]!!
        }
    }

    var lengthStatus = 8

    for (i in status.indices) {
        if (status[i] == ' ') lengthStatus += 5
        for (k in mapMediumLength.keys) {
            if (k == status[i]) lengthStatus += mapMediumLength[k]!!
        }
    }

    val length = if (lengthStatus > lengthName) lengthStatus else lengthName

    repeat(length) {
        print("8")
    }
    print("\n")

    for (j in 1..10) {
        print("88  ")

        if (length == lengthStatus) {
            repeat((lengthStatus - lengthName) / 2) {
                print(" ")
            }
        }

        for (i in name.indices) {
            if (name[i] == ' ') repeat(10) { print(" ") }
            for (k in mapRomanStringNumber.keys) {
                if (k == name[i]) print(Roman.lines[mapRomanStringNumber[k]!! + j])
            }
        }

        if (length == lengthStatus) {
            if ((lengthStatus - lengthName) % 2 == 0) {
                repeat((lengthStatus - lengthName) / 2) {
                    print(" ")
                }
            } else {
                repeat(((lengthStatus - lengthName) / 2) + 1) {
                    print(" ")
                }
            }
        }

        print("  88\n")
    }

    for (j in 1..3) {
        print("88  ")

        if (length == lengthName) {
            repeat((lengthName - lengthStatus) / 2) {
                print(" ")
            }
        }

        for (i in status.indices) {
            if (status[i] == ' ') repeat(5) { print(" ") }
            for (k in mapMediumStringNumber.keys) {
                if (k == status[i]) print(Medium.lines[mapMediumStringNumber[k]!! + j])
            }
        }

        if (length == lengthName) {
            if ((lengthName - lengthStatus) % 2 == 0) {
                repeat((lengthName - lengthStatus) / 2) {
                    print(" ")
                }
            } else {
                repeat(((lengthName - lengthStatus) / 2) + 1) {
                    print(" ")
                }
            }
        }

        print("  88\n")
    }

    repeat(length) {
        print("8")
    }
}

fun main() {
    print("Enter name and surname: ")
    val name = readLine()!!
    print("Enter person's status: ")
    val status = readLine()!!.toUpperCase()

    printTag(name, status)
}