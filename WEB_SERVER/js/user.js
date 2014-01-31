document.ready=function(){
	$("#DB_cardForm").dialog({
      autoOpen:false,
      show:{
        effect:"blind",duration:500
      },
      hide:{
        effect:"blind",duration:500
      },
	  modal:true,height:500,width:600
  });
  $("#referurlPart").dialog({
      autoOpen:false,
      show:{
        effect:"blind",duration:500
      },
      hide:{
        effect:"blind",duration:500
      },
	  modal:true,height:225,width:500
  });
   $("#referurlbtn").click(function(){
      $("#referurlPart").dialog("open");
  });
  $('#expdate').datepicker({
     changeMonth: true,
     changeYear: true,
     dateFormat: 'MM yy',

     onClose: function() {
        var iMonth = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
        var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
        $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
     },

     beforeShow: function() {
       if ((selDate = $(this).val()).length > 0) 
       {
          iYear = selDate.substring(selDate.length - 4, selDate.length);
          iMonth = jQuery.inArray(selDate.substring(0, selDate.length - 5), 
                   $(this).datepicker('option', 'monthNames'));
          $(this).datepicker('option', 'defaultDate', new Date(iYear, iMonth, 1));
          $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
       }
    }
  });
   $("#details").click(function(){
      $("#DB_cardForm").dialog("open");
  });
};