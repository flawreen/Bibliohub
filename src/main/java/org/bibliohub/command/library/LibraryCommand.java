package org.bibliohub.command.library;

import org.bibliohub.service.LibraryService;

public abstract class LibraryCommand {
    protected static final LibraryService libraryService = LibraryService.getInstance();

    LibraryCommand() {

    }

    public abstract void execute(String password);
    public abstract void execute(String password, long id);


}
