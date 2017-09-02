package com.lomoye.nettylearn.serialize;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomoye on 2017/8/30.
 *
 */
public class Test {

    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();

        src.add("msgpack");
        src.add("abcdefg");
        src.add("lomoye");

        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(src);

        List<String> dst = messagePack.read(raw, Templates.tList(Templates.TString));

        for (String d : dst) {
            System.out.println(d);
        }
    }

}
