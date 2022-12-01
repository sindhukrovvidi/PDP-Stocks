Design modifications:

Support for dollar cost averaging:
The following changes are done as our previous program supports only adding a single stock and all
the other functionalities are reused.
  1.  In PortfolioController interface we added a function isBulkStockAddition to check whether the
      user wants to add a single stock or wants to create a plan and implemented that method in
      PortfolioControllerImpl.
  2. In the portfolio model interface, we added function validateInputForMultiStocks to validate the
      weightages and all other inputs added by the user.
  3. addMultipleStocksInPortfolio is added in the Portfolio interface to add the list of stocks
      mentioned in the plan to the portfolio after validating the inputs.

Support for GUI:
  1.Added a controller, GUIMainController which implements the Features to connect the GUI and the
    model.
  2.New classes are created for frames in view under gui package to render different frames based on
    the user selection.
  3.Created a GUIInterface class which has all the methods required in the view.
