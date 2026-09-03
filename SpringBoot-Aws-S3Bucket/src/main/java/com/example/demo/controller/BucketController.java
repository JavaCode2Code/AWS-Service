package com.example.demo.controller;

import com.example.demo.service.BucketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;


/**
 * Contains method to create bucket and upload file in bucket on AWS S3
 * 
 * @author Sateesh Meena
 *
 */
@RestController
@RequestMapping("s3bucket")
@CrossOrigin("*")
@Tag(name = "S3 Bucket Operations",description ="Operations for managing S3 buckets and files" )
public class BucketController {

	@Autowired
	BucketService service;

	/**
	 * Calls Service class to create bucket on AWS S3
	 * 
	 * @param bucketName
	 * @return
	 */
	@Operation(summary = "Create S3 Bucket", description = "Creates a new S3 bucket with the specified name.")
	@GetMapping("/add/{bucketName}")
	public ResponseEntity<String> createBucket(@PathVariable String bucketName) {
		return new ResponseEntity<String>(service.createBucket(bucketName), HttpStatus.OK);
	}

	/**
	 * Calls Service class to upload file on existing bucket
	 * 
	 * @param file
	 * @return
	 */
	@Operation(summary = "Upload File to S3 Bucket", description = "Uploads a file to the specified S3 bucket.")
	@PostMapping(path = "/upload/file/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file,
			@PathVariable String bucketName) {
		return new ResponseEntity<String>(service.uploadFile(file,bucketName), HttpStatus.OK);
	}
	
	@Operation(summary = "Delete File from S3 Bucket", description = "Deletes a file from the specified S3 bucket.")
	@DeleteMapping(path="/delete/file/{bucketName}/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable String bucketName,@PathVariable String fileName)
	{
		return new ResponseEntity<String>(service.deleteFile(bucketName,fileName),HttpStatus.OK);
	}
@Operation(summary = "Delete S3 Bucket", description = "Deletes the specified S3 bucket.")
	@DeleteMapping("/delete/bucket/{bucketName}")
	public ResponseEntity<String> deleteBucket(@PathVariable String bucketName) {
		return new ResponseEntity<String>(service.deleteBucket(bucketName), HttpStatus.OK);
	}

}