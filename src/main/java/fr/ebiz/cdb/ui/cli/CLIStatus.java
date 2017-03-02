package fr.ebiz.cdb.ui.cli;

/**
 * CLI Status. Represents state of the CLI.
 */
enum CLIStatus {

    /**
     * CLI is ready to stop.
     */
    EXIT,

    /**
     * CLI is currently running the index frame.
     */
    INDEX,

    /**
     * CLI is currently running the Computers List frame.
     */
    COMPUTERS,

    /**
     * CLI is currently running the Computer Details frame.
     */
    COMPUTER,

    /**
     * CLI is currently running the Companies List frame.
     */
    COMPANIES,

    /**
     * CLI is currently running the Company Details frame.
     */
    COMPANY,

    /**
     * CLI is currently running the Computer Edit frame.
     */
    COMPUTER_EDIT,

    /**
     * CLI is currently running the Computer Create frame.
     */
    COMPUTER_CREATE;

}
