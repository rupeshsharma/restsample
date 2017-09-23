package com.my.sample.REST.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.config.security.Authorities;
import com.my.sample.config.security.RestSecurity;
import com.my.sample.data.DashboardChartDataRequest;
import com.my.sample.data.ItemGraphDataRequest;
import com.my.sample.service.ChartService;

@RestController
@RequestMapping("/api/chart")
public class ChartController {

	private final ChartService chartService;

	@Autowired
	public ChartController(ChartService chartService) {
		super();

		if (chartService == null) {
			throw new IllegalArgumentException("chartService cannot be null");
		}

		this.chartService = chartService;
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/wholeItemGraph", method = RequestMethod.GET)
	public ResponseEntity<?> getWholeItemGraphData() throws Exception {
		return new ResponseEntity<>(chartService.getWholeItemGraphData(), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/wholeItemGraph/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ResponseEntity<?> getWholeItemGraphDataInRange(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate) throws Exception {
		return new ResponseEntity<>(chartService.getWholeItemGraphDataInRange(fromDate, toDate), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/dashboardGraph", method = RequestMethod.POST)
	public ResponseEntity<?> getDashboardGraphData(@RequestBody DashboardChartDataRequest dashboardChartDataRequest)
			throws Exception {
		return new ResponseEntity<>(chartService.getDashboardGraphData(dashboardChartDataRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/perItemGraph/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> getPerItemGraphData(@RequestBody ItemGraphDataRequest itemGraphDataRequest,
			@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(chartService.getPerItemGraphData(itemGraphDataRequest, id), HttpStatus.OK);
	}

}
