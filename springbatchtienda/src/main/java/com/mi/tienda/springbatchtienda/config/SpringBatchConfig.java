package com.mi.tienda.springbatchtienda.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.mi.tienda.springbatchtienda.entity.Producto;
import com.mi.tienda.springbatchtienda.repository.ProductoRepository;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {


	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private ProductoRepository produtRepository;

	@Bean
	public FlatFileItemReader<Producto> reader() { //fuente de datos
		FlatFileItemReader<Producto> itemReader = new FlatFileItemReader<>();

		itemReader.setResource(new FileSystemResource("src/main/resources/db_producto.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Producto> lineMapper() {
		DefaultLineMapper<Producto> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineToken = new DelimitedLineTokenizer();
		lineToken.setDelimiter(",");
		lineToken.setStrict(false);
		lineToken.setNames("id", "nombre", "descripcion", "precio", "stock");

		BeanWrapperFieldSetMapper<Producto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Producto.class);

		lineMapper.setLineTokenizer(lineToken);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public ProductoProcessor processor() {
		return new ProductoProcessor();
	}

	@Bean
	public RepositoryItemWriter<Producto> write() {
		RepositoryItemWriter<Producto> write = new RepositoryItemWriter<>();
		write.setRepository(produtRepository);
		write.setMethodName("save");
		return write;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("csv-step").<Producto, Producto>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(write()).taskExecutor(taskExecutor()).build();

	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("importProductos").flow(step1()).end().build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}

}
