package ru.ysolutions.converter.helper;
/*
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import java.io.IOException;
import java.io.Writer;

public class CustomCharacterEscapeHandler implements CharacterEscapeHandler {

    public CustomCharacterEscapeHandler() {
        super();
    }

    public void escape(char[] ch, int start, int length, boolean isAttVal, Writer out) throws IOException {
        // avoid calling the Writerwrite method too much by assuming
        // that the escaping occurs rarely.
        // profiling revealed that this is faster than the naive code.
        int limit = start + length;
        for (int i = start; i < limit; i++) {
            char c = ch[i];
            if (c == '&' || c == '<' || c == '>' || c == '\'' || c == '\"') {
                if (i != start)
                    out.write(ch, start, i - start);
                start = i + 1;
                switch (ch[i]) {
                    case '&':
                        out.write("&amp;");
                        break;
                    case '<':
                        out.write("&lt;");
                        break;
                    case '>':
                        out.write("&gt;");
                        break;
                    case '\"':
                        out.write("&quot;");
                        break;
                    case '\'':
                        out.write("&apos;");
                        break;
                }
            }
        }

        if (start != limit)
            out.write(ch, start, limit - start);
    }
}
*/