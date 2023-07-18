const userController = new UserController;
const userFeed = document.getElementById("form");
 userFeed.addEventListener("submit" ,function(event)
 {
 console.log('selvi');
    event.preventDefault()
    const fName = document.getElementById("fName").value;
    const lName = document.getElementById("lName").value;
    const email = document.getElementById("email").value;
    const feedback =  document.getElementById("feedback").value;
    userController.addUser(fName,lName,email,feedback)
    userController.setLocalStorage()

    let fbObject = {} ;

    fbObject.firstName = fName;
    fbObject.lastName = lName;
    fbObject.emailId = email;
    fbObject.feedBack = feedback;


    console.log('fbObject ' + JSON.stringify(fbObject));

    userController.postFb(fbObject);
    userFeed.reset();

 })

