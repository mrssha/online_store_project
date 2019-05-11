package dbservice.service;

import dbservice.dto.ProductDto;
import dbservice.result.LogMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandServiceImpl implements StandService {

    @Autowired
    ProductService productService;

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final Logger logger = Logger.getLogger(StandServiceImpl.class);

    public void updateStandIfTopChanged(){
        List<ProductDto> lastTopList = productService.getLastTopProductsList();
        List<ProductDto> newTopList= productService.getTopProducts();
        try {
            if (!lastTopList.containsAll(newTopList)) {
                jmsTemplate.send(s -> s.createTextMessage("update list"));
                logger.info(LogMessage.JMS_SUCCESS);
            }
        }
        catch (UncategorizedJmsException e){
            logger.warn(LogMessage.JMS_FAILED);
        }
    }
}
