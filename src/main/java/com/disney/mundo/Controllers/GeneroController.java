package com.disney.mundo.Controllers;
import com.disney.mundo.Model.DTO.GeneroDTO;
import com.disney.mundo.Model.DTO.GeneroDTO;
import com.disney.mundo.Model.DTO.GeneroGuardarDTO;
import com.disney.mundo.Services.GeneroService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GeneroController {


    private final GeneroService generoService;
    public GeneroController(GeneroService generoService){
        this.generoService = generoService;
    }
    
    @GetMapping("")
    public ResponseEntity<Page<GeneroDTO>> findAll()
    {
        return ResponseEntity.ok(generoService.findAll(0,10,null));
    }

    @PostMapping("")
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroGuardarDTO dto)
    {
        return ResponseEntity.ok(generoService.save(dto));
    }


}
