package model.repository;

import model.ExcepcionPersona;
import model.PersonaVO;

import java.util.ArrayList;

public interface PersonaRepository {
    ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona;

    void addPersona(PersonaVO var1) throws ExcepcionPersona;

    void deletePersona(String var1) throws ExcepcionPersona;

    void editPersona(PersonaVO var1) throws ExcepcionPersona;

    String lastDni() throws ExcepcionPersona;
}
