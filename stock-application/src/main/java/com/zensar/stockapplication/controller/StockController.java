package com.zensar.stockapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.exception.InvailidStockIdException;
import com.zensar.stockapplication.service.StockService;

//@Controller
@RestController
@RequestMapping("/stocks")
//@CrossOrigin("hlttp://localhost:4200/")
public class StockController {

	@Autowired
	private StockService stockService;

	// @ResponseBody
	// @GetMapping("/stocks")
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	// @RequestMapping( method = {RequestMethod.GET,RequestMethod.POST})
	public List<StockDto> getAllStocks(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortedBy", defaultValue = "stockId", required = false) String sortBy) {
		return stockService.getAllStocks(pageNumber, pageSize, sortBy);
	}

	@GetMapping(value = "/name/{name}/price/{stockPrice}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<StockDto> getStockByName(@PathVariable("name") String name,
			@PathVariable("stockPrice") double price) {
		return stockService.getStockByNameAndPrice(name, price);
	}

	@GetMapping(value = "/name/{name}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<StockDto> getStockByName(@PathVariable("name") String name) {
		return stockService.getStockByName(name);
	}

	@GetMapping(value = "/{stockId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public StockDto getStock(@PathVariable("stockId") long id) throws InvailidStockIdException {
		return stockService.getStock(id);
	}

	// @PostMapping("/stocks")
	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stock,
			@RequestHeader("auth-user") String token) {
		StockDto createStock = stockService.createStock(stock, token);
		if (createStock == null)
			return new ResponseEntity<StockDto>(createStock, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<StockDto>(createStock, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{stockId}")
	public String deleteStockPara(@PathVariable("stockId") long id) {

		return stockService.deleteStockPara(id);
	}

	@PutMapping(value = "/put/{stockId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public StockDto updateStock(@PathVariable int stockId, @RequestBody StockDto stock) throws InvailidStockIdException {
		return stockService.updateStock(stockId, stock);
	}

	@DeleteMapping()
	public ResponseEntity<String> deleteAllStock() {
		String deleteAllStock = stockService.deleteAllStock();
		return new ResponseEntity<String>(deleteAllStock, HttpStatus.OK);
	}
	
	/*
	 * @ExceptionHandler(InvailidStockIdException.class) public String
	 * handleException() { return "Invalid Stock id"; }
	 */

}

/*
 * public StockController() { stocks.add(new Stock(1L, "fdsf", "fsdf", 12));
 * stocks.add(new Stock(2L, "fdsf", "fsdf", 12)); }
 */

// @GetMapping("/stocks/{stockId}")
/*
 * @RequestMapping(value = "/{stockId}", method = RequestMethod.GET) public
 * Stock getStock(@PathVariable("stockId") long id) {
 * 
 * for (Stock stock : stocks) { if (stock.getStockId() == id) { return stock; }
 * } return null; }
 */

/*
 * // @GetMapping("/stocks/id")
 * 
 * @RequestMapping(value = "/id", method = RequestMethod.GET, produces = {
 * MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }) public
 * Stock getStockPara(@RequestParam(required = false, value = "id", defaultValue
 * = "2") long id) {
 * 
 * for (Stock stock : stocks) { if (stock.getStockId() == id) { return stock; }
 * } return null; }
 */
