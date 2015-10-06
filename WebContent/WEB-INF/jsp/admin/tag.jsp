<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="include/menu.jsp"/>

	<link href="${url }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${url }/admin/assets/css/ui.jqgrid.css" />
	<link rel="stylesheet" href="${url }/admin/assets/css/ace.min.css" />

						<div class="page-header">
							<h1>
								标签查看
								<small>
									<i class="icon-double-angle-right"></i>
									查看所有
								</small>
							</h1>
						</div><!-- /.page-header -->

							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<script src="${url }/admin/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${url }/admin/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${url }/admin/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript">
			
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";
				
				jQuery(grid_selector).jqGrid({
					
				url:'<%=request.getAttribute("url")%>/tag/list.htm',
				mtype:"post",
				datatype: "json",
					colNames:['ID','标签名'/*,'计数'*/],
					colModel:[
						 
						{name:'id',index:'id', width:60, sorttype:"int", editable: false},
						{name:'name',index:'name',width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"20"}},
// 						{name:'count',index:'count', width:70, editable: true},
			
					], 
			
					viewrecords : true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector,
					altRows: true,
					//toppager: true,
					jsonReader: {
						root:"colModel",	
						page: "page",	
						total: "total",
						records: "record",
						repeatitems : false
					},
					
					multiselect: false,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
			
					editurl:"<%=request.getAttribute("url")%>/tag/oper.htm",//nothing is saved
					caption: "标签",
					height:500,
			
					autowidth: true
			
				});
 
			
				 
				function styleCheckbox(table) {
				/**
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
			
			
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				*/
				}
				
			
				//unlike navButtons icons, action icons in rows seem to be hard-coded
				//you can change them like this in here if you want
				function updateActionIcons(table) {
					/**
					var replacement = 
					{
						'ui-icon-pencil' : 'icon-pencil blue',
						'ui-icon-trash' : 'icon-trash red',
						'ui-icon-disk' : 'icon-ok green',
						'ui-icon-cancel' : 'icon-remove red'
					};
					$(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
					*/
				}
				
				//replace icons with FontAwesome icons like above
				function updatePagerIcons(table) {
					var replacement = 
					{
						'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
						'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
						'ui-icon-seek-next' : 'icon-angle-right bigger-140',
						'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
					};
					$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			
			
			});
		</script>
	<div style="display:none"></div>
</body>
</html>
