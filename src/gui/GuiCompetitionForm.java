/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.CompetitionService;

/**
 * GUI builder created Form
 *
 * @author Lenovo
 */
public class GuiCompetitionForm extends com.codename1.ui.Form {

    public GuiCompetitionForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public GuiCompetitionForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.table.Table gui_Table = new com.codename1.ui.table.Table();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setRTL(false);
        setScrollableX(false);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("GuiCompetitionForm");
        setName("GuiCompetitionForm");
        gui_Label.setPreferredSizeStr("43.121693mm 9.259259mm");
        gui_Label.setText("List Competition");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setName("Label");
        gui_Table.setPreferredSizeStr("42.32804mm 28.83598mm");
                gui_Table.setInlineStylesTheme(resourceObjectInstance);
        gui_Table.setName("Table");
        gui_Table.setPropertyValue("data", new String[][]{
             {"1", "2"},
            {"3", "4"},
            {"Val2", "Val2"}});
        addComponent(gui_Label);
        addComponent(gui_Table);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label.getParent().getLayout()).setInsets(gui_Label, "4.2328043mm auto auto 3.9106147%").setReferenceComponents(gui_Label, "-1 -1 -1 -1").setReferencePositions(gui_Label, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Table.getParent().getLayout()).setInsets(gui_Table, "0.0mm 1.8518524mm 77.14885% 0.0mm").setReferenceComponents(gui_Table, "0 0 -1 0 ").setReferencePositions(gui_Table, "1.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
