package model;

import java.io.IOException;
import java.net.MalformedURLException;

public interface HTTPRequests {

  StringBuilder getData(String tickerSymbol) throws IOException;

  StringBuilder getWeeklyData(String tickerSymbol) throws IOException;

  StringBuilder getMonthlyData(String tickerSymbol) throws IOException;

}
