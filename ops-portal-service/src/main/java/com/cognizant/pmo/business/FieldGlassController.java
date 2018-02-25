/**
 * 
 */
package com.cognizant.pmo.business;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pmo.bo.Deviations;
import com.cognizant.pmo.service.FieldGlassService;

/**
 * @author 238209
 *
 */
@RestController
public class FieldGlassController extends AbstractController {

	@Resource
	private FieldGlassService fieldGlassService;
	
	@GetMapping("/report/esaVsFg")
	public @ResponseBody Iterable<Deviations> generateEsaVsFgDeviation(List<Long> accountIdList,
			Date startDate, Date endDate) {
		
		List<Deviations> deviationsList = 
				fieldGlassService.getEsaVsFgDeviations(accountIdList, startDate, endDate);
		
		return deviationsList;
	}
}
