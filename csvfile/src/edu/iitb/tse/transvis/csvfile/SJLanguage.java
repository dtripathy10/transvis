/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.transvis.csvfile;

import edu.iitb.tse.transvis.csvfile.lexer.SJTokenId;
import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;

@LanguageRegistration(mimeType = "text/x-mycsv")
public class SJLanguage extends DefaultLanguageConfig {

    @Override
    public Language getLexerLanguage() {
        return SJTokenId.getLanguage();
    }

    @Override
    public String getDisplayName() {
        return "CSV";
    }

}
