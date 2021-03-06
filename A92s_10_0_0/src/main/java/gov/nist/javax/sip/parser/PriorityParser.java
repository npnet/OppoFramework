package gov.nist.javax.sip.parser;

import gov.nist.javax.sip.header.Priority;
import gov.nist.javax.sip.header.SIPHeader;
import java.io.PrintStream;
import java.text.ParseException;

public class PriorityParser extends HeaderParser {
    public PriorityParser(String priority) {
        super(priority);
    }

    protected PriorityParser(Lexer lexer) {
        super(lexer);
    }

    @Override // gov.nist.javax.sip.parser.HeaderParser
    public SIPHeader parse() throws ParseException {
        if (debug) {
            dbg_enter("PriorityParser.parse");
        }
        Priority priority = new Priority();
        try {
            headerName(TokenTypes.PRIORITY);
            priority.setHeaderName("Priority");
            this.lexer.SPorHT();
            priority.setPriority(this.lexer.ttokenSafe());
            this.lexer.SPorHT();
            this.lexer.match(10);
            return priority;
        } finally {
            if (debug) {
                dbg_leave("PriorityParser.parse");
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        String[] p = {"Priority: 8;a\n"};
        for (int i = 0; i < p.length; i++) {
            PrintStream printStream = System.out;
            printStream.println("encoded = " + ((Priority) new PriorityParser(p[i]).parse()).encode());
        }
    }
}
