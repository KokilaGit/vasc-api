class UserController{
    constructor(){
        this.currentId = 0;
        this.feedbackArray = [];

    }


addUser(firstName,lastName,emailId,feedBack){
 const userObj={
    firstName,
    lastName,
    emailId,
    feedBack,
    id:this.currentId++
 }
this.feedbackArray.push(userObj)

}
setLocalStorage(){
    localStorage.setItem("users",JSON.stringify(this.feedbackArray))
}

   async postFb(fbDetails) {
   console.log(' Selvi JSON.stringify(fbDetails) ' + JSON.stringify(fbDetails));
    let response = await fetch("/api/feedbacks/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        // JSON.stringify() turns our object into a string

        body: JSON.stringify(fbDetails),
    });
        if(response.status == 200 || response.status == 201 ){

        alert('Your feedback has been submitted');

        }
        //console.log(response.statusText); // OK

    let fbPostResponse = await response.json();
    console.log(' fbPostResponse' + fbPostResponse );
    return
}

}  