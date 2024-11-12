package model;

public class PersonaVO {
    String dni;
    String nombre;
    String apellidos;
    String direccion;
    String localidad;
    String provincia;

    public PersonaVO(String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public PersonaVO(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String toString() {
        return "PersonaVO{DNI=" + this.dni + ", nombre=" + this.nombre + ", apellidos=" + this.apellidos +", localidad=" + this.localidad +", dirección=" + this.direccion +", provincia=" + this.provincia +'}';
    }
}
