package com.company.converters;

import com.company.commands.NotesCommand;
import com.company.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final Long ID = new Long(1L);
    public static final String RECIPE_NOTES = "recipe notes";
    NotesToNotesCommand notesToNotesCommand;

    @Before
    public void setUp() throws Exception {
        notesToNotesCommand = new NotesToNotesCommand();
    }

    @Test
    public void testNullObj() throws Exception{
        assertNull(notesToNotesCommand.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception{
        assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = notesToNotesCommand.convert(notes);

        //then
        assertNotNull(notesCommand);
        assertEquals(ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }

}