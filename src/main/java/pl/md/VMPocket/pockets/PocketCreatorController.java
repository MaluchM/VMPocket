package pl.md.VMPocket.pockets;

import pl.md.VMPocket.communication.Response;
import pl.md.VMPocket.storage.PocketStorage;

public class PocketCreatorController {
    private PocketStorage pocketStorage;

    public PocketCreatorController(PocketStorage pocketStorage) {
        this.pocketStorage = pocketStorage;
    }

    public Response createNewPocket(String pocketName) {
        if(pocketStorage.existPocket(pocketName)) {
            return new Response(false, "Pocket with this name already exists");
        } else {
            pocketStorage.addPocket(new Pocket(pocketName));
        }
        return new Response(true);
    }
}
