package org.bibliohub.command.library;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.LibraryService;

public abstract class LibraryCommand implements Command {
    protected static final LibraryService libraryService = LibraryService.getInstance();

    LibraryCommand() {

    }

    public abstract void execute();


}
