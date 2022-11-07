The design of the application is as follows:
It contains controller, model and view.
There are individual controllers for the main, portfolio and the stock and these classes are
implemented from individual interfaces mainController, StockController and PortfolioController.

The program starts at main controller. The main controller has a go function which displays the main
 menu to the user and takes the user input and determines which model should work.
 Based on the option the main controller if the user wants to create new portfolio the main
 controller will call the portfolio controller and if it is related to the stocks the main
 controller will call the stock controller.
 The functionality and the descriptions of each controller is as follows:
Controllers:
1.The main controller is responsible for  pre-processing the stock data, displaying the main menu
for the user and tells the model what to do based on the user input.
2. The portfolio controller is responsible for controlling all the operations related to the
maintaining the portfolios.
It handles functions like adding the stock, what to do after adding a stock, and to speculate the
existing portfolio.
3.The stock controller is responsible for controlling how to take a ticker symbol, displaying the
stocks, adding stocks to the portfolio and displaying the portfolio.

Model:
There are six classes and four interfaces in the model.Two classes will solely be responsible to
take the input and output from the user and all other classes will have individual interfaces also
so that this leaves the scope to add new functionalities without making any changes in the existing
code.
1. The file accessors reads,writes and retrieves the required files.
2. The input file takes the inputs accordingly.
3. The list of stocks class has all the methods responsible for mapping the files.
4. The portfolio interface contains the methods that are responsible for adding, getting
and setting
 the stocks and check if it is saved to the portfolio or not.
5. The output class formats the output accordingly.
6. The stocksImpl class retrieves all the fields related to the stocks.

View:
The view has two interfaces and two classes implementing them.
1. When the controller calls the portfolio view it displays the portfolio requested by the user.
2. The stocks view formats the stocks to be displayed.