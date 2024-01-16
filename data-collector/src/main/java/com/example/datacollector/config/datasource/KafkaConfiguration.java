package com.example.datacollector.config.datasource;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@Configuration
public class KafkaConfiguration {
    private Environment env;
    public KafkaConfiguration(@Autowired Environment env) {
        this.env = env;
    }

    public KafkaConsumer<GenericRecord, GenericRecord> getKafkaConsumer(String topicName) {
        Properties propsCons = getPropsCons();
        KafkaConsumer<GenericRecord, GenericRecord> kafkaConsumer = new KafkaConsumer<>(propsCons);
        kafkaConsumer.subscribe(Arrays.asList(topicName));
        return kafkaConsumer;
    }

    private Properties getPropsCons() {
        Properties propsCons = new Properties();
        propsCons.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.datasource.kafka.kafkaConsumer" +
                ".bootstrapAddress"));
        propsCons.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.datasource.kafka.kafkaConsumer" +
                ".groupId"));
        propsCons.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, env.getProperty("spring.datasource.kafka.kafkaConsumer" +
                ".autoOffsetReset"));
        propsCons.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsCons.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        propsCons.put("schema.registry.url", env.getProperty("spring.datasource.kafka.kafkaConsumer" +
                ".schemaRegistry"));

        propsCons.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, env.getProperty("spring.datasource.kafka" +
                ".securityProtocol"));
        propsCons.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, env.getProperty("spring.datasource.kafka" +
                ".sslKeystoreType"));
        propsCons.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, env.getProperty("spring.datasource.kafka" +
                ".sslTruststoreType"));


        String certChain = getCertificateStringFromFile(env.getProperty("spring.datasource.kafka" +
                ".pathToCertChain"));
        propsCons.put(SslConfigs.SSL_KEYSTORE_CERTIFICATE_CHAIN_CONFIG, certChain);

        String keyStoreKey = getCertificateStringFromFile(env.getProperty("spring.datasource.kafka" +
                ".pathToKeyStoreKey"));
        propsCons.put(SslConfigs.SSL_KEYSTORE_KEY_CONFIG, keyStoreKey);

        String trustStoreCertificates = getCertificateStringFromFile(env.getProperty("spring.datasource.kafka" +
                ".pathToTrustStoreCertificates"));
        propsCons.put(SslConfigs.SSL_TRUSTSTORE_CERTIFICATES_CONFIG, trustStoreCertificates);

        return propsCons;
    }

    private String getCertificateStringFromFile(String pathToFile){
        String result = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append("\n");
                sb.append(line);
                line = reader.readLine();
            }
            reader.close();
            result = sb.toString();
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
