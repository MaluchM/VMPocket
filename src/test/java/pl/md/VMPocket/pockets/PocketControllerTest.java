package pl.md.VMPocket.pockets;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import pl.md.VMPocket.communication.Response;
import pl.md.VMPocket.storage.PocketStorage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class PocketControllerTest {

    private static final String POCKET_NAME = "Pocket Name";
    private final PocketStorage pocketStorage = mock(PocketStorage.class);
    private final PocketController pocketController = new PocketController(pocketStorage);

    @Test
    public void shouldCreateNewPocket() {
        Response response = pocketController.createNewPocket(POCKET_NAME);

        assertTrue(response.isSuccess());
        verify(this.pocketStorage).existPocket(POCKET_NAME);
    }

    @Test
    public void shouldNotCreateNewPocketIfPocketNameIsNotUniqe() {
        BDDMockito.given(pocketStorage.existPocket(POCKET_NAME)).willReturn(true);

        Response response = pocketController.createNewPocket(POCKET_NAME);

        assertFalse(response.isSuccess());
        assertEquals("Pocket with this name already exists", response.getMessage());
        verify(this.pocketStorage, never()).addPocket(new Pocket(POCKET_NAME));
    }

    @Test
    public void shouldDeletePocketIfPocketAlreadyExist() {
        BDDMockito.given(pocketStorage.existPocket(POCKET_NAME)).willReturn(true);

        Response response = pocketController.deletePocket(POCKET_NAME);

        assertTrue(response.isSuccess());
        verify(this.pocketStorage, Mockito.times(1)).removePocket(POCKET_NAME);
    }

    @Test
    public void shouldNotDeletePocketIfPocketNotExist() {
        BDDMockito.given(pocketStorage.existPocket(POCKET_NAME)).willReturn(false);

        Response response = pocketController.deletePocket(POCKET_NAME);

        assertFalse(response.isSuccess());
        assertEquals("Pocket with this name not exists", response.getMessage());
        verify(pocketStorage, never()).removePocket(POCKET_NAME);
    }
}