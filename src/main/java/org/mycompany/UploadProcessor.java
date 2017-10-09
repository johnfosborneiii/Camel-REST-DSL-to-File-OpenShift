package org.mycompany;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
import javax.servlet.http.Part;
import javax.activation.DataHandler; 

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

//import io.swagger.models.Response;

public class UploadProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadProcessor.class);
    
	@Override 
    public void process(Exchange exchange) throws Exception {
	
		final String path = "src/main/resources/";
		
		Message in = exchange.getIn();
	    HttpServletRequest request = (HttpServletRequest) in.getHeaders().get(Exchange.HTTP_SERVLET_REQUEST); 
	    Part filePart = request.getPart("fileUpload");
	    
	    final String fileName = getFileName(filePart);

	    LOGGER.info("body tostring: " + in.getBody().toString());
	    
	    if(in.getAttachments().isEmpty())
	    	LOGGER.info("Message does not have attachments");
	    else {
	    	 for (Map.Entry<String, DataHandler> entry : exchange.getIn().getAttachments().entrySet()) {
	    		 LOGGER.info("Found attachment!");
	    	 }
	    }
	    if(filePart == null) {
	    	LOGGER.info("request.getPart(fileUpload) is null");
	    	return;
	    }
	    
	    InputStream filecontent = filePart.getInputStream();
	    OutputStream out = new FileOutputStream(new File(path + File.separator + fileName));
	    
	    try {
		    
	        int read = 0;
	        final byte[] bytes = new byte[1024];
	        
	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        
	    }catch (FileNotFoundException fne) {
	        
	    	LOGGER.info("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent ");
	    	LOGGER.warn("Problems during file upload");
	    	
	    }finally {
		    if (out != null) {
		        out.close();
		    }
		    if (filecontent != null) {
		        filecontent.close();
		    }
        }
        
	}
	
    private String getFileName(final Part part) {
        
    	return "wildfly.png";
    	/*
    	final String partHeader = part.getHeader("content-disposition");
        LOGGER.info("Part Header = {0}" + partHeader);
        
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;*/
    }
    /*rest("/upload").description("Image REST service")
        	.consumes("application/json")
        	.produces("application/json")
        		
            .post("/{id}").description("Update a user").type(User.class)
	            .param().name("id").type(path).description("The ID of the user to update").dataType("integer").endParam()    
	            .param().name("body").type(body).description("The user to update").endParam()
	            .responseMessage().code(204).message("User successfully updated").endResponseMessage()
	            .to("bean:userService?method=saveString(${body})");
        
        
        from("seda:uploadFileProcessor2")
        	.process(new Processor() {
                public void process(Exchange msg) {
                    
                	MediaType mediaType = msg.getIn().getHeader(Exchange.CONTENT_TYPE, MediaType.class);
                    InputStream fileInputStream = msg.getIn().getBody(FileInputStream.class);
                    fileInputStream.
                    try {
                            List<FileItem> items = 
                                new RestletFileUpload(
                                    new DiskFileItemFactory()).parseRepresentation(representation);

                            for (FileItem item : items) {
                                if (!item.isFormField()) {
                                    InputStream inputStream = item.getInputStream();
                                    Path destination = Paths.get("MyFile.jpg");
                                    Files.copy(inputStream, destination,
                                                StandardCopyOption.REPLACE_EXISTING);
                                }
                            }
                        } catch (FileUploadException | IOException e) {
                            e.printStackTrace();
                        }                }
            	    /*
                	/*
                	byte[] bytes = msg.getIn(byte[].class);
                	if(bytes != null)
                		LOG.info("num bytes: " + bytes.length);
                	else
                		LOG.info("bytes == null");
                	
                	File file = msg.getIn().getBody(File.class);
                	String body = msg.getIn().getBody(String.class);
                	LOG.info("body: " + body);
                    BufferedWriter bw = null;
                    FileWriter fw = null;
                    
                    if (!file.exists()) {
                    	LOG.info("File Does Not Exist");
                    }else {
                    	
                    	LOG.info("Processing file: " + file.toString());
                    	try {
	                    	fw = new FileWriter(file);
	                    	bw = new BufferedWriter(fw);
	                  	  	bw.write(file.toString());
	                        System.out.println("File written Successfully");
                    	} catch (IOException ioe) {
                    		ioe.printStackTrace();
                    	}
                    	finally{ 
                    		try{
                    			if(bw!=null)
                    				bw.close();
                    		}catch(Exception ex){
                    			System.out.println("Error in closing the BufferedWriter"+ex);
                    		}
                    	}
                    }                }
            });
        
        from("seda:uploadFileProcessor")
        	.process(new UploadProcessor());

        // @formatter:on
    }*/
}
