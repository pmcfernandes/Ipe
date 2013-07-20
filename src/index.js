// JavaScript Document
function init() {
    document.getElementById("demo").innerHTML = "Ola";   
}

$(document).ready(function() {
   $("#btn").on('click', function() {
      window.app.error(window.app.Properties.getString('customText'));
      window.app.quit();
   });
});