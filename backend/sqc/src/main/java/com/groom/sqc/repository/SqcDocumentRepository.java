package com.groom.sqc.repository;

import com.groom.sqc.domain.documents.SqcDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SqcDocumentRepository extends MongoRepository<SqcDocument, String> {
    // 범위에 해당하는 ID를 조회하는 커스텀 쿼리 메서드
    @Query("{ '_id': { $gte: ?0, $lte: ?1 } }")
    List<SqcDocument> findByIdRange(ObjectId startId, ObjectId endId);
}
