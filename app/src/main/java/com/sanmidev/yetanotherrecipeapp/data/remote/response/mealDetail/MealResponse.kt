package com.sanmidev.yetanotherrecipeapp.data.remote.response.mealDetail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealDetailResponse(
    @Json(name = "dateModified")
    val dateModified: Any?, // null

    @Json(name = "strArea")
    val strArea: String?, // British
    @Json(name = "strCategory")
    val strCategory: String?, // Seafood
    @Json(name = "strDrinkAlternate")
    val strDrinkAlternate: Any?, // null
    @Json(name = "strIngredient1")
    val strIngredient1: String?, // Floury Potatoes
    @Json(name = "strIngredient10")
    val strIngredient10: String?, // Prawns
    @Json(name = "strIngredient11")
    val strIngredient11: String?, // Parsley
    @Json(name = "strIngredient12")
    val strIngredient12: String?, // Dill
    @Json(name = "strIngredient13")
    val strIngredient13: String?, // Lemon
    @Json(name = "strIngredient14")
    val strIngredient14: String?, // Gruyère
    @Json(name = "strIngredient15")
    val strIngredient15: String?, // Lemon
    @Json(name = "strIngredient16")
    val strIngredient16: String?,
    @Json(name = "strIngredient17")
    val strIngredient17: String?,
    @Json(name = "strIngredient18")
    val strIngredient18: String?,
    @Json(name = "strIngredient19")
    val strIngredient19: String?,
    @Json(name = "strIngredient2")
    val strIngredient2: String?, // Olive Oil
    @Json(name = "strIngredient20")
    val strIngredient20: String?,
    @Json(name = "strIngredient3")
    val strIngredient3: String?, // Semi-skimmed Milk
    @Json(name = "strIngredient4")
    val strIngredient4: String?, // White Fish Fillets
    @Json(name = "strIngredient5")
    val strIngredient5: String?, // Plain flour
    @Json(name = "strIngredient6")
    val strIngredient6: String?, // Nutmeg
    @Json(name = "strIngredient7")
    val strIngredient7: String?, // Double Cream
    @Json(name = "strIngredient8")
    val strIngredient8: String?, // Jerusalem Artichokes
    @Json(name = "strIngredient9")
    val strIngredient9: String?, // Leek
    @Json(name = "strMeasure1")
    val strMeasure1: String?, // 900g
    @Json(name = "strMeasure10")
    val strMeasure10: String?, // 200g peeled raw
    @Json(name = "strMeasure11")
    val strMeasure11: String?, // Large handful
    @Json(name = "strMeasure12")
    val strMeasure12: String?, // Handful
    @Json(name = "strMeasure13")
    val strMeasure13: String?, // Grated zest of 1
    @Json(name = "strMeasure14")
    val strMeasure14: String?, // 25g grated
    @Json(name = "strMeasure15")
    val strMeasure15: String?, // Juice of 1
    @Json(name = "strMeasure16")
    val strMeasure16: String?,
    @Json(name = "strMeasure17")
    val strMeasure17: String?,
    @Json(name = "strMeasure18")
    val strMeasure18: String?,
    @Json(name = "strMeasure19")
    val strMeasure19: String?,
    @Json(name = "strMeasure2")
    val strMeasure2: String?, // 2 tbsp
    @Json(name = "strMeasure20")
    val strMeasure20: String?,
    @Json(name = "strMeasure3")
    val strMeasure3: String?, // 600ml
    @Json(name = "strMeasure4")
    val strMeasure4: String?, // 800g
    @Json(name = "strMeasure5")
    val strMeasure5: String?, // 1 tbsp
    @Json(name = "strMeasure6")
    val strMeasure6: String?, // Grating
    @Json(name = "strMeasure7")
    val strMeasure7: String?, // 3 tbsp
    @Json(name = "strMeasure8")
    val strMeasure8: String?, // 200g
    @Json(name = "strMeasure9")
    val strMeasure9: String?, // 1 finely sliced
    @Json(name = "strSource")
    val strSource: String?,
    @Json(name = "strTags")
    val strTags: String?, // Fish,Pie,Breakfast,Baking
    @Json(name = "strYoutube")
    val strYoutube: String?, // https://www.youtube.com/watch?v=2sX4fCgg-UI
    @Json(name = "idMeal")
    val idMeal: String?, // 52802
    @Json(name = "strInstructions")
    val strInstructions: String?,
    @Json(name = "strMealThumb")
    val strMealThumb: String?, // https://www.themealdb.com/images/media/meals/ysxwuq1487323065.jpg
    @Json(name = "strMeal")
    val strMeal: String?// Fish pie

)