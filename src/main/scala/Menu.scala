import CafeModels._ // Import all models and custom errors from the CafeModels package

// Main Menu class to manage items and stock
class Menu {
  private var items: List[MenuItem] = List()

  // Add an item to the menu
  def addItem(item: MenuItem): Either[CafeError, Unit] = {
    // Check if an item with the same name already exists in the list
    if (items.exists(_.name.toString == item.name.toString)) {
      // If the item exists, return an error
      Left(MenuItemAlreadyExistsError(item.name.toString))
    } else {
      // If the item doesn't exist, add it to the list and return success
      items = item :: items
      Right(())
    }
  }

  // Remove an item from the menu by name
  def removeItem(itemName: String): Either[CafeError, Unit] = {
    // Check if an item with the given name exists in the list
    if (items.exists(_.name.toString == itemName)) {
      // If the item exists, filter it out of the list and return success
      items = items.filter(_.name.toString != itemName)
      Right(())
    } else {
      // If the item doesn't exist, return an error
      Left(MenuItemNotFoundError(itemName))
    }
  }

  // Get an item by name
  def getItem(itemName: String): Either[CafeError, MenuItem] = {
    items.find(_.name.toString == itemName) match {
      case Some(item) => Right(item)
      case None => Left(MenuItemNotFoundError(itemName))
    }
  }

  // Update the stock of an item
  def updateStock(itemName: String, newStock: Int): Either[CafeError, MenuItem] = {
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
          // Return the updated item
          Right(updatedItem)
        } else {
          // If the stock value is negative, return an error
          Left(MenuInvalidStockError(itemName))
        }
      case None =>
        // If the item is not found, return an error
        Left(MenuItemNotFoundError(itemName))
    }
  }

  // List all items in the menu
  def listItems: List[MenuItem] = items

  // Check if an item is in stock
  def isInStock(itemName: String, quantity: Int): Boolean = {
    items.exists(item => item.name.toString == itemName && item.stock >= quantity)
  }

  // Reduce stock when an item is purchased
  def purchaseItem(itemName: String, quantity: Int): Either[CafeError, Boolean] = {
    getItem(itemName) match {
      case Right(item) if item.stock >= quantity =>
        updateStock(itemName, item.stock - quantity).map(_ => true) // Update stock and return success
      case Right(_) =>
        Left(MenuInsufficientStockError(itemName)) // Error for insufficient stock
      case Left(error) =>
        Left(error) // Error for item not found
    }
  }
}

