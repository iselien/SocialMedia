package io.yovelas.common;

/**
 * 常量类，规范代码，更加易读
 */

public class Constants {
    public static final String URL = "url";
    public static final String BACK_RECORDID= "0";
    public static final String PAYLOAD = "payload";
    public static final String SEX = "sex";
    public static final String NICK_NAME = "nickname";
    public static final String AVATAR = "avatar";
    public static final String SIGN = "sign";
    public static final String TO_UID = "toUid";
    public static final String FROM_LIVE_ROOM = "fromLiveRoom";
    public static final String TO_NAME = "toName";
    public static final String STREAM = "stream";
    public static final String LIMIT = "limit";
    public static final String UID = "uid";

    public static final String MERCHID = "merchid";

    public static final String TIP = "tip";
    public static final String SHOW_INVITE = "showInvite";
    public static final String USER_BEAN = "userBean";
    public static final String CLASS_ID = "classID";
    public static final String CLASS_NAME = "className";
    public static final String CHECKED_ID = "checkedId";
    public static final String CHECKED_COIN = "checkedCoin";
    public static final String LIVE_DANMU_PRICE = "danmuPrice";
    public static final String COIN_NAME = "coinName";
    public static final String LIVE_BEAN = "liveBean";
    public static final String LIVE_TYPE = "liveType";
    public static final String LIVE_KEY = "liveKey";
    public static final String LIVE_POSITION = "livePosition";
    public static final String LIVE_TYPE_VAL = "liveTypeVal";
    public static final String LIVE_UID = "liveUid";
    public static final String LIVE_STREAM = "liveStream";
    public static final String LIVE_HOME = "liveHome";
    public static final String LIVE_FOLLOW = "liveFollow";
    public static final String LIVE_NEAR = "liveNear";
    public static final String LIVE_CLASS_PREFIX = "liveClass_";
    public static final String LIVE_ADMIN_ROOM = "liveAdminRoom";
    public static final String HAS_GAME = "hasGame";
    public static final String OPEN_FLASH = "openFlash";
    public static final String SHARE_QR_CODE_FILE = "shareQrCodeFile.png";
    public static final String ANCHOR = "anchor";
    public static final String FOLLOW = "follow";
    public static final String DIAMONDS = "钻石";
    public static final String VOTES = "映票";
    public static final String PAY_ALI_NOT_ENABLE = "支付宝未接入";
    public static final String PAY_WX_NOT_ENABLE = "微信支付未接入";
    public static final String PAY_ALL_NOT_ENABLE = "未开启支付";
    public static final String PAY_TYPE_ALI = "ali";
    public static final String PAY_TYPE_WX = "wx";
    public static final String PAY_BUY_COIN_ALI = "Charge.getAliOrder";
    public static final String PAY_BUY_COIN_WX = "Charge.getWxOrder";

    public static final String PACKAGE_NAME_ALI = "com.eg.android.AlipayGphone";//支付宝的包名
    public static final String PACKAGE_NAME_WX = "com.tencent.mm";//微信的包名
    public static final String PACKAGE_NAME_QQ = "com.tencent.mobileqq";//QQ的包名
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String ADDRESS = "address";
    public static final String SCALE = "scale";
    public static final String SELECT_IMAGE_PATH = "selectedImagePath";
    public static final String COPY_PREFIX = "copy://";
    public static final int GUARD_TYPE_NONE = 0;
    public static final int GUARD_TYPE_MONTH = 1;
    public static final int GUARD_TYPE_YEAR = 2;

    public static final String GIF_GIFT_PREFIX = "gif_gift_";
    public static final String GIF_CAR_PREFIX = "gif_car_";
    public static final String DOWNLOAD_MUSIC = "downloadMusic";
    public static final String LINK = "link";
    public static final String REPORT = "report";
    public static final String SAVE = "save";
    public static final String DELETE = "delete";
    public static final String SHARE_FROM = "shareFrom";
    public static final String GOODS = "goods";

    public static final int SHARE_FROM_LIVE = 101;
    public static final int SHARE_FROM_HOME = 102;
    public static final int SETTING_MODIFY_PWD = 15;
    public static final int SETTING_UPDATE_ID = 16;
    public static final int SETTING_CLEAR_CACHE = 18;
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 2;
    public static final int FOLLOW_FROM_FOLLOW = 1002;
    public static final int FOLLOW_FROM_FANS = 1003;
    public static final int FOLLOW_FROM_SEARCH = 1004;
    public static final String IM_FROM_HOME = "imFromHome";
    //直播房间类型
    public static final int LIVE_TYPE_NORMAL = 0;//普通房间
    public static final int LIVE_TYPE_PWD = 1;//密码房间
    public static final int LIVE_TYPE_PAY = 2;//收费房间
    public static final int LIVE_TYPE_TIME = 3;//计时房间
    //主播直播间功能
    public static final int LIVE_FUNC_BEAUTY = 2001;//美颜
    public static final int LIVE_FUNC_CAMERA = 2002;//切换摄像头
    public static final int LIVE_FUNC_FLASH = 2003;//闪光灯
    public static final int LIVE_FUNC_MUSIC = 2004;//伴奏
    public static final int LIVE_FUNC_SHARE = 2005;//分享
    public static final int LIVE_FUNC_GAME = 2006;//游戏
    public static final int LIVE_FUNC_RED_PACK = 2007;//红包
    public static final int LIVE_FUNC_LINK_MIC = 2008;//连麦
    //socket
    public static final String SOCKET_CONN = "conn";
    public static final String SOCKET_BROADCAST = "broadcastingListen";
    public static final String SOCKET_SEND = "broadcast";
    public static final String SOCKET_STOP_PLAY = "stopplay";//超管关闭直播间
    public static final String SOCKET_STOP_LIVE = "stopLive";//超管关闭直播间
    public static final String SOCKET_SEND_MSG = "SendMsg";//发送文字消息，点亮，用户进房间  PS:这种混乱的设计是因为服务器端逻辑就是这样设计的,客户端无法自行修改
    public static final String SOCKET_LIGHT = "light";//飘心
    public static final String SOCKET_SEND_GIFT = "SendGift";//送礼物
    public static final String SOCKET_SEND_BARRAGE = "SendBarrage";//发弹幕
    public static final String SOCKET_LEAVE_ROOM = "disconnect";//用户离开房间
    public static final String SOCKET_LIVE_END = "StartEndLive";//主播关闭直播
    public static final String SOCKET_SYSTEM = "SystemNot";//系统消息
    public static final String SOCKET_KICK = "KickUser";//踢人
    public static final String SOCKET_SHUT_UP = "ShutUpUser";//禁言
    public static final String SOCKET_SET_ADMIN = "setAdmin";//设置或取消管理员
    public static final String SOCKET_CHANGE_LIVE = "changeLive";//切换计时收费类型
    public static final String SOCKET_UPDATE_VOTES = "updateVotes";//门票或计时收费时候更新主播的映票数
    public static final String SOCKET_FAKE_FANS = "requestFans";//僵尸粉
    public static final String SOCKET_LINK_MIC = "ConnectVideo";//连麦
    public static final String SOCKET_LINK_MIC_ANCHOR = "LiveConnect";//主播连麦
    public static final String SOCKET_LINK_MIC_PK = "LivePK";//主播PK
    public static final String SOCKET_BUY_GUARD = "BuyGuard";//购买守护
    public static final String SOCKET_RED_PACK = "SendRed";//红包
    public static final String SOCKET_LUCK_WIN = "luckWin";//幸运礼物中奖
    public static final String SOCKET_PRIZE_POOL_WIN = "jackpotWin";//奖池中奖
    public static final String SOCKET_PRIZE_POOL_UP = "jackpotUp";//奖池升级
    //游戏socket
    public static final String SOCKET_GAME_ZJH = "startGame";//炸金花
    public static final String SOCKET_GAME_HD = "startLodumaniGame";//海盗船长
    public static final String SOCKET_GAME_NZ = "startCattleGame";//开心牛仔
    public static final String SOCKET_GAME_ZP = "startRotationGame";//幸运转盘
    public static final String SOCKET_GAME_EBB = "startShellGame";//二八贝

    public static final int SOCKET_WHAT_CONN = 0;
    public static final int SOCKET_WHAT_DISCONN = 2;
    public static final int SOCKET_WHAT_BROADCAST = 1;
    //socket 用户类型
    public static final int SOCKET_USER_TYPE_NORMAL = 30;//普通用户
    public static final int SOCKET_USER_TYPE_ADMIN = 40;//房间管理员
    public static final int SOCKET_USER_TYPE_ANCHOR = 50;//主播
    public static final int SOCKET_USER_TYPE_SUPER = 60;//超管

    //提现账号类型，1表示支付宝，2表示微信，3表示银行卡
    public static final int CASH_ACCOUNT_ALI = 1;
    public static final int CASH_ACCOUNT_WX = 2;
    public static final int CASH_ACCOUNT_BANK = 3;
    public static final String CASH_ACCOUNT_ID = "cashAccountID";
    public static final String CASH_ACCOUNT = "cashAccount";
    public static final String CASH_ACCOUNT_TYPE = "cashAccountType";


    public static final int RED_PACK_TYPE_AVERAGE = 0;//平均红包
    public static final int RED_PACK_TYPE_SHOU_QI = 1;//拼手气红包
    public static final int RED_PACK_SEND_TIME_NORMAL = 0;//立即发放
    public static final int RED_PACK_SEND_TIME_DELAY = 1;//延时发放

    public static final int JPUSH_TYPE_NONE = 0;
    public static final int JPUSH_TYPE_LIVE = 1;//直播
    public static final int JPUSH_TYPE_MESSAGE = 2;//消息

    public static final String VIDEO_HOME = "videoHome";
    public static final String VIDEO_USER = "videoUser_";
    public static final String VIDEO_KEY = "videoKey";
    public static final String VIDEO_POSITION = "videoPosition";
    public static final String VIDEO_SINGLE = "videoSingle";
    public static final String VIDEO_PAGE = "videoPage";
    public static final String VIDEO_BEAN = "videoBean";
    public static final String VIDEO_ID = "videoId";
    public static final String VIDEO_COMMENT_BEAN = "videoCommnetBean";
    public static final String VIDEO_FACE_OPEN = "videoOpenFace";
    public static final String VIDEO_FACE_HEIGHT = "videoFaceHeight";
    public static final String VIDEO_DURATION = "videoDuration";
    public static final String VIDEO_PATH = "videoPath";
    public static final String VIDEO_FROM_RECORD = "videoFromRecord";
    public static final String VIDEO_MUSIC_BEAN = "videoMusicBean";
    public static final String VIDEO_MUSIC_ID = "videoMusicId";
    public static final String VIDEO_HAS_BGM = "videoHasBgm";
    public static final String VIDEO_MUSIC_NAME_PREFIX = "videoMusicName_";
    public static final String VIDEO_SAVE_TYPE = "videoSaveType";
    public static final int VIDEO_SAVE_SAVE_AND_PUB = 1;//保存并发布
    public static final int VIDEO_SAVE_SAVE = 2;//仅保存
    public static final int VIDEO_SAVE_PUB = 3;//仅发布

    public static final String MOB_QQ = "qq";
    public static final String MOB_QZONE = "qzone";
    public static final String MOB_WX = "wx";
    public static final String MOB_WX_PYQ = "wchat";
    public static final String MOB_FACEBOOK = "facebook";
    public static final String MOB_TWITTER = "twitter";
    public static final String MOB_PHONE = "phone";

    public static final String LIVE_SDK = "liveSdk";
    public static final String LIVE_KSY_CONFIG = "liveKsyConfig";
    public static final int LIVE_SDK_KSY = 0;//金山推流
    public static final int LIVE_SDK_TX = 1;//腾讯推流


    public static final int LINK_MIC_TYPE_NORMAL = 0;//观众与主播连麦
    public static final int LINK_MIC_TYPE_ANCHOR = 1;//主播与主播连麦

    public static final String HAVE_STORE = "haveStore";


    public static final String MONEY_SIGN = "¥";



    /* 补丁版本号 */
    public final static int patchCode = 0;

    /*  此处用于识别   handler  回调的 msg.what   */
    public final static int FacePayRecognizeCode = 11;
    public final static int ScannerPayRecognizeCode = 12;
    public final static int WxCodeScannerRecognizeCode = 13;
    public final static int printerCallBackCode = 14;
    public final static int networkSpeedCallBackCode = 15;
    public final static int FacePayGetFaceCodeRecognizeCode = 16;
    public final static int FacePayGetOpenIdRecognizeCode = 17;

    public final static int LogInRecognizeCode = 18;
    public final static int LogOutRecognizeCode = 19;

    public final static int REQUEST_CODE_MEMBER_PAY = 21;
    public final static int REQUEST_CODE_MEMBER_CARD = 22;

    public final static int REQUEST_CODE_WRITE_SETTINGS = 31;  /* 系统设置权限回调 */
    public final static int ACTION_MANAGE_OVERLAY_PERMISSION = 32;  /* 系统悬浮窗设置权限回调 */
    public final static int ACTION_SYSTEM_SETTING = 33;  /* 系统设置回调 */

    public final static int getClubCardRequest = 34; /* 获取会员卡 */

    public final static int RequestPermissionCode = 100;  /* 权限请求码 */

    final static public int uploadData = 501;  /* 上传数据 */

    final static public int downloadImage = 502;  /* 上传数据 */

    /* 此处修改，需要更新数据库版本才能生效 */
    public final static int SingleCashier = 101;  /* 独立收银 */
    public final static int SuperScanner = 102;  /* 超级扫码盒 */
    public final static int DeepConnect = 103;  /* 深度对接 */
    public final static int TestMode = 104; /* 测试模式 */

    public final static int aibao = 111;  /* 爱宝 */
    public final static int sixun = 112;  /* 思迅商云 */
    public final static int kemai = 113;  /* 科脉智赢 */
    public final static int yinbao = 114;  /* 银豹 */
    public final static int zhibaiwei = 115;  /* 智百威 */
    public final static int hainanchangying = 116;  /* 海南长影 */
    public final static int kemaiqimou = 117;  /* 科脉启某 */
    public final static int jiawei = 118;  /* 佳为 */
    public final static int CashierAssistant = 119;  /* 收银助手 */
    /* 收银系统名称 */
    public final static String aibaoName = "通用单行";
    public final static String sixunName = "思迅商云";
    public final static String kemaiName = "科脉智赢";
    public final static String yinbaoName = "银豹";
    public final static String zhibaiweiName = "智百威";
    public final static String hainanchangyingName = "海南长影";
    public final static String kemaiqimouName = "科脉启谋";
    public final static String jiaweiName = "佳为";
    public final static String CashierAssistantName = "收银助手";

    /* 工作模式 */
    public final static int jiaoban = 131;  /* 交班 */
    public final static int bujiaoban = 132;  /* 不交班 */

    /* 工作模式 */
    public final static String  jiaobanName = "交班模式";  /* 交班 */
    public final static String  bujiaobanName = "无交班模式";  /* 不交班 */


    /* 应用设置 */
    public final static int printerNumber = 121;  /* 打印小票数量 */

    public final static String printerNumberName = "打印小票数量";

    public final static int printerNum = 1; /* 默认打印数量 */

    public final static int maxPrinterNumber = 3; /* 最大打印数量 */


    /* 收银模式介绍 */
    public final static String SingleCashierIntroduct = "单独作为收银系统，在收银员通过键盘输入金额后，客户点击客户端右上方的刷脸支付按钮进行刷脸支付，或者通过出示手机付款二维码对准扫码枪进行付款操作。";
    public final static String SuperScannerIntroduct = "作为超级扫码盒，客户端必须（单工）连接收银机，无需收银员通过键盘输入金额，当用户点击刷脸按钮或者出示付款二维码后，"
            +"客户端传输付款码值到收银机，收银机进行调起支付。";
    public final static String DeepConnectIntroduct = "深度对接模式，对接市面上大部分的收银系统，客户端必须（双工）连接收银机，设备通过客户端获取收银机传输过来的付款金额，"
            + "无需收银员通过键盘输入金额，客户可以通过点击右上方刷脸支付按钮，或者出示付款二维码进行支付。";
    public final static String TestModeIntroduct = "获取收银系统数据，并且上传服务器进行分析";
    /* 收银模式名称 */
    public final static String SingleCashierName = "独立收银";
    public final static String SuperScannerName = "超级扫码盒";
    public final static String DeepConnectName = "深度对接";
    public final static String TestModeName = "测试模式";
    /* 选择 */
    public final static int choosen = 1;  /* 选择 */
    public final static int unchoosen = 0;  /* 不选择 */


    /* 刷脸支付方法 */
    public final static String  face = "0";  /* 人脸付款码 */
    public final static String scanner = "1";  /* 刷卡付款码 */
    /* 支付成功页是否需要展示人脸识别授权项 */
    public final static String show = "1";  /* 展示 */
    public final static String unshow = "0";  /* 不展示 */
    /* 商户端是否对SDK返回支付结果 */
    public final static String updateResult = "0";  /* 更新结果 */
    public final static String unupdateResult = "1";  /* 不更新结果 */
    /* 更新付款结果 */
    public final static String SUCCESS = "SUCCESS";
    public final static String ERROR = "ERROR";
    /* 延迟支付 */
    public final static String FACEPAY = "FACEPAY";
    public final static String FACEPAY_DELAY = "FACEPAY_DELAY";


    public final static int initTencentLongTextTts = 201;
    public final static int initTencentRealTimeTts = 202;

    public static final int TEST_TYPE_NONE = 401;
    public static final int TEST_TYPE_OBO = 402;
    public static final int TEST_TYPE_OBN = 403;
    public static final int TEST_TYPE_OBN_ONCE = 404;

    /* 刷脸支付方式 */
    public final static String ClubFacePay = "会员刷脸支付";
    public final static String CommonFacePay = "普通刷脸支付";
    public final static String ClubScannerPay = "会员扫码支付";
    public final static String CommonScannerPay = "普通扫码支付";

    /* 提示语句 */
    public final static String ScannerPayAlipay= "支付宝扫码支付";
    public final static String ScannerPayWechatPay= "微信扫码支付";
    public final static String FacePayWeChatPay= "微信刷脸支付";

    public final static String Alipay = "支付宝";
    public final static String Wechat= "微信";

    public final static String ScannerPayRequestTimeOut = "扫码支付超时";
    public final static String ScannerPayResponseTimeOut = "支付失败，请撤销订单";
    public final static String ScannerPaySuccess = "扫码支付成功，欢迎下次光临";
    public final static String ScannerPayFail = "扫码支付失败，请重新支付";
    public final static String ScannerPayError = "扫码支付失败，请联系工作人员";
    public final static String ScannerCodeTimeOut = "付款码超时，请刷新付款码重新支付";
    public final static String ScannerPayCancel = "取消扫码支付，请重新提交订单";
    public final static String ScannerPayTimeOut = "订单操作超时";
    public final static String NullPointerException = "空指针异常";
    public final static String ScannerPay= "扫码支付";
    public final static String ServiceUnconnected = "刷脸服务连接异常，请重新提交支付";
    public final static String FacePayCancel = "取消刷脸支付，请重新提交订单";
    public final static String UserCancelPay = "用户取消支付";
    public final static String CashierCancelPay = "收银员取消支付";
    public final static String FacePaySuccess = "刷脸支付成功，欢迎下次光临";
    public final static String FacePayGetPriceFail = "获取商品金额失败，请重新提交订单";
    public final static String FacePayFail = "刷脸支付失败，请重新支付";
    public final static String FacePay= "刷脸支付";
    public final static String UnknowWrong= "未知错误";
    public final static String UnknowUser= "未知用户";
    public final static String NetworkUnable= "网络不可用，请检查网络状态";

    public final static String startMemberPay= "调起会员支付";
    public final static String RequestTimeout= "请求超时";
    public final static String AuthInfoFail= "获取刷脸凭证失败";
    public final static String FacePayDataError= "刷脸支付数据异常";

    public final static String WeChatArrivalAccount= "微信收款到账";
    public final static String AlipayArrivalAccount= "支付宝收款到账";

    public final static String clickTooFast= "点击过于频繁";

    public final static String UnknowLocation= "未知位置";

    /* 打印机打印类型 */
    public final static String printOrder = "printOrder";
    public final static String printScannerPayCode = "printScannerPayCode";
    public final static String printBarCode = "printBarCode";
    public final static String printMemorandum = "printMemorandum";
    public final static String printClubOrder = "printClubOrder";
    public final static String printHandover = "printHandover";
    public final static String RepairPrintClubTicket = "RepairPrintClubTicket";         //补打小票


    public final static String PaySuccess = "支付成功";
    public final static String TimeOut = "操作超时";
    public final static String UserCancel = "用户取消";

    public final static int pay_closeTime = 60; /* 扫码支付超时时间 */
    public final static String scannerPayTimeout="扫码支付超时";

    public final static String AppSetting = "应用设置"; //应用设置
    public final static String SystemSetting = "系统设置"; //系统设置

    public static final String RETURN_CODE = "return_code";
    public static final String RETURN_MSG = "return_msg";

    public static final int RESET_MONEY = 0 ;

    /* pay页面关闭倒计时 */
    public static final int pay_activity_closeTime = 60 ;

    /* 扫码支付返回结果 */
    public final static String ScannerPayCodeTimeOut= "二维码已经过期，请点击右上角操作“暂停使用”后，重新开通";


    /* 刷脸支付返回结果 */
    public final static String USER_CANCEL= "USER_CANCEL";
    public final static String SCAN_PAYMENT= "SCAN_PAYMENT";
    public final static String FACE_UNKNOWN= "FACE_UNKNOWN";



    /* 键盘值 */
    public static final int ESC = 21;
    public static final int PAY = 23;
    public static final int OPT = 29;
    public static final int LIST = 30;
    public static final int REFUND = 12;

    /* 键盘输入，语音提示 */
    public final static String  keyboardPayNotify = "请点击刷脸支付或出示付款二维码";
    public final static String  keyboardEscNotify = "取消支付";
    public final static String  btnFacePayNotify = "请面向摄像头刷脸";
    public final static String  keyboardDisabled = "尚未开通该功能，敬请期待";



    /* 系统设置 item 编号 */
    public final static int cashierMode = 401;  /* 收银模式 */
    public final static int cashierSystem = 402;  /* 收银系统 */
    public final static int applicationSetting = 403;  /* 应用设置 */
    public final static int ethernetSetting = 404;  /* 以太网设置 */
    public final static int wifiSetting = 405;  /* wifi无线网络设置 */
    public final static int showSetting = 406;  /* 显示设置 */
    public final static int homeSetting = 407;  /* 返回主屏幕 */
    public final static int stateSetting = 408;  /* 设备状态 */
    public final static int voiceSetting = 409;  /* 音量设置 */
    public final static int checkVersion = 410;  /* 检查软件版本更新 */
    public final static int systemSetting = 411;  /* 系统设置 */
    public final static int downloadSetting = 412;  /* 手动下载 */
    public final static int workMode = 413;  /* 工作模式 */
    public final static int workManager = 414;  /* 交班管理 */
    public final static int tradeDetail = 415;  /* 交易明细 */

    /* 系统设置列表key名称 */
    public final static String  OPTION_NAME = "optionname";
    public final static String  OPTION_VAL = "optionval";
    public final static String  OPTION_IMAGE = "optionimage";
    public final static String  OPTION_CONTENT_INDEX = "content_index";  /* listview item 中点开后的内容的下标 */
    public final static String  OPTION_INTRODUCT = "introduct";
    public final static String  OPTION_CONTENT_ID = "content_id";/* listview item 中点开后的内容的 编号 */
    public final static String  OPTION_ID = "id";  /* listview item 的 编号 */




    /* 串口参数 */
    public final static int count = 1; /* 结算 */
    public final static int uncount = 0; /* 不结算 */
    public final static int playVoice = 1;  /* 播放语音 */
    public final static int unplayVoice = 0;/* 不播放语音 */



    /* 支付的最大金额 */
    final static public double maxPrice = 9999999.99d;  /* 上传数据 */



    /* 网络状态 */
    public final static String  networkByMOBILE = "正在使用2G/3G/4G网络";
    public final static String  networkByWIFI = "正在使用wifi上网";
    public final static String  networkByETHERNET = "正在使用wifi上网";
    public final static String  networkByBLUETOOTH = "正在使用蓝牙上网";
    public final static String  networkByDEFAULT = "已连接网络";
    public final static String  networkByNONE = "当前无网络连接";

    public final static String  connecting = "连接中...";
    public final static String  checking = "正在验证身份信息...";
    public final static String  gettingIp = "正在获取IP地址...";
    public final static String  connectFail = "连接失败";
    public final static String  connected = "已连接无线网络";
    public final static String  disconnected = "未连接网络";

    /* wifi 加密方式 */
    public final static int WIFICIPHER_WPA = 701;
    public final static int WIFICIPHER_WEP = 702;
    public final static int WIFICIPHER_NOPASS = 703;

    public final static String WPA_PSK = "WPA PSK";
    public final static String WPA2_PSK = "WPA2 PSK";
    public final static String NONE = "无";
    public final static String WEP = "WEP";


    /* 服务器信息加载 */
    public final static int getDomainNameRequest = 241; /* 获取授权域名 */
    public final static int domainNameResponseSuccess = 242 ; /* 获取授权域名成功 */
    public final static int domainNameResponseDeviceNull = 243 ; /* 设备号为空，导致无法获取授权域名 */
    public final static int domainNameResponseChooseItem = 244 ; /* 返回域名列表，展示列表进行选择绑定 */
    public final static int domainNameResponseException = 245 ; /* 异常报错 */
    public final static int domainNameResponseTimeout = 246 ; /* 获取授权域名超时 */
    public final static int domainNameResponseExist = 247 ; /* 获取授权域名已存在 */
    public final static int domainNameResponseGetting = 248 ; /* 授权域名正在获取 */


    public final static int getSystemConfigRequest = 251; /* 获取刷脸支付参数信息 */
    public final static int systemConfigResponseSuccess = 252 ; /* 获取刷脸支付参数信息成功 */
    public final static int systemConfigResponseDomainNull = 253 ; /* 授权域名为空，导致无法获取刷脸参数 */
    public final static int systemConfigResponseTimeout = 254 ; /* 获取刷脸参数超时 */
    public final static int systemConfigResponseException = 255 ; /* 异常报错 */
    public final static int systemConfigResponseFail = 256 ; /* 获取刷脸支付参数信息成功失败 */
    public final static int systemConfigResponseExist = 257 ; /* 获取刷脸支付参数信息已存在 */
    public final static int systemConfigResponseGetting = 258 ; /* 获取刷脸支付参数信息正在获取 */

    public final static int logInResponseTimeout = 261 ; /* 登入超时 */
    public final static int logOutResponseTimeout = 262 ; /* 登出超时 */

    /* Toast 类型 */
    public final static int  ToastError = 301;
    public final static int  ToastSuccess = 302;
    public final static int  ToastInfo = 303;
    public final static int  ToastWarning = 304;
    public final static int  ToastNormal = 305;
    public final static int  ToastCustom = 306;


    /* 定时器 */
    public final static int  TextActionWhat = 451;

}
