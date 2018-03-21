$(document).ready(function() {
var tabla = $("#tablaIncidenciasBody");

var socket = new SockJS('/stomp');

var stompClient = Stomp.over(socket);

  stompClient.connect({ }, function(frame) {
  stompClient.subscribe("/topic/incidencias", function(data) {
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
      </tr>\
      ";
       tabla.append(htmlstring);
    }
  });
  });
});