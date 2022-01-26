package com.disney.mundo.Controllers;
import com.disney.mundo.Model.DTO.PeliculaSerieDTO;
import com.disney.mundo.Model.DTO.PeliculaSerieGuardarDTO;
import com.disney.mundo.Model.DTO.PeliculaSerieListadoDTO;
import com.disney.mundo.Model.DTO.PersonajeDTO;
import com.disney.mundo.Services.PeliculaSerieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieController {

    private final PeliculaSerieService peliculaserieService;

    public PeliculaSerieController(PeliculaSerieService peliculaserieService){
        this.peliculaserieService = peliculaserieService;
    }


    @GetMapping("")
    public ResponseEntity<Page<PeliculaSerieListadoDTO>> findAllListado()
    {
        return ResponseEntity.ok(peliculaserieService.findAllListado(0,10,null));
    }

//    @GetMapping("/all")
//    public ResponseEntity<Page<PeliculaSerieDTO>> findAll()
//    {
//        return ResponseEntity.ok(peliculaserieService.findAll(0,10,null));
//    }


    @GetMapping("/{idPeliculaSerie}")
    public ResponseEntity<Page<PeliculaSerieDTO>> findById(
            @PathVariable(name="idPeliculaSerie") Integer id,
            @RequestParam(name="pageNumber",defaultValue = "1")Integer pageNumber,
            @RequestParam(name="pageSize",defaultValue = "1")Integer pageSize,
            @RequestParam(name="orderField",defaultValue = "titulo")String orderField)

    {
        return ResponseEntity.ok(peliculaserieService.findById(id,pageNumber,pageSize,orderField));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PeliculaSerieDTO>> findByTituloContains(
            @RequestParam(name="title",defaultValue = "",required = true)String titulo,
            @RequestParam(name="genre",defaultValue = "" ,required = false) Integer idGenero,
            @RequestParam(name="order",defaultValue="DESC",required = false) String direction,
            @RequestParam(name="orderField",defaultValue = "titulo")String orderField)
    {
        return ResponseEntity.ok(peliculaserieService
                .findByTituloContains(titulo,idGenero,direction,orderField));
    }




    @PostMapping("")
    public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieGuardarDTO dto)
    {
        return ResponseEntity.ok(peliculaserieService.save(dto));
    }


    @PutMapping("/{idPeliculaSerie}")
    public ResponseEntity<PeliculaSerieDTO> edit(
            @PathVariable(name="idPeliculaSerie") int id,
            @RequestBody  PeliculaSerieDTO dto )
    {
        return ResponseEntity.ok(peliculaserieService.edit(id,dto));
    }

    @DeleteMapping("/{idPeliculaSerie}")
    public ResponseEntity<PeliculaSerieDTO> delete(
            @PathVariable(name="idPeliculaSerie") Integer id){
        return ResponseEntity.ok(peliculaserieService.delete(id));
    }
    
}
