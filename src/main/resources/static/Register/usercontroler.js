// user={first name, last name, email, password, id}

class UserController { 
constructor() {
    this.currentId=0;
    this.userArray=[];
}

addUser(firstname, lastname, email, password) {
    const userObj= {
        firstname,
        lastname,
        email,
        password,
        id:this.currentId++
    }
    this.userArray.push(userObj)
}

setLocalStorage() {
    localStorage.setItem("users", JSON.stringify(this.userArray)
    )
}

}

