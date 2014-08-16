/**
 * 警告提示框插件
 */

(function($) {    
	$.fn.balert = function(options) {  
		  var defaults = {        
		    url: '' ,
		    title:'',
		    close:false,
		    id:0,
		    div:'.table',
		    msgid : 'ksjdflkjirgjklsdjfksdhglksdjfiowjflksdjgksdjflkdjf'
		  };    
		  var opts = $.extend(defaults, options);  
		  
		  $(this).click(function(){
				var id = $(this).attr("data");
				if(opts.id == id) {
					return 1;
				}else{
					opts.id = id;
				}
				var msghtml = '<div id="'+opts.msgid+'" class="alert alert-warning alert-dismissable">';
				msghtml +='<button type="button" class="close" data-dismiss="alert" aria-hidden="true">关闭</button>';
				msghtml +='<strong>警告!</strong> '+opts.title;
				msghtml +='<a href="'+opts.url+opts.id+'">确定</a></div>';
				var msgidobj = $('#'+opts.msgid);
				if(msgidobj) msgidobj.remove();
				var msg = $(msghtml).hide();
				$(opts.div).before(msg);
				msg.show("slow");
		  });
			
		};
		
})(jQuery); 