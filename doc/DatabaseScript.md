# 用户相关


### User

```sql
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `user_account` VARCHAR(50) NOT NULL COMMENT '用户帐号',
  `user_password` VARCHAR(100) NOT NULL COMMENT '用户密码',
  `user_nickname` VARCHAR(50) NOT NULL COMMENT '用户昵称',
  `user_introduction` text NOT NULL COMMENT '用户自述',
  `user_email` VARCHAR(50) DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` VARCHAR(20) DEFAULT NULL COMMENT '用户电话',
  `user_image` VARCHAR(150) DEFAULT NULL COMMENT '用户头像',
  `user_status` int(11) NOT NULL COMMENT '用户状态:0=正常,1=冻结,2=注销',
  `user_create_time` datetime NOT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```


### Photo

```sql
CREATE TABLE `photo` (
  `photo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '照片Id',
  `photo_file_name` VARCHAR(255) NOT NULL COMMENT '文件名',
  `photo_file_origin_name` VARCHAR(255) NOT NULL COMMENT '原文件名',
  `photo_thumbnail_1920x1080` VARCHAR(255) DEFAULT NULL COMMENT '1920x1080缩略图文件名',
  `photo_thumbnail_1024x768` VARCHAR(255) DEFAULT NULL COMMENT '1024x768缩略图文件名',
  `photo_thumbnail_800x600` VARCHAR(255) DEFAULT NULL COMMENT '800x600缩略图文件名',
  `photo_thumbnail_500x375` VARCHAR(255) DEFAULT NULL COMMENT '500x375缩略图文件名',
  `photo_thumbnail_400x300` VARCHAR(255) DEFAULT NULL COMMENT '400x300缩略图文件名',
  `photo_file_size` VARCHAR(255) DEFAULT NULL COMMENT '文件大小',
  `photo_image_width`  VARCHAR(255) DEFAULT NULL COMMENT '图片宽度',
  `photo_image_height`  VARCHAR(255) DEFAULT NULL COMMENT '图片高度',
  `photo_date_time`  VARCHAR(255) DEFAULT NULL COMMENT '拍摄时间',
  `photo_artist` VARCHAR(255) DEFAULT NULL COMMENT '艺术家',
  `photo_image_size` VARCHAR(255) DEFAULT NULL COMMENT '图片尺寸',
  `photo_user_comment` VARCHAR(255) DEFAULT NULL COMMENT '用户评论',
  `photo_exposure_time` VARCHAR(255) DEFAULT NULL COMMENT '曝光时间',
  `photo_f_number` VARCHAR(255) DEFAULT NULL COMMENT '光圈大小',
  `photo_iso_speed_ratings` VARCHAR(255) DEFAULT NULL COMMENT '感光度',
  `photo_focal_length` VARCHAR(255) DEFAULT NULL COMMENT '焦距',
  `photo_metering_mode` VARCHAR(255) DEFAULT NULL COMMENT '测光模式',
  `photo_exposure_mode` VARCHAR(255) DEFAULT NULL COMMENT '曝光模式',
  `photo_exposure_program` VARCHAR(255) DEFAULT NULL COMMENT '曝光程序',
  `photo_flash` VARCHAR(255) DEFAULT NULL COMMENT '闪光灯状态',
  `photo_make` VARCHAR(255) DEFAULT NULL COMMENT '相机厂商',
  `photo_model` VARCHAR(255) DEFAULT NULL COMMENT '相机型号',
  `photo_long_focal_length` VARCHAR(255) DEFAULT NULL COMMENT '镜头长焦距',
  `photo_short_focal_length` VARCHAR(255) DEFAULT NULL COMMENT '镜头短焦距',
  `photo_aperture_value` VARCHAR(255) DEFAULT NULL COMMENT '镜头光圈',
  `photo_software` VARCHAR(255) DEFAULT NULL COMMENT '软件',
  `photo_status` int(11) NOT NULL COMMENT '照片状态:0=正常,1=回收,2=彻底删除',
  `photo_create_time` VARCHAR(255) DEFAULT NULL COMMENT '软件',
  PRIMARY KEY (`photo_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```

```sql
CREATE TABLE `tag` (
  `tag_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '标签Id',
  `tag_name` VARCHAR(25) NOT NULL COMMENT '标签名',
  `tag_describe` TEXT NOT NULL,
  `photo_status` INT(11) NOT NULL COMMENT '标签状态:0=正常,1=回收,2=彻底删除',
  `tag_create_time` DATETIME NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```

```sql
CREATE TABLE `photo_album` (
  `photo_album_id` int auto_increment comment '照片专辑Id',
  `user_id` int not null comment '用户Id',
  `photo_album_name` text not null comment '专辑名字',
  `photo_album_describe` text not null comment '描述',
  `photo_album_central_photo_id` int not null comment '专辑主图Id',
  `photo_album_camera_parameter` int comment '专辑相机参数',
  `photo_album_status` int(11) NOT NULL COMMENT '专辑状态:0=正常,1=回收,2=彻底删除',
  `photo_album_create_time` datetime not null comment '专辑创建时间',
  PRIMARY KEY (`photo_album_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```

```sql
CREATE TABLE `photo_album_photo_list` (
  `id` int primary key auto_increment comment 'id',
  `album_id` int not null comment '专辑id',
  `photo_id` text not null comment '照片id',
  `create_time` datetime not null comment '创建时间');
```

```sql
CREATE TABLE `photo_album_tag_list` (
  `id` int primary key auto_increment,
  `tag` varchar(255) not null,
  `create_time` datetime not null);
```


### Article

```sql
CREATE TABLE `diary` (
  `diary_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '日记Id',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `diary_content` text NOT NULL COMMENT 'name of user in this site',
  `diary_status` int(11) NOT NULL COMMENT '日记状态:0=正常,1=回收,2=彻底删除',
  `create_time` datetime NOT NULL COMMENT 'create this user time',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```


tag_list
photo_list
comment_list


```sql
CREATE TABLE `diary_photo_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `article_id` int(11) NOT NULL,
  `photo_id` text NOT NULL COMMENT 'name of user in this site',
  `create_time` datetime NOT NULL COMMENT 'create this user time',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```


### COMMENT

```sql
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `diary_id` int(11) NOT NULL,
  `comment_content` text NOT NULL COMMENT 'name of user in this site',
  `create_time` datetime NOT NULL COMMENT 'create this user time',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```

commentType:
1 = diary
2 = article

```js
[
  {
    "_id": 1,
    "objectId": 1,
    "commentType": 1,
    "userId": 3,
    "commentContent": "老板，端午节到了，福利需要表示了",
    "like": [
      {
        "userId": 1,
        "createTime": "15:09"
      },{
        "userId": 2,
        "createTime": "15:29"
      }
    ],
    "createTime": "15:00",
    "reply": [
      {
        "replayId": 1,
        "userId": 7,
        "commentContent": "哈哈哈，老哥这广告打的及时",
        "like": [],
        "createTime": "15:02",
        "replyId":null
      },{
        "replayId": 2,
        "userId": 3,
        "commentContent": "家大业大",
        "like": [],
        "createTime": "15:03",
        "replyId":null
      },{
        "replayId": 3,
        "userId": 3,
        "commentContent": "广告肯定得到位",
        "like": [],
        "createTime": "15:08",
        "replyId": 1
      },{
        "replayId": 4,
        "userId": 8,
        "commentContent": "你看老板理你吗",
        "like": [],
        "createTime": "15:04",
        "replyId": null
      }
    ]
  }, {
    "_id": 2,
    "objectId": 1,
    "commentType": 1,
    "userId": 4,
    "commentContent": "给你...一生到老。这是你说的，大将军一言九鼎...战无不胜!",
    "like": [],
    "createTime": "15:02",
    "reply": [
      {
        "replayId": 1,
        "userId": 9,
        "commentContent": "杀破狼？！",
        "like": [],
        "createTime": "15:09",
        "replyId": null
      },{
        "replayId": 2,
        "userId": 4,
        "commentContent": "是！！！",
        "like": [],
        "createTime": "15:10",
        "replyId": 1
      },{
        "replayId": 3,
        "userId": 4,
        "commentContent": "我的白月光[哭][哭][哭]",
        "like": [],
        "createTime": "15:18",
        "replyId": 1
      }
    ]
  }, {
    "_id": 2,
    "objectId": 1,
    "commentType": 1,
    "userId": 5,
    "commentContent": "近来可好",
    "like": [],
    "createTime": "15:03",
    "reply": []
  }, {
    "_id": 2,
    "objectId": 1,
    "commentType": 1,
    "userId": 6,
    "commentContent": "正道的光",
    "like": [],
    "createTime": "15:14",
    "reply": []
  }
]
```


```
评论：

张永鸿: 老板，端午节到了，福利需要表示了  15:00
  |-- 仇一峰: 哈哈哈，老哥这广告打的及时  15:02
  |-- 张永鸿(评论者): 家大业大  15:03
  |-- 张永鸿(评论者) 回复 仇一峰: 广告肯定得到位 15:08
  `-- 朱前超: 你看老板理你吗  15:04

Colmar: 给你...一生到老。这是你说的，大将军一言九鼎...战无不胜!  15:02
  |-- Empty: 杀破狼？！ 15:09
  |-- Colmar(评论者) 回复 Empty: 是！！！ 15:10
  `-- Colmar(评论者) 回复 Empty: 我的白月光[哭][哭][哭]  15:18


毛病陈同学: 近来可好  15:03

二狗小驴: 正道的光  15:14

一条评论包含：评论者，评论内容，评论对应的文章，评论时间，
回复：回复者，回复内容，回复对应的文章，回复对象，回复时间，
```
--------

comment_id_list text comment '评论id列表',
view_user_id_list text comment '浏览过的用户id列表',
like_user_id_list text comment '喜欢过的用户id列表', 


```sql
create table photo_record(
  id int primary key auto_increment,
  photo_name varchar(255) not null,
  user_id int not null,
  tag varchar(255) not null,
  descript varchar(255) not null,
  create_time datetime not null);
```

```sql
create table diary(
  id int primary key auto_increment,
  photo_list varchar(500) not null,
  descript text not null,
  create_time datetime not null);
```






浏览次数
点赞次数



相机参数：遍历专辑中的照片的相机参数，让用户自行选择，用户未选择，则自动选择第一个，若专辑中照片未包含相机参数，则从用户相机列表中选择。

`album_tag` text not null comment '标签',


相机：

图片，型号，名称，适配镜头，

镜头：

图片，型号，名称，适配镜头，




### User 操作日志

```sql
CREATE TABLE `user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'log id',
  `user_id` int(50) NOT NULL COMMENT 'user id',
  `create_time` datetime NOT NULL COMMENT 'create this user time',
  `descript` VARCHAR(500) NOT NULL COMMENT '',
  `server_name` VARCHAR(500) NOT NULL COMMENT '',
  `server_port` VARCHAR(500) NOT NULL COMMENT '',
  `client_host` VARCHAR(500) NOT NULL COMMENT '',
  `client_address` VARCHAR(500) NOT NULL COMMENT '',
  `client_port` VARCHAR(500) NOT NULL COMMENT '',
  `local_name` VARCHAR(500) NOT NULL COMMENT '',
  `local_port` VARCHAR(500) NOT NULL COMMENT '',
  `request_url` VARCHAR(500) NOT NULL COMMENT '',
  `request_query` VARCHAR(500) COMMENT '',
  `request_method` VARCHAR(500) NOT NULL COMMENT '',
  `request_protocol` VARCHAR(500) NOT NULL COMMENT '',
  `request_scheme` VARCHAR(500) NOT NULL COMMENT '',
  `request_origin` VARCHAR(500) NOT NULL COMMENT '',
  `request_referer` VARCHAR(500) NOT NULL COMMENT '',
  `request_cookie` VARCHAR(500) NOT NULL COMMENT '',
  `request_connection` VARCHAR(500) NOT NULL COMMENT '',
  `request_content_length` VARCHAR(500) NOT NULL COMMENT '',
  `request_content_type` VARCHAR(500) NOT NULL COMMENT '',
  `request_accept` VARCHAR(500) NOT NULL COMMENT '',
  `request_accept_encoding` VARCHAR(500) NOT NULL COMMENT '',
  `request_accept_language` VARCHAR(500) COMMENT '',
  `user_agent` VARCHAR(500) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```


## 文章相关

```sql
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL COMMENT 'name of user in this site',
  `tags` text DEFAULT NULL COMMENT 'email of user',
  `create_time` datetime NOT NULL COMMENT 'create this user time',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```

```sql
CREATE TABLE `article_photo_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `article_id` int(11) NOT NULL,
  `photo_id` text NOT NULL COMMENT 'name of user in this site',
  `create_time` datetime NOT NULL COMMENT 'create this user time',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;
```


## 专辑相关

```sql
create table photo_album(
  `id` int primary key auto_increment comment 'id',
  `user_id` int not null comment '作者id',
  `album_name` text not null comment '名字',
  `album_tag` text not null comment '标签',
  `album_describe` text not null comment '描述',
  `central_photo_id` int not null comment '专辑主图id',
  `camera_parameter` text comment '相机参数',
  `create_time` datetime not null comment '创建时间');

insert into photo_album values(1,1,'first','person,city','my first album',1,'canon 5d',now());
into photo_album values(2,1,'second','home,girl','my second album',6,'canon 700d',now());
```

```sql
create table photo_album_photo_list(
  `id` int primary key auto_increment comment 'id',
  `album_id` int not null comment '专辑id',
  `photo_id` text not null comment '照片id',
  `create_time` datetime not null comment '创建时间');
```

```sql
create table photo_album_tag(
  `id` int primary key auto_increment,
  `tag` varchar(255) not null,
  `create_time` datetime not null);
```

```sql
create table photo_album_tag_record(
  `id` int primary key auto_increment,
  `tag_id` int not null,
  `album_id` int not null,
  `create_time` datetime not null);
```

<!-- comment_id_list text comment '评论id列表',
view_user_id_list text comment '浏览过的用户id列表',
like_user_id_list text comment '喜欢过的用户id列表', -->


<!-- ```sql
create table photo_record(
  id int primary key auto_increment,
  photo_name varchar(255) not null,
  user_id int not null,
  tag varchar(255) not null,
  descript varchar(255) not null,
  create_time datetime not null);
```

```sql
create table diary(
  id int primary key auto_increment,
  photo_list varchar(500) not null,
  descript text not null,
  create_time datetime not null);
``` -->



# 测试数据

```sql
insert into photo_albums values(
    1,
    1,
    'first',
    'tagtag',
    'describe',
    1,
    'camere'
    ,now());
```






# Core


## FormData上传照片

通过blob上传二进制图片文件并储存在服务器的指定目录中，然后返回为这个文件随机的名字，这个过程中，很可能会破坏图片的完整性，所以返回名字的同时返回MD5值，方便前端比对上传的完整性，从而尝试重新上传的操作，这样做也会导致服务器中的文件系统存在多份文件，包含着破损的文件，所以前端在上传完成一批文件之后，需要返回上传过，但破损的文件的文件名，让服务器删除多余的文件。


```
`/photo/upload`
获取照片，转为blob，上传后返回name

参数:
- 二进制流
- 文件名
- MD5值

返回: 比较前端MD5值与后端MD5值的匹配，如果相同则返回成功，否则失败
```

```
/album/photo
将照片与专辑关联
```
```
/article
将照片与文章关联
```


相关表的操作：
phtots
albums
articles

## 二进制流上传照片

```
/photo/record
获取recordname，错误md5验证则，在文件系统中删除图片，正确的检索
```



# 技术


## 获取元素数

```java
if(tag.getTagName().equals("File Name")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("File Size")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Data/Time")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Image Width")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Image Height")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Aritst")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("User Comment")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Exposure Time")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("F-Number")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("ISO SPeed Ratings")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Focal Length")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Metering Mode")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Exposure Program")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Flash")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Make")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Model")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Long Focal Length")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Short Focal Length")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Aperture Value")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}else if(tag.getTagName().equals("Software")){
    System.out.println(tag.getTagName()+":"+tag.getDescription());
}
```



## 生成缩略图

```sql
create table photo_thumbnail(
    id int primary key auto_increment,
    photo_id int not null,
	thumbnail_1920x1080 blob not null,
	thumbnail_1024x768 blob not null,
	thumbnail_800x600 blob not null,
	thumbnail_500x375 blob not null,
	thumbnail_400x300 blob not null);
```

```java
public void generateThumbnail(File file) {
        File thumbnail_1920x1080 =  new File("/Users/yovelas/Desktop/thumbnail_1920x1080-"+file.getName());
        File thumbnail_1024x768 =  new File("/Users/yovelas/Desktop/thumbnail_1024x768-"+file.getName());
        File thumbnail_800x600 =  new File("/Users/yovelas/Desktop/thumbnail_800x600-"+file.getName());
        File thumbnail_500x375 =  new File("/Users/yovelas/Desktop/thumbnail_500x375-"+file.getName());
        File thumbnail_400x300 =  new File("/Users/yovelas/Desktop/thumbnail_400x300-"+file.getName());

        try {
            // Scale to original scale
            Thumbnails.of(file).size(1920,1080).toFile(thumbnail_1920x1080);
            Thumbnails.of(file).size(1024,768).toFile(thumbnail_1024x768);
            Thumbnails.of(file).size(800,600).toFile(thumbnail_800x600);
            Thumbnails.of(file).size(500,375).toFile(thumbnail_500x375);
            Thumbnails.of(file).size(400,300).toFile(thumbnail_400x300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```


## Java判断文件是否是图片


1. 判断文件后缀后

这种判断用得比较多，不过非常不妥，把一个不是图片的文件的扩展名修改为图片的扩展名，就绕开了校验。

```java
String extension = "";
int i = fileName.lastIndexOf('.');
if (i > 0) {
    extension = fileName.substring(i+1);
}
//...
if("jpg".equals(extension)){
    //your code
}
```


2. 判断文件头

在后缀未知，或者后缀被修改的文件，可以使用文件头来判断文件是什么文件类型。以下是常见的图片类型的文件头字符(16进制)

```sh
JPEG(jpg),文件头:FFD8FF
PNG(png),文件头:89504E47
GIF(gif),文件头:47494638 
TIFF(tif)，文件头:49492A00 
Windows Bitmap(bmp),文件头:424D
```


3. 通过MimetypesFileTypeMap判断

```java
public class ImageCheck {
    private  MimetypesFileTypeMap mtftp;
    public ImageCheck(){
        mtftp = new MimetypesFileTypeMap();
        /* 不添加下面的类型会造成误判 详见：http://stackoverflow.com/questions/4855627/java-mimetypesfiletypemap-always-returning-application-octet-stream-on-android-e*/
        mtftp.addMimeTypes("image png tif jpg jpeg bmp");
    }
    public  boolean isImage(File file){
        String mimetype= mtftp.getContentType(file);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }
}
```

4. 通过ImageIO判断(适用bmp/gif/jpg/png格式)

```java
try {
    // 通过ImageReader来解码这个file并返回一个BufferedImage对象
    // 如果找不到合适的ImageReader则会返回null，我们可以认为这不是图片文件
    // 或者在解析过程中报错，也返回false
    Image image = ImageIO.read(file);
    return image != null;
} catch(IOException ex) {
    return false;
}
```

5. MimeType

媒体类型(MimeType)的文件都有标识符，图片属于媒体文件，因此可以通过编解码的方式判断图片是否合法。

```java
private static boolean isBMP(byte[] buf){
	byte[] markBuf = "BM".getBytes();  //BMP图片文件的前两个字节
	return compare(buf, markBuf);
}
	
private static boolean isICON(byte[] buf) {
	byte[] markBuf = {0, 0, 1, 0, 1, 0, 32, 32};
	return compare(buf, markBuf);
}

private static boolean isWEBP(byte[] buf) {
	byte[] markBuf = "RIFF".getBytes(); //WebP图片识别符
	return compare(buf, markBuf);
}

private static boolean isGIF(byte[] buf) {
	byte[] markBuf = "GIF89a".getBytes(); //GIF识别符
	if(compare(buf, markBuf)) {
		return true;
	}
	markBuf = "GIF87a".getBytes(); //GIF识别符
	if(compare(buf, markBuf)) {
		return true;
	}
	return false;
}
	
private static boolean isPNG(byte[] buf) {
	byte[] markBuf = {(byte) 0x89,0x50,0x4E,0x47,0x0D,0x0A,0x1A,0x0A}; //PNG识别符
	// new String(buf).indexOf("PNG")>0 //也可以使用这种方式
	return compare(buf, markBuf);
}

private static boolean isJPEGHeader(byte[] buf) {
	byte[] markBuf = {(byte) 0xff, (byte) 0xd8}; //JPEG开始符
	return compare(buf, markBuf);
}
	
private static boolean isJPEGFooter(byte[] buf) {
	byte[] markBuf = {(byte) 0xff, (byte) 0xd9}; //JPEG结束符
	return compare(buf, markBuf);
}


/**
	 * 获取文件的mimeType
	 * @param filename
	 * @return
	 */
	private static String getMimeType(String filename){
		try {
			String mimeType = readType(filename);
			return String.format("image/%s", mimeType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取文件类型
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static String readType(String filename) throws IOException {
		
		FileInputStream fis = null;
		try {
			File f = new File(filename);
			if(!f.exists() || f.isDirectory() || f.length()<8) {
				throw new IOException("the file ["+f.getAbsolutePath()+"] is not image !");
			}
			
			fis= new FileInputStream(f);
			byte[] bufHeaders = readInputStreamAt(fis,0,8);
			if(isJPEGHeader(bufHeaders))
			{	
				long skiplength = f.length()-2-8; //第一次读取时已经读了8个byte,因此需要减掉
				byte[] bufFooters = readInputStreamAt(fis, skiplength, 2);
				if(isJPEGFooter(bufFooters))
				{
					return "jpeg";
				}
			}
			if(isPNG(bufHeaders))
			{
				return "png";
			}
			if(isGIF(bufHeaders)){
				
				return "gif";
			}
			if(isWEBP(bufHeaders))
			{
				return "webp";
			}
			if(isBMP(bufHeaders))
			{
				return "bmp";
			}
			if(isICON(bufHeaders))
			{
				return "ico";
			}
			throw new IOException("the image's format is unkown!");
			
		} catch (FileNotFoundException e) {
			throw e;
		}finally{
			try {
				if(fis!=null) fis.close();
			} catch (Exception e) {
			}
		}
		
	}

	
	/**
	 * 标示一致性比较
	 * @param buf  待检测标示
	 * @param markBuf 标识符字节数组
	 * @return 返回false标示标示不匹配
	 */
	private static boolean compare(byte[] buf, byte[] markBuf) {
		for (int i = 0; i < markBuf.length; i++) {
			byte b = markBuf[i];
			byte a = buf[i];
			
			if(a!=b){
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param fis 输入流对象
	 * @param skiplength 跳过位置长度
	 * @param length 要读取的长度
	 * @return 字节数组
	 * @throws IOException
	 */
	private static byte[] readInputStreamAt(FileInputStream fis, long skiplength, int length) throws IOException
	{
		byte[] buf = new byte[length];
		fis.skip(skiplength);  //
		int read = fis.read(buf,0,length);
		return buf;
	}
```