package org.bibliohub.command.library;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.LibraryService;

public abstract class Library implements Command {
    protected static final LibraryService libraryService = LibraryService.getInstance();

    Library() {

    }

    public abstract void execute();


}
