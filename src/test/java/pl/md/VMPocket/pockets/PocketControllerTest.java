package pl.md.VMPocket.pockets;

import org.junit.Test;
import pl.md.VMPocket.communication.Response;
import pl.md.VMPocket.pockets.PocketController;

import static org.junit.Assert.*;

public class PocketControllerTest {

    private static final String POCKET_NAME = "Pocket Name";

    @Test
    public void shouldCreateNewPocket() {
        Response result = new PocketController().createNewPocket(POCKET_NAME);

        assertTrue(result.isSuccess());

    }
}