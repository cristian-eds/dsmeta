package com.cristian.eds.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristian.eds.dsmeta.entities.Sale;
import com.cristian.eds.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	public SaleService service;
	
	@GetMapping
	public ResponseEntity<List<Sale>> getSales() {
		return ResponseEntity.status(200).body(service.findSales());
	}

}
