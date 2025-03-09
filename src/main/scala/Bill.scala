import CafeModels._ // Import all models and custom errors from the CafeModels package

import scala.math.BigDecimal.RoundingMode


// Handles bill generation and service charge calculation
class Bill {

  // Calculate the subtotal of the order (price * quantity for each item)
  private def calculateSubtotal(order: Order): Either[CafeError, BigDecimal] = {
    // Check if any item has an invalid quantity (<= 0)
    val invalidItems = order.items.filter { case (_, quantity) => quantity <= 0 }
    if (invalidItems.nonEmpty) {
      Left(OrderInvalidQuantityError(invalidItems.keys.head.name.toString))
    } else {
      // Calculate the subtotal
      val subtotal = order.items.map { case (item, quantity) =>
        item.price * quantity
      }.sum
      Right(subtotal)
    }
  }

  // Calculate the service charge based on the order
  private def calculateServiceCharge(order: Order, subtotal: BigDecimal): Either[CafeError, BigDecimal] = {
    val items = order.items.keys.toList

    // Check conditions for service charge
    val hasPremium = items.exists(_.isPremiumSpecial)
    val hasHotFood = items.exists(item => item.category == Food && item.hotOrCold == Hot)
    val hasColdFood = items.exists(item => item.category == Food && item.hotOrCold == Cold)

    // Determine the service charge percentage
    val serviceChargePercentage = if (hasPremium) {
      0.25 // 25% for premium items
    } else if (hasHotFood) {
      0.20 // 20% for hot food
    } else if (hasColdFood) {
      0.10 // 10% for cold food
    } else {
      0.0 // No service charge for drinks only
    }

    // Calculate the service charge
    val autoServiceCharge = subtotal * BigDecimal(serviceChargePercentage)

    // Add custom service charge if provided
    val totalServiceCharge = order.customServiceCharge match {
      case Some(customCharge) if customCharge >= 0 =>
        autoServiceCharge + (subtotal * BigDecimal(customCharge))
      case Some(_) =>
        return Left(OrderInvalidServiceChargeError("Custom service charge must be non-negative."))
      case None =>
        autoServiceCharge
    }

    // Round the service charge to 2 decimal places
    Right(totalServiceCharge.setScale(2, RoundingMode.HALF_UP))
  }

  // Generate an itemised bill with subtotal, service charge, and total
  def generateBill(order: Order): Either[CafeError, String] = {

    // Check if the order is empty
    if (order.items.isEmpty) {
      return Left(OrderInvalidItemListError("Order cannot be empty."))
    }

    for {
      subtotal <- calculateSubtotal(order) // Calculate subtotal or return error
      serviceCharge <- calculateServiceCharge(order, subtotal) // Calculate service charge or return error
    } yield {
      val total = subtotal + serviceCharge

      // Format the bill as a string
      val itemisedBill = order.items.map { case (item, quantity) =>
        s"${item.name} x $quantity: £${(item.price * quantity).setScale(2, RoundingMode.HALF_UP)}"
      }.mkString("\n")

      s"""
         |Itemised Bill:
         |$itemisedBill
         |Subtotal: £${subtotal.setScale(2, RoundingMode.HALF_UP)}
         |Service Charge: £${serviceCharge.setScale(2, RoundingMode.HALF_UP)}
         |Total: £${total.setScale(2, RoundingMode.HALF_UP)}
         |""".stripMargin.trim       //Use .trim to remove leading/trailing whitespace = resolved testing issues
    }
  }
}