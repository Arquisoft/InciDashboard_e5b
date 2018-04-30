$(document).ready(function() {
var tabla = $("#tablaIncidenciasBody");

var socket = new SockJS('/stomp');

var stompClient = Stomp.over(socket);

  stompClient.connect({ }, function(frame) {
  stompClient.subscribe("/topic/incidences", function(data) {
    var incidencia = JSON.parse(data.body);
    var encontrado = false;

    $(".nameIncidencia").each(function(i) {
      if (this.innerHTML === incidencia.contents) {
        encontrado = true;
        return false;
      }
    });

    if (!encontrado) {
      var htmlstring = "\
      <tr>\
        <td>" + incidencia.id + "</td>\
        <td class=\"nameIncidencia\">" + incidencia.name + "</td>\
        <td>" + incidencia.description + "</td>\
        <td>" + incidencia.status + "</td>\
        <td >" + incidencia.expiration + "</td>\
        <td >" + incidencia.operatorComments + "</td>\
        <td >" + incidencia.dangerous + "</td>\
        <td > <iframe width=\"300\" height=\"225\" frameborder=\"0\" style=\"border:0\"" +
        		"src=\"https://www.google.com/maps/embed/v1/view?key=AIzaSyAnjyWNjAWTI8Cr80Uqv0thhdpLUpm3cNk&center=" + incidencia.location +"&zoom=18&maptype=satellite\" allowfullscreen></iframe></td>" +
        				" </tr>\
      ";
       tabla.append(htmlstring);
    }
  });
  });
});