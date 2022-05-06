package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exception.InvailidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StockDto> getAllStocks(int pageNumber, int pageSize, String sortBy) {
		Page<Stock> pageStocks = stockRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
		List<Stock> content = pageStocks.getContent();
		List<StockDto> listStockResponse = new ArrayList<StockDto>();
		for (Stock stock : content) {
			StockDto map = modelMapper.map(stock, StockDto.class);
			listStockResponse.add(map);
		}
		return listStockResponse;
	}
	@Override
	public List<StockDto> getStockByNameAndPrice(String name,double price) {

		List<Stock> findStockByName = stockRepository.findStockByItsNameAndPrice(name,price);
		List<StockDto> stocks = new ArrayList<StockDto>();
		for (Stock st : findStockByName) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}
		return stocks;
	}

	@Override
	public List<StockDto> getStockByName(String name) {

		List<Stock> findStockByName = stockRepository.findStockByItsName(name);
		List<StockDto> stocks = new ArrayList<StockDto>();
		for (Stock st : findStockByName) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}
		return stocks;
	}

	@Override
	public StockDto getStock(long id) throws InvailidStockIdException {
		Stock stock =null;
		Optional<Stock> findById = stockRepository.findById(id);
		if(findById.isPresent()) {
			stock = findById.get();
			return modelMapper.map(stock, StockDto.class);
		}
		throw new InvailidStockIdException("invalid id you are passing");
	}

	@Override
	public StockDto createStock(StockDto stock, String token) {
		Stock map = modelMapper.map(stock, Stock.class);
		if (token.equalsIgnoreCase("gs66548")) {
			Stock save = stockRepository.save(map);
			return modelMapper.map(save, StockDto.class);
		} else
			return null;
	}

	@Override
	public String deleteStockPara(long stockId) {

		stockRepository.deleteById(stockId);
		return "stock deleted with stock id" + stockId;
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stock) throws InvailidStockIdException {
		StockDto availableStock = getStock(stockId);
		Stock map = modelMapper.map(availableStock, Stock.class);
		map.setMarketName(stock.getMarketName());
		map.setName(stock.getName());
		map.setPrice(stock.getPrice());
		return modelMapper.map(stockRepository.save(map), StockDto.class);
	}

	@Override
	public String deleteAllStock() {

		stockRepository.deleteAll();
		return "all stocks deleted";

	}

	public Stock mapToStock(StockDto stockRequest) {
		Stock newStock = new Stock();
		newStock.setMarketName(stockRequest.getMarketName());
		newStock.setName(stockRequest.getName());
		newStock.setPrice(stockRequest.getPrice());
		return newStock;
	}

}