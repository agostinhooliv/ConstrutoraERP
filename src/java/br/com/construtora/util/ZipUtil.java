/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author agostinho
 */
public class ZipUtil {

    FileInputStream fis = null;
    ZipInputStream zis = null;

    public List<ZipEntry> getElementos(String arquivo) throws IOException {

        fis = new FileInputStream(arquivo);
        zis = new ZipInputStream(fis);
        List<ZipEntry> arquivos = new ArrayList<ZipEntry>();
        ZipEntry entrada = null;

        while ((entrada = zis.getNextEntry()) != null) {
            arquivos.add(entrada);
        }
        zis.close();
        fis.close();
        return arquivos;
    }

    public void compactaArquivos(String caminhoArquivo, List<String> listaArquivos, String compac) throws FileNotFoundException, IOException {

        // Cria um buffer para ler os dados do arquivo
        byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(compac));


        for (String arquivos : listaArquivos) {

            // Comprime o arquivo
            FileInputStream in = new FileInputStream(caminhoArquivo + arquivos);

            // Adiciona o arquivo ao fluxo de saída
            out.putNextEntry(new ZipEntry(caminhoArquivo + arquivos));

            // transfere dados do arquivo para o arquivo zip
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            // Finaliza a entrada
            out.closeEntry();
            in.close();
        }

        // Completa o arquivo zip
        out.close();

    }
}
