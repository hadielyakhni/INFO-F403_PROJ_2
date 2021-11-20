import java.util.TreeMap;

%%

%class LexicalAnalyzer
%unicode
%unicode
%line
%column
%type Symbol
%function nextToken

%{
    /**
        * keeps tracks of the last line where a CO comment has been opened
        */
        private int lastOpenedCommentLine = -1;

        /**
        * keeps track of the recognized variables,
        * and the line where they were first encourtered
        */
        public TreeMap<String, Integer> symbolTable = new TreeMap<>();

        /**
        * Takes ScanError as parameter,
        * and output a corresponding message to the console
        * @param err - ScanError to be reported
        */
        private void onError(ScanError err) {
            String errMsg = generateErrorMessage(err);
            printErrorMessage(errMsg);
            System.exit(1);
        }

        /**
        * Generates an error message based on the ScanError error passed as parameter
        * @param err
        * @return a string representing the corresponding error message
        */
        private String generateErrorMessage(ScanError scanError) {
            switch(scanError) {
                case UNCLOSED_COMMENT:
                    return String.format("ERROR: COMMENT STARTED AT LINE %d NOT CLOSED", lastOpenedCommentLine);
                case UNRECOGNIZED_TOKEN:
                    return String.format("ERROR: UNRECOGNIZED TOKEN AT LINE %d, COLUMN %d", yyline + 1, yycolumn + 1);
                default:
                    return "An ScanError was encoutered!";
            }
        }

        /**
        * Prints an error message to the console
        * @param errMessage
        */
        private void printErrorMessage(String errMessage) {
            System.out.println();
            System.out.print(errMessage);
            System.out.println();
        }

        /**
        * Build and return a new symbol based on the token identified by the scanner.
        * @param lexicalUnit -- The corresponding lexical unit of the found token
        * @return A Symbol instance corresponding to the lexical unit parameter, with metadata
        */
        private Symbol buildSymbol(LexicalUnit lexicalUnit) {
            return new Symbol(lexicalUnit, yyline, yycolumn, yytext());
        }

        /**
        * Adds a variable to the symbol table if it's not there,
        * along with it's line of occurence the first time
        * @param varName
        * @param lineNumber
        */
        private void onVariable() {
            String varName = yytext();
            int lineNumber = yyline + 1;
            if(!symbolTable.containsKey(varName)) {
                symbolTable.put(varName, lineNumber);
            }
        }

        /**
        * Switch the state to COMMENT_STATE, and update the lastOpenedCommentLine
        */
        private void onLongCommentStart() {
           lastOpenedCommentLine = yyline + 1;
           yybegin(COMMENT_STATE);
        }

        /**
        * Switch the state back to YYINITIAL after a long comment has been closed
        */
        private void onLongCommentEnd() {
           yybegin(YYINITIAL);
        }

        private void unrecognizedToken() {
            onError(ScanError.UNRECOGNIZED_TOKEN);
        }

        /**
        * Print the symbol table when the end of file is reached with no problems
        * @return end of stream symbol
        */
        private Symbol legalEndOfFile() {
            // printSymbolTable();
            return new Symbol(LexicalUnit.END_OF_STREAM);
        }

        /**
        * Runs when end of a file is reached, but a long comment is still not closed
        */
        private void illegalEndOfFile() {
            onError(ScanError.UNCLOSED_COMMENT);
        }

        /**
        * Prints the symbol table,
        * sorting all recognized variables in lexicographical order
        */
        private void printSymbolTable() {
            System.out.println("\nVariables");

            for (String varName : symbolTable.keySet()) {
                System.out.println(varName + "\t" + symbolTable.get(varName));
            }
        }

%}

// Extended Regular Expressions

PLUS            = \+
MINUS           = -
TIMES           = \*
DIVIDE          = \/
EQUAL           = =
GREATER         = >
SMALLER         = <
ASSIGN          = :=
SEMICOLON       = ;
LPAREN          = \(
RPAREN          = \)
BEG             = begin
END             = end
IF              = if
THEN            = then
ELSE            = else
ENDIF           = endif
NOT             = not
WHILE           = while
DO              = do
ENDWHILE        = endwhile
FOR             = for
FROM            = from
BY              = by
TO              = to
ENDFOR          = endfor
PRINT           = print
READ            = read

EndOfLine       = "\r"?"\n"

WhiteSpace      = {EndOfLine} | [\s\t\f]*

AlphaUpperCase  = [A-Z]
AlphaLowerCase  = [a-z]
Alpha           = {AlphaUpperCase}|{AlphaLowerCase}

ShortComment    = {co}.*{EndOfLine}

co              = co
CO              = CO

Number          = [0-9]

VarName         = {Alpha}({Number}|{Alpha})*

%xstate YYINITIAL, COMMENT_STATE

%%// Identification of tokens

<COMMENT_STATE> {
    {CO}             {onLongCommentEnd();}
    [^]              {}
    <<EOF>>          {illegalEndOfFile();}
}

<YYINITIAL> {
    
    {CO}	         {onLongCommentStart();}
    {ShortComment}   {}
    
    {PLUS}           {return buildSymbol(LexicalUnit.PLUS);}
    {MINUS}          {return buildSymbol(LexicalUnit.MINUS);}
    {TIMES}          {return buildSymbol(LexicalUnit.TIMES);}
    {DIVIDE}         {return buildSymbol(LexicalUnit.DIVIDE);}
    {EQUAL}          {return buildSymbol(LexicalUnit.EQUAL);}
    {GREATER}        {return buildSymbol(LexicalUnit.GREATER);}
    {SMALLER}        {return buildSymbol(LexicalUnit.SMALLER);}
    {ASSIGN}         {return buildSymbol(LexicalUnit.ASSIGN);}
    {SEMICOLON}      {return buildSymbol(LexicalUnit.SEMICOLON);}
    {LPAREN}         {return buildSymbol(LexicalUnit.LPAREN);}
    {RPAREN}         {return buildSymbol(LexicalUnit.RPAREN);}
    {BEG}            {return buildSymbol(LexicalUnit.BEG);}
    {END}            {return buildSymbol(LexicalUnit.END);}
    {IF}             {return buildSymbol(LexicalUnit.IF);}
    {THEN}           {return buildSymbol(LexicalUnit.THEN);}
    {ELSE}           {return buildSymbol(LexicalUnit.ELSE);}
    {ENDIF}          {return buildSymbol(LexicalUnit.ENDIF);}
    {NOT}            {return buildSymbol(LexicalUnit.NOT);}
    {WHILE}          {return buildSymbol(LexicalUnit.WHILE);}
    {DO}             {return buildSymbol(LexicalUnit.DO);}
    {ENDWHILE}       {return buildSymbol(LexicalUnit.ENDWHILE);}
    {FOR}            {return buildSymbol(LexicalUnit.FOR);}
    {FROM}           {return buildSymbol(LexicalUnit.FROM);}
    {BY}             {return buildSymbol(LexicalUnit.BY);}
    {TO}             {return buildSymbol(LexicalUnit.TO);}
    {ENDFOR}         {return buildSymbol(LexicalUnit.ENDFOR);}
    {PRINT}          {return buildSymbol(LexicalUnit.PRINT);}
    {READ}           {return buildSymbol(LexicalUnit.READ);}

    {Number}         {return buildSymbol(LexicalUnit.NUMBER);}
    {VarName}        {onVariable(); return buildSymbol(LexicalUnit.VARNAME);}

    {WhiteSpace}     {/* ignore white spaces and end of lines */}

    [^]              {unrecognizedToken();}

    // Return value of the program
    <<EOF>>          {return legalEndOfFile();}
}