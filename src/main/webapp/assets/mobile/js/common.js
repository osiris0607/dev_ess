
	$(document).ready(function() {
		  // accodion
		  $(".accodion-content").css("display", "none");
		  // $(".accodion li.first").addClass("on");
		  $(".accodion li.first .accodion-content").css("display", "block");
		  $(".accodion li").delegate(".item-toggle", "click", function () {
		    $(this).parent().siblings(".on").toggleClass("on").attr("aria-expanded", "false");
		    $(this).parent().siblings().children(".item-hide").slideUp("fast");
		    $(this).parent().toggleClass("on").attr("aria-expanded", "true");
		    $(this).siblings(".item-hide").stop("true", "true").slideToggle("fast");
		  });
		  //sub accodion
		  $(".sub_accodion-content").css("display", "none");
		  $(".sub_accodion > li").delegate(".sub_item-toggle", "click", function () {
		    $(this).parent().siblings(".on").toggleClass("on").attr("aria-expanded", "false");
		    $(this).parent().siblings().children(".sub_item-hide").slideUp("fast");
		    $(this).parent().toggleClass("on").attr("aria-expanded", "true");
		    $(this).siblings(".sub_item-hide").stop("true", "true").slideToggle("fast");
		  });
	});

  $('#skip_navi a').each(function(){
    $(this).click(function(){
      var thishref = $(this).attr('href');
      $(thishref).attr('tabindex','-1');
      $(thishref).focus();
    });
  });

  // side menu
  $('.menu').on('click', function() {
    $('.sidebar').toggleClass('active');
    $('.mask').fadeIn();
  });
  $('.sideClose').on('click', function() {
    $('.sidebar').toggleClass('active');
    $('.mask').fadeOut();
  });


