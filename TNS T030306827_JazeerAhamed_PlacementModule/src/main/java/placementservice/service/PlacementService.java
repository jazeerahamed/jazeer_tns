package placementservice.service;

import java.util.List;


import placementservice.model.Placement;

public interface PlacementService {
    Placement addPlacement(Placement placement);
    Placement updatePlacement(Placement placement);
    Placement searchPlacement(long id);
    boolean cancelPlacement(long id);

	List<Placement> findAll();
	
}