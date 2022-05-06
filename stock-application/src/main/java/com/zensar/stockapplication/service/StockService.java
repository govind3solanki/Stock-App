package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.exception.InvailidStockIdException;

public interface StockService {

	List<StockDto> getAllStocks(int pageNumber, int pageSize, String sortBy);

	StockDto getStock(long id) throws InvailidStockIdException;

	StockDto createStock(StockDto stock, String token);

	String deleteStockPara(long id);

	StockDto updateStock(int stockId, StockDto stock) throws InvailidStockIdException;

	String deleteAllStock();

	List<StockDto> getStockByName(String name);

	List<StockDto> getStockByNameAndPrice(String name,double price);

}
