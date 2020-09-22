define( function () {
    var baseUrl = "http://localhost:8443/hsf";
    var testController = {
        baseUrl: baseUrl,
        getAll :function (success) {
            $.blockUI({"message":"查询中"});
            return fish.get(baseUrl+"/getAll.action", success).done(function(){
                $.unblockUI();
            });
        },
        addBook :function (params,success) {
            $.blockUI({"message":"添加中"});
            return fish.post(baseUrl+"/addBook.action",params, success).done(function(){
                $.unblockUI();
            });
        },
        delBook :function (params,success) {
            $.blockUI({"message":"删除中"});
            return fish.post(baseUrl+"/deleteBook.action",params, success).done(function(){
                $.unblockUI();
            });
        },
        changeNum :function (params,success) {
            $.blockUI({"message":"修改中"});
            return fish.post(baseUrl+"/changeRemainNum.action",params, success).done(function(){
                $.unblockUI();
            });
        }
    };
    return testController;
});
