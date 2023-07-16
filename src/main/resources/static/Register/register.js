const userController = new UserController
const userForm = document.getElementById('userform')

userForm.addEventListener("submit", function(event){
    event.preventDefault()
    const firstName = document.getElementById('firstname').value

    const lastName = document.getElementById('lastname').value

    const email = document.getElementById('email').value

    const password = document.getElementById('password').value

userController.addUser(firstName, lastName, email, password)

userController.setLocalStorage()

userForm.reset()

})