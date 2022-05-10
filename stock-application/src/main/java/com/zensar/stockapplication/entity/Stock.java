package com.zensar.stockapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ApiModel("sfdhsl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MyStock")
@Table(name = "Stock")
/*
 * //@NamedQueries()
 * @NamedQuery(name = "Stock.findStockByItsNameAndPrice", query =
 * "From MyStock s where s.name=?1 and s.price=?2") //@NamedQuery(name =
 * "Stock.findStockByItsName", query = "From MyStock s where s.name=?1")
 * 
 * @NamedNativeQuery(name = "Stock.findStockByItsName", query =
 * "select * from stock where name=?1",resultClass = Stock.class)
 */public class Stock {
	// @ApiModelProperty("sfdjsl")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long stockId;
	private String name;
	private String marketName;
	private double price;
}
