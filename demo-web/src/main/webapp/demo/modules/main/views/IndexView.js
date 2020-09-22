define([
    'hbs!modules/main/templates/IndexView.html',
    '../actions/IndexAction'
], function(tpl, idxAction) {
    var me =  fish.View.extend({
        // 视图 Dom 元素
        el: '#app',
        // 视图模板
        template: tpl,
        serialize:{
            bookList:[
                {
                    'bookID':1,
                    "bookName":"test",
                    "category":2,
                    "remainNum":3
                }
            ],
        },
        // 视图事件定义
        events: {
            'click #add-book': 'addBook',
            'click #del-book': 'delBook',
            'click #update-num': 'updateNum'
        },
        test:"me",
        // 一些初始化设置 (不能进行dom操作)
        initialize: function() {
            console.log('Enter initialize...');
            var that = this;
        },
        // 视图渲染完毕处理函数
        afterRender: function() {
            console.log('Enter afterRender...');
            var that = this;
            idxAction.getAll(function (success) {
                if(success){
                    var gridData = that.getGridData(success);
                    that.$('.js-list-grid').grid(gridData);
                }
            });
        },
        // 视图被删除时候做的事情
        cleanup: function() {
            me = null;
        },
        addBook:function(){
            var that = this;
            var book = {};
            book.bookName = that.$('.input-book-name').val();
            book.category = that.$('.input-category').val();
            book.remainNum = that.$('.input-remain-number').val();
            if((book.bookName != null) && Number.isInteger(parseInt(book.category))&&Number.isInteger(parseInt(book.remainNum))) {
                idxAction.addBook(book, function (success) {
                    if (success) {
                        var gridData = that.getGridData(success);
                        that.$('.js-list-grid').grid(gridData);
                    }
                });
            }else
                alert("数据为空或类型错误");
        },
        delBook:function(){
            var that = this;
            var bookID =  that.$('.del-book-id').val();
            if(Number.isInteger(parseInt(bookID))) {
                idxAction.delBook(bookID, function (success) {
                    if (success) {
                        var gridData = that.getGridData(success);
                        that.$('.js-list-grid').grid(gridData);
                    }
                });
            }else
                alert("数据为空或类型错误");
        },
        updateNum:function(){
            var that = this;
            var book = {};
            book.bookID = that.$('.update-book-id').val();
            book.remainNum = that.$('.update-number').val();
            if(Number.isInteger(parseInt(book.bookID))&&Number.isInteger(parseInt(book.remainNum))) {
                idxAction.changeNum(book, function (success) {
                    if (success) {
                        var gridData = that.getGridData(success);
                        that.$('.js-list-grid').grid(gridData);
                    }
                });
            }else
                alert("数据为空或类型错误");
        },
        showBookList:function (success) {
            var that = this;
            // var test = "showBookList";
            // console.log(this.test);
            if(success){
                //that.serialize.bookList = success;
                var gridData = that.getGridData(success);
                console.log("BookList：" + that.serialize.bookList);
                that.$('.js-list-grid').grid(gridData);
            }
        },
        getGridData: function (data) {
            var gridData = {
                data: data,
                height: 'auto',
                altRows: false,
                pager: true,
                //rowNum: 3,
                //rowList : [3,10,15,20],
                pgnumbers: true,
                pginput: true,
                viewrecords: true,
                viewtotal: true,
                borderStyle: "none",
                colModel: [
                    {
                        name: 'bookID',
                        label: 'ID',
                        key: true,
                        sortable: false
                    },
                    {
                        name: 'bookName',
                        label: '书名',
                        sortable: false,
                        width: 200,
                    }, {
                        name: 'category',
                        label: '类别代码',
                        sortable: false,
                        width: 200,
                    },{
                        name: 'remainNum',
                        label: '剩余数目',
                        sortable: false,
                        width: 200,
                    }
                ]
            };
            return gridData;
        }

    });
    return me;
});
