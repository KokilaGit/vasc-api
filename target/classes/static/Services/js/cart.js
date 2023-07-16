class Cart{
    constructor(user_id){
        this.user_id = user_id;
        this.cartArr = [];
    }
    addService(service_title,service_price){
        this.cartArr.push({service_title,service_price})
    }

    setLocalStorage(){
        localStorage.setItem("cart",JSON.stringify(this))
    }
    getLocalStorage(){
        let cart = JSON.parse(localStorage.getItem("cart"));
        return cart;
    }
}
