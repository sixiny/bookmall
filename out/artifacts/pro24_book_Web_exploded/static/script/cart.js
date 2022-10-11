
window.onload = function () {
    var vue = new Vue({
        "el":"#cart_div",
        data:{
            cart:{}
        },
        methods:{
            getCart:function () {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'cartInfo'
                    }
                })
                    .then(function (value) {
                        var cart = value.data;
                        vue.cart = cart;
                    })
                    .catch(function (reason) {
                    });
            },
            addCount:function (cartId) {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'addBookCount',
                        cartId:cartId
                    }
                })
                    .then(function (value) {
                        vue.getCart() //刷新数据
                    })
                    .catch(function (reason) {
                    });
            },
            redCount:function (cartId) {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'redBookCount',
                        cartId:cartId
                    }
                })
                    .then(function (value) {
                        vue.getCart() //刷新数据
                    })
                    .catch(function (reason) {
                    });
            }

        },
        // 数据渲染
        mounted:function () {
                this.getCart()
        }

    })
}