$(document).ready(function() {
    $("#quoteField").html("READY");
   
   randomQuote();
   
   $("#rndButton").click(function() {
      randomQuote();
   });
   $("#qbuttonbyid").click(function() {
      newQuoteById();
   });
   $("#qbuttondelete").click(function() {
      deleteQuote();
   });
   $("#qbuttonadd").click(function() {
      addQuote();
   });
});

function randomQuote() {
    $.ajax({
      type : 'GET',
      url : 'api/quote/random'
   }).done(function (data) {
       $("#quoteField").html(data);
   });
}

function newQuoteById() {
    $.ajax({
      type : 'GET',
      url : "api/quote/"+$("#qid").val()
   }).done(function (data) {
       $("#quoteField").html(data);
   });
}

function deleteQuote() {
    $.ajax({
        type:'DELETE',
        url:'api/quote/'+$("#qid").val()
    }).done(function(data) {
        $("#result").html(data);
    });
}

function addQuote() {
    var query = JSON.stringify({quote: $("#qmsg").val()});
    $(quoteField).html("");
    $.ajax({
        type : 'PUT',
        url : 'api/quote',
        data : query,
        contentType : 'text/plain'
    }).done(function(data) {
        $("#result").html(data);
    });
}