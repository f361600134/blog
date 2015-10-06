<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="include/menu.jsp"/>
<script src="js/set_update.js"></script>
						<div class="page-header">
							<h1>
								多说设置
								<small>
									<i class="icon-double-angle-right"></i>
									信息设置
								</small>
							</h1>
						</div><!-- /.page-header -->

							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<form role="form" id="ajaxduoshuo" class="form-horizontal">
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 多说短域名 </label>

										<div class="col-sm-9">
											<input type="text" name="duoshuo_short_name" value="${webs.duoshuo_short_name}" class="col-xs-10 col-sm-5" placeholder="" id="form-field-1">
										</div>
									</div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 多说密钥 </label>

										<div class="col-sm-9">
											<input type="text" name="duoshuo_secret" value="${webs.duoshuo_secret}" class="col-xs-10 col-sm-5" placeholder="" id="form-field-1">
											
										</div>
									</div>
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 是否启用 </label>
										<div class="col-sm-9">
										<label>
 											<input class="ace ace-switch ace-switch-6" type="checkbox" value="duoshuo" checked="checked" name="user_comment_plugin">
											<span class="lbl">&nbsp;</span>
										</label>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button id="duoshuo" type="button" class="btn btn-info">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>

											&nbsp; &nbsp; &nbsp;
											<button type="reset" class="btn">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
									</div>

								</form>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
<jsp:include page="include/footer.jsp"/>