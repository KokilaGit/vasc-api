class UserController{
    constructor(){
        this.currentId = 0;
        this.feedbackArray = [];

    }


addUser(fName,lName,email,feedback){
 const userObj={
    fName,
    lName,
    email,
    feedback,
    id:this.currentId++
 }
this.feedbackArray.push(userObj)

}
setLocalStorage(){
    localStorage.setItem("users",JSON.stringify(this.feedbackArray))
}
}  