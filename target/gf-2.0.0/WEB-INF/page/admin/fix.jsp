<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>报修信息</title>
    #parse("sys/header.html")
</head>
<body>
<div id="rrapp" v-cloak>
    <div v-show="showList">
        <div class="grid-btn">
            <div class="form-group col-sm-2">
                <input type="text" class="form-control" v-model="q.name" @keyup.enter="query" placeholder="关键字">
            </div>
            <a class="btn btn-default" @click="query">查询</a>
            <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
            <a class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
            <a class="btn btn-primary" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
            <a class="btn btn-primary" @click="info"><i class="fa fa-info"></i>&nbsp;详情</a>
        </div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>

    <div v-show="!showList" class="panel panel-success">
        <div class="panel-heading">{{title}}</div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-2 control-label">报修标题</div>
                <div class="col-sm-10">
                    <input type="text" class="form-control" :disabled="showInfo" v-model="fix.name" placeholder="报修标题"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label">详情</div>
                <div class="col-sm-10">
                    <textarea name="" id="" cols="30" rows="5" class="form-control" :disabled="showInfo"
                              v-model="fix.remark" placeholder="详情"></textarea>
                </div>
            </div>
            <div class="form-group" v-if="fix.id">
                <div class="col-sm-2 control-label">物业完成状态</div>
                <div class="col-sm-10">
                    {{fix.state}}
                </div>
            </div>
            <div class="form-group" v-if="fix.id">
                <div class="col-sm-2 control-label">用户确认状态</div>
                <div class="col-sm-10">
                    <select name="" id="" class="form-control" :disabled="showInfo" v-model="fix.ustate">
                        <option value="待确认">待确认</option>
                        <option value="已完成">已完成</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label"></div>
                <input type="button" class="btn btn-primary" @click="saveOrUpdate" value="确定" v-if="!showInfo"/>
                &nbsp;&nbsp;<input type="button" class="btn btn-warning" @click="reload" value="返回"/>
            </div>
        </form>
    </div>
</div>

<script src="${rc.contextPath}/js/admin/fix.js?_${date.systemTime}"></script>
</body>
</html>