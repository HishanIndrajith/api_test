package it.codegen.crawler.services;

import java.util.List;

import it.codegen.crawler.models.CrawlingCriteria;
import it.codegen.crawler.repositories.CrawlingCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlingCriteriaService
{
	@Autowired
	private CrawlingCriteriaRepository crawlingCriteriaRepository;

	public boolean insertCrawlingCriteriaList( List<CrawlingCriteria> crawlingCriterias )
	{
		boolean isSaveSuccessful = true;
		try
		{
			crawlingCriteriaRepository.saveAll( crawlingCriterias );
		}
		catch ( Exception e )
		{
			isSaveSuccessful = false;
		}
		return isSaveSuccessful;
	}
}
