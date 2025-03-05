import CafeModels._ // Import all models from the CafeModels package


// Main Menu class to manage items and stock
class Menu {
  private var items: List[MenuItem] = List()


  // Add an item to the menu
  def addItem(item: MenuItem): Either[String, Unit] = {
    // Check if an item with the same name already exists in the list
    if (items.exists(_.name.toString == item.name.toString)) {
      // If the item exists, return an error message using Left
      Left(s"Item already exists: ${item.name}")
    } else {
      // If the item doesn't exist, add it to the list and return success using Right
      items = item :: items // Add the new item to the beginning of the list
      Right(()) // Return a success value (Unit) wrapped in Right
    }
  }

  // Remove an item from the menu by name
  def removeItem(itemName: String): Either[String, Unit] = {
    // Check if an item with the given name exists in the list
    if (items.exists(_.name.toString == itemName)) {
      // If the item exists, filter it out of the list and return success
      items = items.filter(_.name.toString != itemName) // Keep only items that don't match the name
      Right(()) // Return a success value (Unit) wrapped in Right
    } else {
      // If the item doesn't exist, return an error message using Left
      Left(s"Item not found: $itemName")
    }
  }

  // Get an item by name
  def getItem(itemName: String): Option[MenuItem] = {
    items.find(_.name.toString == itemName)
  }

  // Update the stock of an item using Either
  def updateStock(itemName: String, newStock: Int): Either[String, MenuItem] = {
    // Find the item in the list by name
    items.find(_.name.toString == itemName) match {
      case Some(item) =>
        // If the item is found, check if the new stock value is valid (non-negative)
        if (newStock >= 0) {
          // Create an updated copy of the item with the new stock value
          val updatedItem = item match {
            case coffee: Coffee => coffee.copy(stock = newStock)
            case tea: Tea => tea.copy(stock = newStock)
            case juice: Juice => juice.copy(stock = newStock)
            case cake: Cake => cake.copy(stock = newStock)
            case burger: Burger => burger.copy(stock = newStock)
            case pizza: Pizza => pizza.copy(stock = newStock)
          }
          // Update the list by replacing the old item with the updated item
          items = items.map(i => if (i.name.toString == itemName) updatedItem else i)
          // Return the updated item wrapped in Right
          Right(updatedItem)
        } else {
          // If the stock value is negative, return an error message using Left
          Left("Invalid stock value: Stock cannot be negative.")
        }
      case None =>
        // If the item is not found, return an error message using Left
        Left(s"Item not found: $itemName")
    }
  }

  // List all items in the menu
  def listItems: List[MenuItem] = items

  // Check if an item is in stock
  def isInStock(itemName: String): Boolean = {
    items.exists(item => item.name.toString == itemName && item.stock > 0)
  }

  // Reduce stock when an item is purchased
  def purchaseItem(itemName: String, quantity: Int): Boolean = {
    getItem(itemName) match {
      case Some(item) if item.stock >= quantity =>
        updateStock(itemName, item.stock - quantity)
        true
      case _ =>
        false
    }
  }
}


