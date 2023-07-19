
const userForm = document.getElementById('userform');

let emailExists = false;

// Function to change page to login page once a successful registration form submission is made
const goToLogin = () => {
window.location.href = "../Login/login.html";
}

// Function to check for existence of email in database
const emailCheck = async(enteredEmail) => {
console.log(enteredEmail);
try{
let emailResponse = await fetch(`/api/user/email?email=${enteredEmail}`, {
method: "GET",
headers:{"Content-Type": "application/json"},
});
if(emailResponse.ok === false){
throw new Error("Response Failed");
 }
 else{
 let emailUser = await emailResponse.json()
 console.log(emailUser[0].email);


 if(emailUser[0].email === enteredEmail){
 emailExists = true;
 alert("Email is already in use")

 }

 else{
 console.log("Email is available for use")
 }
 return emailUser;
 }


}
catch(error){
console.error(error);
}



}


// L O G I C _ _ F O R _ _ S U B M I T T I N G _ _ R E G I S T R A T I O N _ _ F O R M

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

emailCheck(userPayload.email);
if(emailExists){
console.log("form not submitted")
userForm.reset
}

else{
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

userForm.reset();

}

})