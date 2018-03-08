package pl.md.VMPocket.pockets;

import org.junit.Test;
import org.mockito.BDDMockito;
import pl.md.VMPocket.communication.Response;
import pl.md.VMPocket.storage.PocketStorage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class PocketCreatorControllerTest {

    private static final String POCKET_NAME = "Pocket Name";
    private final PocketStorage pocketStorage = mock(PocketStorage.class);

    @Test
    public void shouldCreateNewPocket() {
        Response response = new PocketCreatorController(pocketStorage).createNewPocket(POCKET_NAME);

        assertTrue(response.isSuccess());
    }

    @Test
    public void shouldNotCreateNewPocketIfPocketNameIsNotUniqe() {
        BDDMockito.given(pocketStorage.existPocket(POCKET_NAME)).willReturn(true);

        Response response = new PocketCreatorController(pocketStorage).createNewPocket(POCKET_NAME);

        assertFalse(response.isSuccess());
        assertEquals("Pocket with this name already exists", response.getMessage());
        verify(this.pocketStorage, never()).addPocket(new Pocket(POCKET_NAME));
    }
}