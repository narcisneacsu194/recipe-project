package com.company.converters;

import com.company.commands.NotesCommand;
import com.company.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    public static final Long ID = new Long(1L);
    public static final String RECIPE_NOTES = "recipe notes";
    NotesCommandToNotes notesCommandToNotes;

    @Before
    public void setUp() throws Exception {
        notesCommandToNotes = new NotesCommandToNotes();
    }

    @Test
    public void testNullObj() throws Exception{
        assertNull(notesCommandToNotes.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception{
        assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = notesCommandToNotes.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}