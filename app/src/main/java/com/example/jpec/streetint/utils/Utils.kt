package com.example.jpec.streetint.utils

fun <A> createHashMapFromKeys(keys: List<String>, values : ArrayList<A>) : HashMap<String, A>
{
    val result = hashMapOf<String, A>()
    for ((i, k) in keys.withIndex())
        result[k] = values[i]
    return result
}

fun <A> createHashMapFromKeysWithDefaultValue(keys: List<String>, defaultValue : A) : HashMap<String, A>
{
    val result = hashMapOf<String, A>()
    for (k in keys)
        result[k] = defaultValue
    return result
}