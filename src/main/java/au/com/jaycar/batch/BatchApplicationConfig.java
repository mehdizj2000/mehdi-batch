package au.com.jaycar.batch;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import au.com.jaycar.batch.domain.AddressInfo;

@Configuration
@EnableBatchProcessing
public class BatchApplicationConfig {

    @Autowired
    private JobBuilderFactory jobBuilder;

    @Autowired
    private StepBuilderFactory stepBuilder;

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public Step stepOne() {
	// @formatter:off
 	return stepBuilder
 		.get("stepOne")
 		.<AddressInfo, AddressInfo> chunk(100)
 		.reader(addressReader())
 		.writer(addressWriter1())
 	.build();
	// @formatter:on
    }

    @Bean
    public ItemWriter<AddressInfo> addressWriter1() {
	// @formatter:off
 	return new JsonFileItemWriterBuilder<AddressInfo>()
 		.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
		.resource(new FileSystemResource("target/test-outputs/address-info.json"))
		.name("addressJsonFileItemWriter")
	.build();
	// @formatter:on
    }

    @Bean
    public ItemWriter<AddressInfo> addressWriter() {
	return new FlatFileItemWriterBuilder<AddressInfo>().name("itemWriter")
		.resource(new FileSystemResource("target/test-outputs/output.txt"))
		.lineAggregator(new PassThroughLineAggregator<>()).build();
    }

    @Bean
    public ItemReader<AddressInfo> addressReader() {

	// @formatter:off
 	return new JpaPagingItemReaderBuilder<AddressInfo>()
 			.name("addReader")
 			.entityManagerFactory(emf)
 			.maxItemCount(2000)
 			.queryString("select f from AddressInfo f")
 			.pageSize(100)
 		.build();
	// @formatter:on	
    }

    @Bean
    public Job demoJob() {
	// @formatter:off
 	return jobBuilder.get("demoJowdf3")
		.incrementer(new RunIdIncrementer())
		.start(stepOne())
	.build();
	// @formatter:on
    }

}
