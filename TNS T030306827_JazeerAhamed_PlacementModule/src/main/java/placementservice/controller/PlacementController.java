package placementservice.controller;

import placementservice.model.Placement;
import placementservice.service.PlacementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placement")
public class PlacementController {
	
    @Autowired
    private PlacementService placementService;
    
    

    @GetMapping

    public List<Placement> getAllPlacements() {
        return placementService.findAll();
    }
    
    

    @PostMapping
    public ResponseEntity<Placement> addPlacement(@RequestBody Placement placement) {
        return ResponseEntity.ok(placementService.addPlacement(placement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placement> updatePlacement(@PathVariable long id, @RequestBody Placement placement) {
        placement.setId(id);
        Placement updatedPlacement = placementService.updatePlacement(placement);
        if (updatedPlacement != null) {
            return ResponseEntity.ok(updatedPlacement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placement> searchPlacement(@PathVariable long id) {
        Placement placement = placementService.searchPlacement(id);
        if (placement != null) {
            return ResponseEntity.ok(placement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelPlacement(@PathVariable long id) {
        boolean canceled = placementService.cancelPlacement(id);
        if (canceled) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
