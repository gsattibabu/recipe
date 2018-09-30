package com.myapp.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the entry point for Recipe Management Application
 * @author Madhusudan
 *
 */
@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@ComponentScan(basePackages="com.myapp") 
@EntityScan("com.myapp.entity")
@EnableJpaRepositories("com.myapp.dao")
public class ApplicationConfig {

	/**
	 * @param dataSource
	 * @throws DataSetException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void setUp(DataSource dataSource) throws DataSetException, DatabaseUnitException, SQLException, IOException{
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
				dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}
	
	/**
	 * @return
	 * @throws DataSetException
	 * @throws IOException
	 */
	protected static IDataSet getDataSet() throws DataSetException, IOException{
		return new FlatXmlDataSet(ApplicationConfig.class.getResourceAsStream("/RecipeData.xml"));
	}
	
	/**
	 * @param args list of input argument for starting of the application
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class, args);
		
		try {
			setUp(context.getBean("dataSource",DataSource.class));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
