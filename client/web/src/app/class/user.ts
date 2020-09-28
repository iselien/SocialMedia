export class User {
    public id:number;
    public username:String;
    public nickname:String;
    public email:String;
    public phone:String;
    public image:String;
    public createTime:String;
    public logged:Boolean;
    constructor(){};
    setInfo(id,username,nickname,email,phone,image,createTime){
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.createTime = createTime;

    }
}
