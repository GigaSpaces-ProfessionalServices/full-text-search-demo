package com.gigaspaces.search.feeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gigaspaces.search.model.Author;
import com.gigaspaces.search.model.Comment;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliizinchenko on 3/6/17.
 */
public class Copy {

    public static void main(String[] args) throws IOException {
        Integer maxCount = Integer.parseInt(args[0]);
        String pathIn = args[1];
        String pathOut = args[2];

        BufferedReader reader = new BufferedReader(new FileReader(pathIn));

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(pathOut));

        String line;
        int size = 10000;
        int count = 0;
        while ((line = reader.readLine()) != null && count < maxCount) {
            printWriter.println(line);
            count++;
            if(count % size == 0) {
                printWriter.flush();
                System.out.println("wrote: " + count/1000 + " t");
            }
        }

        reader.close();
        printWriter.flush();
        printWriter.close();

        System.out.println("Finished!");
    }

}
