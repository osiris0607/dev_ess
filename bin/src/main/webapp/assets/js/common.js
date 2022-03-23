(function(w, $){
  $('#skip_navi a').each(function(){
    $(this).click(function(){
      var thishref = $(this).attr('href');
      $(thishref).attr('tabindex','-1');
      $(thishref).focus();
    });
  });


  
  // tab
  var tabMenu = $(".tabIntab");
  tabMenu.find("ul > li > div").hide();
  tabMenu.find("li.active > div").show();
  function tabList(e){
    e.preventDefault();
    var target = $(this);
    target.next().show().parent("li").addClass("active").siblings("li").removeClass("active").find("div").hide();
  }
  tabMenu.find("ul > li > a").click(tabList).focus(tabList);

  //요소별 :last-child 적용
  $('.coWrap').last().css({'margin-right':'0'});
}) (window, jQuery);  