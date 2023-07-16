let aboutImg = document.getElementById('aboutUsImg');


const randomImg = () =>{
    let rand= Math.floor(Math.random()* 5);

    switch(rand){
        case 0:
        aboutImg.src='./images/cooking.jpg';
        break;

        case 1: 
        aboutImg.src='./images/couple.jpg';
        break;

        case 2: 
        aboutImg.src='./images/laundry.jpg';
        break;

        case 3: 
        aboutImg.src="./images/seniors.jpg";
        break;

        case 4: 
        aboutImg.src= "./images/spa.jpg";
        break;

        default:
        console.log(error);

    }
}


randomImg();
