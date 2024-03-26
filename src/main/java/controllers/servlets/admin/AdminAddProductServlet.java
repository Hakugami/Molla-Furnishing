package controllers.servlets.admin;

import firebase.FirebaseManager;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDetailsDto;
import models.DTOs.ProductDto;
import org.apache.commons.fileupload2.core.FileItemInput;
import org.apache.commons.fileupload2.core.FileItemInputIterator;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import services.ProductService;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;




public class AdminAddProductServlet extends HttpServlet {

    private ProductService productService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = ProductService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Check that we have a file upload request
        boolean isMultipart = JakartaServletFileUpload.isMultipartContent(req);
        System.out.println(isMultipart);

        if (isMultipart) {

            JakartaServletFileUpload upload = new JakartaServletFileUpload();
            FileItemInputIterator iterStream = upload.getItemIterator(req);
            ProductDto productDto = new ProductDto();
            ProductDetailsDto productDetailsDto = new ProductDetailsDto();
            ArrayList<String> images = new ArrayList<>();

            while (iterStream.hasNext()) {
                FileItemInput item = iterStream.next();
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = new String(item.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                    // Check if the field name matches a specific field and assign its value to the corresponding string
                    //TODO handle incorrect data
                    if (fieldName.equals("productName")) {
                        productDto.setName( fieldValue);
                    } else if (fieldName.equals("category")) {
                        productDto.setCategoryName(fieldValue);
                    } else if (fieldName.equals("subcategory")) {
                        productDto.setSubCategoryName(fieldValue);
                    } else if (fieldName.equals("price")) {
                        productDto.setPrice(Long.parseLong(fieldValue));
                    } else if (fieldName.equals("stock")) {
                        productDto.setQuantity(Integer.parseInt(fieldValue));
                    } else if (fieldName.equals("description")) {
                        productDto.setDescription(fieldValue);
                    } else if (fieldName.equals("material")) {
                        productDetailsDto.setMaterial(fieldValue);
                    } else if (fieldName.equals("dimensions")) {
                        productDetailsDto.setDimensions(fieldValue);
                    } else if (fieldName.equals("color")) {
                        productDetailsDto.setColor(fieldValue);
                    } else if (fieldName.equals("weight")) {
                        productDetailsDto.setWeight(fieldValue);
                    } else if (fieldName.startsWith("image")) {
                        // Extract the image format from the field name
                        String imageFormat = fieldName.substring("image/".length());
                        // Extract the Base64-encoded image data
                        String base64ImageWithoutPrefix = fieldValue.replaceFirst("data:image/[^;]+;base64,", "");
                        byte[] imageBytes = new byte[0];
                        try {
                            imageBytes = Base64.getDecoder().decode(base64ImageWithoutPrefix);
                            int randomNumber = (int) (Math.random() * 1000);

                            // Upload the image to storage
                            String location = FirebaseManager.getInstance().uploadFileToStorage(imageBytes, "ProductImage" + randomNumber, imageFormat);
                            images.add(location);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Unable to decode image. Image will not be saved");
                        }

                    }

                }
            }
            productDto.setDateAdded(new Date());
            productDto.setProductDetails(productDetailsDto);
            productDto.setImages(images);
            productService.insertProduct(productDto);
        }else {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
}