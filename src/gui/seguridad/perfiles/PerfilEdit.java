/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.seguridad.perfiles;

import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Perfil;
import beans.seguridad.Permiso;
import beans.seguridad.Usuario;
import controllers.CParametro;
import controllers.CPerfil;
import controllers.CPermiso;
import controllers.CPista;
import controllers.CUsuario;
import gui.ErrorDialog;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author msolorzano
 */
public class PerfilEdit extends javax.swing.JDialog {

    /**
     * Creates new form PerfilEdit
     */
    List<Parametro> ListaEstado ;
    Perfil perfil;
    CParametro ParametroBL = new CParametro();
    List<Perfil> ListaPerfiles ;

        
    CPerfil Perfil= new CPerfil ();
    
    private Integer idperfil = -1;
    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }
    public Integer getIdperfil() {
        return idperfil;
    }
    
    private void llenarPanelPermisos(){
        List<Permiso> permisos = CPerfil.listarPermisosXPerfil(idperfil);
        
        boolean administracion = CPermiso.buscarPermiso(permisos, "Administracion", 1, null);
        chkAdministracion.setSelected(administracion);
        configurarAdministracion(administracion);
        boolean seguridad = CPermiso.buscarPermiso(permisos, "Seguridad",1,null);
        chkSeguridad.setSelected(seguridad);
        configurarSeguridad(seguridad);
        boolean envios = CPermiso.buscarPermiso(permisos, "Envios",1,null);
        chkEnvios.setSelected(envios);
        configurarEnvios(envios);
        chkSimulacion.setSelected(CPermiso.buscarPermiso(permisos, "Simulacion",1,null));
        boolean clientes = CPermiso.buscarPermiso(permisos, "Clientes",1,null);
        chkClientes.setSelected(clientes);
        configurarClientes(clientes);
        boolean reportes = CPermiso.buscarPermiso(permisos, "Reportes",1,null);
        chkReportes.setSelected(reportes);
        configurarReportes(reportes);
        
        chkAdministracion_Aeropuertos.setSelected(CPermiso.buscarPermiso(permisos, "Aeropuertos",2,"Administracion"));
        chkAdministracion_Tarifas.setSelected(CPermiso.buscarPermiso(permisos, "Tarifas",2,"Administracion"));
        chkAdministracion_Vuelos.setSelected(CPermiso.buscarPermiso(permisos, "Vuelos",2,"Administracion"));
        chkAdministracion_TipoCambio.setSelected(CPermiso.buscarPermiso(permisos, "TipoCambio",2,"Administracion"));
        chkAdministracion_Parametros.setSelected(CPermiso.buscarPermiso(permisos, "Parametros",2,"Administracion"));
        
        chkSeguridad_Usuarios.setSelected(CPermiso.buscarPermiso(permisos, "Usuarios",2,"Seguridad"));
        chkSeguridad_Perfiles.setSelected(CPermiso.buscarPermiso(permisos, "Perfiles",2,"Seguridad"));
        chkSeguridad_LogAuditoria.setSelected(CPermiso.buscarPermiso(permisos, "LogAuditoria",2,"Seguridad"));
        
        chkEnvios_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar",2,"Envios"));
        chkEnvios_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear",2,"Envios"));
        chkEnvios_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar",2,"Envios"));
        
        chkClientes_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar",2,"Clientes"));
        chkClientes_CargaMasiva.setSelected(CPermiso.buscarPermiso(permisos, "Carga Masiva",2,"Clientes"));
        chkClientes_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear",2,"Clientes"));
        chkClientes_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar",2,"Clientes"));
        
        chkReportes_Envios.setSelected(CPermiso.buscarPermiso(permisos, "Envios",2,"Reportes"));
        chkReportes_Incidencias.setSelected(CPermiso.buscarPermiso(permisos, "Incidencias",2,"Reportes"));
        chkReportes_MovAlmacen.setSelected(CPermiso.buscarPermiso(permisos, "MovAlmacen",2,"Reportes"));
        chkReportes_Ventas.setSelected(CPermiso.buscarPermiso(permisos, "Ventas",2,"Reportes"));
        
        chkAdministracion_Aeropuertos_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar", 3, "Aeropuertos"));
        chkAdministracion_Aeropuertos_CargaMasiva.setSelected(CPermiso.buscarPermiso(permisos, "Carga Masiva", 3, "Aeropuertos"));
        chkAdministracion_Aeropuertos_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "Aeropuertos"));
        chkAdministracion_Aeropuertos_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar", 3, "Aeropuertos"));
        
        chkAdministracion_Tarifas_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar", 3, "Tarifas"));
        chkAdministracion_Tarifas_CargaMasiva.setSelected(CPermiso.buscarPermiso(permisos, "Carga Masiva", 3, "Tarifas"));
        chkAdministracion_Tarifas_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "Tarifas"));
        chkAdministracion_Tarifas_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar", 3, "Tarifas"));
        
        chkAdministracion_Vuelos_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar", 3, "Vuelos"));
        chkAdministracion_Vuelos_CargaMasiva.setSelected(CPermiso.buscarPermiso(permisos, "Carga Masiva", 3, "Vuelos"));
        chkAdministracion_Vuelos_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "Vuelos"));
        chkAdministracion_Vuelos_Eventos.setSelected(CPermiso.buscarPermiso(permisos, "Eventos", 3, "Vuelos"));

        chkAdministracion_TipoCambio_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar", 3, "TipoCambio"));
        chkAdministracion_TipoCambio_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "TipoCambio"));
        chkAdministracion_TipoCambio_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar", 3, "TipoCambio"));

        chkAdministracion_Parametros_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar", 3, "Parametros"));
        chkAdministracion_Parametros_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "Parametros"));
        chkAdministracion_Parametros_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar", 3, "Parametros"));
        
        chkSeguridad_Usuarios_Buscar.setSelected(CPermiso.buscarPermiso(permisos, "Buscar/Listar", 3, "Usuarios"));
        chkSeguridad_Usuarios_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "Usuarios"));
        chkSeguridad_Usuarios_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar", 3, "Usuarios"));

        chkSeguridad_Perfiles_Crear.setSelected(CPermiso.buscarPermiso(permisos, "Crear", 3, "Perfiles"));
        chkSeguridad_Perfiles_Modificar.setSelected(CPermiso.buscarPermiso(permisos, "Modificar", 3, "Perfiles"));        

    }
    
    private void cargarcampos(){    
        Perfil perfilBE = CPerfil.BuscarXid(idperfil);
         
        txtNombre.setText(perfilBE.getNombre());
        txtDescripcion.setText(perfilBE.getDescripcion());   
        
        for(int i=0;i<cboEstado.getItemCount();i++){
            Parametro estado = (Parametro)cboEstado.getItemAt(i);
//            if (estado.getIdParametro() == perfilBE.getEstado().getIdParametro()){
            if (estado == perfilBE.getEstado()){                
                cboEstado.setSelectedIndex(i);
                break;
            }
        }

    }    
         
    private void llenarcomboEstado(){
        
        ListaEstado = ParametroBL.buscar("", null, "ESTADO_PERFIL", null);

        for (Parametro p : ListaEstado){
            cboEstado.addItem(p);
        }
    }
    
    private void crearPermisos(Perfil perfilBuscado){
        
        if(chkAdministracion.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Administracion", 1, null);
        if(chkSeguridad.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Seguridad", 1, null);
        if(chkEnvios.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Envios", 1, null);
        if(chkSimulacion.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Simulacion", 1, null);
        if(chkClientes.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Clientes", 1, null);
        if(chkReportes.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Reportes", 1, null);
                
        if(chkAdministracion_Aeropuertos.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Aeropuertos", 2, "Administracion");
        if(chkAdministracion_Tarifas.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Tarifas", 2, "Administracion");
        if(chkAdministracion_Vuelos.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Vuelos", 2, "Administracion");
        if(chkAdministracion_TipoCambio.isSelected()) CPermiso.crearPermiso(perfilBuscado, "TipoCambio", 2, "Administracion");
        if(chkAdministracion_Parametros.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Parametros", 2, "Administracion");

        if(chkSeguridad_Usuarios.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Usuarios", 2, "Seguridad");
        if(chkSeguridad_Perfiles.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Perfiles", 2, "Seguridad");
        if(chkSeguridad_LogAuditoria.isSelected()) CPermiso.crearPermiso(perfilBuscado, "LogAuditoria", 2, "Seguridad");
        
        if(chkEnvios_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 2, "Envios");
        if(chkEnvios_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 2, "Envios");
        if(chkEnvios_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 2, "Envios");
        
        if(chkClientes_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 2, "Clientes");
        if(chkClientes_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 2, "Clientes");
        if(chkClientes_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 2, "Clientes");
        if(chkClientes_CargaMasiva.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 2, "Clientes");
        
        if(chkReportes_Envios.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Envios", 2, "Reportes");
        if(chkReportes_Incidencias.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Incidencias", 2, "Reportes");
        if(chkReportes_Ventas.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Ventas", 2, "Reportes");
        if(chkReportes_MovAlmacen.isSelected()) CPermiso.crearPermiso(perfilBuscado, "MovAlmacen", 2, "Reportes");
        
        if(chkAdministracion_Aeropuertos_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Aeropuertos");
        if(chkAdministracion_Aeropuertos_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Aeropuertos");
        if(chkAdministracion_Aeropuertos_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Aeropuertos");
        if(chkAdministracion_Aeropuertos_CargaMasiva.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Aeropuertos");
        
        if(chkAdministracion_Tarifas_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Tarifas");
        if(chkAdministracion_Tarifas_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Tarifas");
        if(chkAdministracion_Tarifas_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Tarifas");
        if(chkAdministracion_Tarifas_CargaMasiva.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Tarifas");
        
        if(chkAdministracion_Vuelos_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Vuelos");
        if(chkAdministracion_Vuelos_Eventos.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Eventos", 3, "Vuelos");
        if(chkAdministracion_Vuelos_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Vuelos");
        if(chkAdministracion_Vuelos_CargaMasiva.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Vuelos");
        
        if(chkAdministracion_TipoCambio_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "TipoCambio");
        if(chkAdministracion_TipoCambio_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "TipoCambio");
        if(chkAdministracion_TipoCambio_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "TipoCambio");
        
        if(chkAdministracion_Parametros_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Parametros");
        if(chkAdministracion_Parametros_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Parametros");
        if(chkAdministracion_Parametros_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Parametros");
        
        if(chkSeguridad_Usuarios_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Usuarios");
        if(chkSeguridad_Usuarios_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Usuarios");
        if(chkSeguridad_Usuarios_Buscar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Usuarios");
        
        if(chkSeguridad_Perfiles_Crear.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Perfiles");
        if(chkSeguridad_Perfiles_Modificar.isSelected()) CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Perfiles");
        
    }
    
    private String modificarPermisos(Perfil perfilBuscado){
        List<Permiso> permisos = CPerfil.listarPermisosXPerfil(perfilBuscado.getIdPerfil());
        Permiso permisoAux;
        String resultado = "";
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Administracion", 1, null);
        if(this.chkAdministracion.isSelected() && permisoAux == null){
            //Si el permiso esta seleccionado y no lo encuentra en la lista actual de permisos
            //AGREGAR PERMISO
            resultado += CPermiso.crearPermiso(perfilBuscado, "Administracion", 1, null);
        }
        else if(!this.chkAdministracion.isSelected() && permisoAux != null){
            //Si el permiso no esta seleccionado y lo encuentra en la lista actual de permisos
            //ELIMINAR PERMISO
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Seguridad", 1, null);
        if(this.chkSeguridad.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Seguridad", 1, null);
        }
        else if(!this.chkSeguridad.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Envios", 1, null);
        if(this.chkEnvios.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Envios", 1, null);
        }
        else if(!this.chkEnvios.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Simulacion", 1, null);
        if(this.chkSimulacion.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Simulacion", 1, null);
        }
        else if(!this.chkSimulacion.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Clientes", 1, null);
        if(this.chkClientes.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Clientes", 1, null);
        }
        else if(!this.chkClientes.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Reportes", 1, null);
        if(this.chkReportes.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Reportes", 1, null);
        }
        else if(!this.chkReportes.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Tarifas", 2, "Administracion");
        if(this.chkAdministracion_Tarifas.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Tarifas", 2, "Administracion");
        }
        else if(!this.chkAdministracion_Tarifas.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Vuelos", 2, "Administracion");
        if(this.chkAdministracion_Vuelos.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Vuelos", 2, "Administracion");
        }
        else if(!this.chkAdministracion_Vuelos.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Aeropuertos", 2, "Administracion");
        if(this.chkAdministracion_Aeropuertos.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Aeropuertos", 2, "Administracion");
        }
        else if(!this.chkAdministracion_Aeropuertos.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "TipoCambio", 2, "Administracion");
        if(this.chkAdministracion_TipoCambio.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "TipoCambio", 2, "Administracion");
        }
        else if(!this.chkAdministracion_TipoCambio.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Parametros", 2, "Administracion");
        if(this.chkAdministracion_Parametros.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Parametros", 2, "Administracion");
        }
        else if(!this.chkAdministracion_Parametros.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Usuarios", 2, "Seguridad");
        if(this.chkSeguridad_Usuarios.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Usuarios", 2, "Seguridad");
        }
        else if(!this.chkSeguridad_Usuarios.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Perfiles", 2, "Seguridad");
        if(this.chkSeguridad_Perfiles.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Perfiles", 2, "Seguridad");
        }
        else if(!this.chkSeguridad_Perfiles.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "LogAuditoria", 2, "Seguridad");
        if(this.chkSeguridad_LogAuditoria.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "LogAuditoria", 2, "Seguridad");
        }
        else if(!this.chkSeguridad_LogAuditoria.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 2, "Envios");
        if(this.chkEnvios_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 2, "Envios");
        }
        else if(!this.chkEnvios_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 2, "Envios");
        if(this.chkEnvios_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 2, "Envios");
        }
        else if(!this.chkEnvios_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 2, "Envios");
        if(this.chkEnvios_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 2, "Envios");
        }
        else if(!this.chkEnvios_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
//        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 2, "Envios");
//        if(this.chkEnvios_CargaMasiva.isSelected() && permisoAux == null){
//            resultado = CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 2, "Envios");
//        }
//        else if(!this.chkEnvios_CargaMasiva.isSelected() && permisoAux != null){
//            resultado += CPermiso.eliminarPermiso(permisoAux);
//        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 2, "Clientes");
        if(this.chkClientes_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 2, "Clientes");
        }
        else if(!this.chkClientes_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 2, "Clientes");
        if(this.chkClientes_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 2, "Clientes");
        }
        else if(!this.chkClientes_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 2, "Clientes");
        if(this.chkClientes_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 2, "Clientes");
        }
        else if(!this.chkClientes_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 2, "Clientes");
        if(this.chkClientes_CargaMasiva.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 2, "Clientes");
        }
        else if(!this.chkClientes_CargaMasiva.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Ventas", 2, "Reportes");
        if(this.chkReportes_Ventas.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Ventas", 2, "Reportes");
        }
        else if(!this.chkReportes_Ventas.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Envios", 2, "Reportes");
        if(this.chkReportes_Envios.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Envios", 2, "Reportes");
        }
        else if(!this.chkReportes_Envios.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "MovAlmacen", 2, "Reportes");
        if(this.chkReportes_MovAlmacen.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "MovAlmacen", 2, "Reportes");
        }
        else if(!this.chkReportes_MovAlmacen.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Incidencias", 2, "Reportes");
        if(this.chkReportes_Incidencias.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Incidencias", 2, "Reportes");
        }
        else if(!this.chkReportes_Incidencias.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "Tarifas");
        if(this.chkAdministracion_Tarifas_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Tarifas");
        }
        else if(!this.chkAdministracion_Tarifas_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 3, "Tarifas");
        if(this.chkAdministracion_Tarifas_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Tarifas");
        }
        else if(!this.chkAdministracion_Tarifas_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 3, "Tarifas");
        if(this.chkAdministracion_Tarifas_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Tarifas");
        }
        else if(!this.chkAdministracion_Tarifas_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 3, "Tarifas");
        if(this.chkAdministracion_Tarifas_CargaMasiva.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Tarifas");
        }
        else if(!this.chkAdministracion_Tarifas_CargaMasiva.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "Vuelos");
        if(this.chkAdministracion_Vuelos_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Vuelos");
        }
        else if(!this.chkAdministracion_Vuelos_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Eventos", 3, "Vuelos");
        if(this.chkAdministracion_Vuelos_Eventos.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Eventos", 3, "Vuelos");
        }
        else if(!this.chkAdministracion_Vuelos_Eventos.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 3, "Vuelos");
        if(this.chkAdministracion_Vuelos_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Vuelos");
        }
        else if(!this.chkAdministracion_Vuelos_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 3, "Vuelos");
        if(this.chkAdministracion_Vuelos_CargaMasiva.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Vuelos");
        }
        else if(!this.chkAdministracion_Vuelos_CargaMasiva.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "Aeropuertos");
        if(this.chkAdministracion_Aeropuertos_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Aeropuertos");
        }
        else if(!this.chkAdministracion_Aeropuertos_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 3, "Aeropuertos");
        if(this.chkAdministracion_Aeropuertos_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Aeropuertos");
        }
        else if(!this.chkAdministracion_Aeropuertos_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 3, "Aeropuertos");
        if(this.chkAdministracion_Aeropuertos_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Aeropuertos");
        }
        else if(!this.chkAdministracion_Aeropuertos_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 3, "Aeropuertos");
        if(this.chkAdministracion_Aeropuertos_CargaMasiva.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Aeropuertos");
        }
        else if(!this.chkAdministracion_Aeropuertos_CargaMasiva.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "TipoCambio");
        if(this.chkAdministracion_TipoCambio_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "TipoCambio");
        }
        else if(!this.chkAdministracion_TipoCambio_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 3, "TipoCambio");
        if(this.chkAdministracion_TipoCambio_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "TipoCambio");
        }
        else if(!this.chkAdministracion_TipoCambio_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 3, "TipoCambio");
        if(this.chkAdministracion_TipoCambio_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "TipoCambio");
        }
        else if(!this.chkAdministracion_TipoCambio_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
//        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 3, "TipoCambio");
//        if(this.chkAdministracion_TipoCambio_CargaMasiva.isSelected() && permisoAux == null){
//            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "TipoCambio");
//        }
//        else if(!this.chkAdministracion_TipoCambio_CargaMasiva.isSelected() && permisoAux != null){
//            resultado += CPermiso.eliminarPermiso(permisoAux);
//        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "Parametros");
        if(this.chkAdministracion_Parametros_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Parametros");
        }
        else if(!this.chkAdministracion_Parametros_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 3, "Parametros");
        if(this.chkAdministracion_Parametros_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Parametros");
        }
        else if(!this.chkAdministracion_Parametros_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 3, "Parametros");
        if(this.chkAdministracion_Parametros_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Parametros");
        }
        else if(!this.chkAdministracion_Parametros_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "Usuarios");
        if(this.chkSeguridad_Usuarios_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Usuarios");
        }
        else if(!this.chkSeguridad_Usuarios_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 3, "Usuarios");
        if(this.chkSeguridad_Usuarios_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Usuarios");
        }
        else if(!this.chkSeguridad_Usuarios_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Buscar/Listar", 3, "Usuarios");
        if(this.chkSeguridad_Usuarios_Buscar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Buscar/Listar", 3, "Usuarios");
        }
        else if(!this.chkSeguridad_Usuarios_Buscar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
//        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 3, "Usuarios");
//        if(this.chkSeguridad_Usuarios_CargaMasiva.isSelected() && permisoAux == null){
//            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Usuarios");
//        }
//        else if(!this.chkSeguridad_Usuarios_CargaMasiva.isSelected() && permisoAux != null){
//            resultado += CPermiso.eliminarPermiso(permisoAux);
//        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Crear", 3, "Perfiles");
        if(this.chkSeguridad_Perfiles_Crear.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Crear", 3, "Perfiles");
        }
        else if(!this.chkSeguridad_Perfiles_Crear.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
        permisoAux = CPermiso.encontrarPermiso(permisos, "Modificar", 3, "Perfiles");
        if(this.chkSeguridad_Perfiles_Modificar.isSelected() && permisoAux == null){
            resultado += CPermiso.crearPermiso(perfilBuscado, "Modificar", 3, "Perfiles");
        }
        else if(!this.chkSeguridad_Perfiles_Modificar.isSelected() && permisoAux != null){
            resultado += CPermiso.eliminarPermiso(permisoAux);
        }
        
//        permisoAux = CPermiso.encontrarPermiso(permisos, "Carga Masiva", 3, "Perfiles");
//        if(this.chkSeguridad_Perfiles_CargaMasiva.isSelected() && permisoAux == null){
//            resultado += CPermiso.crearPermiso(perfilBuscado, "Carga Masiva", 3, "Perfiles");
//        }
//        else if(!this.chkSeguridad_Perfiles_CargaMasiva.isSelected() && permisoAux != null){
//            resultado += CPermiso.eliminarPermiso(permisoAux);
//        }
        
        return resultado;
    }
    
    public PerfilEdit(javax.swing.JDialog parent, boolean modal, int idPerfilPasado) {
        super(parent, modal);
        initComponents();
        
        idperfil = idPerfilPasado; //comienza con -1
        
        llenarcomboEstado();
        if(idperfil != -1){
            llenarPanelPermisos();
            cargarcampos();
        }
                
        this.setLocationRelativeTo(null);
        pack();
    }
    
    protected JRootPane createRootPane() { 
        JRootPane rootPane = new JRootPane();
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action accion = new AbstractAction() { 
          public void actionPerformed(ActionEvent actionEvent) { 
            setVisible(false);
            dispose();
          } 
        } ;
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", accion);

        return rootPane;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        paneles = new javax.swing.JTabbedPane();
        panelPrincipal = new javax.swing.JPanel();
        chkAdministracion = new javax.swing.JCheckBox();
        chkSeguridad = new javax.swing.JCheckBox();
        chkEnvios = new javax.swing.JCheckBox();
        chkSimulacion = new javax.swing.JCheckBox();
        chkClientes = new javax.swing.JCheckBox();
        chkReportes = new javax.swing.JCheckBox();
        panelAdministracion = new javax.swing.JPanel();
        chkAdministracion_Tarifas = new javax.swing.JCheckBox();
        chkAdministracion_Vuelos = new javax.swing.JCheckBox();
        chkAdministracion_Aeropuertos = new javax.swing.JCheckBox();
        chkAdministracion_TipoCambio = new javax.swing.JCheckBox();
        chkAdministracion_Tarifas_Crear = new javax.swing.JCheckBox();
        chkAdministracion_Tarifas_Modificar = new javax.swing.JCheckBox();
        chkAdministracion_Tarifas_Buscar = new javax.swing.JCheckBox();
        chkAdministracion_Tarifas_CargaMasiva = new javax.swing.JCheckBox();
        chkAdministracion_Vuelos_CargaMasiva = new javax.swing.JCheckBox();
        chkAdministracion_Vuelos_Buscar = new javax.swing.JCheckBox();
        chkAdministracion_Vuelos_Eventos = new javax.swing.JCheckBox();
        chkAdministracion_Vuelos_Crear = new javax.swing.JCheckBox();
        chkAdministracion_Aeropuertos_Crear = new javax.swing.JCheckBox();
        chkAdministracion_Aeropuertos_Modificar = new javax.swing.JCheckBox();
        chkAdministracion_Aeropuertos_Buscar = new javax.swing.JCheckBox();
        chkAdministracion_Aeropuertos_CargaMasiva = new javax.swing.JCheckBox();
        chkAdministracion_TipoCambio_Crear = new javax.swing.JCheckBox();
        chkAdministracion_TipoCambio_Modificar = new javax.swing.JCheckBox();
        chkAdministracion_TipoCambio_Buscar = new javax.swing.JCheckBox();
        chkAdministracion_Parametros = new javax.swing.JCheckBox();
        chkAdministracion_Parametros_Crear = new javax.swing.JCheckBox();
        chkAdministracion_Parametros_Modificar = new javax.swing.JCheckBox();
        chkAdministracion_Parametros_Buscar = new javax.swing.JCheckBox();
        panelSeguridad = new javax.swing.JPanel();
        chkSeguridad_Usuarios = new javax.swing.JCheckBox();
        chkSeguridad_Usuarios_Crear = new javax.swing.JCheckBox();
        chkSeguridad_Usuarios_Modificar = new javax.swing.JCheckBox();
        chkSeguridad_Usuarios_Buscar = new javax.swing.JCheckBox();
        chkSeguridad_Perfiles = new javax.swing.JCheckBox();
        chkSeguridad_Perfiles_Crear = new javax.swing.JCheckBox();
        chkSeguridad_Perfiles_Modificar = new javax.swing.JCheckBox();
        chkSeguridad_LogAuditoria = new javax.swing.JCheckBox();
        panelEnvios = new javax.swing.JPanel();
        chkEnvios_Crear = new javax.swing.JCheckBox();
        chkEnvios_Modificar = new javax.swing.JCheckBox();
        chkEnvios_Buscar = new javax.swing.JCheckBox();
        panelClientes = new javax.swing.JPanel();
        chkClientes_CargaMasiva = new javax.swing.JCheckBox();
        chkClientes_Buscar = new javax.swing.JCheckBox();
        chkClientes_Modificar = new javax.swing.JCheckBox();
        chkClientes_Crear = new javax.swing.JCheckBox();
        panelReportes = new javax.swing.JPanel();
        chkReportes_Ventas = new javax.swing.JCheckBox();
        chkReportes_Envios = new javax.swing.JCheckBox();
        chkReportes_MovAlmacen = new javax.swing.JCheckBox();
        chkReportes_Incidencias = new javax.swing.JCheckBox();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Flytrack - Seguridad - Perfil");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil48x48.png"))); // NOI18N
        jLabel1.setText("Perfil");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(363, 363, 363)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel4.setText("Estado: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkAdministracion.setText("Administración");
        chkAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracionActionPerformed(evt);
            }
        });

        chkSeguridad.setText("Seguridad");
        chkSeguridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridadActionPerformed(evt);
            }
        });

        chkEnvios.setText("Envíos");
        chkEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEnviosActionPerformed(evt);
            }
        });

        chkSimulacion.setText("Simulación");

        chkClientes.setText("Clientes");
        chkClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkClientesActionPerformed(evt);
            }
        });

        chkReportes.setText("Reportes");
        chkReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkReportes)
                    .addComponent(chkClientes)
                    .addComponent(chkSimulacion)
                    .addComponent(chkEnvios)
                    .addComponent(chkSeguridad)
                    .addComponent(chkAdministracion))
                .addGap(519, 519, 519))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(chkAdministracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSeguridad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEnvios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSimulacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkReportes)
                .addGap(155, 155, 155))
        );

        paneles.addTab("Principal", panelPrincipal);

        panelAdministracion.setEnabled(false);

        chkAdministracion_Tarifas.setText("Tarifas");
        chkAdministracion_Tarifas.setEnabled(false);
        chkAdministracion_Tarifas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_TarifasActionPerformed(evt);
            }
        });

        chkAdministracion_Vuelos.setText("Vuelos");
        chkAdministracion_Vuelos.setEnabled(false);
        chkAdministracion_Vuelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_VuelosActionPerformed(evt);
            }
        });

        chkAdministracion_Aeropuertos.setText("Aeropuertos");
        chkAdministracion_Aeropuertos.setEnabled(false);
        chkAdministracion_Aeropuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_AeropuertosActionPerformed(evt);
            }
        });

        chkAdministracion_TipoCambio.setText("Tipo de Cambio");
        chkAdministracion_TipoCambio.setEnabled(false);
        chkAdministracion_TipoCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_TipoCambioActionPerformed(evt);
            }
        });

        chkAdministracion_Tarifas_Crear.setText("Crear");
        chkAdministracion_Tarifas_Crear.setEnabled(false);
        chkAdministracion_Tarifas_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Tarifas_CrearActionPerformed(evt);
            }
        });

        chkAdministracion_Tarifas_Modificar.setText("Modificar");
        chkAdministracion_Tarifas_Modificar.setEnabled(false);
        chkAdministracion_Tarifas_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Tarifas_ModificarActionPerformed(evt);
            }
        });

        chkAdministracion_Tarifas_Buscar.setText("Buscar / Listar");
        chkAdministracion_Tarifas_Buscar.setEnabled(false);
        chkAdministracion_Tarifas_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Tarifas_BuscarActionPerformed(evt);
            }
        });

        chkAdministracion_Tarifas_CargaMasiva.setText("Carga Masiva");
        chkAdministracion_Tarifas_CargaMasiva.setEnabled(false);
        chkAdministracion_Tarifas_CargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Tarifas_CargaMasivaActionPerformed(evt);
            }
        });

        chkAdministracion_Vuelos_CargaMasiva.setText("Carga Masiva");
        chkAdministracion_Vuelos_CargaMasiva.setEnabled(false);
        chkAdministracion_Vuelos_CargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Vuelos_CargaMasivaActionPerformed(evt);
            }
        });

        chkAdministracion_Vuelos_Buscar.setText("Buscar / Listar");
        chkAdministracion_Vuelos_Buscar.setEnabled(false);
        chkAdministracion_Vuelos_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Vuelos_BuscarActionPerformed(evt);
            }
        });

        chkAdministracion_Vuelos_Eventos.setText("Eventos");
        chkAdministracion_Vuelos_Eventos.setEnabled(false);
        chkAdministracion_Vuelos_Eventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Vuelos_EventosActionPerformed(evt);
            }
        });

        chkAdministracion_Vuelos_Crear.setText("Crear");
        chkAdministracion_Vuelos_Crear.setEnabled(false);
        chkAdministracion_Vuelos_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Vuelos_CrearActionPerformed(evt);
            }
        });

        chkAdministracion_Aeropuertos_Crear.setText("Crear");
        chkAdministracion_Aeropuertos_Crear.setEnabled(false);
        chkAdministracion_Aeropuertos_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Aeropuertos_CrearActionPerformed(evt);
            }
        });

        chkAdministracion_Aeropuertos_Modificar.setText("Modificar");
        chkAdministracion_Aeropuertos_Modificar.setEnabled(false);
        chkAdministracion_Aeropuertos_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Aeropuertos_ModificarActionPerformed(evt);
            }
        });

        chkAdministracion_Aeropuertos_Buscar.setText("Buscar / Listar");
        chkAdministracion_Aeropuertos_Buscar.setEnabled(false);
        chkAdministracion_Aeropuertos_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Aeropuertos_BuscarActionPerformed(evt);
            }
        });

        chkAdministracion_Aeropuertos_CargaMasiva.setText("Carga Masiva");
        chkAdministracion_Aeropuertos_CargaMasiva.setEnabled(false);
        chkAdministracion_Aeropuertos_CargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Aeropuertos_CargaMasivaActionPerformed(evt);
            }
        });

        chkAdministracion_TipoCambio_Crear.setText("Crear");
        chkAdministracion_TipoCambio_Crear.setEnabled(false);
        chkAdministracion_TipoCambio_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_TipoCambio_CrearActionPerformed(evt);
            }
        });

        chkAdministracion_TipoCambio_Modificar.setText("Modificar");
        chkAdministracion_TipoCambio_Modificar.setEnabled(false);
        chkAdministracion_TipoCambio_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_TipoCambio_ModificarActionPerformed(evt);
            }
        });

        chkAdministracion_TipoCambio_Buscar.setText("Buscar / Listar");
        chkAdministracion_TipoCambio_Buscar.setEnabled(false);
        chkAdministracion_TipoCambio_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_TipoCambio_BuscarActionPerformed(evt);
            }
        });

        chkAdministracion_Parametros.setText("Parámetros");
        chkAdministracion_Parametros.setEnabled(false);
        chkAdministracion_Parametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_ParametrosActionPerformed(evt);
            }
        });

        chkAdministracion_Parametros_Crear.setText("Crear");
        chkAdministracion_Parametros_Crear.setEnabled(false);
        chkAdministracion_Parametros_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Parametros_CrearActionPerformed(evt);
            }
        });

        chkAdministracion_Parametros_Modificar.setText("Modificar");
        chkAdministracion_Parametros_Modificar.setEnabled(false);
        chkAdministracion_Parametros_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Parametros_ModificarActionPerformed(evt);
            }
        });

        chkAdministracion_Parametros_Buscar.setText("Buscar / Listar");
        chkAdministracion_Parametros_Buscar.setEnabled(false);
        chkAdministracion_Parametros_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministracion_Parametros_BuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAdministracionLayout = new javax.swing.GroupLayout(panelAdministracion);
        panelAdministracion.setLayout(panelAdministracionLayout);
        panelAdministracionLayout.setHorizontalGroup(
            panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministracionLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAdministracion_Tarifas)
                    .addComponent(chkAdministracion_Vuelos)
                    .addGroup(panelAdministracionLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chkAdministracion_Vuelos_Eventos)
                                .addComponent(chkAdministracion_Vuelos_Crear)
                                .addComponent(chkAdministracion_Vuelos_Buscar)
                                .addComponent(chkAdministracion_Vuelos_CargaMasiva))
                            .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chkAdministracion_Tarifas_Modificar)
                                .addComponent(chkAdministracion_Tarifas_Crear)
                                .addComponent(chkAdministracion_Tarifas_Buscar)
                                .addComponent(chkAdministracion_Tarifas_CargaMasiva)))))
                .addGap(61, 61, 61)
                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAdministracionLayout.createSequentialGroup()
                        .addComponent(chkAdministracion_TipoCambio)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelAdministracionLayout.createSequentialGroup()
                        .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAdministracion_Aeropuertos)
                            .addGroup(panelAdministracionLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkAdministracion_TipoCambio_Modificar)
                                        .addComponent(chkAdministracion_TipoCambio_Crear)
                                        .addComponent(chkAdministracion_TipoCambio_Buscar))
                                    .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkAdministracion_Aeropuertos_Modificar)
                                        .addComponent(chkAdministracion_Aeropuertos_Crear)
                                        .addComponent(chkAdministracion_Aeropuertos_Buscar)
                                        .addComponent(chkAdministracion_Aeropuertos_CargaMasiva)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministracionLayout.createSequentialGroup()
                                .addComponent(chkAdministracion_Parametros)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chkAdministracion_Parametros_Modificar)
                                .addComponent(chkAdministracion_Parametros_Crear)
                                .addComponent(chkAdministracion_Parametros_Buscar)))
                        .addGap(79, 79, 79))))
        );
        panelAdministracionLayout.setVerticalGroup(
            panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministracionLayout.createSequentialGroup()
                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministracionLayout.createSequentialGroup()
                        .addComponent(chkAdministracion_Aeropuertos_Crear)
                        .addGap(3, 3, 3)
                        .addComponent(chkAdministracion_Aeropuertos_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAdministracion_Aeropuertos_Buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAdministracion_Aeropuertos_CargaMasiva))
                    .addGroup(panelAdministracionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAdministracionLayout.createSequentialGroup()
                                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkAdministracion_Tarifas)
                                    .addComponent(chkAdministracion_Aeropuertos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkAdministracion_Tarifas_Crear)
                                .addGap(3, 3, 3)
                                .addComponent(chkAdministracion_Tarifas_Modificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkAdministracion_Tarifas_Buscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkAdministracion_Tarifas_CargaMasiva))
                            .addGroup(panelAdministracionLayout.createSequentialGroup()
                                .addComponent(chkAdministracion_Parametros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkAdministracion_Parametros_Crear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkAdministracion_Parametros_Modificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkAdministracion_Parametros_Buscar)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAdministracion_Vuelos)
                    .addComponent(chkAdministracion_TipoCambio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAdministracionLayout.createSequentialGroup()
                        .addComponent(chkAdministracion_Vuelos_Crear)
                        .addGap(3, 3, 3)
                        .addComponent(chkAdministracion_Vuelos_Eventos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAdministracion_Vuelos_Buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAdministracion_Vuelos_CargaMasiva))
                    .addGroup(panelAdministracionLayout.createSequentialGroup()
                        .addComponent(chkAdministracion_TipoCambio_Crear)
                        .addGap(3, 3, 3)
                        .addComponent(chkAdministracion_TipoCambio_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAdministracion_TipoCambio_Buscar)))
                .addGap(56, 56, 56))
        );

        paneles.addTab("Administracion", panelAdministracion);

        panelSeguridad.setEnabled(false);

        chkSeguridad_Usuarios.setText("Usuarios");
        chkSeguridad_Usuarios.setEnabled(false);
        chkSeguridad_Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_UsuariosActionPerformed(evt);
            }
        });

        chkSeguridad_Usuarios_Crear.setText("Crear");
        chkSeguridad_Usuarios_Crear.setEnabled(false);
        chkSeguridad_Usuarios_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_Usuarios_CrearActionPerformed(evt);
            }
        });

        chkSeguridad_Usuarios_Modificar.setText("Modificar");
        chkSeguridad_Usuarios_Modificar.setEnabled(false);
        chkSeguridad_Usuarios_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_Usuarios_ModificarActionPerformed(evt);
            }
        });

        chkSeguridad_Usuarios_Buscar.setText("Buscar / Listar");
        chkSeguridad_Usuarios_Buscar.setEnabled(false);
        chkSeguridad_Usuarios_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_Usuarios_BuscarActionPerformed(evt);
            }
        });

        chkSeguridad_Perfiles.setText("Perfiles");
        chkSeguridad_Perfiles.setEnabled(false);
        chkSeguridad_Perfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_PerfilesActionPerformed(evt);
            }
        });

        chkSeguridad_Perfiles_Crear.setText("Crear");
        chkSeguridad_Perfiles_Crear.setEnabled(false);
        chkSeguridad_Perfiles_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_Perfiles_CrearActionPerformed(evt);
            }
        });

        chkSeguridad_Perfiles_Modificar.setText("Modificar");
        chkSeguridad_Perfiles_Modificar.setEnabled(false);
        chkSeguridad_Perfiles_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeguridad_Perfiles_ModificarActionPerformed(evt);
            }
        });

        chkSeguridad_LogAuditoria.setText("Logs de Auditoria");
        chkSeguridad_LogAuditoria.setEnabled(false);

        javax.swing.GroupLayout panelSeguridadLayout = new javax.swing.GroupLayout(panelSeguridad);
        panelSeguridad.setLayout(panelSeguridadLayout);
        panelSeguridadLayout.setHorizontalGroup(
            panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeguridadLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSeguridad_LogAuditoria)
                    .addGroup(panelSeguridadLayout.createSequentialGroup()
                        .addGroup(panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkSeguridad_Usuarios)
                            .addGroup(panelSeguridadLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkSeguridad_Usuarios_Modificar)
                                    .addComponent(chkSeguridad_Usuarios_Crear)
                                    .addComponent(chkSeguridad_Usuarios_Buscar))))
                        .addGap(100, 100, 100)
                        .addGroup(panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkSeguridad_Perfiles)
                            .addGroup(panelSeguridadLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkSeguridad_Perfiles_Modificar)
                                    .addComponent(chkSeguridad_Perfiles_Crear))))))
                .addGap(249, 249, 249))
        );
        panelSeguridadLayout.setVerticalGroup(
            panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeguridadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSeguridadLayout.createSequentialGroup()
                        .addComponent(chkSeguridad_Usuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSeguridad_Usuarios_Crear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSeguridad_Usuarios_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSeguridad_Usuarios_Buscar))
                    .addGroup(panelSeguridadLayout.createSequentialGroup()
                        .addComponent(chkSeguridad_Perfiles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSeguridad_Perfiles_Crear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSeguridad_Perfiles_Modificar)))
                .addGap(42, 42, 42)
                .addComponent(chkSeguridad_LogAuditoria)
                .addGap(164, 164, 164))
        );

        paneles.addTab("Seguridad", panelSeguridad);

        panelEnvios.setEnabled(false);

        chkEnvios_Crear.setText("Crear");
        chkEnvios_Crear.setEnabled(false);

        chkEnvios_Modificar.setText("Modificar");
        chkEnvios_Modificar.setEnabled(false);

        chkEnvios_Buscar.setText("Buscar / Listar");
        chkEnvios_Buscar.setEnabled(false);

        javax.swing.GroupLayout panelEnviosLayout = new javax.swing.GroupLayout(panelEnvios);
        panelEnvios.setLayout(panelEnviosLayout);
        panelEnviosLayout.setHorizontalGroup(
            panelEnviosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnviosLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(panelEnviosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEnvios_Buscar)
                    .addComponent(chkEnvios_Modificar)
                    .addComponent(chkEnvios_Crear))
                .addGap(523, 523, 523))
        );
        panelEnviosLayout.setVerticalGroup(
            panelEnviosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnviosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEnvios_Crear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEnvios_Modificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEnvios_Buscar)
                .addGap(260, 260, 260))
        );

        paneles.addTab("Envios", panelEnvios);

        panelClientes.setEnabled(false);

        chkClientes_CargaMasiva.setText("Carga Masiva");
        chkClientes_CargaMasiva.setEnabled(false);

        chkClientes_Buscar.setText("Buscar / Listar");
        chkClientes_Buscar.setEnabled(false);

        chkClientes_Modificar.setText("Modificar");
        chkClientes_Modificar.setEnabled(false);

        chkClientes_Crear.setText("Crear");
        chkClientes_Crear.setEnabled(false);

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkClientes_CargaMasiva)
                    .addComponent(chkClientes_Buscar)
                    .addComponent(chkClientes_Modificar)
                    .addComponent(chkClientes_Crear))
                .addGap(523, 523, 523))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkClientes_Crear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkClientes_Modificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkClientes_Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkClientes_CargaMasiva)
                .addGap(230, 230, 230))
        );

        paneles.addTab("Clientes", panelClientes);

        panelReportes.setEnabled(false);

        chkReportes_Ventas.setText("Ventas");
        chkReportes_Ventas.setEnabled(false);

        chkReportes_Envios.setText("Envíos");
        chkReportes_Envios.setEnabled(false);

        chkReportes_MovAlmacen.setText("Movimientos de Almacen");
        chkReportes_MovAlmacen.setEnabled(false);

        chkReportes_Incidencias.setText("Incidencias");
        chkReportes_Incidencias.setEnabled(false);
        chkReportes_Incidencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkReportes_IncidenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReportesLayout = new javax.swing.GroupLayout(panelReportes);
        panelReportes.setLayout(panelReportesLayout);
        panelReportesLayout.setHorizontalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkReportes_Incidencias)
                    .addComponent(chkReportes_MovAlmacen)
                    .addComponent(chkReportes_Envios)
                    .addComponent(chkReportes_Ventas))
                .addGap(449, 449, 449))
        );
        panelReportesLayout.setVerticalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkReportes_Ventas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkReportes_Envios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkReportes_MovAlmacen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkReportes_Incidencias)
                .addGap(230, 230, 230))
        );

        paneles.addTab("Reportes", panelReportes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(paneles, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setText("Regresar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarAdministracion(boolean estado){
        this.panelAdministracion.setEnabled(estado);
        
        this.chkAdministracion_Tarifas.setEnabled(estado);
        this.chkAdministracion_Tarifas_Crear.setEnabled(estado);
        this.chkAdministracion_Tarifas_Modificar.setEnabled(estado);
        this.chkAdministracion_Tarifas_Buscar.setEnabled(estado);
        this.chkAdministracion_Tarifas_CargaMasiva.setEnabled(estado);
        this.chkAdministracion_TipoCambio.setEnabled(estado);
        this.chkAdministracion_TipoCambio_Crear.setEnabled(estado);
        this.chkAdministracion_TipoCambio_Modificar.setEnabled(estado);
        this.chkAdministracion_TipoCambio_Buscar.setEnabled(estado);
        this.chkAdministracion_Aeropuertos.setEnabled(estado);
        this.chkAdministracion_Aeropuertos_Crear.setEnabled(estado);
        this.chkAdministracion_Aeropuertos_Modificar.setEnabled(estado);
        this.chkAdministracion_Aeropuertos_Buscar.setEnabled(estado);
        this.chkAdministracion_Aeropuertos_CargaMasiva.setEnabled(estado);
        this.chkAdministracion_Vuelos.setEnabled(estado);
        this.chkAdministracion_Vuelos_Crear.setEnabled(estado);
        this.chkAdministracion_Vuelos_Buscar.setEnabled(estado);
        this.chkAdministracion_Vuelos_Eventos.setEnabled(estado);
        this.chkAdministracion_Vuelos_CargaMasiva.setEnabled(estado);
        this.chkAdministracion_Parametros.setEnabled(estado);
        this.chkAdministracion_Parametros_Crear.setEnabled(estado);
        this.chkAdministracion_Parametros_Buscar.setEnabled(estado);
        this.chkAdministracion_Parametros_Modificar.setEnabled(estado);
        
        if(estado == Boolean.FALSE){
            this.chkAdministracion_Tarifas.setSelected(estado);
            this.chkAdministracion_Tarifas_Crear.setSelected(estado);
            this.chkAdministracion_Tarifas_Modificar.setSelected(estado);
            this.chkAdministracion_Tarifas_Buscar.setSelected(estado);
            this.chkAdministracion_Tarifas_CargaMasiva.setSelected(estado);
            this.chkAdministracion_TipoCambio.setSelected(estado);
            this.chkAdministracion_TipoCambio_Crear.setSelected(estado);
            this.chkAdministracion_TipoCambio_Modificar.setSelected(estado);
            this.chkAdministracion_TipoCambio_Buscar.setSelected(estado);
            this.chkAdministracion_Aeropuertos.setSelected(estado);
            this.chkAdministracion_Aeropuertos_Crear.setSelected(estado);
            this.chkAdministracion_Aeropuertos_Modificar.setSelected(estado);
            this.chkAdministracion_Aeropuertos_Buscar.setSelected(estado);
            this.chkAdministracion_Aeropuertos_CargaMasiva.setSelected(estado);
            this.chkAdministracion_Vuelos.setSelected(estado);
            this.chkAdministracion_Vuelos_Crear.setSelected(estado);
            this.chkAdministracion_Vuelos_Buscar.setSelected(estado);
            this.chkAdministracion_Vuelos_Eventos.setSelected(estado);
            this.chkAdministracion_Vuelos_CargaMasiva.setSelected(estado);
            this.chkAdministracion_Parametros.setSelected(estado);
            this.chkAdministracion_Parametros_Crear.setSelected(estado);
            this.chkAdministracion_Parametros_Buscar.setSelected(estado);
            this.chkAdministracion_Parametros_Modificar.setSelected(estado);
        }
    }
    
    private void configurarSeguridad(boolean estado){
        this.panelSeguridad.setEnabled(estado);
        
        this.chkSeguridad_Usuarios.setEnabled(estado);
        this.chkSeguridad_Usuarios_Crear.setEnabled(estado);
        this.chkSeguridad_Usuarios_Buscar.setEnabled(estado);
        this.chkSeguridad_Usuarios_Modificar.setEnabled(estado);
        this.chkSeguridad_Perfiles.setEnabled(estado);
        this.chkSeguridad_Perfiles_Crear.setEnabled(estado);
        this.chkSeguridad_Perfiles_Modificar.setEnabled(estado);
        this.chkSeguridad_LogAuditoria.setEnabled(estado);
        
        if(estado == Boolean.FALSE){
            this.chkSeguridad_Usuarios.setSelected(estado);
            this.chkSeguridad_Usuarios_Crear.setSelected(estado);
            this.chkSeguridad_Usuarios_Buscar.setSelected(estado);
            this.chkSeguridad_Usuarios_Modificar.setSelected(estado);
            this.chkSeguridad_Perfiles.setSelected(estado);
            this.chkSeguridad_Perfiles_Crear.setSelected(estado);
            this.chkSeguridad_Perfiles_Modificar.setSelected(estado);
            this.chkSeguridad_LogAuditoria.setSelected(estado);
        }
    }
    
    private void configurarEnvios(boolean estado){
        this.panelEnvios.setEnabled(estado);
        
        this.chkEnvios_Buscar.setEnabled(estado);
        this.chkEnvios_Crear.setEnabled(estado);
        this.chkEnvios_Modificar.setEnabled(estado);        
        
        if(estado == Boolean.FALSE){
            this.chkEnvios_Buscar.setSelected(estado);
            this.chkEnvios_Crear.setSelected(estado);
            this.chkEnvios_Modificar.setSelected(estado);        
        }
    }
    
    private void configurarClientes(boolean estado){
        this.panelClientes.setEnabled(estado);
        
        this.chkClientes_Buscar.setEnabled(estado);
        this.chkClientes_CargaMasiva.setEnabled(estado);
        this.chkClientes_Crear.setEnabled(estado);
        this.chkClientes_Modificar.setEnabled(estado);
        
        if(estado == Boolean.FALSE){
            this.chkClientes_Buscar.setSelected(estado);
            this.chkClientes_CargaMasiva.setSelected(estado);
            this.chkClientes_Crear.setSelected(estado);
            this.chkClientes_Modificar.setSelected(estado);
        }
    }
    
    private void configurarReportes(boolean estado){
        this.panelReportes.setEnabled(estado);
        
        this.chkReportes_Envios.setEnabled(estado);
        this.chkReportes_Incidencias.setEnabled(estado);
        this.chkReportes_MovAlmacen.setEnabled(estado);
        this.chkReportes_Ventas.setEnabled(estado);
        
        if(estado == Boolean.FALSE){
            this.chkReportes_Envios.setSelected(estado);
            this.chkReportes_Incidencias.setSelected(estado);
            this.chkReportes_MovAlmacen.setSelected(estado);
            this.chkReportes_Ventas.setSelected(estado);
        }
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String error_message = null;
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if(idperfil == -1){
            error_message = CPerfil.validar(idperfil, txtNombre.getText(), txtDescripcion.getText(), (Parametro)cboEstado.getSelectedItem());
        }
        
        if (error_message == null || error_message.isEmpty()) {
            if (idperfil == -1){//Nuevo Perfil
                Perfil perfilNuevo = CPerfil.agregarPerfil(txtNombre.getText(), txtDescripcion.getText(), (Parametro)cboEstado.getSelectedItem());
                crearPermisos(perfilNuevo);
                perfilNuevo = CPerfil.BuscarXid(perfilNuevo.getIdPerfil());
                CPista.guardarPista("Seguridad", "Perfiles", "Crear", perfilNuevo.aString());
                
            }

            else{//Modificar Perfil
                Perfil perfilAnterior = CPerfil.BuscarXid(idperfil);
                Perfil perfilModificado = CPerfil.modificarPerfil(idperfil, txtNombre.getText(),txtDescripcion.getText() ,(Parametro)cboEstado.getSelectedItem());
                String huboCambios = modificarPermisos(perfilModificado);
                if(!huboCambios.isEmpty()){
                    perfilModificado = CPerfil.BuscarXid(perfilModificado.getIdPerfil());
                    CPista.guardarPista("Seguridad", "Perfiles", "Modificar", perfilAnterior.aString(), perfilModificado.aString());
                }
                if( idperfil == Sesion.getUsuario().getPerfil().getIdPerfil() && //Si el perfil que modifico es del usuario que esta en la sesion
                    !huboCambios.isEmpty()){ //y ademas se nota que hubo cambios
                    Sesion.setUsuario(CUsuario.actualizarUsuario(Sesion.getUsuario())); //debo actualizar el usuario y por ende sus permisos porque han cambiado
                    Sesion.setCambioPerfil(true); //le indicas a la sesion que ha cambiado el perfil
                }
            }
            this.setVisible(false);
            this.dispose();
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracionActionPerformed
        // TODO add your handling code here:
        configurarAdministracion(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkAdministracionActionPerformed

    private void chkSeguridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridadActionPerformed
        // TODO add your handling code here:
        configurarSeguridad(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkSeguridadActionPerformed

    private void chkEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEnviosActionPerformed
        // TODO add your handling code here:
        configurarEnvios(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkEnviosActionPerformed

    private void chkClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkClientesActionPerformed
        // TODO add your handling code here:
        configurarClientes(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkClientesActionPerformed

    private void chkReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkReportesActionPerformed
        // TODO add your handling code here:
        configurarReportes(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkReportesActionPerformed

    private void chkAdministracion_TarifasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_TarifasActionPerformed
        // TODO add your handling code here:
        chkAdministracion_Tarifas_Buscar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Tarifas_CargaMasiva.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Tarifas_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Tarifas_Modificar.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkAdministracion_TarifasActionPerformed

    private void chkAdministracion_VuelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_VuelosActionPerformed
        // TODO add your handling code here:
        chkAdministracion_Vuelos_Buscar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Vuelos_CargaMasiva.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Vuelos_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Vuelos_Eventos.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkAdministracion_VuelosActionPerformed

    private void chkAdministracion_AeropuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_AeropuertosActionPerformed
        // TODO add your handling code here:
        chkAdministracion_Aeropuertos_Buscar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Aeropuertos_CargaMasiva.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Aeropuertos_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Aeropuertos_Modificar.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkAdministracion_AeropuertosActionPerformed

    private void chkAdministracion_TipoCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_TipoCambioActionPerformed
        // TODO add your handling code here:
        chkAdministracion_TipoCambio_Buscar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_TipoCambio_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_TipoCambio_Modificar.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkAdministracion_TipoCambioActionPerformed

    private void chkAdministracion_Tarifas_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Tarifas_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
            this.chkAdministracion_Tarifas.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Tarifas_Buscar.isSelected() &&
                !this.chkAdministracion_Tarifas_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Tarifas_Modificar.isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Tarifas_CrearActionPerformed

    private void chkAdministracion_Tarifas_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Tarifas_ModificarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Tarifas_Crear.isSelected() &&
                !this.chkAdministracion_Tarifas_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Tarifas_Buscar.isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Tarifas_ModificarActionPerformed

    private void chkAdministracion_Tarifas_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Tarifas_BuscarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Tarifas_Crear.isSelected() &&
                !this.chkAdministracion_Tarifas_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Tarifas_Modificar.isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Tarifas_BuscarActionPerformed

    private void chkAdministracion_Tarifas_CargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Tarifas_CargaMasivaActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Tarifas_Buscar.isSelected() &&
                !this.chkAdministracion_Tarifas_Crear.isSelected() &&
                !this.chkAdministracion_Tarifas_Modificar.isSelected()){
                this.chkAdministracion_Tarifas.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Tarifas_CargaMasivaActionPerformed

    private void chkAdministracion_Vuelos_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Vuelos_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Vuelos_Buscar.isSelected() &&
                !this.chkAdministracion_Vuelos_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Vuelos_Eventos.isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Vuelos_CrearActionPerformed

    private void chkAdministracion_Vuelos_EventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Vuelos_EventosActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Vuelos_Buscar.isSelected() &&
                !this.chkAdministracion_Vuelos_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Vuelos_Crear.isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Vuelos_EventosActionPerformed

    private void chkAdministracion_Vuelos_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Vuelos_BuscarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Vuelos_Crear.isSelected() &&
                !this.chkAdministracion_Vuelos_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Vuelos_Eventos.isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Vuelos_BuscarActionPerformed

    private void chkAdministracion_Vuelos_CargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Vuelos_CargaMasivaActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Vuelos_Buscar.isSelected() &&
                !this.chkAdministracion_Vuelos_Crear.isSelected() &&
                !this.chkAdministracion_Vuelos_Eventos.isSelected()){
                this.chkAdministracion_Vuelos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Vuelos_CargaMasivaActionPerformed

    private void chkAdministracion_Aeropuertos_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Aeropuertos_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Aeropuertos_Buscar.isSelected() &&
                !this.chkAdministracion_Aeropuertos_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Aeropuertos_Modificar.isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Aeropuertos_CrearActionPerformed

    private void chkAdministracion_Aeropuertos_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Aeropuertos_ModificarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Aeropuertos_Buscar.isSelected() &&
                !this.chkAdministracion_Aeropuertos_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Aeropuertos_Crear.isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Aeropuertos_ModificarActionPerformed

    private void chkAdministracion_Aeropuertos_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Aeropuertos_BuscarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Aeropuertos_Modificar.isSelected() &&
                !this.chkAdministracion_Aeropuertos_CargaMasiva.isSelected() &&
                !this.chkAdministracion_Aeropuertos_Crear.isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Aeropuertos_BuscarActionPerformed

    private void chkAdministracion_Aeropuertos_CargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Aeropuertos_CargaMasivaActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Aeropuertos_Buscar.isSelected() &&
                !this.chkAdministracion_Aeropuertos_Modificar.isSelected() &&
                !this.chkAdministracion_Aeropuertos_Crear.isSelected()){
                this.chkAdministracion_Aeropuertos.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Aeropuertos_CargaMasivaActionPerformed

    private void chkAdministracion_TipoCambio_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_TipoCambio_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_TipoCambio.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_TipoCambio_Buscar.isSelected() &&
                !this.chkAdministracion_TipoCambio_Modificar.isSelected()){
                this.chkAdministracion_TipoCambio.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_TipoCambio_CrearActionPerformed

    private void chkAdministracion_TipoCambio_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_TipoCambio_ModificarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_TipoCambio.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_TipoCambio_Buscar.isSelected() &&
                !this.chkAdministracion_TipoCambio_Crear.isSelected()){
                this.chkAdministracion_TipoCambio.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_TipoCambio_ModificarActionPerformed

    private void chkAdministracion_TipoCambio_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_TipoCambio_BuscarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_TipoCambio.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_TipoCambio_Crear.isSelected() &&
                !this.chkAdministracion_TipoCambio_Modificar.isSelected()){
                this.chkAdministracion_TipoCambio.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_TipoCambio_BuscarActionPerformed

    private void chkSeguridad_UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_UsuariosActionPerformed
        // TODO add your handling code here:
        chkSeguridad_Usuarios_Buscar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkSeguridad_Usuarios_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkSeguridad_Usuarios_Modificar.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkSeguridad_UsuariosActionPerformed

    private void chkSeguridad_PerfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_PerfilesActionPerformed
        // TODO add your handling code here:
        chkSeguridad_Perfiles_Modificar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkSeguridad_Perfiles_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkSeguridad_PerfilesActionPerformed

    private void chkAdministracion_ParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_ParametrosActionPerformed
        // TODO add your handling code here:
        chkAdministracion_Parametros_Crear.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Parametros_Buscar.setSelected(((JCheckBox)evt.getSource()).isSelected());
        chkAdministracion_Parametros_Modificar.setSelected(((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_chkAdministracion_ParametrosActionPerformed

    private void chkSeguridad_Usuarios_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_Usuarios_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkSeguridad_Usuarios.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkSeguridad_Usuarios_Buscar.isSelected() &&
                !this.chkSeguridad_Usuarios_Modificar.isSelected()){
                this.chkSeguridad_Usuarios.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkSeguridad_Usuarios_CrearActionPerformed

    private void chkSeguridad_Usuarios_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_Usuarios_ModificarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkSeguridad_Usuarios.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkSeguridad_Usuarios_Buscar.isSelected() &&
                !this.chkSeguridad_Usuarios_Crear.isSelected()){
                this.chkSeguridad_Usuarios.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkSeguridad_Usuarios_ModificarActionPerformed

    private void chkSeguridad_Usuarios_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_Usuarios_BuscarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkSeguridad_Usuarios.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkSeguridad_Usuarios_Crear.isSelected() &&
                !this.chkSeguridad_Usuarios_Modificar.isSelected()){
                this.chkSeguridad_Usuarios.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkSeguridad_Usuarios_BuscarActionPerformed

    private void chkSeguridad_Perfiles_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_Perfiles_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkSeguridad_Perfiles.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkSeguridad_Perfiles_Modificar.isSelected()){
                this.chkSeguridad_Perfiles.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkSeguridad_Perfiles_CrearActionPerformed

    private void chkSeguridad_Perfiles_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeguridad_Perfiles_ModificarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkSeguridad_Perfiles.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkSeguridad_Perfiles_Crear.isSelected()){
                this.chkSeguridad_Perfiles.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkSeguridad_Perfiles_ModificarActionPerformed

    private void chkAdministracion_Parametros_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Parametros_CrearActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Parametros.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Parametros_Buscar.isSelected() &&
                !this.chkAdministracion_Parametros_Modificar.isSelected()){
                this.chkAdministracion_Parametros.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Parametros_CrearActionPerformed

    private void chkAdministracion_Parametros_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Parametros_ModificarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Parametros.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Parametros_Buscar.isSelected() &&
                !this.chkAdministracion_Parametros_Crear.isSelected()){
                this.chkAdministracion_Parametros.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Parametros_ModificarActionPerformed

    private void chkAdministracion_Parametros_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministracion_Parametros_BuscarActionPerformed
        // TODO add your handling code here:
        if(((JCheckBox)evt.getSource()).isSelected()){
                this.chkAdministracion_Parametros.setSelected(Boolean.TRUE);
        }
        else{
            if( !this.chkAdministracion_Parametros_Crear.isSelected() &&
                !this.chkAdministracion_Parametros_Modificar.isSelected()){
                this.chkAdministracion_Parametros.setSelected(Boolean.FALSE);
            }
        }
    }//GEN-LAST:event_chkAdministracion_Parametros_BuscarActionPerformed

    private void chkReportes_IncidenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkReportes_IncidenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkReportes_IncidenciasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PerfilEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfilEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfilEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfilEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PerfilEdit dialog = new PerfilEdit(new javax.swing.JDialog(), true,-1);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cboEstado;
    private javax.swing.JCheckBox chkAdministracion;
    private javax.swing.JCheckBox chkAdministracion_Aeropuertos;
    private javax.swing.JCheckBox chkAdministracion_Aeropuertos_Buscar;
    private javax.swing.JCheckBox chkAdministracion_Aeropuertos_CargaMasiva;
    private javax.swing.JCheckBox chkAdministracion_Aeropuertos_Crear;
    private javax.swing.JCheckBox chkAdministracion_Aeropuertos_Modificar;
    private javax.swing.JCheckBox chkAdministracion_Parametros;
    private javax.swing.JCheckBox chkAdministracion_Parametros_Buscar;
    private javax.swing.JCheckBox chkAdministracion_Parametros_Crear;
    private javax.swing.JCheckBox chkAdministracion_Parametros_Modificar;
    private javax.swing.JCheckBox chkAdministracion_Tarifas;
    private javax.swing.JCheckBox chkAdministracion_Tarifas_Buscar;
    private javax.swing.JCheckBox chkAdministracion_Tarifas_CargaMasiva;
    private javax.swing.JCheckBox chkAdministracion_Tarifas_Crear;
    private javax.swing.JCheckBox chkAdministracion_Tarifas_Modificar;
    private javax.swing.JCheckBox chkAdministracion_TipoCambio;
    private javax.swing.JCheckBox chkAdministracion_TipoCambio_Buscar;
    private javax.swing.JCheckBox chkAdministracion_TipoCambio_Crear;
    private javax.swing.JCheckBox chkAdministracion_TipoCambio_Modificar;
    private javax.swing.JCheckBox chkAdministracion_Vuelos;
    private javax.swing.JCheckBox chkAdministracion_Vuelos_Buscar;
    private javax.swing.JCheckBox chkAdministracion_Vuelos_CargaMasiva;
    private javax.swing.JCheckBox chkAdministracion_Vuelos_Crear;
    private javax.swing.JCheckBox chkAdministracion_Vuelos_Eventos;
    private javax.swing.JCheckBox chkClientes;
    private javax.swing.JCheckBox chkClientes_Buscar;
    private javax.swing.JCheckBox chkClientes_CargaMasiva;
    private javax.swing.JCheckBox chkClientes_Crear;
    private javax.swing.JCheckBox chkClientes_Modificar;
    private javax.swing.JCheckBox chkEnvios;
    private javax.swing.JCheckBox chkEnvios_Buscar;
    private javax.swing.JCheckBox chkEnvios_Crear;
    private javax.swing.JCheckBox chkEnvios_Modificar;
    private javax.swing.JCheckBox chkReportes;
    private javax.swing.JCheckBox chkReportes_Envios;
    private javax.swing.JCheckBox chkReportes_Incidencias;
    private javax.swing.JCheckBox chkReportes_MovAlmacen;
    private javax.swing.JCheckBox chkReportes_Ventas;
    private javax.swing.JCheckBox chkSeguridad;
    private javax.swing.JCheckBox chkSeguridad_LogAuditoria;
    private javax.swing.JCheckBox chkSeguridad_Perfiles;
    private javax.swing.JCheckBox chkSeguridad_Perfiles_Crear;
    private javax.swing.JCheckBox chkSeguridad_Perfiles_Modificar;
    private javax.swing.JCheckBox chkSeguridad_Usuarios;
    private javax.swing.JCheckBox chkSeguridad_Usuarios_Buscar;
    private javax.swing.JCheckBox chkSeguridad_Usuarios_Crear;
    private javax.swing.JCheckBox chkSeguridad_Usuarios_Modificar;
    private javax.swing.JCheckBox chkSimulacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAdministracion;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelEnvios;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelSeguridad;
    private javax.swing.JTabbedPane paneles;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
