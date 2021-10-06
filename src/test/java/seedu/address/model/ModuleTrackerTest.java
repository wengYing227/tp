package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.ModuleTracker;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

class ModuleTrackerTest {

    private final ModuleTracker moduleTracker = new ModuleTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), moduleTracker.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModuleTracker_replacesData() {
        ModuleTracker newData = getTypicalModule();
        moduleTracker.resetData(newData);
        assertEquals(newData, moduleTracker);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two persons with the same identity fields
        seedu.address.model.module.Module editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<seedu.address.model.person.Module> newPersons = Arrays.asList(ALICE, editedAlice);
        ModuleTrackerTest.ModuleTrackerStub newData = new ModuleTrackerTest.ModuleTrackerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTracker.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInModuleTracker_returnsFalse() {
        assertFalse(moduleTracker.hasModule(ALICE));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInModuleTracker_returnsTrue() {
        moduleTracker.addModule(ALICE);
        seedu.address.model.person.Module editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(moduleTracker.hasModule(editedAlice));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> moduleTracker.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyModuleTracker whose persons list can violate interface constraints.
     */
    private static class ModuleTrackerStub implements ReadOnlyModuleTracker {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        ModuleTrackerStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }


        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }


}