<%--
  Created by IntelliJ IDEA.
  User: Zimomo
  Date: 2019/8/2
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Search <input type="text" name="name" class="inputtable" id="name" autocomplete="false"/>
    <input type="button" value="搜索" border="0" style="margin-bottom:-4px">

    <div id="context1" style="background-color:white; border: 1px solid red;width:170px;position: absolute;top: 30px;left:65px;display:none" >
    </div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
    $(".inputtable").keyup(function(){
        var content=$(this).val();
        //如果当前搜索内容为空，无须进行查询
        if(content==""){
            $("#context1").css("display","none");
            return ;
        }
        //由于浏览器的缓存机制 所以我们每次传入一个时间

        $.ajax({
            type:"get",
            //新建一个名为findBooksAjaxServlet的servlet
            url:"/AjaxSearch",
            data:{name:content},
            success:function(data){
                //拼接html
                var res=data.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    //每一个div还有鼠标移出、移入点击事件
                    html+="<div onclick='setSearch_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#context1").html(html);
                //显示为块级元素
                $("#context1").css("display","block");
            }
        });
    });

    //鼠标移动到内容上
    function changeBackColor_over(div){
        $(div).css("background-color","#CCCCCC");
    }
    //鼠标离开内容
    function changeBackColor_out(div){
        $(div).css("background-color","");
    }
    //将点击的内容放到搜索框
    function setSearch_onclick(div){
        $(".inputtable").val(div.innerText);
        $("#context1").css("display","none");
    }
</script>
</html>
