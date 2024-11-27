package model;

import java.util.ArrayList;

public class PersonaUtil {

    public static PersonaVO personaToPersonaVO(Persona persona) {
        if (persona == null) return null;
        return new PersonaVO(
                persona.getDni(),
                persona.getNombre(),
                persona.getApellidos(),
                persona.getDireccion(),
                persona.getLocalidad(),
                persona.getProvincia()
        );
    }

    // Conversión de PersonaVO a Persona
    public static Persona personaVOToPersona(PersonaVO personaVO) {
        if (personaVO == null) return null;
        Persona persona = new Persona();
        persona.setDni(personaVO.getDni());
        persona.setNombre(personaVO.getNombre());
        persona.setApellidos(personaVO.getApellidos());
        persona.setDireccion(personaVO.getDireccion());
        persona.setLocalidad(personaVO.getLocalidad());
        persona.setProvincia(personaVO.getProvincia());
        return persona;
    }

    // Conversión de ArrayList<Persona> a ArrayList<PersonaVO>
    public static ArrayList<PersonaVO> personaListToPersonaVOList(ArrayList<Persona> personaList) {
        if (personaList == null) return null;
        ArrayList<PersonaVO> personaVOList = new ArrayList<>();
        for (Persona persona : personaList) {
            personaVOList.add(personaToPersonaVO(persona));
        }
        return personaVOList;
    }

    // Conversión de ArrayList<PersonaVO> a ArrayList<Persona>
    public static ArrayList<Persona> personaVOListToPersonaList(ArrayList<PersonaVO> personaVOList) {
        if (personaVOList == null) return null;
        ArrayList<Persona> personaList = new ArrayList<>();
        for (PersonaVO personaVO : personaVOList) {
            personaList.add(personaVOToPersona(personaVO));
        }
        return personaList;
    }

}
