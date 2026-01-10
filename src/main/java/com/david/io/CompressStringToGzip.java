package com.david.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

public class CompressStringToGzip {

    static void main(String[] args) throws IOException {

        String data = "Texto de ejemplo para comprimir en GZIP";

        byte[] compressed = compress(data);

        System.out.println("Compressed size: " + compressed.length);
    }

    private static byte[] compress(String data) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (GZIPOutputStream gzip =
                     new GZIPOutputStream(outputStream, Deflater.BEST_COMPRESSION);
             Writer writer = new OutputStreamWriter(gzip)) {

            writer.write(data);
        }

        return outputStream.toByteArray();
    }

}
