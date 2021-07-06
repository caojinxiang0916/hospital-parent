package cn.caojinxiang.hospital.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.caojinxiang.hospital.hosp.mapper")
public class HospitalSetApplication {
    public static void main(String[] args) {
    SpringApplication.run(HospitalSetApplication.class, args);
    }
}
