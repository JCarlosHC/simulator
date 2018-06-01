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
                        <img src="images/ipn-logo.png" alt="logo ipn" width="100%;">
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
                <%
                    SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date fecha_actual = new java.util.Date();
                    simulacion datos = new simulacion();
                    JSONObject objJson = datos.getSimulacion();
                    JSONArray jsonArray = objJson.getJSONArray("listaA");
                    JSONArray fresas = objJson.getJSONArray("fresas");
                    JSONArray tornos = objJson.getJSONArray("tornos");
                    JSONArray jsonArrayB = objJson.getJSONArray("listaB");
                    SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                   
                %>
                
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" id="tablasimulacion" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Pieza A</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada (<%= fecha.format(fecha_actual)%>)</th>
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
                                
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject p = jsonArray.getJSONObject(i);
                                    String strhorallegada = p.getString("horaLlegada");
                                    String strhorainicio = p.getString("horaInicio");
                                    String strhorasalida = p.getString("horaSalida");
                                    Date horallegada = formato.parse(strhorallegada);
                                    Date horainicio = formato.parse(strhorainicio);
                                    Date horasalida = formato.parse(strhorasalida);
                            %>
                            <tr>
                                <td><%=p.getInt("id")%> </td>
                                <td><%=p.getDouble("ram1")%></td>
                                <td><%=p.getDouble("timeInLlegar")%></td>
                                <td><%=fecha.format(horallegada)%></td>
                                <td><%=fecha.format(horainicio)%></td>
                                <td><%=p.getDouble("ram2")%></td>
                                <td><%=p.getDouble("timeInA")%></td>
                                <td><%=fecha.format(horasalida)%></td>
                                <td><%=p.getDouble("tiempoEspera")%></td>
                                <td><%=p.getDouble("ram3")%></td>
                                <td><%=p.getBoolean("isDefectuosa")%></td>
                                <td><%=p.getDouble("timeInSystem")%></td>
                                <td><%=p.getInt("idtorno")%></td>
                            </tr>
                            <% }%>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Pieza A</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada (<%= fecha.format(fecha_actual)%>)</th>
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
                                
                <!-- PIEZA B -->   
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" id="tablasimulacionB" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Pieza B</th>
                                <th>ri</th>
                                <th>Tiempo en llegar (y = 2 + 6ri)</th>
                                <th>Hora de llegada (<%= fecha.format(fecha_actual)%>)</th>
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
                                
                                for (int i = 0; i < jsonArrayB.length(); i++) {
                                    JSONObject p = jsonArrayB.getJSONObject(i);
                                    String strhorallegadaA = p.getString("horaLlegada");
                                    String strhorainicioA = p.getString("horaInicio");
                                    String strhorasalidaA = p.getString("horaSalida");
                                    Date horallegadaA = formato.parse(strhorallegadaA);
                                    Date horainicioA = formato.parse(strhorainicioA);
                                    Date horasalidaA = formato.parse(strhorasalidaA);
                                    
                                    String strhorallegadatornos = p.getString("horaLlegadaTornos");
                                    String strhorainiciotornos = p.getString("horaInicioTornos");
                                    String strhorasalidatornos = p.getString("horaSalidaTornos");
                                    Date horallegadatornos = formato.parse(strhorallegadatornos);
                                    Date horainiciotornos = formato.parse(strhorainiciotornos);
                                    Date horasalidatornos = formato.parse(strhorasalidatornos);
                            %>
                            <tr>
                                <td><%=p.getInt("id")%> </td>
                                <td><%=p.getDouble("ram1")%></td>
                                <td><%=p.getDouble("timeInLlegar")%></td>
                                <td><%=fecha.format(horallegadaA)%></td>
                                <td><%=fecha.format(horainicioA)%></td>
                                <td><%=p.getDouble("ram2")%></td>
                                <td><%=p.getDouble("timeInB")%></td>
                                <td><%=fecha.format(horasalidaA)%></td>
                                <td><%=p.getDouble("tiempoEsperaTornos")%></td>
                                <td><%=fecha.format(horallegadatornos)%></td>
                                <td><%=fecha.format(horainiciotornos)%></td>
                                <td><%=p.getDouble("ram3")%></td>
                                <td><%=p.getDouble("timeInA")%></td>
                                <td><%=fecha.format(horasalidatornos)%></td>
                                <td><%=p.getDouble("tiempoEspera")%></td>
                                <td><%=p.getDouble("ram3")%></td>
                                <td><%=p.getBoolean("isDefectuosa")%></td>
                                <td><%=p.getDouble("timeInSystem")%></td>
                                <td><%=p.getInt("idtorno")%></td>
                                <td><%=p.getInt("idfresa")%></td>
                            </tr>
                            <% }%>
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
                $('#tablasimulacion').DataTable();
                $('#tablasimulacionB').DataTable();
            });
        </script>
    </body>
</html>
