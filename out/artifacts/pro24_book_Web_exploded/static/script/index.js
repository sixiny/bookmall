window.onload = function () {
    var vue = new Vue({
        "el":".books-list",
        data:{
            booklists:{}
        },
        methods:{
            getBookList:function () {
                axios({
                    method:"POST",
                    url:"book.do",
                    params: {
                        operate: 'getBookList'
                    }
                })
                    .then(function (value) {
                        var booklists = value.data;
                        vue.booklists = booklists;
                    })
                    .catch(function (reason) {

                    })
            },
            addCart:function (bookId) {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'addCart',
                        bookId:bookId
                    }
                })
            }
        },
        mounted:function () {
            this.getBookList();
        }



    })
}