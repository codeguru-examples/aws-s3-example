package com.mycompany.app;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.*;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();

        //Creating S3 bucket
        String bucketName = "java-bucket-" + UUID.randomUUID();
        s3.createBucket(bucketName);

        //Upload file into S3
        s3.putObject(bucketName, "myObject", createFile());
        System.out.println("file uploaded successfully");
    }

    /* Create sample file */
    private static File createFile() throws IOException {
        File file = File.createTempFile("sample-file", ".txt");
        //deletes file on your local machine once java program execution is complete
        file.deleteOnExit();


        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("This is sample text");
        writer.close();

        return file;
    }
}
