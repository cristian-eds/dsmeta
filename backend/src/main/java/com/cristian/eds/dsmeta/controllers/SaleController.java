package com.cristian.eds.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristian.eds.dsmeta.entities.Sale;
import com.cristian.eds.dsmeta.services.SaleService;
import com.cristian.eds.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleService service;
	
	@Autowired
	private SmsService smsService;
	
	
	@GetMapping
	public ResponseEntity<Page<Sale>> getSales(@RequestParam(defaultValue = "") String minDate,@RequestParam(defaultValue = "") String maxDate, Pageable pageable) {
		return ResponseEntity.status(200).body(service.findSales(minDate, maxDate, pageable));
	}

	
	@GetMapping("/{id}/notification")
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}
}
