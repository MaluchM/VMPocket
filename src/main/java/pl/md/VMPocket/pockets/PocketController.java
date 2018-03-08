package pl.md.VMPocket.pockets;

import pl.md.VMPocket.communication.Response;
import pl.md.VMPocket.storage.PocketStorage;

public class PocketController {
    private PocketStorage pocketStorage;

    public PocketController(PocketStorage pocketStorage) {
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

    public Response deletePocket(String pocketName) {
        if(!pocketStorage.existPocket(pocketName)) {
            return new Response(false, "Pocket with this name not exists");
        } else {
            pocketStorage.removePocket(pocketName);
        }
        return new Response(true);
    }
}
