package com.eduardo.lojavirtual.controllers;

import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.lojavirtual.models.Costumer;
import com.eduardo.lojavirtual.repository.CostumerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/costumer")
@CrossOrigin(origins = "*")
@Api(value = "API REST Costumer")
public class CostumerController {

	private static final Logger log = LoggerFactory.getLogger(CostumerController.class);

	@Autowired
	CostumerRepository costumerRepository;

	@ResponseBody
	@GetMapping("/all")
	@ApiOperation(value = "Returns all customers")
	public List<Costumer> costumerAll() throws Exception {

		log.info("Retornando todos os clientes ");
		List<Costumer> ltResult = null;
		try {
			if (ltResult.size() > 0) {
				ltResult = costumerRepository.findAll();
			}
		} catch (Exception e) {
			log.error("costumerAll: {}", e.toString());
		}
		return ltResult;
	}

	@ResponseBody
	@GetMapping("/findcod")
	@ApiOperation(value = "Returns all cod")
	public List<Costumer> costumerFindcod(@RequestBody Costumer costumer, BigDecimal cod) throws Exception {

		log.info("Returning all cod");
		List<Costumer> ltResult = null;
		try {
			if (ltResult.size() > 0) {
				ltResult = costumerRepository.findCostumerByCod(cod);
			}
		} catch (Exception e) {
			log.error("costumerFindcod: {}", e.toString());
		}
		return ltResult;
	}

	@ResponseBody
	@GetMapping("like/{findname}")
	@ApiOperation(value = "Returns all customers")
	public List<Costumer> costumerFindName(@RequestBody Costumer costumer, String findname) throws Exception {

		log.info("Returning all customers");
		List<Costumer> ltResult = null;
		try {
			if (ltResult.size() > 0) {
				ltResult = costumerRepository.findCostumerByNameIgnoreCase(findname);
			}
		} catch (Exception e) {
			log.error("costumerFindName: {}", e.toString());
		}
		return ltResult;
	}

	@ApiOperation(value = "Register Costumer")
	@PostMapping("/register")
	// @PreAuthorize("hasrole('ADMIN')")
	public Costumer registerCostumer(@RequestBody Costumer costumer) {

		log.info("Registering customer");
		Costumer result = new Costumer();
		try {
			result = costumerRepository.save(costumer);
		} catch (Exception e) {
			log.error("registerCostumer: {}", e.toString());
		}
		return result;
	}

	@ApiOperation(value = "Delete Costumers")
	@DeleteMapping("/delete/id/{id}")
//	@PreAuthorize("hasrole('ADMIN')")
	public void deleteCostumer(@RequestBody Costumer costumer, Long id) {

		log.info("Deleting a client by id");
		try {
			costumerRepository.deleteById(id);
		} catch (Exception e) {
			log.error("deleteCostumer: {}", e.toString());
		}
	}

	@ApiOperation(value = "Updates Costumer")
	@PutMapping("/update")
	// @PreAuthorize("hasrole('ADMIN')")
	public Costumer updatesCostumer(@RequestBody Costumer costumer) {

		Costumer result = new Costumer();
		log.info("\r\n" + "Updating customer data");
		try {
			result = costumerRepository.save(costumer);
		} catch (Exception e) {
			log.error("updatesCostumer: {}", e.toString());
		}
		return result;
	}

}
