// JavaScript Document
function init() {
   $("#demo").html("Ola3");
}

$(document).ready(function() {
   init();

   $("#btn").on('click', function() {
       window.app.error("Ola");
   });
});