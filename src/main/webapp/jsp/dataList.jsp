<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
</head>
<body>

<h2 style="text-align: center;">商品信息数据列表</h2>
<div style="margin: 20px 10px 10px 10px;">
    <div style="display: flex; align-items: center;justify-content: space-between;">
        <div>
            <button onclick="deleteAll(this)" class='btn btn-warning btn-sm'>
                <span class="glyphicon glyphicon-trash " aria-hidden="true"></span>
                批量删除
            </button>
            <button type="button" class='btn btn-success btn-sm' data-toggle="modal" data-target="#exampleModal"
                    data-whatever="@mdo">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                添加
            </button>
        </div>
        <form action="../findGoodsServlet" class="form-inline" style="margin: 10px;">
            <div class="form-group">
                <label for="gname">商品名称</label>
                <input type="text" class="form-control" name="gname" placeholder="支持模糊查询" id="gname">
            </div>
            <div class="form-group">
                <label for="gprice">商品单价</label>
                <input type="number" class="form-control" name="gprice" placeholder="小于输入值" id="gprice">
            </div>
            <div class="form-group">
                <label for="gstock">库存</label>
                <input type="number" class="form-control" name="gstock" placeholder="gstock" id="gstock">
            </div>
            <div class="form-group">
                <label style="width: 30px;"></label>
            </div>
            <button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                查 询
            </button>
        </form>

    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr class="info">
            <th><input class="form-control allcheck" type="checkbox"/></th>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品图片</th>
            <th>商品单价</th>
            <th>库存</th>
            <th>保值日期</th>
            <th>分类</th>
            <th>商品描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${goodsList}" var="goods">
            <tr>
                <td>
                    <input type="checkbox">
                </td>
                <td class="goodid">${goods.gid}</td>
                <td>${goods.gname}</td>
                <td>
                    <img src="../${goods.gimg}" height="35px">
                </td>
                <td>${goods.gprice}</td>
                <td>${goods.gstock}</td>
                <td>${goods.maintainDate}</td>
                <td>${goods.classify}</td>
                <td>${goods.gdescribe}</td>
                <td>
                    <button class="btn btn-success btn-sm" data-toggle="modal" data-target="#updateModal">
                        <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                        修 改
                    </button>
                    <button class="btn btn-danger btn-sm delete" onclick="del(this)">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        删 除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!--
        作者：offline
        时间：2019-09-21
        描述：表格底部的分页导航条
    -->
    <nav aria-label="page navigation">
        <ul class="pagination" style="margin: 0;">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
        <label style="font-size: 25px; vertical-align: top; margin-left: 10px;">共5页 48条数据</label>
    </nav>
</div>
<%--修改--%>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateModalLabel">修改商品信息</h4>
            </div>
            <div class="modal-body">
                <form action="../updateGoods" method="post" id="updateForm">
                    <div class="form-group">
                        <label class="control-label">商品编号</label>
                        <div style="width: 240px;">
                            <input type="text" class="form-control" name="gid" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">商品名称</label>
                        <div style="width: 240px;">
                            <input type="text" class="form-control" name="gname" placeholder="请输入名称" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">单价</label>
                        <div style="width: 240px;">
                            <input type="number" class="form-control" name="gprice" placeholder="请输入单价" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">库存</label>
                        <div style="width: 240px;">
                            <input type="number" class="form-control" name="gstock" placeholder="请输入库存" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">保值日期</label>
                        <div style="width: 240px;">
                            <input type="date" class="form-control" name="maintainDate" placeholder="请输入日期" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">商品分类</label>
                        <div style="width: 240px;">
                            <select class="form-control" name="classify">
                                <option>蔬菜</option>
                                <option>厨具</option>
                                <option>手机数码</option>
                                <option>休息娱乐</option>
                                <option>电脑办公</option>
                                <option>食品</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">商品描述</label>
                        <textarea class="form-control" name="gdescribe">这个人很懒，什么都没有说！</textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <!--因为当前button提交按钮在form标签外部，需要使用form属性对form标签进行关联-->
                <button type="submit" form="updateForm" class="btn btn-primary">确认修改</button>
            </div>
        </div>
    </div>
</div>
<%--添加--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">添加商品信息</h4>
            </div>
            <div class="modal-body">
                <form action="../AddServlet" method="post" class="addForm" id="addForm" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label">商品名称</label>
                        <div style="width: 240px;">
                            <input type="text" class="form-control" name="gname" placeholder="请输入名称" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">图片</label>
                        <div style="width: 240px;">
                            <input type="file" class="form-control" name="gimg"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">单价</label>
                        <div style="width: 240px;">
                            <input type="number" class="form-control" name="gprice" placeholder="请输入单价"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">库存</label>
                        <div style="width: 240px;">
                            <input type="number" class="form-control" name="gstock" placeholder="请输入库存"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">保值日期</label>
                        <div style="width: 240px;">
                            <input type="date" class="form-control" name="maintainDate" placeholder="请输入日期"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">商品分类</label>
                        <div style="width: 240px;">
                            <select class="form-control" name="classify">
                                <option>蔬菜</option>
                                <option>厨具</option>
                                <option>手机数码</option>
                                <option>休息娱乐</option>
                                <option>电脑办公</option>
                                <option>食品</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">商品描述</label>
                        <textarea class="form-control" name="gdescribe">这个人很懒，什么都没有说！</textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <!--因为当前button提交按钮在form标签外部，需要使用form属性对form标签进行关联-->
                <button type="submit" form="addForm" class="btn btn-primary">确认添加</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    // $(".delete").click(function(){
    // 	var $n = $(".delete").parents("td").siblings().eq(1).text()
    // 	console.log($n)
    // })
    function del(btn) {
        var num = $(btn).parents("td").siblings().eq(1).text();
        var r = window.confirm("确定要删除当前行吗？");
        if (r) {
            window.location.assign("../delServlet?gid=" + num);
        }
    }

    // 模态框的监听
    $('#updateModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        // 根据按钮对象获取当前的行对应的所有td
        var $tds = button.parent().siblings();
        // 遍历所有的td，获取对应的数据
        // 创建一个数组封装数据
        // $tds.each(function (i,td){
        // 	console.log(td);
        // })

        var modal = $(this)
        modal.find('.modal-body input[name="gid"]').val($tds.eq(1).text());
        modal.find('.modal-body input[name="gname"]').val($tds.eq(2).text());
        modal.find('.modal-body input[name="gprice"]').val($tds.eq(4).text());
        modal.find('.modal-body input[name="gstock"]').val($tds.eq(5).text());
        modal.find('.modal-body input[name="maintainDate"]').val($tds.eq(6).text());
        var classifyValue = $tds.eq(6).text();
        var arr = ["蔬菜", "厨具", "手机数码", "休息娱乐", "电脑办公", "食品"];
        // var res = arr.findIndex(function(value){
        // 	return value = classifyValue;
        // })
        // console.log(res);
        // modal.find('.modal-body select).prop()
        modal.find('.modal-body input[name="gdescribe"]').val($tds.eq(8).text());
    })

    $(".allcheck").click(function () {
        $("tr td input").prop("checked", this.checked)
    })

    function deleteAll(btn) {
        var num = new Array();
        let i = 0;
        $("tr td input:checked").each(function () {
            //将标签的值放入数组中
            num[i] = $(this).parents("td").siblings().eq(0).text();
            console.log(num[i]);
            i++;
        });
        window.location.assign("../DelCheckServlet?array=" + num)
        // $.ajax({
        // 	type: 'POST',
        // 	url: "../DelCheckServlet",
        // 	data: {
        // 		"array": num
        // 	},
        // 	cache: false,
        // 	async: false,
        // 	traditional: true,
        // 	success: function(data) {}
        // });
    }
</script>
</body>
</html>
