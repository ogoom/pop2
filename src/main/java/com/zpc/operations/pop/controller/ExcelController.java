package com.zpc.operations.pop.controller;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import com.zpc.operations.pop.helper.ExcelHelper;
import com.zpc.operations.pop.message.ResponseMessage;
import com.zpc.operations.pop.repository.OutwardItemsDetailsReportRepository;
import com.zpc.operations.pop.repository.UserRepository;
import com.zpc.operations.pop.service.ExcelService;
import com.zpc.operations.pop.service.ReportService;
import com.zpc.operations.pop.util.ReportGenerator;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;


//@CrossOrigin("http://localhost:8080")
@Controller
@RequiredArgsConstructor
public class ExcelController {

    private static final String EXTERNAL_FILE_PATH = "C:/code/java/spring/";
    private static final String EXTERNAL_FILE_PATH_ZIP = "C:/code/java/spring/pop2/reports/";
    private final ReportService reportService;
    private final OutwardItemsDetailsReportRepository repository;
    private final EntityManager entityManager;
    @Autowired
    ExcelService fileService;
    @Autowired
    ReportGenerator generator;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/upload")
    public String upload(Model model) {
        return "upload";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                System.out.println("file is valid");
                List<OutwardItemsDetailsReport> reports = fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e;
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }


    @GetMapping("/create_report/{id}")
    public String createReport(@PathVariable Long id, HttpServletResponse httpResponse) throws JRException, IOException {

        String response = generator.generateReport(id);
        System.out.println(response + "Back from generator");

        return ("redirect:/download/" + response);

    }

    @RequestMapping("/download/{fileName:.+}")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("fileName") String fileName) throws IOException {

        File file = new File(EXTERNAL_FILE_PATH + fileName);
        if (file.exists()) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            /**
             * In a regular HTTP response, the Content-Disposition response header is a
             * header indicating if the content is expected to be displayed inline in the
             * browser, that is, as a Web page or as part of a Web page, or as an
             * attachment, that is downloaded and saved locally.
             *
             */

            /**
             * Here we have mentioned it to show inline
             */
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }

    @RequestMapping("/downloadzip/{fileName}")
    public void downloadZipResource(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("fileName") String fileName) throws IOException {

        File file = new File(EXTERNAL_FILE_PATH_ZIP + fileName + ".zip");
        if (file.exists()) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            /**
             * In a regular HTTP response, the Content-Disposition response header is a
             * header indicating if the content is expected to be displayed inline in the
             * browser, that is, as a Web page or as part of a Web page, or as an
             * attachment, that is downloaded and saved locally.
             *
             */

            /**
             * Here we have mentioned it to show inline
             */
//            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            //Here we have mentioned it to show as attachment
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }


    @RequestMapping("/index")
    public String main() {
        return "index";
    }

//    @GetMapping("/signup")
//    public String showSignUpForm(User user) {
//        return "add-user";
//    }
//
//    @PostMapping("/adduser")
//    public String addUser( User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        userRepository.save(user);
//        return "redirect:/index";
//    }

//    @RequestMapping(value = "/pop/report", method = RequestMethod.GET)
//    public String getAllReports(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
//                                @RequestParam(value = "size", required = false, defaultValue = "7") int size, Model model) {
//        model.addAttribute("reports", reportService.getPage(pageNumber, size));
////
//        return "reports";
//    }

//    @GetMapping("/index")
//    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "index";
//    }


}
