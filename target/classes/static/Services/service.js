async function getProducts() {
  let response = await fetch("/api/product/allProducts", {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });
  const responseStatus = response.ok;
  if (responseStatus) {
    let productResponse = await response.json();
    return productResponse;
  } else {
    alert("Error: Could not add product");
  }
}

//cartFunction
async function cartList() {
  let cartListResponse = await fetch("/api/cart/user/1", {
  method: "GET",
  headers: { "Content-Type": "application/json" },
});

let cartListStatus = cartListResponse.ok;
if (cartListStatus) {
  let productLists = await cartListResponse.json();
  console.log("hello");
  console.log(productLists);
  console.log("Product:" + productLists[0].product.title);
  let cartDisplay = document.getElementById("product-table");
  let deleteBody = document.getElementsByClassName("demoClass")[0];
  if (deleteBody != null) {
    deleteBody.remove();
  }
  let tableBody = document.createElement("tbody");
  tableBody.setAttribute("class", "demoClass");
  cartDisplay.append(tableBody);
  let total = 0;
  productLists.forEach((productList) => {
    let newRow = document.createElement("tr");

    newRow.innerHTML = `
                              <td>${productList.product.title}</td><td></td>
                              <td>$${productList.product.price}</td>`;
    total += Number(productList.product.price);
    tableBody.append(newRow);
  });
  let totalRow = document.createElement("tr");
  totalRow.innerHTML = ` <td><b>Total</b></td>
                        <td></td>
                         <td>$${total}</td>
                         `;
  tableBody.append(totalRow);
  let checkoutRow = document.createElement("tr");
checkoutRow.innerHTML = '<td class="chechkout-btn"><button onclick="myFunc()">Checkout</button></td>';
tableBody.appendChild(checkoutRow);
} else {
  alert("Error: Could not add product");
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
  let selectServiceBtn = document.querySelectorAll(".btn");
  console.log(selectServiceBtn);
  selectServiceBtn.forEach((btn) => {
    btn.addEventListener("click", async function (event) {
      try {
        //duplicate checking
        let duplicateResponse = await fetch("/api/cart/user/1", {
          method: "GET",
          headers: { "Content-Type": "application/json" },
        });
        let duplicateCartList = [];
        let duplicateStatus = duplicateResponse.ok;
        if (duplicateStatus) {
          duplicateCartList = await duplicateResponse.json();
        } else {
          alert("Error: Couldn't call cart api");
        }
        duplicateCartList.forEach((duplicateList) => {
          if (duplicateList.product.id === Number(event.target.getAttribute("data-pid"))) {
            throw "Item already added!";
          }
        });

        let cart = {
          product_id: Number(event.target.getAttribute("data-pid")),
          user_id: 1,
        };

        let response = await fetch("/api/cart/add", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(cart),
        });
        const responseStatus = response.ok;
        if (responseStatus) {
          let addedProduct = await response.json();
        } else {
          alert("Error: Could not add player");
        }
        cartList();
      } catch (err) {
        alert(err);
      }
    });
  });
})();
function myFunc() {
  alert("Your order has been placed")
}
window.onload(cartList());
