//
////creating servicearray of objects for card
//const serviceArr = [
//  {
//    title: "Cooking",
//    imageName: "./images/home-cooking.jpg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//    price: "50",
//  },
//  {
//    title: "Grocery",
//    imageName: "./images/grocery2.jpg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//      price: "60"
//  },
//  {
//    title: "Health",
//    imageName: "./images/health.jpg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//    price: "100"
//  },
//  {
//    title: "Entertainment",
//    imageName: "./images/entertainment.jpeg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//    price: "150"
//  },
//  {
//    title: "Spa",
//    imageName: "./images/spa.jpg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//    price: "70"
//  },
//  {
//    title: "Laundry",
//    imageName: "./images/laundry.jpg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//    price: "30"
//  },
//  {
//    title: "Special-Care",
//    imageName: "./images/special-care.jpg",
//    description:
//      "Our care team provides thoughtful in-home cooking services for seniors to ensure you have the most enjoyable culinary experience at every meal.",
//    price: "170"
//  },
//];

async function getProducts() {
  let response = await fetch("/product/allProducts", {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });
  const responseStatus = response.ok;
  if (responseStatus) {
    let productResponse = await response.json();
    return productResponse;
  } else {
    alert("Error: Could not add player");
  }
}

(async () => {
  let serviceArray = await getProducts();
  console.log(serviceArray);
  let serviceElement = document.getElementById("service");
  serviceArray.forEach((service) => {
    let serviceCard = document.createElement("div");
    serviceCard.innerHTML = `<div class="card" style="width: 18rem;">
    <img src="${service.imageName}" class="card-img-top" alt="...">
    <div class="card-body text-center">
      <h5 class="card-title">${service.title}</h5>
      <p class="card-text">${service.description}</p>
      <p id = "price">$${service.price}</p>
      <a href="#" data-id = ${service.title} class="btn btn-primary" data-pid = ${service.id}>Select Service</a>
    </div>
    </div>`;
    serviceElement.appendChild(serviceCard);
  });
  let userId = 1;
  console.log("hello");
  let selectServiceBtn = document.querySelectorAll(".btn");
  console.log(selectServiceBtn);
  selectServiceBtn.forEach((btn) => {
    btn.addEventListener("click", async function (event) {
      const list = document.querySelectorAll("#listElement");
      let addFlag = true;
      let totalPrice = 0;
      for (let i = 0; i < list.length; i++) {
        //splitting the list to compare only the title value to the selected service
        let listValue = list[i].innerHTML.split(" ");
        totalPrice += Number(listValue[1].replace("-$", " "));
        if (listValue[0] === event.target.getAttribute("data-id")) {
          addFlag = false;
        }
      }

      if (addFlag) {
        const userList = document.getElementById("selectedList");
        if (list.length === 0) {
          let heading = document.createElement("h4");
          heading.innerHTML = "Selected Service";
          userList.appendChild(heading);
        }
        //      Create cart obj

        let cart = {
          product_id: Number(event.target.getAttribute("data-pid")),
          user_id: 1
        };
        console.log(cart);
        let response = await fetch("http://localhost:8080/cart/add", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(cart),
        });
        const responseStatus = response.ok;
        if (responseStatus) {
          let addedProduct = await response.json();
          console.log(addedProduct);
        } else {
          alert("Error: Could not add player");
        }

        let priceElement = serviceArr.find((x) => x.title === event.target.getAttribute("data-id")).price;
        userList.innerHTML += `
        <li id = "listElement">${event.target.getAttribute("data-id")} -$${priceElement}</li>`;
        let total = document.querySelector(".total");
        total.innerHTML = `Total=${(totalPrice += Number(priceElement))}`;
        //update cart object
        cartObj.addService(event.target.getAttribute("data-id"), priceElement);
        cartObj.setLocalStorage();
      } else {
        alert("Duplicate item");
      }
    });
  });

})();

//create object

//let cartObj = new Cart(userId)
//adding content to servicecard
//let serviceElement = document.getElementById("service");
//serviceArr.forEach((service) => {
//  let serviceCard = document.createElement("div");
//  serviceCard.innerHTML = `<div class="card" style="width: 18rem;">
//  <img src="${service.imageName}" class="card-img-top" alt="...">
//  <div class="card-body text-center">
//    <h5 class="card-title">${service.title}</h5>
//    <p class="card-text">${service.description}</p>
//    <p id = "price">$${service.price}</p>
//    <a href="#" data-id = ${service.title} class="btn btn-primary">Select Service</a>
//  </div>
//  </div>`;
//  serviceElement.appendChild(serviceCard);
//});

//adding event listener to display selected service on the side
