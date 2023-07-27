$(document).ready(function(){

let reset  = $(".links").find("li").find("#reset"); 

  //----------- reset ---------------------
  reset.on("click",function(e){
    e.preventDefault();
    $(this).parent().parent().siblings("form")
    .find(".input__block").find(".input").val("");
  })
});