/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.transvis.csvfile.lexer;

/**
 *
 * @author dtripathy10
 */
import edu.iitb.tse.transvis.csvfile.jcclexer.JavaCharStream;
import edu.iitb.tse.transvis.csvfile.jcclexer.JavaParserTokenManager;
import edu.iitb.tse.transvis.csvfile.jcclexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

class SJLexer implements Lexer<SJTokenId> {

    private LexerRestartInfo<SJTokenId> info;
    private JavaParserTokenManager javaParserTokenManager;

    SJLexer(LexerRestartInfo<SJTokenId> info) {
        this.info = info;
        JavaCharStream stream = new JavaCharStream(info.input());
        javaParserTokenManager = new JavaParserTokenManager(stream);
    }

    @Override
    public org.netbeans.api.lexer.Token<SJTokenId> nextToken() {
        Token token = javaParserTokenManager.getNextToken();
        if (info.input().readLength() < 1) {
            return null;
        }
        return info.tokenFactory().createToken(SJLanguageHierarchy.getToken(token.kind));
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }

}
