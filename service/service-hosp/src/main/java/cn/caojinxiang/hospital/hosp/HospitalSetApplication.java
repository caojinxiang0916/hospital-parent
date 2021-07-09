package cn.caojinxiang.hospital.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("cn.caojinxiang.hospital.hosp.mapper")
@ComponentScan("cn.caojinxiang")
public class HospitalSetApplication {
    public static void main(String[] args) {
    SpringApplication.run(HospitalSetApplication.class, args);
    }
}
