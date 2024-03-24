package controllers.servlets.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;
import services.ProductService;
import urls.enums.UrlMapping;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class AdminAddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check that we have a file upload request
        boolean isMultipart = JakartaServletFileUpload.isMultipartContent(req);
        System.out.println("Is multipart :" + isMultipart);
        String servletDirectory = getServletContext().getRealPath("/");
        String imageFileName = "Image.png";

// Specify the path to save the image file within the servlet directory
        String imagePath = servletDirectory + File.separator + imageFileName;

        if (isMultipart) {
            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

            List items = upload.parseRequest(req);

            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                if (!item.isFormField()) {
                    try (
                            InputStream uploadedStream = item.getInputStream();

                            OutputStream out = new FileOutputStream(imagePath);) {

                        out.write(uploadedStream.readAllBytes());

                        IOUtils.copy(uploadedStream, out);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        }
    }
}