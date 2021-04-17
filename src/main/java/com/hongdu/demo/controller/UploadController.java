package com.hongdu.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hongdu.demo.code.RetCode;
import com.hongdu.demo.code.RetResult;

@RestController
public class UploadController {
//	 @PostMapping("/upload")
	    public RetResult<Object> SingleFileUpLoad(@RequestParam("file") MultipartFile file,@RequestParam("phone") String phone,@RequestParam("id") String id, Model model) {
		 RetResult<Object> result = new RetResult<Object>() ;
		 result.setCode(RetCode.FAIL);
		 result.setMsg("error");
		    //判断文件是否为空
	        if(file.isEmpty()){
	            result.setMsg("文件为空");
	            return result;
	        }

		  	if(null==phone||phone.equals("")){
			 result.setMsg("手机为空");
			 return result;
		 	}

		 	if(null==id||id.equals("")){
			 result.setMsg("身份证号为空");
			 return result;
		 	}
	        

	        //创建输入输出流
	        InputStream inputStream = null;
	        OutputStream outputStream = null;

	        try {
	            //指定上传的位置为当前项目upload文件下
	            String path = "upload/";
	            //获取文件的输入流
	            inputStream = file.getInputStream();
	            //获取上传时的文件名
	            String fileName = file.getOriginalFilename();
	            //注意是路径+文件名
	            File targetFile = new File(path + phone+"_"+id+"_"+fileName);
	            //如果之前的 String path = "upload/" 没有在最后加 / ，那就要在 path 后面 + "/"

	            //判断文件父目录是否存在
	            if(!targetFile.getParentFile().exists()){
	                //不存在就创建一个
	                targetFile.getParentFile().mkdir();
	            }

	            //获取文件的输出流
	            outputStream = new FileOutputStream(targetFile);
	            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
	            FileCopyUtils.copy(inputStream, outputStream);
	            /*参数是通过源码
	                public static int copy(InputStream in, OutputStream out) throws IOException {
	                    ......
	                }
	           而得知的*/
	            
	            //告诉页面上传成功了
	            result.setMsg("上传成功");
	        } catch (Exception e) {
	            e.printStackTrace();
	            //出现异常，则告诉页面失败
	            return result;
	        } finally {
	            //无论成功与否，都有关闭输入输出流
	            if (inputStream != null) {
	                try {
	                    inputStream.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return result;
	                }
	            }
	            if (outputStream != null) {
	                try {
	                    outputStream.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return result;
	                }
	            }
	        }
	        result.setCode(RetCode.SUCCESS);
	        return result;
	    }
}
