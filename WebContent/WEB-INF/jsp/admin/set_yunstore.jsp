<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="include/menu.jsp"/>
<script src="js/set_update.js"></script>		
						<div class="page-header">
							<h1>
								云存储设置
								<small>
									<i class="icon-double-angle-right"></i>
									信息设置
								</small>
							</h1>
						</div><!-- /.page-header -->

							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<form role="form" id="ajaxyunstore" class="form-horizontal">
									<input type="hidden" value="qiniu" name="bucket_type">
									<div class="form-group"> 
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 七牛  ACESS_KEY </label>

										<div class="col-sm-9">
											<input type="text" name="qiniu_access_key" value="${webs.qiniu_access_key }" class="col-xs-10 col-sm-5" placeholder="" id="form-field-1">
										</div>
									</div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 七牛 SECRET_KEY </label>

										<div class="col-sm-9">
											<input type="text" name="qiniu_secret_key" value="${webs.qiniu_secret_key}" class="col-xs-10 col-sm-5" placeholder="" id="form-field-1">
											
										</div>
									</div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 七牛HOST </label>

										<div class="col-sm-9">
										<input type="text" name="qiniu_host" value="${webs.qiniu_host}" class="col-xs-10 col-sm-5" placeholder="" id="form-field-1">
											
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 七牛仓库名 </label>

										<div class="col-sm-9">
										<input type="text" name="qiniu_bucket" value="${webs.qiniu_bucket}" class="col-xs-10 col-sm-5" placeholder="" id="form-field-1">
											
										</div>
									</div>
									<div class="space-4"></div>

									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button id="yunstore" type="button" class="btn btn-info">
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