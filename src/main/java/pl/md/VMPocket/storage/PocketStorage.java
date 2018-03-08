package pl.md.VMPocket.storage;

import pl.md.VMPocket.pockets.Pocket;

public interface PocketStorage {
    void addPocket(Pocket pocket);

    boolean existPocket(String pocketName);

    void removePocket(String pocketName);
}
