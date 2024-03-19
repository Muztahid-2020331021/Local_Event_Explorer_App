package com.example.eventexplorerapp.data

import com.example.eventexplorerapp.R

enum class Category(val imageId: Int){
    Basketball(R.drawable.basketball),
    Concert(R.drawable.concert),
    Cooking(R.drawable.cooking),
    Drama(R.drawable.drama),
    Football(R.drawable.football),
    IUPC(R.drawable.iupc),
    Karate(R.drawable.karate),
    Marathon(R.drawable.marathon),
    ScienceFair(R.drawable.science_fair),
    Tour(R.drawable.tour)
}

data class Event(
    val category: Category = Category.Basketball,
    val title : String = "",
    val time : String = "",
    val date : String = "",
    val description : String = "",
    val location : String = "",
    val latitude : Double = 0.0,
    val longitude: Double = 0.0,
    var id:Int = -1,
    val organizer: String = "",
    val phoneNumber: String = ""
){


    fun doesMatchSearchQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            category.name,
            title,
            time,
            date
        )
        return matchingCombinations.any{
            it.contains(query,ignoreCase = true)
        }
    }

}
