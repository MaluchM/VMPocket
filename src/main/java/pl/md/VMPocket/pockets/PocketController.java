package pl.md.VMPocket.pockets;

import pl.md.VMPocket.communication.Response;

public class PocketController {
    public Response createNewPocket(String pocketName) {
        return new Response(true);
    }
}
