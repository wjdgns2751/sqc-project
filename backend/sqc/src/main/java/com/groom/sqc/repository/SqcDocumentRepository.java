package com.groom.sqc.repository;

import com.groom.sqc.domain.documents.SqcDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SqcDocumentRepository extends MongoRepository<SqcDocument, Long> {

}
