package fr.ebiz.cdb.ui.cli;

/**
 * CLI Status. Represents state of the CLI.
 */
public enum CLIStatus {

	/**
	 * CLI is ready to stop.
	 */
	EXIT,

	/**
	 * CLI is currently running the index page.
	 */
	INDEX,

	/**
	 * CLI is currently running the Computers List page.
	 */
	COMPUTERS,

	/**
	 * CLI is currently running the Computer Details page.
	 */
	COMPUTER,

	/**
	 * CLI is currently running the Companies List page.
	 */
	COMPANIES,

	/**
	 * CLI is currently running the Computer Edit page.
	 */
	COMPUTER_EDIT;

}
