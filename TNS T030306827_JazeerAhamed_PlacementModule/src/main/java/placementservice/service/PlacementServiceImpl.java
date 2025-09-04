package placementservice.service;

import placementservice.model.Placement;
import placementservice.repository.PlacementRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacementServiceImpl implements PlacementService {

    @Autowired
    private PlacementRepository placementRepository;
    
    
	@Override
    public Placement addPlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    @Override
    public Placement updatePlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    @Override
    public Placement searchPlacement(long id) {
        return placementRepository.findById(id).orElse(null);
    }

    @Override
    public boolean cancelPlacement(long id) {
        if (placementRepository.existsById(id)) {
            placementRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Placement> findAll() {
        return placementRepository.findAll();
    }
}


