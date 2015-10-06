
	$(function(){
		$(".btn-info").click(function(){
			var formId="ajax"+$(this).attr("id");
			$.post('website/update',$("#"+formId).serialize(),function(data){
				if(data.success){
					$.gritter.add({
						title: '  操作成功...',
						class_name: 'gritter-success' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : ''),
					});
					location.href = location.href;
				}else{
					$.gritter.add({
						title: '  发生了一些异常...',
						class_name: 'gritter-error' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : ''),
					});
				}
			});
		});
	});