package com.electrodna.scheduler.db

fun <T> String.query(vararg parameterValues: Any) : Query<T>
{
    return Query(this,listOf<Column<T>>())
}

infix fun <T> String.column(columnDef: String) : Query<T>
{
    return Query(this,listOf<Column<T>>())
}

fun <T> String.query(columns: List<Column<T>>) : Query<T>
{
    return Query<T>(this,columns)
}

fun <T> String.query(column: Column<T>) : Query<T>
{
    return Query<T>(this,listOf<Column<T>>(column))
}

data class Column<T>(val name:String, val value: T)

class Query<T>(queryText: String,val parameters: List<Column<T>>)
{
    var finalQueryText = queryText
    init {

    }
    fun execute() : List<T>
    {
        return listOf<T>()
    }
}