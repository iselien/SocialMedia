package io.yovelas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController("fs/")
public class TreeController {
//	@Autowired
//	IterateDirService iterateDirService;
//
//	@Autowired
//	IterateDirServiceForLinux iterateDirServiceForLinux;
//
//	@Autowired
//	FileNodeInfoDao fileNodeInfoDao;

	static String os = System.getProperty("os.name");

	@RequestMapping("/")
	public String index() {
		System.out.println(os);
		Properties properties = System.getProperties();
		for(int i = 0; i< properties.size();i++){
			System.out.println(properties.get(i));
		}
		return "/index";
	}

//	@RequestMapping("/GetSingleLevelNodes")
//	@ResponseBody
//	public Fileinfo[] GetSingleLevelNodes(String path) throws IOException {
//		File file = new File(path);
//		if ((path == "") || !(file.exists())) {
//			System.out.println("目录可能为空或者不存在");
//			if (os.toLowerCase().startsWith("win")) {
//				path = "C:\\";
//				;
//			} else
//				path = "/";
//
//		}
//
//		Fileinfo fileinfo = new Fileinfo(path);
//		return fileinfo.readSingleLevelfiles(path, fileNodeInfoDao);
//	}
//
//	@RequestMapping("/asncReadSingleLevelfiles")
//	@ResponseBody
//	public Fileinfo[] asncReadSingleLevelfiles(String path) throws IOException {
//		Fileinfo fileinfo = new Fileinfo(path);
//		return fileinfo.asncReadSingleLevelfiles(path, fileNodeInfoDao);
//	}
//
//	@RequestMapping("/IterateDirChildren")
//	@ResponseBody
//	public DirBean IterateDirChildren(String path) throws Exception {
//		System.out.println("IterateDirService getFileName");
//		DirBean dirBean = new DirBean();
//		if (path == null)
//			return null;
//		if (os.toLowerCase().startsWith("win"))
//			dirBean = iterateDirService.getFiles(path);
//		else
//			dirBean = iterateDirServiceForLinux.getFiles(path);
//		return dirBean;
//	}
//
//	// Only For thread study
//	@RequestMapping("/ThreadIterateDirChildren")
//	@ResponseBody
//	public DirBean ThreadIterateDirChildren(String path) throws Exception {
//		System.out.println("ThreadIterateDirChildren getFileName");
//		if (path == null)
//			return null;
//		ThreadOfIterateDirService threadOfIterateDirService = new ThreadOfIterateDirService();
//		DirBean dirBean = threadOfIterateDirService.getFiles(path);
//		return dirBean;
//	}
//
//	@RequestMapping("/delTreeNode")
//	@ResponseBody
//	public boolean delTreeNode(String path) throws Exception {
//		boolean executionResult = iterateDirService.deletefile(path);
//		return executionResult;
//	}
}
