package CafeModels


// Nutritional information as a pure data structure
case class NutritionalInfo(
                            calories: Option[Int],
                            carbs: Option[Int],
                            sugar: Option[Int],
                            fat: Option[Int],
                            protein: Option[Int] = None,
                            fiber: Option[Int] = None
                          ) {
  // Method to calculate total calories
  def totalCalories: Int = calories.getOrElse(0) + carbs.getOrElse(0) * 4 + protein.getOrElse(0) * 4 + fat.getOrElse(0) * 9
}
