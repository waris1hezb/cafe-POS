package CafeModels


// Hot or Cold trait
sealed trait HotOrCold
case object Hot extends HotOrCold
case object Cold extends HotOrCold