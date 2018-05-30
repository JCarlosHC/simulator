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
            <div class="row">
                <h3>Piezas A</h3>
                <%
                    SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date fecha_actual = new java.util.Date();
                %>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" id="simulacion" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Num.</th>
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
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                simulacion datos = new simulacion();
                                ArrayList<pieza_A> lista = (ArrayList<pieza_A>) datos.getSimulacionA();
                                for (int i = 0; i < lista.size(); i++) {
                                    pieza_A p = lista.get(i);
                            %>
                            <tr>
                                <td><%=p.getId()%></td>
                                <td><%=p.getRam1()%></td>
                                <td><%=p.getTimeInLlegar()%></td>
                                <td><%=fecha.format(p.getHoraLlegada())%></td>
                                <td><%=fecha.format(p.getHoraInicio())%></td>
                                <td><%=p.getRam2()%></td>
                                <td><%=p.getTimeInA()%></td>
                                <td><%=fecha.format(p.getHoraSalida())%></td>
                                <td><%=p.getTiempoEspera()%></td>
                                <td><%=p.getRam3()%></td>
                                <td><%=p.getIsDefectuosa()%></td>
                                <td><%=p.getTimeInSystem()%></td>
                            </tr>
                            <% }%>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Num.</th>
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
                            </tr>
                        </tfoot>
                    </table>
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
        <!-- Custom scripts for this page-->
        <script src="js/sb-admin-datatables.min.js"></script>
        <script src="js/sb-admin-charts.min.js"></script>
        
    </body>
</html>
