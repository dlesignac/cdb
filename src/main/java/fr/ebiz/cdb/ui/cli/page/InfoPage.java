package fr.ebiz.cdb.ui.cli.page;

/**
 * Info Content Page Component. Computes data into a String to be printed for
 * further info.
 */
public class InfoPage implements ContentPage {

    private String info;

    /**
     * Constructor.
     * @param info
     *            info to be displayed
     */
    public InfoPage(String info) {
        this.info = info;
    }

    @Override
    public String toDisplay() {
        return info;
    }

}
