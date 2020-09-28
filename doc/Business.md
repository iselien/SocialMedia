# 用户


### 登录

1. 登录方式：帐号+密码，手机号+密码，邮箱+密码


### 注册

1. 自动生成用户帐号(`user_account`)，纯数字，12位数，生成要确保不重复。
2. 


### 安全验证

1. 何时验证：登录时密码错误超过3次，异地登录，新设备登录

2. 验证方式：

- 用户历史头像超3个，选择最近用过的1个头像。
- 用户发布过的图片超过1张，选择最近发布的图片
- 输入验证码。
- 邮箱验证
- 手机短信



## Comment

```java
private int _id;
private int objectId;
private int userId;
private int commentType;
private String commentContent;
private int commentStatus;
private String commentTime;
private List<Like> like;
private List<Reply> reply;
```


## Like

```java
private int userId;
private int likeStatus;
private String likeTime;
```


## Reply

```java
private int id;
private int userId;
private int replayId;
private String commentContent;
private int commentStatus;
private String commentTime;
private List<Like> like;
```

```js
{
    "_id": "评论Id",
    "objectId": "评论对象Id",
    "userId": "用户Id",
    "commentType": "评论类型:1=diary,2=article",
    "commentContent": "评论内容",
    "commentStatus": "评论状态",
    "commentTime": "评论时间",
    "like": [  // 点赞列表
        {
            "userId": "用户Id",
            "likeStatus": "点赞状态",
            "likeTime": "点赞时间"
        }
    ],
    "reply": [  // 回复列表
        "id": "回复Id",
        "userId": "用户Id",
        "replayId": "回复对象",
        "replyContent": "回复内容",
        "replyStatus": "回复状态",
        "replyTime": "回复时间",
        "like": [  // 点赞列表
            {
                "userId": "用户Id",
                "likeStatus": "点赞状态",
                "createTime": "点赞时间"
            }
        ]
    ]
}
```
