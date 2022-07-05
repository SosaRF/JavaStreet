package cz.isgastreet.core;

import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Marco Schulz
 */
public class SelectBehave extends DefaultListSelectionModel
{

    @Override
    public void setSelectionInterval(int index0, int index1)
    {
        if (super.isSelectedIndex(index0))
        {
            super.removeSelectionInterval(index0, index1);
        } else
        {
            super.addSelectionInterval(index0, index1);
        }
    }

}
