package br.com.pferreira;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MemeServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(MemeServiceApplication.class);
    }
}
