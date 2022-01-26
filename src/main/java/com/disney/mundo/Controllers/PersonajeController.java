package com.disney.mundo.Controllers;
import com.disney.mundo.Model.DTO.PersonajeDTO;
import com.disney.mundo.Model.DTO.PersonajeGuardarDTO;
import com.disney.mundo.Model.DTO.PersonajeListadoDTO;
import com.disney.mundo.Model.Entities.Personaje;
import com.disney.mundo.Services.PersonajeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    private final PersonajeService  personajeService;
    public PersonajeController(PersonajeService personajeService){
        this.personajeService = personajeService;
    }


    @GetMapping("")
    public ResponseEntity<Page<PersonajeListadoDTO>> findAllListado()
    {
        return ResponseEntity.ok(personajeService.findAllListado(0,10,null));
    }

    @GetMapping("/{idPersonaje}")
    public ResponseEntity<Page<PersonajeDTO>> findById(
            @PathVariable(name="idPersonaje") Integer id,
            @RequestParam(name="pageNumber",defaultValue = "1")Integer pageNumber,
            @RequestParam(name="pageSize",defaultValue = "1")Integer pageSize,
            @RequestParam(name="orderField",defaultValue = "nombre")String orderField)
    {
        return ResponseEntity.ok(personajeService.findById(id,pageNumber,pageSize,orderField));
    }


//    @GetMapping("/all")
//    public ResponseEntity<Page<PersonajeDTO>> findAll()
//    {
//        return ResponseEntity.ok(personajeService.findAll(0,10,null));
//    }


    @GetMapping("/search")
    public ResponseEntity<Page<PersonajeDTO>> findByNombreContains(
            @RequestParam(name="name",defaultValue = "",required = true)String nombre,
            @RequestParam(name="age",defaultValue = "",required = false) Integer edad,
            @RequestParam(name="weight",defaultValue = "",required = false)Double peso,
            @RequestParam(name="order",defaultValue="DESC",required = false) String order,
            @RequestParam(name="orderField",defaultValue = "nombre")String orderField)

    {
        return ResponseEntity.ok(personajeService
                .findByNombreContains(nombre,edad,peso,order,orderField));
    }



    @PostMapping("")
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeGuardarDTO dto) {
        return ResponseEntity.ok(personajeService.save(dto));
    }


    @PutMapping("/{idPersonaje}")
    public ResponseEntity<PersonajeDTO> edit(
            @PathVariable(name="idPersonaje") int id,
            @RequestBody  PersonajeGuardarDTO dto ) {
        return ResponseEntity.ok(personajeService.edit(id,dto));
    }

    @DeleteMapping("/{idPersonaje}")
    public ResponseEntity<PersonajeListadoDTO> delete(
            @PathVariable(name="idPersonaje") Integer id) {
        return ResponseEntity.ok(personajeService.delete(id));
    }

}
