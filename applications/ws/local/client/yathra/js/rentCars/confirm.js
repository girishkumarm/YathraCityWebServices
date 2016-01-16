$(document).ready(function() {

var width = $(window).width();
      if (width >= 850) {
          $('.websiteView').show();
      } else {
          $('.websiteView').hide();
      }
     $('.alert').hide();

 $('.glyphicon-arrow-left').click(function(){
        parent.history.back();

    });

});
