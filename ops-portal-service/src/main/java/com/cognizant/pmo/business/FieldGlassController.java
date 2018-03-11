/**
 * 
 */
package com.cognizant.pmo.business;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pmo.bo.AssociateUBR;
import com.cognizant.pmo.bo.Deviations;
import com.cognizant.pmo.bo.HolidayBo;
import com.cognizant.pmo.bo.LoginResponse;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.service.AssociateService;
import com.cognizant.pmo.service.FieldGlassService;
import com.cognizant.pmo.utils.Utils;

/**
 * @author 238209
 *
 */
@RestController
public class FieldGlassController extends AbstractController {

	@Resource
	private FieldGlassService fieldGlassService;
	
	@Resource
	private AssociateService associateService;
	
	@GetMapping("/report/esaVsFg")
	public @ResponseBody Iterable<Deviations> generateEsaVsFgDeviation() {
		List<Long> accountIdList = new ArrayList<Long>();
		accountIdList.add(new Long(1225597));
		
		LocalDate startDate = LocalDate.of(2018, Month.JANUARY, 1);
		LocalDate endDate = LocalDate.of(2018, Month.FEBRUARY, 17);
		
		LocalDate weekStartDate = Utils.getPreviousWeekStartDate(startDate);
		LocalDate weekEndDate = Utils.getNextWeekStartDate(endDate, false);
		
		List<Deviations> deviationsList = 
				fieldGlassService.getEsaVsFgDeviations(accountIdList, weekStartDate, weekEndDate);
		
		return deviationsList;
	}
	
	@GetMapping("/report/ubr/{month}")
	public @ResponseBody Iterable<AssociateUBR> getUBRDetails(@PathVariable("month") String month) {
		List<Long> accountIdList = new ArrayList<Long>();
		accountIdList.add(new Long(1225597));
		
		List<AssociateUBR> ubrList = 
				fieldGlassService.getUBRDetails(accountIdList, month);
		
		return ubrList;
	}
	
	@PostMapping("/save/holiday")
	public @ResponseBody LoginResponse saveHoliday(@RequestBody HolidayBo holidayBo) {
		
		boolean saved = associateService.saveHoliday(holidayBo);
		
		LoginResponse response = new LoginResponse();
		response.setSuccess(saved);
		
		return response;
	}

	@GetMapping("/holidays/all")
	public @ResponseBody Iterable<HolidayBo> fetchHolidays() {
		return associateService.getAllHolidays();
	}

	@GetMapping("/report/missingxref")
	public @ResponseBody Iterable<MAssociate> getAssociatesWithMissingXref() {
		return associateService.findAssociateWithMissingXref();
	}

	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date startDate = cal.getTime();
		
		cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 28);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date endDate = cal.getTime();
		
		int workDays = Utils.calculateWorkDays(startDate, endDate);
		System.out.println("Work Days: " + workDays);
	}
}
