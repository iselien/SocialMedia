package io.yovelas.controller;

import io.yovelas.common.Constants;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("download")
public class FileDownloadController {



    private

    final

    static

    String UPLOADED_FOLDER = "/Users/shicheng/Desktop/test/";

    @GetMapping
    public String download ( @RequestParam ( value = "file" ) String file , HttpServletResponse response ) {
        if (! file . isEmpty ()) {
            File source = new File ( UPLOADED_FOLDER + file );
            if ( source . exists ()) {
                response . setContentType ( "application/force-download" ); // 设置强制下载不打开
                response . addHeader ( "Content-Disposition" , "attachment;fileName=" + file );
                // 设置文件名
                byte [] buffer = new byte [ 1024 ];
                FileInputStream fileInputStream = null ;
                BufferedInputStream bufferedInputStream = null ;
                try { fileInputStream = new FileInputStream ( source );
                    bufferedInputStream = new BufferedInputStream ( fileInputStream );
                    OutputStream outputStream = response . getOutputStream ();
                    int i = bufferedInputStream . read ( buffer );
                    while ( i != - 1 ) {
                        outputStream . write ( buffer , 0 , i );
                        i = bufferedInputStream . read ( buffer );
                    }

                    return "文件下载成功" ;
                } catch ( Exception e ) {
                    e . printStackTrace ();

                } finally {
                    if ( bufferedInputStream != null ) {
                        try {
                            bufferedInputStream . close ();
                        } catch ( IOException e ) {
                            return "文件下载失败,失败原因: " + e . getMessage ();
                        }
                    } if ( fileInputStream != null ) {
                        try {
                            fileInputStream . close ();
                        } catch ( IOException e ) {
                            return "文件下载失败,失败原因: " + e . getMessage ();
                        }
                    }

                }

            }

        }
        return "文件下载失败" ;

    }

    @CrossOrigin
    @GetMapping("/download/{fileName}")
    ResponseEntity fileDownload(HttpServletResponse resp, @PathVariable("fileName") String fileName) throws IOException {

        System.out.printf("文件名称:%s,文件存储路径:%s\n", fileName, Constants.FILE_STORAGE_PATH + "/" + fileName);

        Path filestorage = Paths.get("filestorage").resolve(Constants.FILE_STORAGE_PATH + "/" + fileName);
        UrlResource urlResource = new UrlResource(filestorage.toUri());
        ResponseEntity<UrlResource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+"").body(urlResource);
        return body;
    }
}
