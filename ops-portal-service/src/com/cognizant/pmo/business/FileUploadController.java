/**
 * 
 */
package com.cognizant.pmo.business;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lenovo
 *
 */
@RestController
public class FileUploadController extends AbstractController {

	
	@PostMapping("/file/upload")
	public void fileUpload(@RequestParam("file") MultipartFile uploadFile) {
		
	}
	
}
