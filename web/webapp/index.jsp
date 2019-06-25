<%@ page import="LoginRegister.Tools" %>
<%@ page import="StaffManager.Staff" %>
<%@ page import="UserManager.Customer" %>
<%@ page import="StaffManager.StaffTools" %>
<%@ page import="ShowInfoBean.ShowInfo" %>
<%@ page import="OrderManager.OrderTools" %>
<%@ page import="OrderManager.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="MaterialManager.Material" %>
<%@ page import="MaterialManager.MaterialTools" %>
<%@ page import="ProductionManager.ProductionTools" %>
<%@ page import="ProductionManager.Produce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ch">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="icon" href="images/favicon.ico">
        <title>广东正诚电气科技有限公司</title>
        <!--引入wangeditor的css文件-->
        <link rel="stylesheet" type="text/css" href="wangeditor/dist/css/wangEditor.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(function() {
                $(".meun-item").click(function() {
                    $(".meun-item").removeClass("meun-item-active");
                    $(this).addClass("meun-item-active");
                    var itmeObj = $(".meun-item").find("img");
                    itmeObj.each(function() {
                        var items = $(this).attr("src");
                        items = items.replace("_grey.png", ".png");
                        items = items.replace(".png", "_grey.png")
                        $(this).attr("src", items);
                    });
                    var attrObj = $(this).find("img").attr("src");
                    ;
                    attrObj = attrObj.replace("_grey.png", ".png");
                    $(this).find("img").attr("src", attrObj);
                });
                $("#topAD").click(function() {
                    $("#topA").toggleClass(" glyphicon-triangle-right");
                    $("#topA").toggleClass(" glyphicon-triangle-bottom");
                });
                $("#topBD").click(function() {
                    $("#topB").toggleClass(" glyphicon-triangle-right");
                    $("#topB").toggleClass(" glyphicon-triangle-bottom");
                });
                $("#topCD").click(function() {
                    $("#topC").toggleClass(" glyphicon-triangle-right");
                    $("#topC").toggleClass(" glyphicon-triangle-bottom");
                });
                $(".toggle-btn").click(function() {
                    $("#leftMeun").toggleClass("show");
                    $("#rightContent").toggleClass("pd0px");
                })
            })
        </script>
        <!--[if lt IE 9]>
  <script src="js/respond.min.js"></script>
<![endif]-->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/common.css" />
        <link rel="stylesheet" type="text/css" href="css/slide.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/flat-ui.min.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.nouislider.css">
    </head>

    <%
        Tools tools = new Tools();
        String account = tools.getSession(request);
        tools.setAccount(account);
        StaffTools staffTools = new StaffTools();
        staffTools.setAccount(account);
        ShowInfo showInfo;
        Staff staff = staffTools.getStaff();
        Customer customer = tools.getCustomer();
        int exit = tools.FindAccount();
        if(exit == 0){
            showInfo = new ShowInfo(account, staff.getName(), staff.getSex(), staff.getPhone(), staff.getAddress(), staff.getPower(), staff.getImg());
            staffTools.setPassword(staff.getPassword());
        }
        else {
            showInfo = new ShowInfo(account, customer.getName(), customer.getSex(), customer.getPhone(), customer.getAddress(), customer.getPower(), customer.getImg());
            tools.setPassword(customer.getPassword());
        }
    %>

    <body>
        <div id="wrap">
            <!-- 左侧菜单栏目块 -->
            <div class="leftMeun" id="leftMeun">
                <div id="personInfor">
                    <p id="userName">你好，<%=showInfo.getAccount()%></p>
                    <p>
                        <a href="/LogoutServlet">退出登录</a>
                    </p>
                </div>

                <%
                    if(showInfo.getPower() == 0){
                %>

                <div class="meun-title">客户</div>
                <div class="meun-item meun-item-active" href="#info" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_source.png">个人信息</div>
                <div class="meun-item" href="#editpass" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">修改密码</div>
                <div class="meun-item" href="#my" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">我的订单</div>

                <%
                    } else if (showInfo.getPower() == 1){
                %>

                <div class="meun-title">老板</div>
                <div class="meun-item meun-item-active" href="#info" aria-controls="info" role="tab" data-toggle="tab"><img src="images/icon_source.png">个人信息</div>
                <div class="meun-item" href="#editpass" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">修改密码</div>
                <div class="meun-item" href="#add_order" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">添加订单</div>
                <div class="meun-item" href="#analyse" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">订单统计</div>
                <div class="meun-item" href="#add_em" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">添加员工</div>
                <div class="meun-item" href="#em_manage" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">员工信息</div>
                <div class="meun-item" href="#edit_power" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">修改权限</div>
                <div class="meun-item" href="#dele_em" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">解雇员工</div>
                <div class="meun-item" href="#produce_manager" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">查看产品</div>
                <%
                    } else if (showInfo.getPower() == 2){
                %>

                <div class="meun-title">技术员</div>
                <div class="meun-item meun-item-active" href="#info" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_source.png">个人信息</div>
                <div class="meun-item" href="#editpass" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">修改密码</div>
                <div class="meun-item" href="#accept" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_change_grey.png">承接订单</div>
                <div class="meun-item" href="#accepted" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">已接订单</div>
                <div class="meun-item" href="#add_material" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">材料需求</div>

                <%
                    } else if (showInfo.getPower() == 3){
                %>

                <div class="meun-title">仓管</div>
                <div class="meun-item meun-item-active" href="#info" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_source.png">个人信息</div>
                <div class="meun-item" href="#editpass" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_user_grey.png">修改密码</div>
                <div class="meun-item" href="#prepare" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">配货订单</div>
                <div class="meun-item" href="#store" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">零件库存</div>
                <div class="meun-item" href="#add_parts" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">添加配件</div>
                <div class="meun-item" href="#change_parts" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">修改配件</div>
                <div class="meun-item" href="#delete_parts" aria-controls="" role="tab" data-toggle="tab"><img src="images/icon_chara_grey.png">删除配件</div>
                <%
                    }
                %>

            </div>

            <!-- 右侧具体内容栏目 -->
            <div id="rightContent">
                <a class="toggle-btn" id="nimei">
                    <i class="glyphicon glyphicon-align-justify"></i>
                </a>

                <!-- Tab panes -->
                <div class="tab-content">

                    <!--个人信息模块-->
                    <div role="tabpanel" class="tab-pane active" id="info">

                        <div class="data-div" >

                            <div class="row" style="padding-left: 65px;">
                                    <h3><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>　用户信息</h3>
                            </div>

                            <div style="background: white;	border-radius: 50px; margin-left: 50px; margin-right: 50px;margin-top: 10px;margin-bottom: 30px;">
                                <div class="row" style="padding-left: 65px;padding-right: 145px;width: 245px;padding-top: 50px;">
                                    <div style="float: left;">

                                        <img class="img-thumbnail" src="headImg/<%=showInfo.getImg()%>" alt="头像" style="width:245px;height: 316px;"/>
                                        <div style="text-align: center;width: 240px;font-size: 17px;padding-top: 5px;">
                                            <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>　用户头像
                                        </div>

                                        <!--上传头像-->
                                        <div style="text-align: center;width: 240px;font-size: 13px;padding-top: 5px;padding-left:0px;">
                                            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#changeHeadImg" style="margin-left: -110px;">修改头像</button>
                                        </div>

                                        <!--修改头像弹出窗口-->
                                        <div class="modal fade" id="changeHeadImg" role="dialog" aria-labelledby="gridSystemModalLabel">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title">修改头像</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="container-fluid">
                                                            <form class="form-horizontal" action="/ChangeHeadImgServlet" method="post" enctype="multipart/form-data">
                                                                <div class="form-group">
                                                                    <input id="lefile" type="file" style="display:none" name="headfile" >
                                                                    <div class="input-append">
                                                                        <a class="btn btn-info btn-sm showbtn" style="margin-top: 20px; margin-left: 150px;" onclick="$('input[id=lefile]').click();">选择图片</a>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                                                                    <button type="submit" class="btn btn-xs btn-green">保 存</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.modal-content -->
                                            </div>
                                            <!-- /.modal-dialog -->
                                        </div>
                                        <!-- /.modal -->

                                        <%--上传头像所需JavaScript--%>
                                        <script>
                                            $(document).ready(function(){
                                                //上传图片框
                                                $('input[id=lefile]').change(function() {
                                                    $('#photoCover').val($(this).val());
                                                });
                                            });
                                        </script>

                                        <button class="btn btn-success btn-lg" data-toggle="modal" data-target="#changeinfo" style="margin-top: -45px; margin-left: 120px;">修改资料</button>
                                    </div>

                                    <%--个人信息内容显示--%>
                                    <div style="font-size: 17px;width: 1100px;padding-left: 285px;font-weight: bold;height: 550px;">
                                        <div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>　账号：<%=showInfo.getAccount()%></div>
                                        <div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>　姓名：<%=showInfo.getName()%></div>
                                        <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>　性别：<%=showInfo.getSex()%></div>
                                        <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>　手机号：<%=showInfo.getPhone()%></div>
                                        <div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>　地址：<%=showInfo.getAddress()%></div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <!--修改资料弹出窗口-->
                        <div class="modal fade" id="changeinfo" role="dialog" aria-labelledby="gridSystemModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="gridSystemModalLabel">修改资料</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="container-fluid">
                                            <form class="form-horizontal" method="post">
                                                <div class="form-group ">
                                                    <label for="sName" class="col-xs-3 control-label">姓名：</label>
                                                    <div class="col-xs-8 ">
                                                        <input type="email" class="form-control input-sm duiqi" id="sName" placeholder="<%=showInfo.getName()%>">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-xs-3 control-label">性别：</label>
                                                    <div class="col-xs-8 " style="margin-left: -25px;">
                                                        <label class="control-label" >
                                                        <select id="sSex">
                                                            <option value ="男">男</option>
                                                            <option value ="女">女</option>
                                                        </select>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="sPhone" class="col-xs-3 control-label">手机号：</label>
                                                    <div class="col-xs-8">
                                                        <input type="" class="form-control input-sm duiqi" id="sPhone" placeholder="<%=showInfo.getPhone()%>">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="sAddress" class="col-xs-3 control-label">地址：</label>
                                                    <div class="col-xs-8" >
                                                        <input type="" class="form-control input-sm duiqi" id="sAddress" placeholder="<%=showInfo.getAddress()%>">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                                        <input type="button" class="btn btn-xs btn-green" data-id="<%=showInfo.getPower()%>" id="btn_ChangeInfo" value="保 存">
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->

                        <script src="js/jquery.nouislider.js"></script>

                        <script>
                            $(function () {
                                $("#btn_ChangeInfo").click(function () {
                                    var name = document.getElementById("sName");
                                    var sex = document.getElementById("sSex");
                                    var phone = document.getElementById("sPhone");
                                    var address = document.getElementById("sAddress");
                                    var id = this.dataset.id;
                                    var flag = 1;

                                    if (name.value == "") {
                                        alert("姓名不能为空!");
                                        flag = 0;
                                    }
                                    else {
                                        if (phone.value == "") {
                                            alert("手机号不能为空!");
                                            flag = 0;
                                        }
                                        else {
                                            var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
                                            if (!myreg.test(phone.value)) {
                                                alert("手机号码有误，请重填");
                                                flag = 0;
                                            }
                                            else {
                                                if (address.value == "") {
                                                    alert("地址不能为空!");
                                                    flag = 0;
                                                }
                                            }
                                        }
                                    }
                                    if (flag == 1) {
                                        if (id == 0) {
                                            $.get(
                                                "/ChangeUserInfoServlet",
                                                {
                                                    name: name.value,
                                                    sex: sex.value,
                                                    phone: phone.value,
                                                    address: address.value
                                                },
                                                function (data) {
                                                    var exit = data.mark;
                                                    if (exit == 1) {
                                                        alert("修改成功");
                                                        window.location.href = "index.jsp";
                                                    }
                                                    else {
                                                        alert("修改失败");
                                                    }
                                                }
                                            );
                                        }
                                        else {
                                            $.get(
                                                "/ChangeStaffInfoServlet",
                                                {
                                                    name: name.value,
                                                    sex: sex.value,
                                                    phone: phone.value,
                                                    address: address.value
                                                },
                                                function (data) {
                                                    var exit = data.mark;
                                                    if (exit == 1) {
                                                        alert("修改成功");
                                                        window.location.href = "index.jsp";
                                                    }
                                                    else {
                                                        alert("修改失败");
                                                    }
                                                }
                                            );
                                        }
                                    }
                                });
                            });
                        </script>

                    </div>

                    <!--我的订单管理模块-->
                    <div role="tabpanel" class="tab-pane" id="my">

                            <!--显示内容-->
                            <div class="data-div">
                                <div class="row tableHeader">
                                    <div class="col-xs-1 ">
                                        订单号
                                    </div>
                                    <div class="col-xs-1">
                                        客户
                                    </div>
                                    <div class="col-xs-1">
                                        技术员
                                    </div>
                                    <div class="col-xs-1">
                                        已付金额
                                    </div>
                                    <div class="col-xs-2">
                                        送货地址
                                    </div>
                                    <div class="col-xs-1">
                                        是否接单
                                    </div>
                                    <div class="col-xs-1">
                                        是否完成
                                    </div>
                                    <div class="col-xs-2">
                                        下单日期
                                    </div>
                                    <div class="col-xs-2">
                                        完成日期
                                    </div>
                                </div>
                                <div class="tablebody" id="myOrder">
                                </div>
                            </div>
                            <tr>
                                <td colspan="5">
                                    <button onclick="page(this)" class="frstn">首页</button>
                                    <button onclick="page(this)" class="prvtn">上一页</button>
                                    <span id="page"></span>
                                    <button onclick="page(this)" class="nxttn">下一页</button>
                                    <button onclick="page(this)" class="lsttn">尾页</button>
                                </td>
                            </tr>

                    </div>
                    <script type="text/javascript">
                        $(function(){
                            $.ajax({
                                url:"/AjaxServlet",
                                data:"",
                                dataType:"json",
                                type:"post",
                                success:function(obj){
                                    for(var i=0;i<obj[0]["length"];i++){
                                        $("#myOrder").append(
                                            "<div class='row'>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["orderNumber"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[i]["userAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[i]["staffAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["paid"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            obj[i]["address"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["isAccept"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["isCompletion"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[i]["startDate"])+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[i]["realDate"])+"" +
                                            "</div>" +
                                            "</div>"
                                        )
                                    }
                                }
                            });
                        })
                        $(function() {
                            $.ajax({
                                url:"/AjaxServlet?method=getPage",
                                data:"",
                                dataType:"json",
                                type:"post",
                                success:function(obj){
                                    console.log()
                                    $("#page").text(obj["currentPage"] + "/" + obj["lastPage"]);
                                    $(".frstn")[0].id=1;
                                    $(".prvtn")[0].id=obj["prePage"];
                                    $(".nxttn")[0].id=obj["nextPage"];
                                    $(".lsttn")[0].id=obj["lastPage"];
                                }
                            });
                        })
                        function page(btn){
                            var page=btn.id;
                            $.ajax({
                                url:"/AjaxServlet?method=changePage",
                                type:"post",
                                data:{
                                    page:page
                                },
                                dataType:"json",
                                success:function(obj){

                                    $("#page").text(obj[0]["currentPage"]+"/"+obj[0]["lastPage"]);
                                    $(".frstn")[0].id=1;
                                    $(".prvtn")[0].id=obj[0]["prePage"];
                                    $(".nxttn")[0].id=obj[0]["nextPage"];
                                    $(".lsttn")[0].id=obj[0]["lastPage"];

                                    for(var i=0;i<obj[0]["pageSize"];i++){
                                        $("#myOrder").children().remove();
                                    }

                                    for(var i=0;i<obj[1]["length"];i++){
                                        $("#myOrder").append(
                                            "<div class='row'>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["orderNumber"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[1][i]["userAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[1][i]["staffAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["paid"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            obj[1][i]["address"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["isAccept"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["isCompletion"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[1][i]["startDate"])+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[1][i]["realDate"])+"" +
                                            "</div>" +
                                            "</div>"
                                        )
                                    }
                                }
                            });
                        }

                        function getLocalTime(nS) {
                            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
                        }
                    </script>
                    <script src="js/jquery.nouislider.js"></script>

                    <!-- this page specific inline scripts -->
                    <script>
                        //min/max slider
                        function huadong(my, unit, def, max) {
                            $(my).noUiSlider({
                                range: [0, max],
                                start: [def],
                                handles: 1,
                                connect: 'upper',
                                slide: function () {
                                    var val = Math.floor($(this).val());
                                    $(this).find(".noUi-handle").text(
                                        val + unit
                                    );
                                    console.log($(this).find(".noUi-handle").parent().parent().html());
                                },
                                set: function () {
                                    var val = Math.floor($(this).val());
                                    $(this).find(".noUi-handle").text(
                                        val + unit
                                    );
                                }
                            });
                            $(my).val(def, true);
                        }
                        huadong('.slider-minmax1', "分钟", "5", 30);
                        huadong('.slider-minmax2', "分钟", "6", 15);
                        huadong('.slider-minmax3', "分钟", "10", 60);
                        huadong('.slider-minmax4', "次", "2", 10);
                        huadong('.slider-minmax5', "天", "3", 7);
                        huadong('.slider-minmax6', "天", "8", 10);
                    </script>

                    <!-- 添加订单模块 -->
                    <div role="tabpanel" class="tab-pane" id="add_order">
                        <div class="row">
                            <div class="col-md-12" style="text-align: center">
                                <h3>添加订单</h3>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form action="" method="post">
                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="atitle" class="col-xs-2 control-label" style="text-align: right;">客户账号：</label>
                                        <input type="text" class="form-control"  placeholder="客户名" id="aTitle" value="" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aPay" class="col-xs-2 control-label" style="text-align: right;">已付金额：</label>
                                        <input onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" type="text" class="form-control"  placeholder="已付金额" id="aPay" style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aAddress" class="col-xs-2 control-label" style="text-align: right;">送货地址：</label>
                                        <input type="text" class="form-control"  placeholder="送货地址" id="aAddress" style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" >
                                        <label for="aNote" class="col-xs-2 control-label" style="text-align: right;">客户备注：</label>
                                        <textarea class="form-control" rows="12"  id="aNote" placeholder="客户备注" style="height: 250px;resize: none;width: 600px;"></textarea>
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_AddOrder" style="width:300px;margin-top: 50px;margin-left: 300px;" value="添加">
                                </form>


                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                    </div>

                    <%--添加订单所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_AddOrder").click(function () {
                                var flag = 1;
                                var aTitle = document.getElementById("aTitle");
                                var aPay = document.getElementById("aPay");
                                var aAddress = document.getElementById("aAddress");
                                var aNote = document.getElementById("aNote");
                                if(aTitle.value == ""){
                                    alert("用户账号不能为空");
                                    flag = 0;
                                }
                                else{
                                    if(aPay.value == ""){
                                        alert("已付金额不能为空");
                                        flag = 0;
                                    }
                                    else{
                                        if(aAddress.value == ""){
                                            alert("收货地址不能为空");
                                            flag = 0;
                                        }
                                    }
                                }

                                if (flag == 1) {
                                    $.get(
                                        "/InsertOrderServlet",
                                        {
                                            userAccount: aTitle.value,
                                            paid: aPay.value,
                                            address: aAddress.value,
                                            request: aNote.value
                                        },
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 0) {
                                                alert("该用户名不存在,无法添加");
                                            } else {
                                                alert("添加订单成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!-- 添加员工模块 -->
                    <div role="tabpanel" class="tab-pane" id="add_em">
                        <div class="row">
                            <div class="col-md-12" style="text-align: center">
                                <h3>添加员工</h3>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form method="post">

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aeName" class="col-xs-2 control-label" style="text-align: right;">账号：</label>
                                        <input type="text" class="form-control"  placeholder="账号" id="aeAccount" value="" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aeName" class="col-xs-2 control-label" style="text-align: right;">姓名：</label>
                                        <input type="text" class="form-control"  placeholder="姓名" id="aeName" value="" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aSex" class="col-xs-2 control-label" style="text-align: right;">性别：</label>
                                        <select id="aSex" style="height: 40px;">
                                            <option value ="男">男</option>
                                            <option value ="女">女</option>
                                        </select>
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aPosition" class="col-xs-2 control-label" style="text-align: right;">职位：</label>
                                            <select id="aPosition" style="height: 40px;">
                                                <option value ="技术员">技术员</option>
                                                <option value ="仓管">仓管</option>
                                            </select>
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="IDcard" class="col-xs-2 control-label" style="text-align: right;">身份证号：</label>
                                        <input type="text" class="form-control"  placeholder="身份证号" id="IDcard" value="" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_AddStaff" style="width:300px;margin-top: 50px;margin-left: 300px;" value="添加">
                                </form>


                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                    </div>

                    <%--添加员工所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_AddStaff").click(function () {
                                var flag = 1;
                                var account = document.getElementById("aeAccount");
                                var name = document.getElementById("aeName");
                                var sex = document.getElementById("aSex");
                                var position = document.getElementById("aPosition");
                                var IDcard = document.getElementById("IDcard");
                                var power;
                                if(position.value == "技术员")
                                    power = 2;
                                else
                                    power = 3;
                                if(account.value == ""){
                                    alert("员工账号不能为空");
                                    flag = 0;
                                }
                                else {
                                    if (name.value == "") {
                                        alert("员工姓名不能为空");
                                        flag = 0;
                                    } else {
                                        if (IDcard.value == "") {
                                            alert("身份证不能为空");
                                            flag = 0;
                                        } else {
                                            if (!(/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/.test(IDcard.value))) {
                                                alert("身份证号有误，请重新输入");
                                                flag = 0;
                                            }
                                        }
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/InsertStaffServlet",
                                        {
                                            account: account.value,
                                            name: name.value,
                                            sex: sex.value,
                                            power: power,
                                            IDcard: IDcard.value
                                        },
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 1) {
                                                alert("该账号已存在");
                                            } else {
                                                alert("添加员工成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!--承接订单模块-->
                    <div role="tabpanel" class="tab-pane" id="accept">

                        <div class="data-div">
                            <%--承接订单栏目--%>
                            <div class="row tableHeader">
                                <div class="col-xs-1 ">
                                    订单号
                                </div>
                                <div class="col-xs-1">
                                    客户
                                </div>
                                <div class="col-xs-4">
                                    送货地址
                                </div>
                                <div class="col-xs-2">
                                    下单日期
                                </div>
                                <div class="col-xs-2">
                                    操作
                                </div>
                            </div>

                            <%--承接内容显示--%>
                            <%
                                if(showInfo.getPower() == 2) {
                                    OrderTools orderTools = new OrderTools();
                                    List<Orders> isAcceptOrderlist = orderTools.FindIsAcceptOrders("0");
                                    for (int i = 0; i < isAcceptOrderlist.size(); i++) {
                                        Orders orders = isAcceptOrderlist.get(i);
                            %>
                            <div class="tablebody">
                                <div class="row">
                                    <div class="col-xs-1 ">
                                        <%=orders.getOrderNumber()%>
                                    </div>
                                    <div class="col-xs-1 ">
                                        <%=orders.getUserAccount()%>
                                    </div>
                                    <div class="col-xs-4 ">
                                        <%=orders.getAddress()%>
                                    </div>
                                    <div class="col-xs-2 ">
                                        <%=orders.getStartDate()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <input class="btn btn-success btn-xs btn_AcceptOrder" data-toggle="modal" data-id="<%=orders.getOrderNumber()%>" data-target="#reviseUser" value="接单" style="margin-top: 22px; width: 50px;">
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>

                        <!--页码块-->
                        <footer class="footer">
                            <ul class="pagination">
                                <li>
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                    页
                                </li>
                                <li class="gray">
                                    共20页
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-left">
                                    </i>
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-right">
                                    </i>
                                </li>
                            </ul>
                        </footer>

                        <script src="js/jquery.nouislider.js"></script>

                        <!-- this page specific inline scripts -->
                        <script>
                            //min/max slider
                            function huadong(my, unit, def, max) {
                                $(my).noUiSlider({
                                    range: [0, max],
                                    start: [def],
                                    handles: 1,
                                    connect: 'upper',
                                    slide: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                        console.log($(this).find(".noUi-handle").parent().parent().html());
                                    },
                                    set: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                    }
                                });
                                $(my).val(def, true);
                            }
                            huadong('.slider-minmax1', "分钟", "5", 30);
                            huadong('.slider-minmax2', "分钟", "6", 15);
                            huadong('.slider-minmax3', "分钟", "10", 60);
                            huadong('.slider-minmax4', "次", "2", 10);
                            huadong('.slider-minmax5', "天", "3", 7);
                            huadong('.slider-minmax6', "天", "8", 10);
                        </script>
                    </div>

                    <%--承接订单所需JavaScript--%>
                    <script type="text/javascript">
                        $(function(){
                            $.ajax({
                                url:"/AjaxServlet",
                                data:"",
                                dataType:"json",
                                type:"post",
                                success:function(obj){
                                    for(var i=0;i<obj.length;i++){
                                        $("#myOrder").append(
                                            "<div class='row'>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["orderNumber"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[i]["userAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[i]["staffAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["paid"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            obj[i]["address"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["isAccept"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[i]["isCompletion"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[i]["startDate"])+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[i]["realDate"])+"" +
                                            "</div>" +
                                            "</div>"
                                        )
                                    }
                                }
                            });
                        })
                        $(function() {
                            $.ajax({
                                url:"/AjaxServlet?method=getPage",
                                data:"",
                                dataType:"json",
                                type:"post",
                                success:function(obj){
                                    console.log()
                                    $("#page").text(obj["currentPage"] + "/" + obj["lastPage"]);
                                    $(".frstn")[0].id=1;
                                    $(".prvtn")[0].id=obj["prePage"];
                                    $(".nxttn")[0].id=obj["nextPage"];
                                    $(".lsttn")[0].id=obj["lastPage"];
                                }
                            });
                        })
                        function page(btn){
                            var page=btn.id;
                            $.ajax({
                                url:"/AjaxServlet?method=changePage",
                                type:"post",
                                data:{
                                    page:page
                                },
                                dataType:"json",
                                success:function(obj){

                                    $("#page").text(obj[0]["currentPage"]+"/"+obj[0]["lastPage"]);
                                    $(".frstn")[0].id=1;
                                    $(".prvtn")[0].id=obj[0]["prePage"];
                                    $(".nxttn")[0].id=obj[0]["nextPage"];
                                    $(".lsttn")[0].id=obj[0]["lastPage"];

                                    for(var i=0;i<obj[0]["pageSize"];i++){
                                        $("#myOrder").children().remove();
                                    }

                                    for(var i=0;i<obj[1]["length"];i++){
                                        $("#myOrder").append(
                                            "<div class='row'>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["orderNumber"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[1][i]["userAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"
                                            +obj[1][i]["staffAccount"]+""+
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["paid"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            obj[1][i]["address"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["isAccept"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-1'>"+
                                            obj[1][i]["isCompletion"]+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[1][i]["startDate"])+"" +
                                            "</div>" +
                                            "<div class='col-xs-2'>"+
                                            getLocalTime(obj[1][i]["realDate"])+"" +
                                            "</div>" +
                                            "</div>"
                                        )
                                    }
                                }
                            });
                        }

                        function getLocalTime(nS) {
                            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
                        }
                    </script>
                    <script>
                        $(function () {
                            $(".btn_AcceptOrder").click(function () {
                                var id = this.dataset.id;
                                $.get(
                                    "/AcceptOrder",
                                    {
                                        orderNumber: id
                                    },
                                    function (data) {
                                        var exit = data.mark;
                                        if (exit == 1) {
                                            alert("接受订单成功");
                                            window.location.href = "index.jsp";
                                        } else {
                                            alert("该订单已被其他技术员接单");
                                        }
                                    }
                                );
                            });
                        });
                    </script>

                    <!--已接订单模块-->
                    <div role="tabpanel" class="tab-pane" id="accepted">

                        <div class="data-div">

                            <%--已接订单栏目--%>
                            <div class="row tableHeader">
                                <div class="col-xs-1 ">
                                    订单号
                                </div>
                                <div class="col-xs-1">
                                    客户
                                </div>
                                <div class="col-xs-1">
                                    已付金额
                                </div>
                                <div class="col-xs-2">
                                    送货地址
                                </div>
                                <div class="col-xs-1">
                                    是否备好材料
                                </div>
                                <div class="col-xs-2">
                                    下单日期
                                </div>
                                <div class="col-xs-1">
                                    操作
                                </div>
                            </div>

                            <%--已接订单内容显示--%>
                            <%
                                if(showInfo.getPower() == 2) {
                                    OrderTools orderTools = new OrderTools();
                                    List StaffAcceptOrderList = orderTools.FindStaffAcceptOrders(staffTools.getAccount());
                                    for (int i = 0; i < StaffAcceptOrderList.size(); i++) {
                                        Orders staffAcceptOrder = (Orders) StaffAcceptOrderList.get(i);
                            %>
                            <div class="tablebody">
                                <div class="row">
                                    <div class="col-xs-1">
                                        <%=staffAcceptOrder.getOrderNumber()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=staffAcceptOrder.getUserAccount()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=staffAcceptOrder.getPaid()%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=staffAcceptOrder.getAddress()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=(staffAcceptOrder.getIsDistribute().equals("0"))?"未备好材料":"材料已备好"%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=staffAcceptOrder.getStartDate()%>
                                    </div>
                                    <%
                                        if(staffAcceptOrder.getIsCompletion().equals("1")){
                                    %>
                                    <div class="col-xs-1">
                                        已完成
                                    </div>
                                    <%
                                        }else{
                                    %>
                                    <div class="col-xs-1">
                                        <input type="button" class="btn btn-success btn-xs btn_staffAccept" data-toggle="modal" data-id="<%=staffAcceptOrder.getOrderNumber()%>" data-target="#reviseUser" value="完成" style="width: 50px; margin-top: 22px;">
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>

                        </div>

                        <!--页码块-->
                        <footer class="footer">
                            <ul class="pagination">
                                <li>
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                    页
                                </li>
                                <li class="gray">
                                    共20页
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-left">
                                    </i>
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-right">
                                    </i>
                                </li>
                            </ul>
                        </footer>

                        <script src="js/jquery.nouislider.js"></script>

                        <!-- this page specific inline scripts -->
                        <script>
                            //min/max slider
                            function huadong(my, unit, def, max) {
                                $(my).noUiSlider({
                                    range: [0, max],
                                    start: [def],
                                    handles: 1,
                                    connect: 'upper',
                                    slide: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                        console.log($(this).find(".noUi-handle").parent().parent().html());
                                    },
                                    set: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                    }
                                });
                                $(my).val(def, true);
                            }
                            huadong('.slider-minmax1', "分钟", "5", 30);
                            huadong('.slider-minmax2', "分钟", "6", 15);
                            huadong('.slider-minmax3', "分钟", "10", 60);
                            huadong('.slider-minmax4', "次", "2", 10);
                            huadong('.slider-minmax5', "天", "3", 7);
                            huadong('.slider-minmax6', "天", "8", 10);
                        </script>
                    </div>

                    <%--已接订单所需JavaScript--%>
                    <script>
                        $(function () {
                            $(".btn_staffAccept").click(function () {
                                var id = this.dataset.id;
                                $.get(
                                    "/CompleteOrder",
                                    {
                                        orderNumber: id
                                    },
                                    function (data) {
                                        var exit = data.mark;
                                        if (exit == 0) {
                                            alert("该订单未配材料，不能完成该订单");
                                        } else {
                                            alert("订单完成");
                                            window.location.href = "index.jsp";
                                        }
                                    }
                                );
                            });
                        });
                    </script>

                    <!--配货订单模块-->
                    <div role="tabpanel" class="tab-pane" id="prepare">

                        <div class="data-div">
                            <%--配货订单栏目--%>
                            <div class="row tableHeader">
                                <div class="col-xs-1 ">
                                    订单号
                                </div>
                                <div class="col-xs-1">
                                    技术员
                                </div>
                                <div class="col-xs-4">
                                    所需材料
                                </div>
                                <div class="col-xs-2">
                                    下单日期
                                </div>
                                <div class="col-xs-1">
                                    操作
                                </div>
                            </div>

                            <%--配货订单内容显示--%>
                            <%
                                if(showInfo.getPower() == 3) {
                                    OrderTools orderTools = new OrderTools();
                                    List NotDistributeOrderlist = orderTools.FindIsDistributeOrders("0");
                                    for (int i = 0; i < NotDistributeOrderlist.size(); i++) {
                                        Orders orders = (Orders) NotDistributeOrderlist.get(i);
                            %>
                            <div class="tablebody">
                                <div class="row">
                                    <div class="col-xs-1 ">
                                        <%=orders.getOrderNumber()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getStaffAccount()%>
                                    </div>
                                    <div class="col-xs-4">
                                        <%=orders.getRequestMaterial()%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=orders.getStartDate()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <input class="btn btn-success btn-xs btn_DistributeOrder" data-toggle="modal" data-id="<%=orders.getOrderNumber()%>" data-target="#reviseUser" value="备货" style="width: 50px; margin-top: 22px;">
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>

                        </div>

                        <!--页码块-->
                        <footer class="footer">
                            <ul class="pagination">
                                <li>
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                    页
                                </li>
                                <li class="gray">
                                    共20页
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-left">
                                    </i>
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-right">
                                    </i>
                                </li>
                            </ul>
                        </footer>

                        <script src="js/jquery.nouislider.js"></script>

                        <!-- this page specific inline scripts -->
                        <script>
                            //min/max slider
                            function huadong(my, unit, def, max) {
                                $(my).noUiSlider({
                                    range: [0, max],
                                    start: [def],
                                    handles: 1,
                                    connect: 'upper',
                                    slide: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                        console.log($(this).find(".noUi-handle").parent().parent().html());
                                    },
                                    set: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                    }
                                });
                                $(my).val(def, true);
                            }
                            huadong('.slider-minmax1', "分钟", "5", 30);
                            huadong('.slider-minmax2', "分钟", "6", 15);
                            huadong('.slider-minmax3', "分钟", "10", 60);
                            huadong('.slider-minmax4', "次", "2", 10);
                            huadong('.slider-minmax5', "天", "3", 7);
                            huadong('.slider-minmax6', "天", "8", 10);
                        </script>
                    </div>

                    <%--配货订单所需JavaScript--%>
                    <script>
                        $(function () {
                            $(".btn_DistributeOrder").click(function () {
                                var id = this.dataset.id;
                                $.get(
                                    "/DistributeOrder",
                                    {
                                        orderNumber: id
                                    },
                                    function (data) {
                                        var exit = data.mark;
                                        if (exit == 0) {
                                            alert("该订单未有程序员接单，不能备货该订单");
                                        } else {
                                            alert("备货订单成功");
                                            window.location.href = "index.jsp";
                                        }
                                    }
                                );
                            });
                        });
                    </script>

                    <!--零件库存模块-->
                    <div role="tabpanel" class="tab-pane" id="store">

                        <div class="data-div">
                            <%--零件库存栏目--%>
                            <div class="row tableHeader">
                                <div class="col-xs-1">
                                    零件号
                                </div>
                                <div class="col-xs-2">
                                    零件名称
                                </div>
                                <div class="col-xs-1">
                                    数量
                                </div>
                            </div>

                            <%--零件库存内容显示--%>
                            <%
                                if(showInfo.getPower() == 3) {
                                    MaterialTools materialTools = new MaterialTools();
                                    List materialList = materialTools.GetRs();
                                    for (int i = 0; i < materialList.size(); i++) {
                                        Material material = (Material) materialList.get(i);
                            %>
                            <div class="tablebody">

                                <div class="row">
                                    <div class="col-xs-1 ">
                                        <%=material.getID()%>
                                    </div>
                                    <div class="col-xs-2 ">
                                        <%=material.getMaterial()%>
                                    </div>
                                    <div class="col-xs-1 ">
                                        <%=material.getNum()%>斤
                                    </div>
                                </div>

                            </div>
                            <%
                                    }
                                }
                            %>

                        </div>

                        <!--页码块-->
                        <footer class="footer">
                            <ul class="pagination">
                                <li>
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                    页
                                </li>
                                <li class="gray">
                                    共20页
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-left">
                                    </i>
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-right">
                                    </i>
                                </li>
                            </ul>
                        </footer>


                        <script src="js/jquery.nouislider.js"></script>

                        <!-- this page specific inline scripts -->
                        <script>
                            //min/max slider
                            function huadong(my, unit, def, max) {
                                $(my).noUiSlider({
                                    range: [0, max],
                                    start: [def],
                                    handles: 1,
                                    connect: 'upper',
                                    slide: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                        console.log($(this).find(".noUi-handle").parent().parent().html());
                                    },
                                    set: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                    }
                                });
                                $(my).val(def, true);
                            }
                            huadong('.slider-minmax1', "分钟", "5", 30);
                            huadong('.slider-minmax2', "分钟", "6", 15);
                            huadong('.slider-minmax3', "分钟", "10", 60);
                            huadong('.slider-minmax4', "次", "2", 10);
                            huadong('.slider-minmax5', "天", "3", 7);
                            huadong('.slider-minmax6', "天", "8", 10);
                        </script>
                    </div>

                    <!-- 添加配件模块 -->
                    <div role="tabpanel" class="tab-pane" id="add_parts">
                        <div class="row">
                            <div class="col-md-12" style="text-align: center">
                                <h3>添加配件</h3>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form method="post">

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aeName" class="col-xs-2 control-label" style="text-align: right;">材料名：</label>
                                        <input type="text" class="form-control"  placeholder="材料名" id="aeParts_name" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aeName" class="col-xs-2 control-label" style="text-align: right;">材料数量：</label>
                                        <input onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" type="text" class="form-control"  placeholder="材料数量" id="aeParts_num" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_AddParts" style="width:300px;margin-top: 50px;margin-left: 300px;" value="添加">
                                </form>


                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                    </div>

                    <%--添加配件所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_AddParts").click(function () {
                                var flag = 1;
                                var material = document.getElementById("aeParts_name");
                                var num = document.getElementById("aeParts_num");
                                if(material.value == ""){
                                    alert("材料名不能为空");
                                    flag = 0;
                                }
                                else{
                                    if(num.value < 0){
                                        alert("材料数量不能为负数");
                                        flag = 0;
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/InsertMaterialServlet",
                                        {
                                            material: material.value,
                                            num: num.value
                                        },
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 1) {
                                                alert("该材料已存在");
                                            } else {
                                                alert("添加材料成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!--员工管理模块-->
                    <div role="tabpanel" class="tab-pane" id="em_manage">

                        <div class="data-div">
                            <div class="row tableHeader">
                                <div class="col-xs-1 ">
                                    员工号
                                </div>
                                <div class="col-xs-1 ">
                                    账号
                                </div>
                                <div class="col-xs-1">
                                    姓名
                                </div>
                                <div class="col-xs-1">
                                    性别
                                </div>
                                <div class="col-xs-2">
                                    手机号
                                </div>
                                <div class="col-xs-2">
                                    身份证号
                                </div>
                                <div class="col-xs-2">
                                    地址
                                </div>
                                <div class="col-xs-2">
                                    职位
                                </div>
                            </div>

                            <%
                                if(showInfo.getPower() == 1) {
                                    StaffTools staffTools_manage = new StaffTools();
                                    List stafflist = staffTools_manage.GetRs();
                                    for (int i = 0; i < stafflist.size(); i++) {
                                        Staff staff_manage = (Staff)stafflist.get(i);
                            %>
                            <div class="tablebody">
                                <div class="row">
                                    <div class="col-xs-1 ">
                                        <%=staff_manage.getWorkNum()%>
                                    </div>
                                    <div class="col-xs-1 ">
                                        <%=staff_manage.getAccount()%>
                                    </div>
                                    <div class="col-xs-1 ">
                                        <%=staff_manage.getName()%>
                                    </div>
                                    <div class="col-xs-1 ">
                                        <%=staff_manage.getSex()%>
                                    </div>
                                    <div class="col-xs-2 ">
                                        <%=staff_manage.getPhone()%>
                                    </div>
                                    <div class="col-xs-2 ">
                                        <%=staff_manage.getIDcard()%>
                                    </div>
                                    <div class="col-xs-2 ">
                                        <%=staff_manage.getAddress()%>
                                    </div>
                                    <div class="col-xs-1 ">
                                        <%=(staff_manage.getPower() == 2)?"技术员":(staff_manage.getPower() == 3)?"仓库管理员":"老板"%>
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>

                        </div>

                        <!--页码块-->
                        <footer class="footer">
                            <ul class="pagination">
                                <li>
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                    页
                                </li>
                                <li class="gray">
                                    共20页
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-left">
                                    </i>
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-right">
                                    </i>
                                </li>
                            </ul>
                        </footer>

                    </div>

                    <!-- 修改密码模块 -->
                    <div role="tabpanel" class="tab-pane" id="editpass">
                        <div class="row" style="margin-top: 70px;">
                            <div class="col-md-12" style="text-align: center">
                                <h2>修改密码</h2>
                            </div>
                        </div>

                        <!--修改密码-->
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form action="" method="post">
                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　原密码　</span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="old_password" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　新密码　</span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="new_password1" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:40px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　确认密码</span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="new_password2" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_editpass" style="width:200px;margin:0 auto;" value="保存">
                                </form>
                            </div>
                            <div class="col-md-4"></div>
                        </div>

                    </div>

                    <%--修改密码所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_editpass").click(function () {
                                var old_password = document.getElementById("old_password");
                                var new_password1 = document.getElementById("new_password1");
                                var new_password2 = document.getElementById("new_password2")
                                var flag = 1;
                                if(old_password.value == "") {
                                    alert("原密码不能为空");
                                    flag = 0;
                                }else {
                                    if(new_password1.value == ""){
                                        alert("新密码不能为空");
                                        flag = 0;
                                    }
                                    else {
                                        if(new_password2.value == ""){
                                            alert("请确认新密码");
                                            flag = 0;
                                        }
                                        else{
                                            if(new_password1.value != new_password2.value){
                                                alert("两次密码不一致");
                                                flag = 0;
                                            }
                                        }
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/ChangeUserPasswordServlet",
                                        {
                                            old_password: old_password.value,
                                            new_password: new_password1.value
                                        },
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 1) {
                                                alert("修改密码成功，点击确认后返回登陆页面重新验证登陆");
                                                window.location.href = "../login.html";
                                            }
                                            else
                                                alert("原密码错误");
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!-- 解雇模块 -->
                    <div role="tabpanel" class="tab-pane" id="dele_em">
                        <div class="row" style="margin-top: 70px;">
                            <div class="col-md-12" style="text-align: center">
                                <h2>解雇员工</h2>
                            </div>
                        </div>

                        <!--解雇员工-->
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form action="" method="post">
                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　管理员密码</span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="admin_password" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　员工账号　</span></span>
                                        <input type="password" class="form-control"  placeholder="ID" id="workNum" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_dele_em" style="width:200px;margin:0 auto;" value="解雇">
                                </form>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>

                    </div>

                    <%--解雇所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_dele_em").click(function () {
                                var admin_password = document.getElementById("admin_password");
                                var account = document.getElementById("workNum");
                                var flag = 1;
                                if(admin_password.value == "" ){
                                    alert("管理员密码不能为空");
                                    flag = 0;
                                }
                                else {
                                    if (account.value == "") {
                                        alert("员工账号不能为空");
                                        flag = 0;
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/DeleteStaffServlet",
                                        {
                                            account:account.value,
                                            password:admin_password.value},
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 0) {
                                                alert("删除失败,该员工账号不存在");
                                            }
                                            else if (exit == 2) {
                                                alert("删除失败,管理员密码错误");
                                            }
                                            else {
                                                alert("删除成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!-- 追加材料需求模块 -->
                    <div role="tabpanel" class="tab-pane" id="add_material">
                        <div class="row" style="margin-top: 70px;">
                            <div class="col-md-12" style="text-align: center">
                                <h3>追加材料需求</h3>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form action="" method="post">
                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="atitle" class="col-xs-2 control-label" style="text-align: right;">订单号：</label>
                                        <input type="text" class="form-control"  placeholder="OrderNumber" id="material_order" value="" autofocus style="height: 40px;width: 600px;">
                                    </div>

                                    <div class="form-group" >
                                        <label for="aNote" class="col-xs-2 control-label" style="text-align: right;">材料需求：</label>
                                        <textarea class="form-control" rows="12"  id="material_require" placeholder="Material Require" style="height: 250px;resize: none;width: 600px;"></textarea>
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_insert_material" style="width:200px;margin:0 auto;" value="添加">
                                </form>


                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row" style="height: 30px;"></div>

                    </div>

                    <%--追加材料所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_insert_material").click(function () {
                                var OrderNumber = document.getElementById("material_order");
                                var material_require = document.getElementById("material_require");
                                var flag = 1;
                                if(OrderNumber.value == "" ){
                                    alert("订单号不能为空");
                                    flag = 0;
                                }
                                else {
                                    if (material_require.value == "") {
                                        alert("材料需求不能为空");
                                        flag = 0;
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/AddMaterialRequest",
                                        {
                                            orderNumber:OrderNumber.value,
                                            material_require:material_require.value},
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 0) {
                                                alert("添加失败,该订单不存在");
                                            }
                                            else if (exit == 2) {
                                                alert("添加失败,您不是该订单的负责人");
                                            }
                                            else if (exit == 3){
                                                alert("添加失败,订单处于已完成状态");
                                            }
                                            else {
                                                alert("添加材料需求成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!--订单统计模块-->
                    <div role="tabpanel" class="tab-pane" id="analyse">

                        <div class="data-div">
                            <div class="row tableHeader">
                                <div class="col-xs-1 ">
                                    订单号
                                </div>
                                <div class="col-xs-1">
                                    客户
                                </div>
                                <div class="col-xs-1">
                                    技术员
                                </div>
                                <div class="col-xs-1">
                                    已付金额
                                </div>
                                <div class="col-xs-1">
                                    送货地址
                                </div>
                                <div class="col-xs-1">
                                    是否接单
                                </div>
                                <div class="col-xs-1">
                                    是否备货
                                </div>
                                <div class="col-xs-1">
                                    是否完成
                                </div>
                                <div class="col-xs-2">
                                    下单日期
                                </div>
                                <div class="col-xs-2">
                                    完成日期
                                </div>
                            </div>

                            <%--订单内容显示--%>
                            <%
                                if(showInfo.getPower() == 1) {
                                    OrderTools orderTools = new OrderTools();
                                    List OrderList = orderTools.GetRs();
                                    for (int i = 0; i < OrderList.size(); i++) {
                                        Orders orders = (Orders)OrderList.get(i);
                            %>
                            <div class="tablebody">
                                <div class="row">
                                    <div class="col-xs-1">
                                        <%=orders.getOrderNumber()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getUserAccount()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getStaffAccount()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getPaid()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getAddress()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=(orders.getIsAccept().equals("0"))?"未接单":"已接单"%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=(orders.getIsDistribute().equals("0"))?"未备材料":"已备材料"%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=(orders.getIsCompletion().equals("0"))?"未完成":"已完成"%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=orders.getStartDate()%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=orders.getRealDate()%>
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>

                        </div>
                    </div>
                    <script src="js/jquery.nouislider.js"></script>

                    <!-- this page specific inline scripts -->
                    <script>
                            //min/max slider
                            function huadong(my, unit, def, max) {
                                $(my).noUiSlider({
                                    range: [0, max],
                                    start: [def],
                                    handles: 1,
                                    connect: 'upper',
                                    slide: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                        console.log($(this).find(".noUi-handle").parent().parent().html());
                                    },
                                    set: function() {
                                        var val = Math.floor($(this).val());
                                        $(this).find(".noUi-handle").text(
                                            val + unit
                                        );
                                    }
                                });
                                $(my).val(def, true);
                            }
                            huadong('.slider-minmax1', "分钟", "5", 30);
                            huadong('.slider-minmax2', "分钟", "6", 15);
                            huadong('.slider-minmax3', "分钟", "10", 60);
                            huadong('.slider-minmax4', "次", "2", 10);
                            huadong('.slider-minmax5', "天", "3", 7);
                            huadong('.slider-minmax6', "天", "8", 10);
                        </script>

                    <!-- 删除配件模块 -->
                    <div role="tabpanel" class="tab-pane" id="delete_parts">
                        <div class="row" style="margin-top: 70px;">
                            <div class="col-md-12" style="text-align: center">
                                <h2>删除配件</h2>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form action="" method="post">
                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　管理员密码</span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="parts_password" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　零件名　</span></span>
                                        <input type="text" class="form-control"  placeholder="零件名" id="parts_name" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_dele_parts" style="width:200px;margin:0 auto;" value="删除">
                                </form>
                            </div>
                            <div class="col-md-4"></div>
                        </div>

                        <div class="row" style="height: 30px;"></div>

                    </div>

                    <%--删除配件所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_dele_parts").click(function () {
                                var password = document.getElementById("parts_password");
                                var materialName = document.getElementById("parts_name");
                                var flag = 1;
                                if(password.value == "" ){
                                    alert("管理员密码不能为空");
                                    flag = 0;
                                }
                                else {
                                    if (materialName.value == "") {
                                        alert("材料名不能为空");
                                        flag = 0;
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/DeleteMaterialServlet",
                                        {
                                            password:password.value,
                                            materialName:materialName.value},
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 0) {
                                                alert("删除失败,该材料不存在");
                                            }
                                            else if (exit == 2) {
                                                alert("删除失败,管理员密码错误");
                                            }
                                            else {
                                                alert("删除材料成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!-- 修改权限模块 -->
                    <div role="tabpanel" class="tab-pane" id="edit_power">
                        <div class="row" style="margin-top: 70px;">
                            <div class="col-md-12" style="text-align: center">
                                <h2>修改员工权限</h2>
                            </div>
                        </div>

                        <!--修改权限-->
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form action="" method="post">
                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　管理员密码 </span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="edit_admin_password" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　员工账号　</span></span>
                                        <input type="text" class="form-control"  placeholder="员工账号" id="edit_workAccount" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aPosition" class="col-xs-2 control-label" style="text-align: right;width: 100px;margin-left: 100px;">职位：</label>
                                        <select id="edit_Position" style="height: 40px;">
                                            <option value ="2">技术员</option>
                                            <option value ="3">仓管</option>
                                        </select>
                                    </div>
                                    <input type="button" class="btn btn-info btn-block" id="btn_edit_power" style="width:200px;margin:0 auto;" value="修改">
                                </form>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                    </div>

                    <%-- 修改权限所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_edit_power").click(function () {
                                var edit_admin_password = document.getElementById("edit_admin_password");
                                var edit_workAccount = document.getElementById("edit_workAccount");
                                var edit_Position = document.getElementById("edit_Position");
                                var flag = 1;
                                if(edit_admin_password.value == "" ){
                                    alert("管理员密码不能为空");
                                    flag = 0;
                                }
                                else {
                                    if (edit_workAccount.value == "") {
                                        alert("员工账号不能为空");
                                        flag = 0;
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/ChangeStaffPower",
                                        {
                                            staffAccount:edit_workAccount.value,
                                            password:edit_admin_password.value,
                                            power:edit_Position.value
                                        },
                                        function (data) {
                                            var exit = data.mark;
                                            if (exit == 0) {
                                                alert("该员工账号不存在");
                                            }
                                            else if(exit == 2){
                                                alert("管理员密码错误");
                                            }
                                            else {
                                                alert("修改成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!-- 修改配件模块 -->
                    <div role="tabpanel" class="tab-pane" id="change_parts">
                        <div class="row" style="margin-top: 70px;">
                            <div class="col-md-12" style="text-align: center">
                                <h2>修改零件数量</h2>
                            </div>
                        </div>

                        <!--修改配件-->
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form action="" method="post">
                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　管理员密码 </span></span>
                                        <input type="password" class="form-control"  placeholder="Password" id="change_admin_password" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　零件名　</span></span>
                                        <input type="text" class="form-control"  placeholder="零件名" id="change_partName" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="input-group input-group-lg" style="margin-bottom:30px;margin-top:30px;">
                                        <span class="input-group-addon" ><span class="glyphicon glyphicon-lock" aria-hidden="true" style="color: black">　变更数量　</span></span>
                                        <input onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" type="text" class="form-control"  placeholder="变更数量" id="change_num" aria-describedby="sizing-addon1" value="">
                                    </div>

                                    <div class="form-group" style="margin-bottom:30px;margin-top:30px;">
                                        <label for="aPosition" class="col-xs-2 control-label" style="text-align: right;width: 100px;margin-left: 100px;">类型：</label>
                                        <select id="change_type" style="height: 40px;">
                                            <option value ="1">添加</option>
                                            <option value ="2">减少</option>
                                        </select>
                                    </div>

                                    <input type="button" class="btn btn-info btn-block" id="btn_change_part" style="width:200px;margin:0 auto;" value="修改">
                                </form>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                    </div>

                    <%-- 修改配件所需JavaScript--%>
                    <script>
                        $(function () {
                            $("#btn_change_part").click(function () {
                                var password = document.getElementById("change_admin_password");
                                var materialName = document.getElementById("change_partName");
                                var change_type = document.getElementById("change_type");
                                var num = document.getElementById("change_num");
                                var flag = 1;
                                if(password.value == "" ){
                                    alert("管理员密码不能为空");
                                    flag = 0;
                                }
                                else {
                                    if (materialName.value == "") {
                                        alert("员工账号不能为空");
                                        flag = 0;
                                    }
                                }
                                if (flag == 1) {
                                    $.get(
                                        "/ChangeMaterialNum",
                                        {
                                            materialName:materialName.value,
                                            password:password.value,
                                            change_type:change_type.value,
                                            num:num.value
                                        },
                                        function (data) {
                                            var exit = data.exit;
                                            if (exit == 0) {
                                                alert("仓库中没有该配件");
                                            }
                                            else if(exit == 2){
                                                alert("管理员密码错误");
                                            }
                                            else if(exit == 3){
                                                alert("修改后零件数量小于0,请入库后再备货");
                                            }
                                            else {
                                                alert("修改成功");
                                                window.location.href = "index.jsp";
                                            }
                                        }
                                    );
                                }
                            });
                        });
                    </script>

                    <!--产品统计模块-->
                    <div role="tabpanel" class="tab-pane" id="produce_manager">

                        <div class="data-div">
                            <div class="row tableHeader">
                                <div class="col-xs-1 ">
                                    订单号
                                </div>
                                <div class="col-xs-1">
                                    客户
                                </div>
                                <div class="col-xs-1">
                                    技术员
                                </div>
                                <div class="col-xs-1">
                                    已付金额
                                </div>
                                <div class="col-xs-2">
                                    送货地址
                                </div>
                                <div class="col-xs-2">
                                    下单日期
                                </div>
                                <div class="col-xs-2">
                                    完成日期
                                </div>
                                <div class="col-xs-1">
                                    需付金额
                                </div>
                                <div class="col-xs-1">
                                    是否拿货
                                </div>
                            </div>

                            <%--产品内容显示--%>
                            <%
                                if(showInfo.getPower() == 1) {
                                    ProductionTools productionTools = new ProductionTools();
                                    OrderTools orderTools = new OrderTools();
                                    List ProduceList = productionTools.GetRs();
                                    for (int i = 0; i < ProduceList.size(); i++) {
                                        Produce produce = (Produce)ProduceList.get(i);
                                        orderTools.setOrderNumber(produce.getOrderNumber());
                                        Orders orders = orderTools.getOrder();
                                        if(orders.getIsCompletion().equals("1")){
                            %>
                            <div class="tablebody">
                                <div class="row">
                                    <div class="col-xs-1">
                                        <%=produce.getOrderNumber()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getUserAccount()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getStaffAccount()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=orders.getPaid()%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=orders.getAddress()%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=orders.getStartDate()%>
                                    </div>
                                    <div class="col-xs-2">
                                        <%=orders.getRealDate()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=produce.getNeedPay()%>
                                    </div>
                                    <div class="col-xs-1">
                                        <%=(orders.getIsCompletion().equals("0"))?"已经拿货":"未拿货"%>
                                    </div>
                                </div>
                            </div>
                            <%
                                        }
                                    }
                                }
                            %>

                        </div>

                        <!--页码块-->
                        <footer class="footer">
                            <ul class="pagination">
                                <li>
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                    页
                                </li>
                                <li class="gray">
                                    共20页
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-left">
                                    </i>
                                </li>
                                <li>
                                    <i class="glyphicon glyphicon-menu-right">
                                    </i>
                                </li>
                            </ul>
                        </footer>

                    </div>

                    <script src="js/jquery.nouislider.js"></script>

                    <!-- this page specific inline scripts -->
                    <script>
                        //min/max slider
                        function huadong(my, unit, def, max) {
                            $(my).noUiSlider({
                                range: [0, max],
                                start: [def],
                                handles: 1,
                                connect: 'upper',
                                slide: function() {
                                    var val = Math.floor($(this).val());
                                    $(this).find(".noUi-handle").text(
                                        val + unit
                                    );
                                    console.log($(this).find(".noUi-handle").parent().parent().html());
                                },
                                set: function() {
                                    var val = Math.floor($(this).val());
                                    $(this).find(".noUi-handle").text(
                                        val + unit
                                    );
                                }
                            });
                            $(my).val(def, true);
                        }
                        huadong('.slider-minmax1', "分钟", "5", 30);
                        huadong('.slider-minmax2', "分钟", "6", 15);
                        huadong('.slider-minmax3', "分钟", "10", 60);
                        huadong('.slider-minmax4', "次", "2", 10);
                        huadong('.slider-minmax5', "天", "3", 7);
                        huadong('.slider-minmax6', "天", "8", 10);
                    </script>

                </div>
            </div>
        </div>
</body>

</html>