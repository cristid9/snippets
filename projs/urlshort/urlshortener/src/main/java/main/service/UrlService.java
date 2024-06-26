package main.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.UrlEntity;
import main.repository.UrlRepository;

@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class UrlService {

    private UrlRepository urlRepository;

    public UrlEntity getLongUrlByShort(String shortened) {
        return urlRepository.findOptionalByShortenedUrl(shortened).orElse(null);
    }

    @Transactional
    public UrlEntity createUrl(String originalUrl) {
        var urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl(originalUrl);
        urlEntity.setShortenedUrl(String.valueOf(originalUrl.hashCode()));
        return urlRepository.save(urlEntity);
    }

}
