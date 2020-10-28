package com.github.exerosis.redhat

import java.text.NumberFormat


val FORMAT_CURRENCY = NumberFormat.getCurrencyInstance()!!
val PRICES = mapOf<String, (Int) -> (Double)>(
    "apple" to { it * 0.60 },
    "orange" to { it * 0.25 }
)
//map orders to responses. (using caps to represent "class like" functionality)
fun Shop(onOrder: Observable<Sequence<String>>): Observable<String> = { listener ->
   onOrder { items ->
       listener(try { //FIXME if given more time, perhaps don't use try as logic.
           //group by the price functor then sum by that functor and group count
           FORMAT_CURRENCY.format(items.groupBy {
               PRICES[it.toLowerCase()] ?: throw Exception("Could not find item: $it")
           }.asSequence().sumByDouble { (price, group) -> price(group.size) })
           //alternatively something failed (likely finding the item)
       } catch (reason: Throwable) { reason.message ?: "failed" })
   }
}