
const userForm = document.getElementById('userform')

const goToLogin = () => {
window.location.href = "../Login/login.html";
}

userForm.addEventListener("submit", async function(event){
    event.preventDefault();

    const firstName = document.getElementById('firstname').value;

        const lastName = document.getElementById('lastname').value;

        const email = document.getElementById('email').value;

        const password = document.getElementById('password').value;

    let userPayload = {
    firstName: firstName,
    lastName: lastName,
    email: email,
    password: password}



let response = await fetch("/api/user/add", {
method: "POST",
headers: {"Content-Type": "application/json"},
body: JSON.stringify(userPayload),
});

const responseStatus = response.ok;
if (responseStatus){
let addedUser = await response.json();
goToLogin();
}
else{
alert("Error: Could not add user")
}

console.log(userPayload);
userform.reset();



})