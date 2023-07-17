const userController = new UserController;
const userFeed = document.getElementById("form");
 userFeed.addEventListener("submit" ,function(event)
 {
    event.preventDefault()
    const fName = document.getElementById("fName").value;
    const lName = document.getElementById("fName").value;
    const email = document.getElementById("email").value;
    const feedback =  document.getElementById("feedback").value;
    userController.addUser(fName,lName,email,feedback)
    userController.setLocalStorage()
    userFeed.reset()
 })
       