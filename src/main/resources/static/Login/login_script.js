// Variables

let usernameExists = false;
let passwordMatch = false;





const goToServices = () =>{
window.location.href = "../AboutUs/aboutUs.html";
}





// Function to check for existence of email in database
const emailCheck = async(enteredEmail, enteredPassword) => {
console.log(enteredEmail, enteredPassword);
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


 if((emailUser[0].email === enteredEmail)&& (emailUser[0].password === enteredPassword)){
 usernameExists = true;
 passwordMatch = true;

 }


 else{
 console.log("hello");

 }
 return emailUser;
 }


}
catch(error){
console.error(error);
}



}





const userForm = document.getElementById('userform');



// L O G I C _ _ F O R _ _ S U B M I T T I N G _ _ L O G I N _ _ F O R M

userForm.addEventListener("submit", function(event){
    event.preventDefault();



        const email = document.getElementById('username').value;

        const password = document.getElementById('password').value;

    let userPayload = {
    email: email,
    password: password}

    console.log(userPayload);

emailCheck(userPayload.email, userPayload.password);

if((usernameExists) && (passwordMatch)){

goToServices();
}

else{

alert("Incorrect Login/Password entered. Try again...");
// userForm.reset();

}

})