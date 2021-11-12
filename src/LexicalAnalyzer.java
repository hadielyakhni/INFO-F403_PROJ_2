/* The following code was generated by JFlex 1.7.0 */

import java.util.TreeMap;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>src/LexicalAnalyzer.flex</tt>
 */
class LexicalAnalyzer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT_STATE = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\40\1\37\1\42\1\42\1\36\22\0\1\40\7\0\1\12"+
    "\1\13\1\3\1\1\1\0\1\2\1\0\1\4\12\46\1\10\1\11"+
    "\1\7\1\5\1\6\2\0\2\41\1\44\13\41\1\45\13\41\6\0"+
    "\1\35\1\14\1\43\1\21\1\15\1\22\1\16\1\24\1\17\2\41"+
    "\1\25\1\32\1\20\1\27\1\34\1\41\1\31\1\26\1\23\2\41"+
    "\1\30\1\41\1\33\1\41\12\0\1\42\32\0\1\40\u15df\0\1\40"+
    "\u097f\0\13\40\35\0\1\42\1\42\5\0\1\40\57\0\1\40\u0fa0\0"+
    "\1\40\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\ud00f\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\0\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\2\1\12\1\13\1\14\13\15\1\1"+
    "\2\15\1\16\2\17\1\20\1\15\1\21\2\15\1\22"+
    "\1\15\1\23\3\15\1\24\4\15\1\25\1\26\1\15"+
    "\1\27\1\15\1\30\1\31\5\15\2\0\4\15\1\32"+
    "\1\33\1\34\1\15\1\35\1\15\1\36\1\37\2\15"+
    "\1\40\1\41\1\42\2\15\1\43";

  private static int [] zzUnpackAction() {
    int [] result = new int[81];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\47\0\116\0\116\0\116\0\116\0\116\0\116"+
    "\0\116\0\116\0\165\0\116\0\116\0\116\0\234\0\303"+
    "\0\352\0\u0111\0\u0138\0\u015f\0\u0186\0\u01ad\0\u01d4\0\u01fb"+
    "\0\u0222\0\u0249\0\u0270\0\u0297\0\116\0\116\0\u02be\0\116"+
    "\0\u02e5\0\352\0\u030c\0\u0333\0\352\0\u035a\0\352\0\u0381"+
    "\0\u03a8\0\u03cf\0\352\0\u03f6\0\u041d\0\u0444\0\u046b\0\352"+
    "\0\116\0\u0492\0\u04b9\0\u04e0\0\352\0\352\0\u0507\0\u052e"+
    "\0\u0555\0\u057c\0\u05a3\0\u05ca\0\u05f1\0\u0618\0\u063f\0\u0666"+
    "\0\u068d\0\352\0\352\0\352\0\u06b4\0\352\0\u06db\0\352"+
    "\0\352\0\u0702\0\u0729\0\352\0\352\0\352\0\u0750\0\u0777"+
    "\0\352";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[81];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\4\21\1\27\1\30\2\21"+
    "\1\31\1\21\3\32\1\21\1\32\1\33\1\34\1\21"+
    "\1\35\44\36\1\37\2\36\54\0\1\40\55\0\1\21"+
    "\1\41\15\21\1\42\2\21\3\0\1\21\1\0\4\21"+
    "\14\0\4\21\1\43\4\21\1\44\10\21\3\0\1\21"+
    "\1\0\4\21\14\0\22\21\3\0\1\21\1\0\4\21"+
    "\14\0\6\21\1\45\13\21\3\0\1\21\1\0\4\21"+
    "\14\0\13\21\1\46\6\21\3\0\1\21\1\0\4\21"+
    "\14\0\13\21\1\47\6\21\3\0\1\21\1\0\4\21"+
    "\14\0\13\21\1\50\1\21\1\51\4\21\3\0\1\21"+
    "\1\0\4\21\14\0\10\21\1\52\2\21\1\53\6\21"+
    "\3\0\1\21\1\0\4\21\14\0\10\21\1\54\11\21"+
    "\3\0\1\21\1\0\4\21\14\0\1\21\1\55\20\21"+
    "\3\0\1\21\1\0\4\21\14\0\15\21\1\56\4\21"+
    "\3\0\1\21\1\0\4\21\36\0\3\32\1\0\1\32"+
    "\20\0\13\21\1\57\6\21\3\0\1\21\1\0\4\21"+
    "\14\0\22\21\3\0\1\21\1\0\2\21\1\60\1\21"+
    "\45\0\1\61\15\0\2\21\1\62\17\21\3\0\1\21"+
    "\1\0\4\21\14\0\5\21\1\63\14\21\3\0\1\21"+
    "\1\0\4\21\14\0\12\21\1\64\7\21\3\0\1\21"+
    "\1\0\4\21\14\0\7\21\1\65\12\21\3\0\1\21"+
    "\1\0\4\21\14\0\15\21\1\66\4\21\3\0\1\21"+
    "\1\0\4\21\14\0\13\21\1\67\6\21\3\0\1\21"+
    "\1\0\4\21\14\0\1\21\1\70\20\21\3\0\1\21"+
    "\1\0\4\21\14\0\3\21\1\71\16\21\3\0\1\21"+
    "\1\0\4\21\14\0\21\21\1\72\3\0\1\21\1\0"+
    "\4\21\14\0\3\21\1\73\16\21\3\0\1\21\1\0"+
    "\4\21\14\74\22\57\1\75\1\36\1\74\1\57\1\0"+
    "\4\57\14\0\3\21\1\76\16\21\3\0\1\21\1\0"+
    "\4\21\14\0\3\21\1\77\2\21\1\100\5\21\1\101"+
    "\5\21\3\0\1\21\1\0\4\21\14\0\1\21\1\102"+
    "\20\21\3\0\1\21\1\0\4\21\14\0\16\21\1\103"+
    "\3\21\3\0\1\21\1\0\4\21\14\0\4\21\1\104"+
    "\15\21\3\0\1\21\1\0\4\21\14\0\11\21\1\105"+
    "\10\21\3\0\1\21\1\0\4\21\14\0\5\21\1\106"+
    "\14\21\3\0\1\21\1\0\4\21\14\0\4\21\1\107"+
    "\15\21\3\0\1\21\1\0\4\21\36\74\1\75\1\36"+
    "\2\74\1\0\4\74\37\0\1\36\23\0\4\21\1\110"+
    "\15\21\3\0\1\21\1\0\4\21\14\0\6\21\1\111"+
    "\13\21\3\0\1\21\1\0\4\21\14\0\13\21\1\112"+
    "\6\21\3\0\1\21\1\0\4\21\14\0\10\21\1\113"+
    "\11\21\3\0\1\21\1\0\4\21\14\0\1\21\1\114"+
    "\20\21\3\0\1\21\1\0\4\21\14\0\7\21\1\115"+
    "\12\21\3\0\1\21\1\0\4\21\14\0\15\21\1\116"+
    "\4\21\3\0\1\21\1\0\4\21\14\0\3\21\1\117"+
    "\16\21\3\0\1\21\1\0\4\21\14\0\11\21\1\120"+
    "\10\21\3\0\1\21\1\0\4\21\14\0\1\21\1\121"+
    "\20\21\3\0\1\21\1\0\4\21";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1950];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\0\10\11\1\1\3\11\16\1\2\11\1\1"+
    "\1\11\20\1\1\11\12\1\2\0\24\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[81];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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
            printSymbolTable();
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



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LexicalAnalyzer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 176) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Symbol nextToken() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              return legalEndOfFile();
            }  // fall though
            case 82: break;
            case COMMENT_STATE: {
              illegalEndOfFile();
            }  // fall though
            case 83: break;
            default:
        return null;
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { /* ignore white spaces and end of lines */
            } 
            // fall through
          case 36: break;
          case 2: 
            { unrecognizedToken();
            } 
            // fall through
          case 37: break;
          case 3: 
            { return buildSymbol(LexicalUnit.PLUS);
            } 
            // fall through
          case 38: break;
          case 4: 
            { return buildSymbol(LexicalUnit.MINUS);
            } 
            // fall through
          case 39: break;
          case 5: 
            { return buildSymbol(LexicalUnit.TIMES);
            } 
            // fall through
          case 40: break;
          case 6: 
            { return buildSymbol(LexicalUnit.DIVIDE);
            } 
            // fall through
          case 41: break;
          case 7: 
            { return buildSymbol(LexicalUnit.EQUAL);
            } 
            // fall through
          case 42: break;
          case 8: 
            { return buildSymbol(LexicalUnit.GREATER);
            } 
            // fall through
          case 43: break;
          case 9: 
            { return buildSymbol(LexicalUnit.SMALLER);
            } 
            // fall through
          case 44: break;
          case 10: 
            { return buildSymbol(LexicalUnit.SEMICOLON);
            } 
            // fall through
          case 45: break;
          case 11: 
            { return buildSymbol(LexicalUnit.LPAREN);
            } 
            // fall through
          case 46: break;
          case 12: 
            { return buildSymbol(LexicalUnit.RPAREN);
            } 
            // fall through
          case 47: break;
          case 13: 
            { onVariable(); return buildSymbol(LexicalUnit.VARNAME);
            } 
            // fall through
          case 48: break;
          case 14: 
            { return buildSymbol(LexicalUnit.NUMBER);
            } 
            // fall through
          case 49: break;
          case 15: 
            { 
            } 
            // fall through
          case 50: break;
          case 16: 
            { return buildSymbol(LexicalUnit.ASSIGN);
            } 
            // fall through
          case 51: break;
          case 17: 
            { return buildSymbol(LexicalUnit.BY);
            } 
            // fall through
          case 52: break;
          case 18: 
            { return buildSymbol(LexicalUnit.IF);
            } 
            // fall through
          case 53: break;
          case 19: 
            { return buildSymbol(LexicalUnit.DO);
            } 
            // fall through
          case 54: break;
          case 20: 
            { return buildSymbol(LexicalUnit.TO);
            } 
            // fall through
          case 55: break;
          case 21: 
            { onLongCommentStart();
            } 
            // fall through
          case 56: break;
          case 22: 
            { onLongCommentEnd();
            } 
            // fall through
          case 57: break;
          case 23: 
            { return buildSymbol(LexicalUnit.END);
            } 
            // fall through
          case 58: break;
          case 24: 
            { return buildSymbol(LexicalUnit.NOT);
            } 
            // fall through
          case 59: break;
          case 25: 
            { return buildSymbol(LexicalUnit.FOR);
            } 
            // fall through
          case 60: break;
          case 26: 
            { return buildSymbol(LexicalUnit.ELSE);
            } 
            // fall through
          case 61: break;
          case 27: 
            { return buildSymbol(LexicalUnit.FROM);
            } 
            // fall through
          case 62: break;
          case 28: 
            { return buildSymbol(LexicalUnit.THEN);
            } 
            // fall through
          case 63: break;
          case 29: 
            { return buildSymbol(LexicalUnit.READ);
            } 
            // fall through
          case 64: break;
          case 30: 
            { return buildSymbol(LexicalUnit.BEG);
            } 
            // fall through
          case 65: break;
          case 31: 
            { return buildSymbol(LexicalUnit.ENDIF);
            } 
            // fall through
          case 66: break;
          case 32: 
            { return buildSymbol(LexicalUnit.WHILE);
            } 
            // fall through
          case 67: break;
          case 33: 
            { return buildSymbol(LexicalUnit.PRINT);
            } 
            // fall through
          case 68: break;
          case 34: 
            { return buildSymbol(LexicalUnit.ENDFOR);
            } 
            // fall through
          case 69: break;
          case 35: 
            { return buildSymbol(LexicalUnit.ENDWHILE);
            } 
            // fall through
          case 70: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
