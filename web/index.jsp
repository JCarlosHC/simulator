<%@page import="net.model.pieza_B_Error"%>
<%@page import="java.util.List"%>
<%@page import="net.model.pieza_A_Error"%>
<%@page import="net.model.torno"%>
<%@page import="net.model.fresa"%>
<%@page import="net.model.pieza_B"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.json.JSONArray"%>
<%@page import="com.json.JSONObject"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.model.pieza_A"%>
<%@page import="net.dao.simulacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Simulacion del sistema</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
        <link href="css/sb-admin.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css" />
        
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <div class="row">
                    <div class="col-md-1">
                        <img src="images/ipn-logo.png" alt="logo ipn" width="150%;">
                    </div>
                    <div class="col-md-10">
                        <h1 class="text-center">INSTITUTO POLITECNICO NACIONAL</h1>
                        <p class="text-center">Unidad Profesional Interdisciplinaria de Ingeniería y Ciencias Sociales y Administrativas</p>
                    </div>
                    <div class="col-md-1">
                        <img src="images/upiicsa-logo.png" alt="logo upiicsa" width="100%;">
                    </div>
                </div>
            </div>
            <h2 class="text-center">Simulacion del sistema</h2>   
            <p>Integrantes: 
            <ul><li>Hernandez Castro Juan Carlos</li><li>Tepale Cruz Tonatiuh</li></ul>
            </p>
            <a href="javascript:void(0);" id="show">Mostrar redacción</a>
            <div id="element" class="row" style="display: none;">
                <div id="close"><a href="javascript:void(0);" id="hide">ocultar</a></div>
                <p>
                    3.62. A un sistema de producción llegan piezas de tipo A a cada 5+-3 minutos y piezas tipo B cada 3+-2 minutos. Las piezas A pasan por el departamento de tornos cuyo tiempo de proceso es 8+-3 minutos, al salir de este departamento el 25% son defectuosas y deben reprocesarse, el 75% son buenas y salen del sistema hacia ventas. Las piezas B pasan primero por el departamento de fresas donde el tiempo de proceso es de 9+-3 minutos y, despues, por el departamento de tornos donde el tiempo de proceso es de 3+-1 minutos, al salir de este departamento se estima que el 5% son defectuosas y deben reprocesarse desde el principio; el 95% restante sale del sistema para su venta.
                </p>
                <p>
                    A) Simule 1 mes y determine el minimo numero de tornos y fresas para que ambos productos fluyan continuamente a traves del sistema. Indicando el numero de piezas A y B producidas durante el mes.
                </p>
                <p>
                    B) Resuelva ahora considerando fallas de los transformadores de energia del departamento de fresas. Cada 15 horas, con distribucion exponencial, ocurre una falla de energia electrica y el tiempo para levantar de nuevo la energia es de 15+-8 minutos. 
                </p>
            </div>
            <form action="servletSimulacion" method="post">
                <div class="row">
                    <div class="col-md-3">
                        <label for="fechainit">Fecha inicio </label>
                        <input type="datetime" class="form-control" name="fechainit" id="fechainit" readonly="readonly" >
                        <input type="hidden" id="fechacompleta">
                    </div>
                    <div class="col-md-3">
                        <label for="days">Dias a simular </label> 
                        <input type="text" class="form-control" name="days" id="days">
                    </div>
                    <div class="col-md-3">
                        <label for="fechaend">Fecha fin </label>
                        <input type="datetime" class="form-control" name="fechaend" id="fechaend" readonly="readonly" >
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary">Simular</button>
                    </div>
                </div>
            </form>
            <%
                Boolean success = (Boolean) request.getAttribute("success");
                
                if(success!=null){
                    SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    ArrayList<pieza_A> listaA = (ArrayList<pieza_A>) request.getAttribute("listaA");
                    ArrayList<pieza_B> listaB = (ArrayList<pieza_B>) request.getAttribute("listaB");
                    ArrayList<fresa> fresas = (ArrayList<fresa>) request.getAttribute("fresas");
                    ArrayList<torno> tornos = (ArrayList<torno>) request.getAttribute("tornos");
                    
            %>
            <br>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3">
                            Cantidad de tornos:
                            <%=tornos.size()%>
                        </div>
                        <div class="col-md-3">
                            Cantidad de fresas:
                            <%=fresas.size()%>
                        </div>
                        <div class="col-md-3">
                            Numero piezas A
                            <%=listaA.size()%>
                        </div>
                        <div class="col-md-3">
                            Numero piezas B
                            <%=listaB.size()%>
                        </div>
                    </div>
                </div>
            </div><br><br>
            <h3 class="text-center">Simulacion de la pieza A</h3>
            <div class="row">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" id="tablasimulacion" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Pieza A</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada</th>
                                <th>Hora de Inicio </th>
                                <th>ri</th>
                                <th>Tiempo en dep. A (z = 5 + 6ri)</th>
                                <th>Hora de salida</th>
                                <th>Tiempo de espera</th>
                                <th>ri</th>
                                <th>Defectuosa/no defectuosa</th>
                                <th>Tiempo en el sistema</th>
                                <th>id torno</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < listaA.size(); i++) {
                                    pieza_A p = listaA.get(i);
                                    List<pieza_A_Error> errorA = p.getErrores();
                            %>
                            <tr>
                                <td><%=p.getId()%> </td>
                                <td><%=p.getRam1()%></td>
                                <td><%=p.getTimeInLlegar()%></td>
                                <td><%=fecha.format(p.getHoraLlegada())%></td>
                                <td><%=fecha.format(p.getHoraInicio())%></td>
                                <td><%=p.getRam2()%></td>
                                <td><%=p.getTimeInA()%></td>
                                <td><%=fecha.format(p.getHoraSalida())%></td>
                                <td><%=p.getTiempoEspera()%></td>
                                <td><%=p.getRam3()%></td>
                                <td><%if(errorA.size() > 0){
                                        JSONArray errorAJsonObj = new JSONArray(errorA);
                                    %>
                                    <a data-toggle="modal" data-target="#mostrarErrores" data-type="a" data-errores='<%=errorAJsonObj%>'>
                                        <span class="fa fa-eye"></span>
                                        +
                                    </a>
                                    <%}%>
                                    <%=p.getIsDefectuosa()%>
                                </td>
                                <td><%=p.getTimeInSystem()%></td>
                                <td><%=p.getIdtorno()%></td>
                            </tr>
                            <% }%>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Pieza A</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada</th>
                                <th>Hora de Inicio </th>
                                <th>ri</th>
                                <th>Tiempo en dep. A (z = 5 + 6ri)</th>
                                <th>Hora de salida</th>
                                <th>Tiempo de espera</th>
                                <th>ri</th>
                                <th>Defectuosa/no defectuosa</th>
                                <th>Tiempo en el sistema</th>
                                <th>id torno</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div><br>
            <h3 class="text-center">Simulacion de la pieza B</h3>
            <div class="row">                    
                <!-- PIEZA B -->   
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" id="tablasimulacionB" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Pieza B</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada</th>
                                <th>Hora de Inicio fresas</th>
                                <th>ri</th>
                                <th>Tiempo en dep. fresas (6 + 6ri)</th>
                                <th>Hora de salida</th>
                                <th>Tiempo de espera</th>
                                <th>Hora de llegada tornos</th>
                                <th>Hora de Inicio tornos</th>
                                <th>ri</th>
                                <th>Tiempo en dep. tornos (2 + 2ri)</th>
                                <th>Hora de salida</th>
                                <th>Tiempo de espera</th>
                                <th>ri</th>
                                <th>Defectuosa/no defectuosa</th>
                                <th>Tiempo en el sistema</th>
                                <th>id torno</th>
                                <th>id fresa</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                
                                for (int i = 0; i < listaB.size(); i++) {
                                    pieza_B p = listaB.get(i);
                                    List<pieza_B_Error> errorb = p.getErrores();
                            %>
                            <tr>
                                <td><%=p.getId()%> </td>
                                <td><%=p.getRam1()%></td>
                                <td><%=p.getTimeInLlegar()%></td>
                                <td><%=fecha.format(p.getHoraLlegada())%></td>
                                <td><%=fecha.format(p.getHoraInicio())%></td>
                                <td><%=p.getRam2()%></td>
                                <td><%=p.getTimeInB()%></td>
                                <td><%=fecha.format(p.getHoraSalida())%></td>
                                <td><%=p.getTiempoEspera()%></td>
                                <td><%=fecha.format(p.getHoraLlegadaTornos())%></td>
                                <td><%=fecha.format(p.getHoraInicioTornos())%></td>
                                <td><%=p.getRam3()%></td>
                                <td><%=p.getTimeInA()%></td>
                                <td><%=fecha.format(p.getHoraSalidaTornos())%></td>
                                <td><%=p.getTiempoEsperaTornos()%></td>
                                <td><%=p.getRam4()%></td>
                                <td><%if(errorb.size() > 0){
                                        JSONArray errorBJsonObj = new JSONArray(errorb);
                                    %>
                                    <a data-toggle="modal" data-target="#mostrarErrores" data-type="b" data-errores='<%=errorBJsonObj%>'>
                                        <span class="fa fa-eye"></span>
                                        +
                                    </a>
                                    <%}%>
                                    <%=p.getIsDefectuosa()%>
                                </td>
                                <td><%=p.getTimeInSystem()%></td>
                                <td><%=p.getIdtorno()%></td>
                                <td><%=p.getIdfresa()%></td>
                            </tr>
                            <% } %>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Num.</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada</th>
                                <th>Hora de Inicio </th>
                                <th>ri</th>
                                <th>Tiempo en dep. A (z = 5 + 6ri)</th>
                                <th>Hora de salida</th>
                                <th>Tiempo de espera</th>
                                <th>Hora de llegada tornos</th>
                                <th>Hora de Inicio tornos</th>
                                <th>ri</th>
                                <th>Tiempo en dep. tornos (2 + 2ri)</th>
                                <th>Hora de salida</th>
                                <th>Tiempo de espera</th>
                                <th>ri</th>
                                <th>Defectuosa/no defectuosa</th>
                                <th>Tiempo en el sistema</th>
                                <th>id torno</th>
                                <th>fresa</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <!--fin piezas b-->
            </div>
            <%}%>
            <!-- Errores modal -->
            <div class="modal fade" id="mostrarErrores" tabindex="-1" role="dialog" aria-labellebdy="title" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header bg-light">
                            <h5 class="modal-title" id="title">Errores</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>                    
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive" id="tablaerrores">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div> 
            
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <!-- Page level plugin JavaScript-->
        <script src="vendor/chart.js/Chart.min.js"></script>
        <script src="vendor/datatables/jquery.dataTables.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                var d = new Date();
                $("#fechacompleta").val(d);
                $('#fechainit').val(d.toLocaleString());
                $("#days").keyup(function() {
                    var fecha = new Date($('#fechacompleta').val());
                    var dias = parseInt(fecha.getDate()) + parseInt($(this).val());
                    fecha.setDate(dias);
                    $('#fechaend').attr('value', fecha.toLocaleString());
                });
                
                $('#tablasimulacion').DataTable();
                $('#tablasimulacionB').DataTable();
                
                $("#hide").click(function(){
                    $("#element").hide();
                });
                $("#show").click(function(){
                    $("#element").show();
                });
                
                //Errores pieza A
                $('#mostrarErrores').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var data = button.data('errores');
                    var tipo = button.data('type');
                    var tabla = '';
                    var trHTML = '';
                    if(tipo=='a'){
                        tabla = '<table class="table table-striped table-bordered" id="tablasimulacion" width="100%" cellspacing="0">';
                        tabla += '<thead>';
                        tabla += '<tr>';
                        //10 columnas
                        tabla += '<th>Pieza A</th><th>Hora de llegada</th><th>Hora de Inicio </th><th>ri</th><th>Tiempo en dep. A (z = 5 + 6ri)</th>';
                        tabla += '<th>Hora de salida</th><th>Tiempo de espera</th><th>ri</th><th>Defectuosa/no defectuosa</th><th>id torno</th>'
                        tabla += '</tr>';
                        tabla += '</thead>';
                        tabla += '<tbody>';
                        
                        trHTML = '';
                        $.each(data, function (i, item) {
                            trHTML += '<tr><td>' + item.id + '</td><td>' + item.horaLlegada.toLocaleString() + '</td><td>' + item.horaInicio.toLocaleString() + '</td>';
                            trHTML += '<td>' + item.ramTornos + '</td><td>' + item.timeInA + '</td><td>' + item.horaSalida.toLocaleString() + '</td>';
                            trHTML += '<td>' + item.tiempoEspera + '</td><td>' + item.ramDefectuosa + '</td><td>' + item.isDefectuosa + '</td>';
                            trHTML += '<td>' + item.idtorno + '</td></tr>';
                        });
                        
                        tabla += trHTML;
                        tabla += '</tbody></table>';
                    }else{
                        tabla = '<table class="table table-striped table-bordered" id="tablasimulacion" width="100%" cellspacing="0">';
                            tabla += '<thead>';
                            tabla += '<tr>';
                            //17 columnas
                            tabla += '<th>Pieza B</th><th>Hora de llegada</th><th>Hora de Inicio fresas</th><th>ri</th><th>Tiempo en dep. fresas (6 + 6ri)</th>';
                            tabla += '<th>Hora de salida</th><th>Tiempo de espera</th><th>Hora de Inicio tornos</th><th>ri</th>';
                            tabla += '<th>Tiempo en dep. tornos (2 + 2ri)</th><th>Hora de salida</th><th>Tiempo de espera</th><th>ri</th><th>Defectuosa/no defectuosa</th>';
                            tabla += '<th>id torno</th><th>id fresa</th>';
                            tabla += '</tr>';
                            tabla += '</thead>';
                            tabla += '<tbody>';
                        
                        trHTML = '';
                        $.each(data, function (i, item) {
                            trHTML += '<tr><td>' + item.id + '</td><td>' + item.horaLlegada.toLocaleString() + '</td><td>' + item.horaInicioFresas.toLocaleString() + '</td>';
                            trHTML += '<td>' + item.ramFresas + '</td><td>' + item.timeInFresas + '</td><td>' + item.horaSalidaFresas.toLocaleString() + '</td>';
                            trHTML += '<td>' + item.tiempoEsperaFresas + '</td><td>' + item.horaInicioTornos + '</td><td>' + item.ramTornos + '</td>';
                            trHTML += '<td>' + item.timeInTornos + '</td><td>' + item.horaSalidaTornos + '</td><td>' + item.tiempoEsperaTornos + '</td>';
                            trHTML += '<td>' + item.ramDefectuosa + '</td><td>' + item.isDefectuosa + '</td><td>' + item.idtorno + '</td><td>' + item.idfresa + '</td></tr>';
                        });
                        
                        
                        tabla += trHTML;
                        tabla += '</tbody></table>';
                        
                    }
                    $('#tablaerrores').html( tabla );
                });

            });
        </script>
    </body>
</html>
