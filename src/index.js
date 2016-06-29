// JavaScript Document
function btnCustomProperty_Click() {
    var x = window.app.getProperties().getString("customText");   
    alert(x);
}

function btnQuit_Click() {
    window.app.quit();
}

function btnPasteClipboard_Click() {
    var x = window.app.Clipboard.getText();
    document.getElementById("demo").innerHTML = x;
}

function btnNewWindow_Click() {
    window.app.UI.createDialog("my_name", "index_1.html", "Test a new window", 300, 200);
}