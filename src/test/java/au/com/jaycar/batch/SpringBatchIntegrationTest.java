package au.com.jaycar.batch;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@SpringBootTest
public class SpringBatchIntegrationTest {

    // other test constants

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    public void cleanUp() {
	jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void testTheJob() throws Exception {

	
	JobParametersBuilder builder = new JobParametersBuilder();
	builder.addDate("date", new Date());
//	jobLauncher.run(job, builder.toJobParameters());
	JobExecution jobExecution = jobLauncherTestUtils.launchJob(builder.toJobParameters());

	Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

    }
}