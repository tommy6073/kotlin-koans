package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year != other.year) return year - other.year
        if (month != other.month) return month - other.month
        return dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> =
        object : Iterator<MyDate> {
            var current = start

            override fun hasNext(): Boolean = current <= endInclusive

            override fun next(): MyDate {
                val result = current
                current = current.nextDay()
                return result
            }

        }

    operator fun contains(d: MyDate): Boolean {
        return start <= d && d <= endInclusive
    }
}
