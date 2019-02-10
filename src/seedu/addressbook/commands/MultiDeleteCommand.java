package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.ArrayList;
import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Deletes multiple people identified using the input which is taken as the
 * index of the person.
 */
public class MultiDeleteCommand extends Command{
    
    private ArrayList<Integer> multiIndexStorage;
    private boolean isValid;

    public static final String COMMAND_WORD = "multidelete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "Deletes all persons identified by the index numbers " +
            "used in the last find/list call.\n" +
            "Parameters: INDICES\n" + 
            "Example: " + COMMAND_WORD + " 2 4 5";
    public static final String MESSAGE_MULTIDELETE_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_INVALID_INPUT = "Invalid Input";
    
    public MultiDeleteCommand(ArrayList<Integer> listIndex, boolean isValid) {
        this.multiIndexStorage = listIndex;
        this.isValid = isValid;
    }
    
    @Override
    public CommandResult execute() {
        
        if(isValid) {
            try{
                ArrayList<ReadOnlyPerson> targetIndex = new ArrayList<>();
                for(int j = 0; j < multiIndexStorage.size(); j++) {
                    targetIndex.add(relevantPersons.get(multiIndexStorage.get(j) - DISPLAYED_INDEX_OFFSET));
                }
                for(int k = 0; k < multiIndexStorage.size(); k++) {
                    addressBook.removePerson(targetIndex.get(k));
                }
                return new CommandResult(String.format(MESSAGE_MULTIDELETE_SUCCESS));
            } catch (IndexOutOfBoundsException ie) {
                return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            } catch (UniquePersonList.PersonNotFoundException pnfe) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            } 
        } else {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX); 
        }
    }
    public void setValid(boolean valid) {
        isValid = valid;
    }
}
